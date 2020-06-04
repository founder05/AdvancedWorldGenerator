package com.marc_val_96.advancedworldgenerator.util.minecraftTypes;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeLoadInstruction;
import com.marc_val_96.advancedworldgenerator.configuration.standard.MinecraftBiomeTemplates.*;
import com.marc_val_96.advancedworldgenerator.configuration.standard.MojangSettings;
import com.marc_val_96.advancedworldgenerator.configuration.standard.StandardBiomeTemplate;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;

/**
 * Enumeration containing the Proper names and IDs of the default Minecraft biomes as well as some
 * helper methods
 */
public enum DefaultBiome
{
    OCEAN("minecraft:ocean", "Ocean", Ocean.class),
    PLAINS("minecraft:plains", "Plains", Plains.class),
    DESERT("minecraft:desert", "Desert", Desert.class),
    MOUNTAINS("minecraft:mountains", "Mountains", Mountains.class),
    FOREST("minecraft:forest", "Forest", Forest.class),
    TAIGA("minecraft:taiga", "Taiga", Taiga.class),
    SWAMP("minecraft:swamp", "Swamp", Swampland.class),
    RIVER("minecraft:river", "River", River.class),
    NETHER("minecraft:nether", "Nether", Nether.class),
    THE_END("minecraft:the_end", "The End", TheEnd.class),
    FROZEN_OCEAN("minecraft:frozen_ocean", "Frozen ocean", FrozenOcean.class),
    FROZEN_RIVER("minecraft:frozen_river", "Frozen river", FrozenRiver.class),
    SNOWY_TUNDRA("minecraft:snowy_tundra", "Snowy tundra", SnowyTundra.class),
    SNOWY_MOUNTAINS("minecraft:snowy_mountains", "Snowy mountains", SnowyMountains.class),
    MUSHROOM_FIELDS("minecraft:mushroom_fields", "Mushroom fields", MushroomFields.class),
    MUSHROOM_FIELD_SHORE("minecraft:mushroom_field_shore", "Mushroom fields shore", MushroomFieldsShore.class),
    BEACH("minecraft:beach", "Beach", Beach.class),
    DESERT_HILLS("minecraft:desert_hills", "Desert hills", DesertHills.class),
    WOODED_HILLS("minecraft:wooded_hills", "Wooded hills", WoodedHills.class),
    TAIGA_HILLS("minecraft:taiga_hills", "Taiga hills", TaigaHills.class),
    MOUNTAIN_EDGE("minecraft:mountain_edge", "Mountain edge", MountainEdge.class),
    JUNGLE("minecraft:jungle", "Jungle", Jungle.class),
    JUNGLE_HILLS("minecraft:jungle_hills", "Jungle hills", JungleHills.class),
    JUNGLE_EDGE("minecraft:jungle_edge", "Jungle edge", JungleEdge.class),
    DEEP_OCEAN("minecraft:deep_ocean", "Deep Ocean", DeepOcean.class),
    STONE_SHORE("minecraft:stone_shore", "Stone shore", StoneShore.class),
    SNOWY_BEACH("minecraft:snowy_beach", "Snowy beach", SnowyBeach.class),
    BIRCH_FOREST("minecraft:birch_forest", "Birch forest", BirchForest.class),
    BIRCH_FOREST_HILLS("minecraft:birch_forest_hills", "Birch forest hills", BirchForestHills.class),
    DARK_FOREST("minecraft:dark_forest", "Dark forest", DarkForest.class),
    SNOWY_TAIGA("minecraft:snowy_taiga", "Snowy taiga", SnowyTaiga.class),
    SNOWY_TAIGA_HILLS("minecraft:snowy_taiga_hills", "Snowy taiga hills", SnowyTaigaHills.class),
    GIANT_TREE_TAIGA("minecraft:giant_tree_taiga", "Giant tree taiga", GiantTreeTaiga.class),
    GIANT_TREE_TAIGA_HILLS("minecraft:giant_tree_taiga_hills", "Giant tree taiga hills", GiantTreeTaigaHills.class),
    WOODED_MOUNTAINS("minecraft:wooded_mountains", "Wooded mountains", WoodedMountains.class),
    SAVANNA("minecraft:savanna", "Savanna", Savanna.class),
    SAVANNA_PLATEAU("minecraft:savanna_plateau", "Savanna Plateau", SavannaPlateau.class),
    BADLANDS("minecraft:badlands", "Badlands", Badlands.class),
    WOODED_BADLANDS_PLATEAU("minecraft:wooded_badlands_plateau", "Wooded badlands plateau", WoodedBadlandsPlateau.class),
    BADLANDS_PLATEAU("minecraft:badlands_plateau", "Badlands plateau", BadlandsPlateau.class),

	/*
	SMALL_END_ISLANDS("minecraft:small_end_islands", new SmallEndIslandsBiome());
	END_MIDLANDS("minecraft:end_midlands", new EndMidlandsBiome());
	END_HIGHLANDS("minecraft:end_highlands", new EndHighlandsBiome());
	END_BARRENS("minecraft:end_barrens", new EndBarrensBiome());
	WARM_OCEAN("minecraft:warm_ocean", new WarmOceanBiome());
	LUKEWARM_OCEAN("minecraft:lukewarm_ocean", new LukewarmOceanBiome());
	COLD_OCEAN("minecraft:cold_ocean", new ColdOceanBiome());
	DEEP_WARM_OCEAN("minecraft:deep_warm_ocean", new DeepWarmOceanBiome());
	DEEP_LUKEWARM_OCEAN("minecraft:deep_lukewarm_ocean", new DeepLukewarmOceanBiome());
	DEEP_COLD_OCEAN("minecraft:deep_cold_ocean", new DeepColdOceanBiome());
	DEEP_FROZEN_OCEAN("minecraft:deep_frozen_ocean", new DeepFrozenOceanBiome());
	*/

    THE_VOID("minecraft:the_void", "The void", TheVoid.class),
    SUNFLOWER_PLAINS("minecraft:sunflower_plains", "Sunflower plains", SunflowerPlains.class),
    DESERT_LAKES("minecraft:desert_lakes", "Desert lakes", DesertLakes.class),
    GRAVELLY_MOUNTAINS("minecraft:gravelly_mountains", "Gravelly mountains", GravellyMountains.class),
    FLOWER_FOREST("minecraft:flower_forest", "Flower forest", FlowerForest.class),
    TAIGA_MOUNTAINS("minecraft:taiga_mountains", "Taiga mountains", TaigaMountains.class),
    SWAMP_HILLS("minecraft:swamp_hills", "Swamp hills", SwampHills.class),
    ICE_SPIKES("minecraft:ice_spikes", "Ice spikes", IceSpikes.class),
    MODIFIED_JUNGLE("minecraft:modified_jungle", "Modified jungle", ModifiedJungle.class),
    MODIFIED_JUNGLE_EDGE("minecraft:modified_jungle_edge", "Modified jungle edge", ModifiedJungleEdge.class),
    TALL_BIRCH_FOREST("minecraft:tall_birch_forest", "Tall birch forest", TallBirchForest.class),
    TALL_BIRCH_HILLS("minecraft:tall_birch_hills", "Tall birch hills", TallBirchHills.class),
    DARK_FOREST_HILLS("minecraft:dark_forest_hills", "Dark forest hills", DarkForestHills.class),
    SNOWY_TAIGA_MOUNTAINS("minecraft:snowy_taiga_mountains", "Snowy taiga mountains", SnowyTaigaMountains.class),
    GIANT_SPRUCE_TAIGA("minecraft:giant_spruce_taiga", "Giant spruce taiga", GiantSpruceTaiga.class),
    GIANT_SPRUCE_TAIGA_HILLS("minecraft:giant_spruce_taiga_hills", "Giant spruce taiga hills", GiantSpruceTaigaHills.class),
    MODIFIED_GRAVELLY_MOUNTAINS("minecraft:modified_gravelly_mountains", "Modified gravelly mountains", ModifiedGravellyMountains.class),

    SHATTERED_SAVANNA("minecraft:shattered_savanna", "Shattered savanna", ShatteredSavanna.class),
    SHATTERED_SAVANNA_PLATEAU("minecraft:shattered_savanna_plateau", "Shattered savanna plateau", ShatteredSavannaPlateau.class),
    ERODED_BADLANDS("minecraft:eroded_badlands", "Eroded badlands", ErodedBadlands.class),
    MODIFIED_WOODED_BADLANDS_PLATEAU("minecraft:modified_wooded_badlands_plateau", "Modified wooded badlands plateau", ModifiedWoodedBadlandsPlateau.class),
    MODIFIED_BADLANDS_PLATEAU("minecraft:modified_badlands_plateau", "Modified badlands plateau", ModifiedBadlandsPlateau.class);

	/*
	BAMBOO_JUNGLE("minecraft:bamboo_jungle", new BambooJungleBiome());
	BAMBOO_JUNGLE_HILLS("minecraft:bamboo_jungle_hills", new BambooJungleHillsBiome());
    */

    /**
     * The ID of the specific default biome represented
     */
    public final String resourceLocation;

    /**
     * The proper name of the specific default biome represented
     */
    public final String Name;

    /**
     * Default settings of this biome. Access this using
     * {@link DefaultBiome#getLoadInstructions(MojangSettings, int)}
     */
    private final Class<? extends MinecraftBiomeTemplate> defaultSettingsClass;

    private DefaultBiome(String resourceLocation, String name, Class<? extends MinecraftBiomeTemplate> defaultSettings)
    {
        this.resourceLocation = resourceLocation;
        this.Name = name;
        this.defaultSettingsClass = defaultSettings;
    }

    public BiomeLoadInstruction getLoadInstructions(MojangSettings mojangSettings, int maxWorldHeight)
    {
        try
        {
            StandardBiomeTemplate template = (StandardBiomeTemplate) defaultSettingsClass.getConstructors()[0].newInstance(mojangSettings, maxWorldHeight);
            return new BiomeLoadInstruction(Name, template);
        }
        catch (Exception e)
        {
            OTG.log(LogMarker.FATAL, "Failed to create default biome");
            OTG.printStackTrace(LogMarker.FATAL, e);

            // Use the standard settings for custom biomes
            return new BiomeLoadInstruction(Name, new StandardBiomeTemplate(maxWorldHeight));
        }
    }

    /**
     * Returns true or false depending on if this DefaultBiome has the given name
     *
     * @param name The string to test this.Name against
     * @return Boolean whether or not this DefaultBiome has the given name
     */
    public static boolean Contain(String name)
    {
        for (DefaultBiome biome : DefaultBiome.values())
        {
            if (biome.Name.equals(name))
            {
                return true;
            }
        }
        return false;
    }
}
