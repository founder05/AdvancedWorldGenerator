package com.marc_val_96.advancedworldgenerator.configuration;

import com.marc_val_96.advancedworldgenerator.LocalBiome;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfigFinder.BiomeConfigStub;
import com.marc_val_96.advancedworldgenerator.configuration.io.FileSettingsReader;
import com.marc_val_96.advancedworldgenerator.configuration.io.FileSettingsWriter;
import com.marc_val_96.advancedworldgenerator.configuration.io.SettingsMap;
import com.marc_val_96.advancedworldgenerator.configuration.standard.BiomeStandardValues;
import com.marc_val_96.advancedworldgenerator.configuration.standard.PluginStandardValues;
import com.marc_val_96.advancedworldgenerator.configuration.standard.WorldStandardValues;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import com.marc_val_96.advancedworldgenerator.util.helpers.FileHelper;


import java.io.File;
import java.util.*;

/**
 * Holds the WorldConfig and all BiomeConfigs.
 *
 * <h3>A note about {@link LocalWorld} usage</h3>
 * <p>Currently, a {@link LocalWorld} instance is passed to the constructor of
 * this class. That is bad design. The plugin should be able to read the
 * settings and then create a world based on that. Now the world is created, and
 * then the settings are injected. It is also strange that the configuration
 * code is now able to spawn a cow, to give one example.</p>
 *
 * <p>Fixing that will be a lot of work - {@link LocalWorld} is currently a God
 * class that is required everywhere. If a rewrite of that class is ever
 * planned, be sure to split that class up!</p>
 */
public final class ServerConfigProvider implements ConfigProvider
{
    private static final int MAX_INHERITANCE_DEPTH = 15;
    private final LocalWorld world;
    private final File settingsDir;
    private WorldConfig worldConfig;

    /**
     * Holds all biome configs. Generation Id => BiomeConfig
     * <p>
     * Must be simple array for fast access. Warning: some ids may contain
     * null values, always check.
     */
    private LocalBiome[] biomesByAWGId;

    /**
     * The number of loaded biomes.
     */
    private int biomesCount;

    /**
     * Loads the settings from the given directory for the given world.
     * @param settingsDir The directory to load from.
     * @param world       The world to load the settings for.
     */
    public ServerConfigProvider(File settingsDir, LocalWorld world, File worldSaveFolder)
    {
        this.settingsDir = settingsDir;
        this.world = world;
        this.biomesByAWGId = new LocalBiome[PluginStandardValues.MAX_BIOMES_PER_WORLD];

        loadSettings(worldSaveFolder, false);
    }

    @Override
    public WorldConfig getWorldConfig()
    {
        return worldConfig;
    }

    @Override
    public LocalBiome getBiomeByOTGIdOrNull(int id)
    {
        if (id < 0 || id > biomesByAWGId.length)
        {
            return null;
        }
        return biomesByAWGId[id];
    }

    @Override
    public LocalBiome[] getBiomeArrayByOTGId()
    {
        return this.biomesByAWGId;
    }

    /**
     * Loads all settings. Expects the biomes array to be empty (filled with
     * nulls), the savedBiomes collection to be empty and the biomesCount
     * field to be zero.
     */
    private void loadSettings(File worldSaveFolder, boolean isReload)
    {
        SettingsMap worldConfigSettings = loadAndInitWorldConfig();
        loadFallbacks();
        loadBiomes(worldConfigSettings, worldSaveFolder, isReload);

        // We have to wait for the loading in order to get things like
        // temperature
        worldConfig.biomeGroupManager.processBiomeData(world);
    }

    private void loadFallbacks()
    {
        File fallbackFile = new File(settingsDir, WorldStandardValues.FALLBACK_FILE_NAME);
        SettingsMap settingsMap = FileSettingsReader.read(world.getName(), fallbackFile);

        FallbackConfig fallbacks = new FallbackConfig(settingsMap);

        this.worldConfig.addWorldFallbacks(fallbacks);
        FileSettingsWriter.writeToFile(fallbacks.getSettingsAsMap(), fallbackFile, OTG.getPluginConfig().settingsMode);
    }

    private SettingsMap loadAndInitWorldConfig()
    {
        File worldConfigFile = new File(settingsDir, WorldStandardValues.WORLD_CONFIG_FILE_NAME);
        SettingsMap settingsMap = FileSettingsReader.read(world.getName(), worldConfigFile);

        ArrayList<String> biomes = new ArrayList<String>();
        File biomesDirectory = new File(settingsDir, WorldStandardValues.WORLD_BIOMES_DIRECTORY_NAME);

        addBiomesFromDirRecursive(biomes, biomesDirectory);

        this.worldConfig = new WorldConfig(settingsDir, settingsMap, world, biomes);
        FileSettingsWriter.writeToFile(worldConfig.getSettingsAsMap(), worldConfigFile, worldConfig.settingsMode);

        return settingsMap;
    }

    private void addBiomesFromDirRecursive(ArrayList<String> biomes, File biomesDirectory)
    {
        if(biomesDirectory.exists())
        {
            for(File biomeConfig : biomesDirectory.listFiles())
            {
                if(biomeConfig.isFile() && biomeConfig.getName().endsWith(BiomeStandardValues.BIOME_CONFIG_EXTENSION.getDefaultValue()))
                {
                    biomes.add(biomeConfig.getName().replace(BiomeStandardValues.BIOME_CONFIG_EXTENSION.getDefaultValue(), ""));
                }
                else if(biomeConfig.isDirectory())
                {
                    addBiomesFromDirRecursive(biomes, biomeConfig);
                }
            }
        }
    }

    private void loadBiomes(SettingsMap worldConfigSettings, File worldSaveFolder, boolean isReload)
    {
        // Establish folders
        List<File> biomeDirs = new ArrayList<File>(2);
        // OpenTerrainGenerator/Presets/<WorldName>/<WorldBiomes/
        biomeDirs.add(new File(settingsDir, OTG.correctOldBiomeConfigFolder(settingsDir)));
        // OpenTerrainGenerator/GlobalBiomes/
        biomeDirs.add(new File(OTG.getEngine().getOTGRootFolder(), PluginStandardValues.BiomeConfigDirectoryName));

        FileHelper.makeFolders(biomeDirs);

        // Build a set of all biomes to load
        Collection<BiomeLoadInstruction> biomesToLoad = new HashSet<BiomeLoadInstruction>();

        // If we're creating a new world with new configs then add the default biomes
        if(worldConfigSettings.isNewConfig())
        {
            Collection<? extends BiomeLoadInstruction> defaultBiomes = OTG.getEngine().getDefaultBiomes();
            for (BiomeLoadInstruction defaultBiome : defaultBiomes)
            {
                this.worldConfig.worldBiomes.add(defaultBiome.getBiomeName());
                biomesToLoad.add(new BiomeLoadInstruction(defaultBiome.getBiomeName(), defaultBiome.getBiomeTemplate()));
            }
        }

        // Load all files
        BiomeConfigFinder biomeConfigFinder = new BiomeConfigFinder(OTG.getPluginConfig().biomeConfigExtension);
        Map<String, BiomeConfigStub> biomeConfigStubs = biomeConfigFinder.findBiomes(this.worldConfig, this.worldConfig.worldHeightScale, biomeDirs, biomesToLoad);

        // Read all settings
        Map<String, BiomeConfig> loadedBiomes = readAndWriteSettings(this.worldConfig, biomeConfigStubs, true);

        // Index all necessary settings
        String loadedBiomeNames = indexSettings(worldConfigSettings.isNewConfig(), loadedBiomes, worldSaveFolder, isReload);

        OTG.log(LogMarker.DEBUG, "{} biomes Loaded", biomesCount);
        OTG.log(LogMarker.DEBUG, "{}", loadedBiomeNames);
    }

    @Override
    public void reload()
    {
        // Clear biome collections
        Arrays.fill(this.biomesByOTGId, null);
        this.biomesCount = 0;

        // Load again
        loadSettings(this.world.getWorldSaveDir(), true);
    }

    public static Map<String, BiomeConfig> readAndWriteSettings(WorldConfig worldConfig, Map<String, BiomeConfigStub> biomeConfigStubs, boolean write)
    {
        Map<String, BiomeConfig> loadedBiomes = new HashMap<String, BiomeConfig>();

        for (BiomeConfigStub biomeConfigStub : biomeConfigStubs.values())
        {
            // Allow to let world settings influence biome settings
            //biomeConfigStub.getSettings().setFallback(worldConfigSettings); // TODO: Make sure this can be removed safely

            // Inheritance
            processInheritance(biomeConfigStubs, biomeConfigStub, 0);
            processMobInheritance(biomeConfigStubs, biomeConfigStub, 0);

            // Settings reading
            BiomeConfig biomeConfig = new BiomeConfig(biomeConfigStub.getLoadInstructions(), biomeConfigStub, biomeConfigStub.getSettings(), worldConfig);
            loadedBiomes.put(biomeConfigStub.getBiomeName(), biomeConfig);

            // Settings writing
            if(write)
            {
                File writeFile = biomeConfigStub.getFile();
                if (!biomeConfig.biomeExtends.isEmpty())
                {
                    writeFile = new File(writeFile.getAbsolutePath() + ".inherited");
                }
                FileSettingsWriter.writeToFile(biomeConfig.getSettingsAsMap(), writeFile, worldConfig.settingsMode);
            }
        }

        return loadedBiomes;
    }

    private static void processInheritance(Map<String, BiomeConfigStub> biomeConfigStubs, BiomeConfigStub biomeConfigStub, int currentDepth)
    {
        if (biomeConfigStub.biomeExtendsProcessed)
        {
            // Already processed
            return;
        }

        String extendedBiomeName = biomeConfigStub.getSettings().getSetting(BiomeStandardValues.BIOME_EXTENDS);
        if (extendedBiomeName.isEmpty())
        {
            // Not extending anything
            biomeConfigStub.biomeExtendsProcessed = true;
            return;
        }

        // This biome extends another biome
        BiomeConfigStub extendedBiomeConfig = biomeConfigStubs.get(extendedBiomeName);
        if (extendedBiomeConfig == null)
        {
            OTG.log(LogMarker.WARN, "The biome {} tried to extend the biome {}, but that biome doesn't exist.", biomeConfigStub.getBiomeName(), extendedBiomeName);
            return;
        }

        // Check for too much recursion
        if (currentDepth > MAX_INHERITANCE_DEPTH)
        {
            OTG.log(LogMarker.FATAL,
                "The biome {} cannot extend the biome {} - too much configs processed already! Cyclical inheritance?",
                biomeConfigStub.getBiomeName(), extendedBiomeConfig.getBiomeName());
        }

        if (!extendedBiomeConfig.biomeExtendsProcessed)
        {
            // This biome has not been processed yet, do that first
            processInheritance(biomeConfigStubs, extendedBiomeConfig, currentDepth + 1);
        }

        // Merge the two
        biomeConfigStub.getSettings().setFallback(extendedBiomeConfig.getSettings());

        // Done
        biomeConfigStub.biomeExtendsProcessed = true;
    }

    private static void processMobInheritance(Map<String, BiomeConfigStub> biomeConfigStubs, BiomeConfigStub biomeConfigStub, int currentDepth)
    {
        if (biomeConfigStub.inheritMobsBiomeNameProcessed)
        {
            // Already processed
            return;
        }

        String stubInheritMobsBiomeName = biomeConfigStub.getSettings().getSetting(BiomeStandardValues.INHERIT_MOBS_BIOME_NAME, biomeConfigStub.getLoadInstructions().getBiomeTemplate().defaultInheritMobsBiomeName);

        if(stubInheritMobsBiomeName != null && stubInheritMobsBiomeName.length() > 0)
        {
            String[] inheritMobsBiomeNames = stubInheritMobsBiomeName.split(",");
            for(String inheritMobsBiomeName : inheritMobsBiomeNames)
            {
                if (inheritMobsBiomeName.isEmpty())
                {
                    // Not extending anything
                    continue;
                }

                // This biome inherits mobs from another biome
                BiomeConfigStub inheritMobsBiomeConfig = biomeConfigStubs.get(inheritMobsBiomeName);

                if (inheritMobsBiomeConfig == null || inheritMobsBiomeConfig == biomeConfigStub) // Most likely a legacy config that is not using resourcelocation yet, for instance: Plains instead of minecraft:plains. Try to convert.
                {
                    String vanillaBiomeName = BiomeRegistryNames.getRegistryNameForDefaultBiome(inheritMobsBiomeName);
                    if(vanillaBiomeName != null)
                    {
                        inheritMobsBiomeConfig = null;
                        inheritMobsBiomeName = vanillaBiomeName;
                    }
                    else if(inheritMobsBiomeConfig == biomeConfigStub)
                    {
                        OTG.log(LogMarker.WARN, "The biome {} tried to inherit mobs from itself.", new Object[] { biomeConfigStub.getBiomeName()});
                        continue;
                    }
                }

                // Check for too much recursion
                if (currentDepth > MAX_INHERITANCE_DEPTH)
                {
                    OTG.log(LogMarker.FATAL, "The biome {} cannot inherit mobs from biome {} - too much configs processed already! Cyclical inheritance?", new Object[] { biomeConfigStub.getFile().getName(), inheritMobsBiomeConfig.getFile().getName()});
                }

                if(inheritMobsBiomeConfig != null)
                {
                    if (!inheritMobsBiomeConfig.inheritMobsBiomeNameProcessed)
                    {
                        // This biome has not been processed yet, do that first
                        processMobInheritance(biomeConfigStubs, inheritMobsBiomeConfig, currentDepth + 1);
                    }

                    // Merge the two
                    biomeConfigStub.mergeMobs(inheritMobsBiomeConfig);
                } else {

                    // This is a vanilla biome or a biome added by another mod.
                    OTG.getEngine().mergeVanillaBiomeMobSpawnSettings(biomeConfigStub, inheritMobsBiomeName);
                    continue;
                }
            }

            // Done
            biomeConfigStub.inheritMobsBiomeNameProcessed = true;
        }
    }

    // TODO: This sorts, updates and registers biomes, saves biome id data and creates OTG biomes.
    // This is used when creating new worlds and dims, and when loading them.
    // This has to work for modern (>v7) and legacy (<v7) worlds, that use different
    // rules for OTG biome ids and saved id's.
    // This method does too much and handles too many different situations, split it up!
    // TODO: Drop support for legacy worlds for 1.13/1.14 and clean this up.
    private String indexSettings(boolean isNewWorldConfig, Map<String, BiomeConfig> loadedBiomes, File worldSaveFolder, boolean isReload)
    {
        StringBuilder loadedBiomeNames = new StringBuilder();

        ArrayList<BiomeConfig> nonVirtualBiomesExisting = new ArrayList<BiomeConfig>();
        ArrayList<BiomeConfig> nonVirtualBiomes = new ArrayList<BiomeConfig>();

        // If this is a previously created world then load the biome id data and register biomes to the same OTG biome id as before.
        ArrayList<BiomeIdData> loadedBiomeIdData = BiomeIdData.loadBiomeIdData(worldSaveFolder);
        boolean hasWorldData = loadedBiomeIdData != null;
        if(hasWorldData)
        {
            boolean bFound = false;
            // When creating a world with dims, the worlddata is saved after the overworld is generated,
            // so when the dims are created, worlddata already exists, even though the dim's biomes haven't
            // been registered yet.
            for(BiomeIdData biomeIdData : loadedBiomeIdData)
            {
                if(biomeIdData.biomeName.startsWith(world.getName() + "_"))
                {
                    bFound = true;
                    break;
                }
            }
            hasWorldData = bFound;
            if(!hasWorldData)
            {
                loadedBiomeIdData = null;
            }
        }

        List<BiomeConfig> loadedBiomeList = new ArrayList<BiomeConfig>(loadedBiomes.values());

        // Get OTG world save version
        WorldSaveData worldSaveData = WorldSaveData.loadWorldSaveData(worldSaveFolder);

        // This is a legacy (pre-v7) world if its not being created and either has no worldsavedata or worldsavedata version 6.
        // If this world has biome data but not worldsavedata, it's v7.
        // This ignores legacy worlds updated by v7 and earlier v8 versions unfortunately (though very few ppl should be affected).
        //boolean isLegacyWorld = !OTG.IsNewWorldBeingCreated && (!hasWorldData || (worldSaveData != null && worldSaveData.version == 6));
        boolean isLegacyWorld = (!hasWorldData || (worldSaveData != null && worldSaveData.version == 6));
        if(worldSaveData == null)
        {
            worldSaveData = new WorldSaveData(isLegacyWorld ? 6 : 8);
            WorldSaveData.saveWorldSaveData(worldSaveFolder, worldSaveData);
        }

        List<BiomeConfig> usedBiomes = loadedBiomeList;
        if(loadedBiomeIdData != null)
        {
            // For existing worlds, only register biomes that were previously registered/used, don't include new ones.
            usedBiomes = new ArrayList<BiomeConfig>();
            for(BiomeIdData biomeIdData : loadedBiomeIdData)
            {
                if(biomeIdData.biomeName.startsWith(world.getName() + "_"))
                {
                    for(BiomeConfig biomeConfig : loadedBiomeList)
                    {
                        if((world.getName() + "_" + biomeConfig.getName()).equals(biomeIdData.biomeName))
                        {
                            if(!usedBiomes.contains(biomeConfig))
                            {
                                usedBiomes.add(biomeConfig);
                            }
                            if(OTG.getEngine().isOTGBiomeIdAvailable(world.getName(), biomeIdData.otgBiomeId))
                            {
                                OTG.getEngine().setOTGBiomeId(world.getName(), biomeIdData.otgBiomeId, biomeConfig, false);
                            }
                            else if((world.getName() + "_" + OTG.getEngine().getOTGBiomeIds(world.getName())[biomeIdData.otgBiomeId].getName()).equals(biomeIdData.biomeName))
                            {
                                OTG.getEngine().setOTGBiomeId(world.getName(), biomeIdData.otgBiomeId, biomeConfig, true);
                            } else {
                                // The only time a biomeId can be claimed already is when an
                                // unloaded world is being reloaded.
                                throw new RuntimeException("Error: OTG Biome id " + biomeIdData.otgBiomeId + " for biome " + biomeConfig.getName() + " was taken by " + OTG.getBiomeByOTGId(biomeIdData.otgBiomeId).getName());
                            }

                            if(biomeIdData.otgBiomeId > -1)
                            {
                                nonVirtualBiomesExisting.add(biomeConfig);
                            } else {
                                // TODO: Remove after testing
                                throw new RuntimeException("This shouldnt happen.");
                            }
                            break;
                        }
                    }
                }
            }
        }

        // Set OTG biome id's for biomes, make sure there is enough space to register all biomes.
        for (BiomeConfig biomeConfig : usedBiomes)
        {
            // Statistics of the loaded biomes
            this.biomesCount++;
            loadedBiomeNames.append(biomeConfig.getName());
            loadedBiomeNames.append(", ");

            BiomeConfig[] otgIds2 = OTG.getEngine().getOTGBiomeIds(world.getName());

            int otgBiomeId = -1;

            // Exclude already registered biomes from loadedBiomeIdData / default biomes
            boolean bFound = false;
            for(int i = 0; i < otgIds2.length; i++)
            {
                BiomeConfig biomeConfig2 = otgIds2[i];
                if(biomeConfig == biomeConfig2)
                {
                    bFound = true;
                    break;
                }
                // Forge dimensions: If a world is being reloaded after being unloaded replace the existing biomeConfig
                else if(
                    biomeConfig2 != null &&
                        biomeConfig.getName().equals(biomeConfig2.getName()) &&
                        biomeConfig.worldConfig.getName().equals(biomeConfig2.worldConfig.getName())
                )
                {
                    OTG.getEngine().setOTGBiomeId(world.getName(), i, biomeConfig2, true);
                    otgBiomeId = i;
                    break;
                }
            }
            if(bFound)
            {
                continue; // biome is from loadedBiomeIdData, already registered.
            }

            if(otgBiomeId == -1)
            {
                // Find the next available id
                for(int i = 0; i < otgIds2.length; i++)
                {
                    if(OTG.getEngine().isOTGBiomeIdAvailable(world.getName(), i))
                    {
                        otgBiomeId = i;
                        OTG.getEngine().setOTGBiomeId(world.getName(), i, biomeConfig, false);
                        break;
                    }
                }
                if(otgBiomeId > -1)
                {
                    nonVirtualBiomes.add(biomeConfig);
                } else {
                    // TODO: Remove after testing
                    throw new RuntimeException("This shouldnt happen.");
                }
            }
        }

        // When loading an existing world load the existing biomes first, new biomes after so they don't claim reserved biome id's.
        for (BiomeConfig biomeConfig : nonVirtualBiomesExisting)
        {
            createAndRegisterBiome(loadedBiomeIdData, biomeConfig, isReload);
        }
        for (BiomeConfig biomeConfig : nonVirtualBiomes)
        {
            createAndRegisterBiome(loadedBiomeIdData, biomeConfig, isReload);
        }

        BiomeIdData.saveBiomeIdData(worldSaveFolder, this, this.world);

        // Forge dimensions are seperate worlds that can share biome configs so
        // use the highest maxSmoothRadius of any of the loaded worlds.
        // Worlds loaded before this one will not use biomes from this world
        // so no need to change their this.worldConfig.maxSmoothRadius
        ArrayList<LocalWorld> worlds = OTG.getAllWorlds();
        if(worlds != null)
        {
            for(LocalWorld world : worlds)
            {
                if (this.worldConfig.maxSmoothRadius < world.getConfigs().getWorldConfig().maxSmoothRadius)
                {
                    this.worldConfig.maxSmoothRadius = world.getConfigs().getWorldConfig().maxSmoothRadius;
                }
            }
        }

        if (this.biomesCount > 0)
        {
            // Remove last ", "
            loadedBiomeNames.delete(loadedBiomeNames.length() - 2, loadedBiomeNames.length());
        }
        return loadedBiomeNames.toString();
    }

    private void createAndRegisterBiome(ArrayList<BiomeIdData> loadedBiomeIdData, BiomeConfig biomeConfig, boolean isReload)
    {
        // Get the assigned OTG biome id
        int otgBiomeId = -1;
        BiomeConfig[] otgIds2 = OTG.getEngine().getOTGBiomeIds(world.getName());
        for(int i = 0; i < otgIds2.length; i++)
        {
            if(otgIds2[i] == biomeConfig)
            {
                otgBiomeId = i;
                break;
            }
        }
        if(otgBiomeId == -1)
        {
            // TODO: Remove after testing.
            throw new RuntimeException("This should not happen.");
        }

        // Create biome
        LocalBiome biome = world.createBiomeFor(biomeConfig, otgBiomeId, this, isReload);

        this.biomesByOTGId[biome.getOTGBiomeId()] = biome;

        // Indexing ReplacedBlocks
        if (!this.worldConfig.biomeConfigsHaveReplacement)
        {
            this.worldConfig.biomeConfigsHaveReplacement = biomeConfig.replacedBlocks.hasReplaceSettings();
        }

        // Indexing MaxSmoothRadius
        if (this.worldConfig.maxSmoothRadius < biomeConfig.smoothRadius)
        {
            this.worldConfig.maxSmoothRadius = biomeConfig.smoothRadius;
        }

        // Indexing BiomeColor
        if (this.worldConfig.biomeMode == OTG.getBiomeModeManager().FROM_IMAGE)
        {
            if (this.worldConfig.biomeColorMap == null)
            {
                this.worldConfig.biomeColorMap = new HashMap<Integer, Integer>();
            }

            int color = biomeConfig.biomeColor;
            this.worldConfig.biomeColorMap.put(color, biome.getOTGBiomeId());
        }
    }
}
