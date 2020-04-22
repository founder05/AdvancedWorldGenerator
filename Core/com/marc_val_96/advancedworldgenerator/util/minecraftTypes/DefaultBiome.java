package com.marc_val_96.advancedworldgenerator.util.minecraftTypes;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeLoadInstruction;
import com.marc_val_96.advancedworldgenerator.configuration.standard.MinecraftBiomeTemplates;
import com.marc_val_96.advancedworldgenerator.configuration.standard.MojangSettings;
import com.marc_val_96.advancedworldgenerator.configuration.standard.StandardBiomeTemplate;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;

/**
 * Enumeration containing the Proper names and IDs of the default Minecraft biomes as well as some
 * helper methods
 */
public enum DefaultBiome {

    /**
     * Default ID, proper name and default settings class for an Ocean biome
     */
    OCEAN(0, "Ocean", MinecraftBiomeTemplates.Ocean.class),
    /**
     * Default ID, proper name and default settings class for a Plains biome
     */
    PLAINS(1, "Plains", MinecraftBiomeTemplates.Plains.class),
    /**
     * Default ID, proper name and default settings class for a Desert biome
     */
    DESERT(2, "Desert", MinecraftBiomeTemplates.Desert.class),
    /**
     * Default ID, proper name and default settings class for an Extreme Hills biome
     */
    EXTREME_HILLS(3, "Extreme Hills", MinecraftBiomeTemplates.ExtremeHills.class),
    /**
     * Default ID, proper name and default settings class for a Forest biome
     */
    FOREST(4, "Forest", MinecraftBiomeTemplates.Forest.class),
    /**
     * Default ID, proper name and default settings class for a Taiga biome
     */
    TAIGA(5, "Taiga", MinecraftBiomeTemplates.Taiga.class),
    /**
     * Default ID, proper name and default settings class for a Swampland biome
     */
    SWAMPLAND(6, "Swampland", MinecraftBiomeTemplates.Swampland.class),
    /**
     * Default ID, proper name and default settings class for a River biome
     */
    RIVER(7, "River", MinecraftBiomeTemplates.River.class),
    /**
     * Default ID, proper name and default settings class for a Hell biome
     */
    HELL(8, "Hell", MinecraftBiomeTemplates.Hell.class),
    /**
     * Default ID, proper name and default settings class for a Sky biome
     */
    SKY(9, "The End", MinecraftBiomeTemplates.Sky.class),
    /**
     * Default ID, proper name and default settings class for a Frozen Ocean biome
     */
    FROZEN_OCEAN(10, "FrozenOcean", MinecraftBiomeTemplates.FrozenOcean.class),
    /**
     * Default ID, proper name and default settings class for a Frozen River biome
     */
    FROZEN_RIVER(11, "FrozenRiver", MinecraftBiomeTemplates.FrozenRiver.class),
    /**
     * Default ID, proper name and default settings class for an Ice Plains biome
     */
    ICE_PLAINS(12, "Ice Plains", MinecraftBiomeTemplates.IcePlains.class),
    /**
     * Default ID, proper name and default settings class for an Ice Mountains biome
     */
    ICE_MOUNTAINS(13, "Ice Mountains", MinecraftBiomeTemplates.IceMountains.class),
    /**
     * Default ID, proper name and default settings class for a Mushroom Island biome
     */
    MUSHROOM_ISLAND(14, "MushroomIsland", MinecraftBiomeTemplates.MushroomIsland.class),
    /**
     * Default ID, proper name and default settings class for a Mushroom Island Shore biome
     */
    MUSHROOM_SHORE(15, "MushroomIslandShore", MinecraftBiomeTemplates.MushroomIslandShore.class),
    /**
     * Default ID, proper name and default settings class for a Beach biome
     */
    BEACH(16, "Beach", MinecraftBiomeTemplates.Beach.class),
    /**
     * Default ID, proper name and default settings class for a Desert Hills biome
     */
    DESERT_HILLS(17, "DesertHills", MinecraftBiomeTemplates.DesertHills.class),
    /**
     * Default ID, proper name and default settings class for a Forest Hills biome
     */
    FOREST_HILLS(18, "ForestHills", MinecraftBiomeTemplates.ForestHills.class),
    /**
     * Default ID, proper name and default settings class for a Taiga Hills biome
     */
    TAIGA_HILLS(19, "TaigaHills", MinecraftBiomeTemplates.TaigaHills.class),
    /**
     * Default ID, proper name and default settings class for an Extreme Hills Edge biome
     */
    SMALL_MOUNTAINS(20, "Extreme Hills Edge", MinecraftBiomeTemplates.ExtremeHillsEdge.class),
    /**
     * Default ID, proper name and default settings class for a Jungle biome
     */
    JUNGLE(21, "Jungle", MinecraftBiomeTemplates.Jungle.class),
    /**
     * Default ID, proper name and default settings class for a Jungle Hills biome
     */
    JUNGLE_HILLS(22, "JungleHills", MinecraftBiomeTemplates.JungleHills.class),
    /**
     * Default ID, proper name and default settings class for... I think you understand it now.
     */
    JUNGLE_EDGE(23, "JungleEdge", MinecraftBiomeTemplates.JungleEdge.class),
    DEEP_OCEAN(24, "Deep Ocean", MinecraftBiomeTemplates.DeepOcean.class),
    STONE_BEACH(25, "Stone Beach", MinecraftBiomeTemplates.StoneBeach.class),
    COLD_BEACH(26, "Cold Beach", MinecraftBiomeTemplates.ColdBeach.class),
    BIRCH_FOREST(27, "Birch Forest", MinecraftBiomeTemplates.BirchForest.class),
    BIRCH_FOREST_HILLS(28, "Birch Forest Hills", MinecraftBiomeTemplates.BirchForestHills.class),
    ROOFED_FOREST(29, "Roofed Forest", MinecraftBiomeTemplates.RoofedForest.class),
    COLD_TAIGA(30, "Cold Taiga", MinecraftBiomeTemplates.ColdTaiga.class),
    COLD_TAIGA_HILLS(31, "Cold Taiga Hills", MinecraftBiomeTemplates.ColdTaigaHills.class),
    MEGA_TAIGA(32, "Mega Taiga", MinecraftBiomeTemplates.MegaTaiga.class),
    MEGA_TAIGA_HILLS(33, "Mega Taiga Hills", MinecraftBiomeTemplates.MegaTaigaHills.class),
    EXTREME_HILLS_PLUS(34, "Extreme Hills+", MinecraftBiomeTemplates.ExtremeHillsPlus.class),
    SAVANNA(35, "Savanna", MinecraftBiomeTemplates.Savanna.class),
    SAVANNA_PLATEAU(36, "Savanna Plateau", MinecraftBiomeTemplates.SavannaPlateau.class),
    MESA(37, "Mesa", MinecraftBiomeTemplates.Mesa.class),
    MESA_PLATEAU_FOREST(38, "Mesa Plateau F", MinecraftBiomeTemplates.MesaPlateauForest.class),
    MESA_PLATEAU(39, "Mesa Plateau", MinecraftBiomeTemplates.MesaPlateau.class),
    THE_VOID(127, "The Void", MinecraftBiomeTemplates.TheVoid.class),
    SUNFLOWER_PLAINS(129, "Sunflower Plains", MinecraftBiomeTemplates.SunflowerPlains.class),
    DESERT_MOUNTAINS(130, "Desert M", MinecraftBiomeTemplates.DesertMountains.class),
    EXTREME_HILLS_MOUNTAINS(131, "Extreme Hills M", MinecraftBiomeTemplates.ExtremeHillsMountains.class),
    FLOWER_FOREST(132, "Flower Forest", MinecraftBiomeTemplates.FlowerForest.class),
    TAIGA_MOUNTAINS(133, "Taiga M", MinecraftBiomeTemplates.TaigaMountains.class),
    SWAMPLAND_MOUNTAINS(134, "Swampland M", MinecraftBiomeTemplates.SwamplandMountains.class),
    ICE_PLAINS_SPIKES(140, "Ice Plains Spikes", MinecraftBiomeTemplates.IcePlainsSpikes.class),
    JUNGLE_MOUNTAINS(149, "Jungle M", MinecraftBiomeTemplates.JungleMountains.class),
    JUNGLE_EDGE_MOUNTAINS(151, "JungleEdge M", MinecraftBiomeTemplates.JungleEdgeMountains.class),
    BIRCH_FOREST_MOUNTAINS(155, "Birch Forest M", MinecraftBiomeTemplates.BirchForestMountains.class),
    BIRCH_FOREST_HILLS_MOUNTAINS(156, "Birch Forest Hills M", MinecraftBiomeTemplates.BirchForestHillsMountains.class),
    ROOFED_FOREST_MOUNTAINS(157, "Roofed Forest M", MinecraftBiomeTemplates.RoofedForestMountains.class),
    COLD_TAIGA_MOUNTAINS(158, "Cold Taiga M", MinecraftBiomeTemplates.ColdTaigaMountains.class),
    MEGA_SPRUCE_TAIGA(160, "Mega Spruce Taiga", MinecraftBiomeTemplates.MegaSpruceTaiga.class),
    MEGA_SPRUCE_TAIGA_HILLS(161, "Redwood Taiga Hills M", MinecraftBiomeTemplates.MegaSpruceTaigaHills.class),
    EXTREME_HILLS_PLUS_MOUNTAINS(162, "Extreme Hills+ M", MinecraftBiomeTemplates.ExtremeHillsPlusMountains.class),
    SAVANNA_MOUNTAINS(163, "Savanna M", MinecraftBiomeTemplates.SavannaMountains.class),
    SAVANNA_PLATEAU_MOUNTAINS(164, "Savanna Plateau M", MinecraftBiomeTemplates.SavannaPlateauMountains.class),
    MESA_BRYCE(165, "Mesa (Bryce)", MinecraftBiomeTemplates.MesaBryce.class),
    MESA_PLATEAU_FOREST_MOUNTAINS(166, "Mesa Plateau F M", MinecraftBiomeTemplates.MesaPlateauForestMountains.class),
    MESA_PLATEAU_MOUNTAINS(167, "Mesa Plateau M", MinecraftBiomeTemplates.MesaPlateauMountains.class);
    /**
     * A DefaultBiome lookup table with the biome ID being the array index
     */
    private static DefaultBiome[] lookupID;

    static {
        // Declares and Defines the DefaultBiome lookup table
        lookupID = new DefaultBiome[256];
        for (DefaultBiome biome : DefaultBiome.values()) {
            // Register by id
            lookupID[biome.Id] = biome;
        }
    }

    /**
     * The ID of the specific default biome represented
     */
    public final int Id;
    /**
     * The proper name of the specific default biome represented
     */
    public final String Name;
    /**
     * Default settings of this biome. Access this using
     * {@link DefaultBiome#getLoadInstructions(MojangSettings, int)}
     */
    private final Class<? extends MinecraftBiomeTemplates.MinecraftBiomeTemplate> defaultSettingsClass;

    DefaultBiome(int i, String name, Class<? extends MinecraftBiomeTemplates.MinecraftBiomeTemplate> defaultSettings) {
        this.Id = i;
        this.Name = name;
        this.defaultSettingsClass = defaultSettings;
    }

    /**
     * Returns a DefaultBiome object with the given biome ID
     *
     * @param id the ID of the DeafultBiome that is to be returned
     * @return A DefaultBiome with the given ID
     */
    public static DefaultBiome getBiome(int id) {
        if (id < lookupID.length) {
            return lookupID[id];
        } else {
            return null;
        }
    }

    /**
     * Returns true or false depending on if this DefaultBiome has the given name
     *
     * @param name The string to test this.Name against
     * @return Boolean whether or not this DefaultBiome has the given name
     */
    public static boolean Contain(String name) {
        for (DefaultBiome biome : DefaultBiome.values()) {
            if (biome.Name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the biome id depending on if this DefaultBiome has the
     * given name
     * <p/>
     *
     * @param name The string to test this.Name against
     *             <p/>
     * @return int the Id of the biome with String name
     */
    public static Integer getId(String name) {
        for (DefaultBiome biome : DefaultBiome.values()) {
            if (biome.Name.equals(name)) {
                return biome.Id;
            }
        }
        return null;
    }

    public BiomeLoadInstruction getLoadInstructions(MojangSettings mojangSettings, int maxWorldHeight) {
        try {
            StandardBiomeTemplate template = (StandardBiomeTemplate) defaultSettingsClass.getConstructors()[0].newInstance(mojangSettings, maxWorldHeight);
            return new BiomeLoadInstruction(Name, Id, template);
        } catch (Exception e) {
            AWG.log(LogMarker.FATAL, "Failed to create default biome");
            AWG.printStackTrace(LogMarker.FATAL, e);

            // Use the standard settings for custom biomes
            return new BiomeLoadInstruction(Name, Id, new StandardBiomeTemplate(maxWorldHeight));
        }
    }

}
