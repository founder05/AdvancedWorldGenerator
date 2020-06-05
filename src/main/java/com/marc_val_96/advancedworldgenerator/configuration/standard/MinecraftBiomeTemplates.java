package com.marc_val_96.advancedworldgenerator.configuration.standard;

import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig.MineshaftType;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig.RareBuildingType;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig.VillageType;
import com.marc_val_96.advancedworldgenerator.configuration.WeightedMobSpawnGroup;
import com.marc_val_96.advancedworldgenerator.generator.surface.MesaSurfaceGenerator;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultBiome;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.TreeType;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to hold all default settings of all default biomes.
 *
 * Because most vanilla biomes just have a few changes, it isn't needed to
 * give each their own top-level class, a simple inner class is enough.
 */
public class MinecraftBiomeTemplates
{
    public abstract static class MinecraftBiomeTemplate extends StandardBiomeTemplate
    {
        protected final MojangSettings mojangSettings;

        // For each biome's vanilla settings see net.minecraft.world.biome.Biome
        public MinecraftBiomeTemplate(MojangSettings mojangSettings, int worldHeight)
        {
            super(worldHeight);
            this.mojangSettings = mojangSettings;

            // Some settings are provided by MojangSettings,
            // which gets them from Minecraft
            this.defaultBiomeSurface = this.mojangSettings.getSurfaceHeight();
            this.defaultBiomeVolatility = this.mojangSettings.getSurfaceVolatility();
            this.defaultSurfaceBlock = this.mojangSettings.getSurfaceBlock() != null ? this.mojangSettings.getSurfaceBlock().toDefaultMaterial() : DefaultMaterial.GRASS_BLOCK;
            this.defaultGroundBlock = this.mojangSettings.getGroundBlock() != null ? this.mojangSettings.getGroundBlock().toDefaultMaterial() : DefaultMaterial.DIRT;
            this.defaultBiomeTemperature = this.mojangSettings.getTemperature();
            this.defaultBiomeWetness = this.mojangSettings.getWetness();


            this.defaultCreatures = new ArrayList<WeightedMobSpawnGroup>();
            this.defaultMonsters = new ArrayList<WeightedMobSpawnGroup>();
            this.defaultWaterCreatures = new ArrayList<WeightedMobSpawnGroup>();
            this.defaultAmbientCreatures = new ArrayList<WeightedMobSpawnGroup>();

            this.defaultMonsters = this.mojangSettings.getMobSpawnGroup(MojangSettings.EntityCategory.MONSTER);
            this.defaultCreatures = this.mojangSettings.getMobSpawnGroup(MojangSettings.EntityCategory.CREATURE);
            this.defaultWaterCreatures = this.mojangSettings.getMobSpawnGroup(MojangSettings.EntityCategory.WATER_CREATURE);
            this.defaultAmbientCreatures = this.mojangSettings.getMobSpawnGroup(MojangSettings.EntityCategory.AMBIENT_CREATURE);
        }

        protected void clearDefaultBorder()
        {
            this.defaultBorder.clear();
            this.defaultNotBorderNear.clear();
            this.defaultSizeWhenBorder = 8;
        }
    }

    public static class Ocean extends MinecraftBiomeTemplate
    {
        public Ocean(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultColor = 0x000070;
            this.defaultStrongholds = false;
            this.defaultRiverBiome = "";
            this.defaultTree = new Object[] {1, TreeType.BigTree, 1, TreeType.Tree, 9};
            this.defaultOceanMonuments = true;

            this.defaultInheritMobsBiomeName = "minecraft:ocean";
            this.defaultBiomeDictId = "OCEAN";
        }
    }

    public static class Plains extends MinecraftBiomeTemplate
    {
        public Plains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultDandelions = 3;
            this.defaultPoppies = 1;
            this.defaultAzureBluets = 1;
            this.defaultOxeyeDaisies = 1;
            this.defaultTulips = 3;
            this.defaultGrass = 100;
            this.defaultColor = 0x8DB360;
            this.defaultStrongholds = false;
            this.defaultVillageType = BiomeConfig.VillageType.wood;
            this.defaultDoubleGrass = 10;
            this.defaultDoubleGrassIsGrouped = true;
            this.defaultReed = 5;

            this.defaultInheritMobsBiomeName = "minecraft:plains";
            this.defaultBiomeDictId = "PLAINS";
        }
    }

    public static class Desert extends MinecraftBiomeTemplate
    {
        public Desert(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultWaterLakes = false;
            this.defaultDeadBush = 4;
            this.defaultGrass = 0;
            this.defaultReed = 10;
            this.defaultCactus = 10;
            this.defaultColor = 0xFA9418;
            this.defaultWell = new Object[] {DefaultMaterial.SANDSTONE, DefaultMaterial.SANDSTONE_STAIRS, DefaultMaterial.WATER, 1, 0.1, 2,
                this.worldHeight, DefaultMaterial.SAND};
            this.defaultVillageType = VillageType.sandstone;
            this.defaultRareBuildingType = BiomeConfig.RareBuildingType.desertPyramid;
            this.defaultBorder.add(DefaultBiome.BADLANDS.Name);
            this.defaultNotBorderNear.add(DefaultBiome.OCEAN.Name);
            this.defaultNotBorderNear.add(DefaultBiome.BADLANDS_PLATEAU.Name);
            this.defaultNotBorderNear.add(DefaultBiome.WOODED_BADLANDS_PLATEAU.Name);
            this.defaultNotBorderNear.add(DefaultBiome.MODIFIED_BADLANDS_PLATEAU.Name);
            this.defaultNotBorderNear.add(DefaultBiome.MODIFIED_WOODED_BADLANDS_PLATEAU.Name);
            this.defaultNotBorderNear.add(DefaultBiome.ERODED_BADLANDS.Name);
            this.defaultFossilRarity = 1.156; // 1/64 chance of spawning

            this.defaultInheritMobsBiomeName = "minecraft:desert";
            this.defaultBiomeDictId = "HOT, DRY, SANDY";
        }
    }

    public static class Mountains extends MinecraftBiomeTemplate
    {
        public Mountains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultColor = 0x606060;
            this.defaultDandelions = 4;
            this.defaultEmeraldOre = BiomeStandardValues.EmeraldDepositFrequency;
            this.defaultTree = new Object[] {1, TreeType.Taiga2, 10, TreeType.BigTree, 1, TreeType.Tree, 9};
            this.defaultSurfaceSurfaceAndGroundControl = new Object[] {DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT, 1.0,
                DefaultMaterial.STONE, DefaultMaterial.STONE, 10.0};

            this.defaultInheritMobsBiomeName = "minecraft:mountains";
            this.defaultBiomeDictId = "MOUNTAIN, HILLS";
        }
    }

    public static class Forest extends MinecraftBiomeTemplate
    {
        public Forest(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultRarityWhenIsle = 96;
            this.defaultIsle.add(DefaultBiome.PLAINS.Name);
            this.defaultGrass = 30;
            this.defaultColor = 0x056621;
            this.defaultTree = new Object[] {10, TreeType.Birch, 20, TreeType.Tree, 100};
            this.defaultTallFlowers = 2;
            this.defaultPoppies = 4;
            this.defaultReed = 3;
            this.defaultMushroom = 1;

            this.defaultInheritMobsBiomeName = "minecraft:forest";
            this.defaultBiomeDictId = "FOREST";
        }
    }

    public static class Taiga extends MinecraftBiomeTemplate
    {
        public Taiga(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultGrass = 10;
            this.defaultColor = 0x0B6659;
            this.defaultTree = new Object[] {10, TreeType.Taiga1, 35, TreeType.Taiga2, 100};

            // Place taiga on the border of Mega Taiga
            this.defaultBorder.add(DefaultBiome.GIANT_TREE_TAIGA.Name);
            this.defaultNotBorderNear.add(DefaultBiome.GIANT_SPRUCE_TAIGA.Name);
            this.defaultNotBorderNear.add(DefaultBiome.GIANT_TREE_TAIGA_HILLS.Name);
            this.defaultNotBorderNear.add(DefaultBiome.GIANT_SPRUCE_TAIGA_HILLS.Name);
            this.defaultSizeWhenBorder = 6;

            this.defaultVillageType = VillageType.taiga;

            this.defaultInheritMobsBiomeName = "minecraft:taiga";
            this.defaultBiomeDictId = "COLD, CONIFEROUS, FOREST";
        }
    }

    public static class Swampland extends MinecraftBiomeTemplate
    {
        public Swampland(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultDandelions = 0;
            this.defaultMushroom = 8;
            this.defaultReed = 10;
            this.defaultWaterSand = 0;
            this.defaultWaterGravel = 0;
            this.defaultWaterLily = 4;
            this.defaultSwampPatches = 1;
            this.defaultDandelions = 0;
            this.defaultBlueOrchids = 2;

            this.defaultColor = 0x07F9B2;
            this.defaultWaterColorMultiplier = 0xe0ffae;
            this.defaultGrassColor = 0x7E6E7E;
            this.defaultFoliageColor = 0x7E6E7E;

            this.defaultGrass = 30;
            this.defaultRareBuildingType = RareBuildingType.swampHut;
            this.defaultTree = new Object[] {2, TreeType.SwampTree, 100};
            this.defaultStrongholds = false;
            this.defaultFossilRarity = 1.156; // 1/64 chance of spawning

            this.defaultInheritMobsBiomeName = "minecraft:swampland";
            this.defaultBiomeDictId = "WET, SWAMP";
        }
    }

    public static class River extends MinecraftBiomeTemplate
    {
        public River(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultSize = 8;
            this.defaultRarity = 95;
            this.defaultColor = 0x0000FF;
            this.defaultStrongholds = false;
            this.defaultTree = new Object[] {1, TreeType.BigTree, 1, TreeType.Tree, 9};
            this.defaultOceanMonuments = true;

            this.defaultInheritMobsBiomeName = "minecraft:river";
            this.defaultBiomeDictId = "RIVER";
        }
    }

    public static class Nether extends MinecraftBiomeTemplate
    {
        public Nether(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xFF0000;

            this.defaultInheritMobsBiomeName = "minecraft:nether";
            this.defaultBiomeDictId = "HOT, DRY, NETHER";
            this.defaultNetherFortressEnabled = true;
        }
    }

    public static class TheEnd extends MinecraftBiomeTemplate
    {
        public TheEnd(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x8080FF;

            this.defaultInheritMobsBiomeName = "minecraft:the_end";
            this.defaultBiomeDictId = "COLD, DRY, END";
        }
    }

    public static class FrozenOcean extends Ocean
    {
        public FrozenOcean(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultColor = 0x9090A0;

            this.defaultInheritMobsBiomeName = "minecraft:frozen_ocean";
            this.defaultBiomeDictId = "COLD, OCEAN, SNOWY";
        }
    }

    public static class FrozenRiver extends MinecraftBiomeTemplate
    {
        public FrozenRiver(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultColor = 0xA0A0FF;
            this.defaultStrongholds = false;
            this.defaultOceanMonuments = true;

            this.defaultInheritMobsBiomeName = "minecraft:frozen_river";
            this.defaultBiomeDictId = "COLD, RIVER, SNOWY";
        }
    }

    public static class SnowyTundra extends MinecraftBiomeTemplate
    {
        public SnowyTundra(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultColor = 0xFFFFFF;
            this.defaultRiverBiome = DefaultBiome.FROZEN_RIVER.Name;
            this.defaultPoppies = 3;
            this.defaultGrass = 20;
            this.defaultGrassIsGrouped = true;
            this.defaultTree = new Object[] {1, TreeType.Taiga2, 15};
            this.defaultRareBuildingType = RareBuildingType.igloo;

            this.defaultInheritMobsBiomeName = "minecraft:snowy_tundra";
            this.defaultBiomeDictId = "COLD, SNOWY, WASTELAND";
        }
    }

    public static class SnowyMountains extends MinecraftBiomeTemplate
    {
        public SnowyMountains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultColor = 0xA0A0A0;
            this.defaultSizeWhenIsle = 6;
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.add(DefaultBiome.SNOWY_MOUNTAINS.Name);
            this.defaultRiverBiome = DefaultBiome.FROZEN_RIVER.Name;

            this.defaultInheritMobsBiomeName = "minecraft:snowy_mountains";
            this.defaultBiomeDictId = "COLD, SNOWY, MOUNTAIN";
        }
    }

    public static class MushroomFields extends MinecraftBiomeTemplate
    {
        public MushroomFields(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultSurfaceBlock = DefaultMaterial.MYCELIUM;
            this.defaultMushroom = 2;
            this.defaultGrass = 0;
            this.defaultDandelions = 0;
            this.defaultRarityWhenIsle = 1;
            this.defaultRiverBiome = "";
            this.defaultSizeWhenIsle = 6;
            this.defaultIsle.add(DefaultBiome.OCEAN.Name);
            this.defaultIsle.add(DefaultBiome.DEEP_OCEAN.Name);
            this.defaultColor = 0xFF00FF;
            this.defaultWaterLily = 1;
            this.defaultStrongholds = false;
            this.defaultTree = new Object[] {1, TreeType.HugeMushroom, 100};

            this.defaultInheritMobsBiomeName = "minecraft:mushroom_fields";
            this.defaultBiomeDictId = "MUSHROOM";
        }
    }

    public static class MushroomFieldsShore extends MushroomFields
    {
        public MushroomFieldsShore(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultSizeWhenBorder = 9;
            this.defaultBorder.add(DefaultBiome.MUSHROOM_FIELDS.Name);
            this.defaultColor = 0xA000FF;
            this.defaultTree = null; // No mushrooms on the shore

            this.defaultInheritMobsBiomeName = "minecraft:mushroom_field_shore";
            this.defaultBiomeDictId = "MUSHROOM, BEACH";
        }
    }

    public static class Beach extends MinecraftBiomeTemplate
    {
        public Beach(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultSizeWhenBorder = 8;
            this.defaultBorder.add(DefaultBiome.OCEAN.Name);
            this.defaultNotBorderNear.add(DefaultBiome.RIVER.Name);
            this.defaultNotBorderNear.add(DefaultBiome.SWAMP.Name);
            this.defaultNotBorderNear.add(DefaultBiome.MOUNTAINS.Name);
            this.defaultNotBorderNear.add(DefaultBiome.ICE_SPIKES.Name);
            this.defaultNotBorderNear.add(DefaultBiome.SNOWY_TUNDRA.Name);
            this.defaultNotBorderNear.add(DefaultBiome.SNOWY_TAIGA.Name);
            this.defaultNotBorderNear.add(DefaultBiome.SNOWY_TAIGA_HILLS.Name);
            this.defaultNotBorderNear.add(DefaultBiome.SNOWY_TAIGA_MOUNTAINS.Name);
            this.defaultNotBorderNear.add(DefaultBiome.SNOWY_MOUNTAINS.Name);
            this.defaultNotBorderNear.add(DefaultBiome.MUSHROOM_FIELDS.Name);
            this.defaultNotBorderNear.add(DefaultBiome.DEEP_OCEAN.Name);
            this.defaultNotBorderNear.add(DefaultBiome.SNOWY_BEACH.Name);
            this.defaultNotBorderNear.add(DefaultBiome.STONE_SHORE.Name);
            this.defaultNotBorderNear.add(DefaultBiome.BADLANDS.Name);
            this.defaultColor = 0xFADE55;
            this.defaultStrongholds = false;

            this.defaultInheritMobsBiomeName = "minecraft:beach";
            this.defaultBiomeDictId = "BEACH";
        }
    }

    public static class DesertHills extends Desert
    {
        public DesertHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultWaterLakes = false;
            this.defaultSizeWhenIsle = 6;
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.add(DefaultBiome.DESERT.Name);
            this.defaultDeadBush = 4;
            this.defaultGrass = 0;
            this.defaultReed = 50;
            this.defaultCactus = 10;
            this.defaultColor = 0xD25F12;
            this.defaultWell = new Object[] {DefaultMaterial.SANDSTONE, DefaultMaterial.SANDSTONE_STAIRS, DefaultMaterial.WATER, 1, 0.1, 2,
                this.worldHeight, DefaultMaterial.SAND};
            this.defaultVillageType = VillageType.sandstone;
            this.defaultRareBuildingType = RareBuildingType.desertPyramid;

            // Don't inherit border properties of the Desert biome
            this.clearDefaultBorder();

            this.defaultInheritMobsBiomeName = "minecraft:desert_hills";
            this.defaultBiomeDictId = "HOT, DRY, SANDY, HILLS";
        }
    }

    public static class WoodedHills extends Forest
    {
        public WoodedHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultSizeWhenIsle = 6;
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.add(DefaultBiome.FOREST.Name);
            this.defaultGrass = 15;
            this.defaultColor = 0x22551C;

            this.defaultInheritMobsBiomeName = "minecraft:wooded_hills";
            this.defaultBiomeDictId = "FOREST, HILLS";
        }
    }

    public static class TaigaHills extends Taiga
    {
        public TaigaHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.clearDefaultBorder();

            this.defaultSizeWhenIsle = 6;
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.add(DefaultBiome.TAIGA.Name);
            this.defaultGrass = 10;
            this.defaultColor = 0x163933;
            this.defaultRiverBiome = DefaultBiome.FROZEN_RIVER.Name;

            this.defaultInheritMobsBiomeName = "minecraft:taiga_hills";
            this.defaultBiomeDictId = "COLD, CONIFEROUS, FOREST, HILLS";
        }
    }

    public static class MountainEdge extends Mountains
    {
        public MountainEdge(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultSizeWhenBorder = 8;
            this.defaultBorder.add(DefaultBiome.MOUNTAINS.Name);
            this.defaultNotBorderNear.add(DefaultBiome.WOODED_MOUNTAINS.Name);
            this.defaultColor = 0x72789A;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[0];

            this.defaultInheritMobsBiomeName = "minecraft:mountain_edge";
            this.defaultBiomeDictId = "MOUNTAIN";
        }
    }

    public static class Jungle extends MinecraftBiomeTemplate
    {
        public Jungle(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultGrass = 60;
            this.defaultFerns = 20;
            this.defaultPoppies = 4;
            this.defaultDandelions = 4;
            this.defaultColor = 0x537B09;
            this.defaultRareBuildingType = RareBuildingType.jungleTemple;
            this.defaultTree = new Object[] {50, TreeType.BigTree, 10, TreeType.GroundBush, 50, TreeType.JungleTree, 35,
                TreeType.CocoaTree, 100};
            this.defaultMelons = 1;

            this.defaultInheritMobsBiomeName = "minecraft:jungle";
            this.defaultBiomeDictId = "HOT, WET, DENSE, JUNGLE";
        }
    }

    public static class JungleHills extends Jungle
    {
        public JungleHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultColor = 0x2C4205;
            this.defaultIsle.add(DefaultBiome.JUNGLE.Name);

            this.defaultInheritMobsBiomeName = "minecraft:jungle_hills";
            this.defaultBiomeDictId = "HOT, WET, DENSE, JUNGLE, HILLS";
        }
    }

    public static class JungleEdge extends Jungle
    {
        public JungleEdge(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x628B17;
            this.defaultSizeWhenBorder = 8;
            this.defaultBorder.add(DefaultBiome.JUNGLE.Name);

            this.defaultInheritMobsBiomeName = "minecraft:jungle_edge";
            this.defaultBiomeDictId = "HOT, WET, JUNGLE, FOREST";
        }
    }

    public static class DeepOcean extends Ocean
    {
        public DeepOcean(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x000030;
            this.defaultIsle.add(DefaultBiome.OCEAN.Name);
            this.defaultSizeWhenIsle = 4;
            this.defaultRarityWhenIsle = 100;

            this.defaultInheritMobsBiomeName = "minecraft:deep_ocean";
            this.defaultBiomeDictId = "OCEAN";
        }
    }

    public static class StoneShore extends MinecraftBiomeTemplate
    {
        public StoneShore(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xA2A284;

            this.defaultInheritMobsBiomeName = "minecraft:stone_shore";
            this.defaultBiomeDictId = "BEACH";
        }
    }

    public static class SnowyBeach extends MinecraftBiomeTemplate
    {
        public SnowyBeach(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xFAF0C0;
            this.defaultStrongholds = false;

            this.defaultInheritMobsBiomeName = "minecraft:snowy_beach";
            this.defaultBiomeDictId = "COLD, BEACH, SNOWY";
        }
    }

    public static class BirchForest extends Forest
    {
        public BirchForest(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x307444;
            this.defaultTree = new Object[] {10, TreeType.Birch, 80};
            // Forest spawns as an isle in Plains, BirchForest shouldn't
            this.defaultIsle.clear();

            this.defaultInheritMobsBiomeName = "minecraft:birch_forest";
            this.defaultBiomeDictId = "FOREST";
        }
    }

    public static class BirchForestHills extends BirchForest
    {
        public BirchForestHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x1F5F32;
            this.defaultIsle.add(DefaultBiome.BIRCH_FOREST.Name);
            this.defaultRarityWhenIsle = 97;

            this.defaultInheritMobsBiomeName = "minecraft:birch_forest_hills";
            this.defaultBiomeDictId = "FOREST, HILLS";
        }
    }

    public static class DarkForest extends MinecraftBiomeTemplate
    {
        public DarkForest(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x40511A;
            this.defaultGrass = 15;
            this.defaultTree = new Object[] {20, TreeType.HugeMushroom, 3, TreeType.DarkOak, 66, TreeType.Birch, 20, TreeType.Tree, 100};
            this.defaultTallFlowers = 1;
            this.defaultPoppies = 4;

            this.defaultWoodlandMansions = true;

            this.defaultInheritMobsBiomeName = "minecraft:dark_forest";
            this.defaultBiomeDictId = "SPOOKY, DENSE, FOREST";
        }
    }

    public static class SnowyTaiga extends Taiga
    {
        public SnowyTaiga(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.clearDefaultBorder();

            this.defaultColor = 0x31554A;
            this.defaultRarity = 35;
            this.defaultRareBuildingType = RareBuildingType.igloo;

            this.defaultInheritMobsBiomeName = "minecraft:snowy_taiga";
            this.defaultBiomeDictId = "COLD, CONIFEROUS, FOREST, SNOWY";
        }
    }

    public static class SnowyTaigaHills extends SnowyTaiga
    {
        public SnowyTaigaHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);

            this.defaultColor = 0x243F36;
            this.defaultSizeWhenIsle = 6;
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.add(DefaultBiome.SNOWY_TAIGA.Name);
            this.defaultRareBuildingType = RareBuildingType.disabled;

            this.defaultInheritMobsBiomeName = "minecraft:snowy_taiga_hills";
            this.defaultBiomeDictId = "COLD, CONIFEROUS, FOREST, SNOWY, HILLS";
        }
    }

    public static class GiantTreeTaiga extends MinecraftBiomeTemplate
    {
        public GiantTreeTaiga(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x596651;
            this.defaultTree = new Object[] {10, TreeType.HugeTaiga1, 33, TreeType.Taiga1, 33, TreeType.Taiga2, 100};
            this.defaultBoulder = 2;
            this.defaultGrass = 16;
            this.defaultFerns = 80;
            this.defaultMushroom = 8;
            this.defaultLargeFerns = 60;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[] {DefaultMaterial.DIRT + ":2", DefaultMaterial.DIRT, -0.95,
                DefaultMaterial.DIRT + ":1", DefaultMaterial.DIRT, 1.75};

            this.defaultVillageType = VillageType.taiga;

            this.defaultInheritMobsBiomeName = "minecraft:giant_tree_taiga";
            this.defaultBiomeDictId = "COLD, CONIFEROUS, FOREST";
        }
    }

    public static class GiantTreeTaigaHills extends GiantTreeTaiga
    {
        public GiantTreeTaigaHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x454F3E;
            this.defaultSize = 6;
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.add(DefaultBiome.GIANT_TREE_TAIGA.Name);

            this.defaultInheritMobsBiomeName = "minecraft:giant_tree_taiga_hills";
            this.defaultBiomeDictId = "COLD, CONIFEROUS, FOREST, HILLS";
        }
    }

    public static class WoodedMountains extends Mountains
    {
        public WoodedMountains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x507050;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[0];
            this.defaultTree = new Object[] {1, TreeType.Taiga2, 66, TreeType.BigTree, 10, TreeType.Tree, 100};
            this.defaultIsle.add(DefaultBiome.MOUNTAINS.Name);
            this.defaultRarityWhenIsle = 97;

            this.defaultInheritMobsBiomeName = "minecraft:wooded_mountains";
            this.defaultBiomeDictId = "MOUNTAIN, FOREST, SPARSE";
        }
    }

    public static class Savanna extends MinecraftBiomeTemplate
    {
        public Savanna(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xBDB25F;
            this.defaultVillageType = VillageType.wood;
            this.defaultGrass = 200;
            this.defaultDoubleGrass = 4;
            this.defaultDandelions = 4;
            this.defaultTree = new Object[] {1, TreeType.Acacia, 80, TreeType.Tree, 100};

            this.defaultVillageType = VillageType.savanna;

            this.defaultInheritMobsBiomeName = "minecraft:savanna";
            this.defaultBiomeDictId = "HOT, SAVANNA, PLAINS, SPARSE";
        }
    }

    public static class SavannaPlateau extends Savanna
    {
        public SavannaPlateau(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xA79D64;
            this.defaultSizeWhenIsle = 6;
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.add(DefaultBiome.SAVANNA.Name);

            this.defaultInheritMobsBiomeName = "minecraft:savanna_plateau";
            this.defaultBiomeDictId = "HOT, SAVANNA, PLAINS, SPARSE";
        }
    }

    public static class Badlands extends MinecraftBiomeTemplate
    {
        public Badlands(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xD94515;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[] {MesaSurfaceGenerator.NAME_NORMAL};
            this.defaultDandelions = 0;
            this.defaultDeadBush = 7;
            this.defaultReed = 5;
            this.defaultCactus = 10;
            this.defaultGrass = 0;
            this.defaultMineshaftType = MineshaftType.mesa;

            this.defaultInheritMobsBiomeName = "minecraft:badlands";
            this.defaultBiomeDictId = "MESA, SANDY";
        }
    }

    public static class WoodedBadlandsPlateau extends BadlandsPlateau
    {
        public WoodedBadlandsPlateau(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xB09765;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[] {MesaSurfaceGenerator.NAME_FOREST};
            this.defaultTree = new Object[] {1, TreeType.Tree, 100};
            this.defaultGrass = 10;

            // Minecraft has chosen sand and stained clay as the surface and
            // ground blocks. It then places hardcoded grass and dirt.
            // Open Terrain Generator does it the other way round: it places
            // hardcoded sand and stained clay and lets the user change the
            // grass and dirt blocks.
            this.defaultSurfaceBlock = DefaultMaterial.GRASS_BLOCK;
            this.defaultGroundBlock = DefaultMaterial.DIRT;

            this.defaultInheritMobsBiomeName = "minecraft:wooded_badlands_plateau";
            this.defaultBiomeDictId = "MESA, SPARSE, SANDY";
        }
    }

    public static class BadlandsPlateau extends Badlands
    {
        public BadlandsPlateau(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xCA8C65;
            this.defaultIsle.add(DefaultBiome.BADLANDS.Name);
            this.defaultRarityWhenIsle = 99;

            this.defaultInheritMobsBiomeName = "minecraft:badlands_plateau";
            this.defaultBiomeDictId = "MESA, SANDY";
        }
    }

    public static class TheVoid extends MinecraftBiomeTemplate
    {
        public TheVoid(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xB6D0FF;
            this.defaultDisableBiomeHeight = true;
            this.defaultStrongholds = false;
            Arrays.fill(this.defaultCustomHeightControl, -100);

            this.defaultInheritMobsBiomeName = "minecraft:the_void";
            this.defaultBiomeDictId = ""; // TODO: Should this be END?
        }
    }

    public static class SunflowerPlains extends Plains
    {
        public SunflowerPlains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xDEFF00;
            this.defaultSunflowers = 30;
            this.defaultRarity = 10;

            this.defaultInheritMobsBiomeName = "minecraft:sunflower_plains";
        }
    }

    public static class DesertLakes extends Desert
    {
        public DesertLakes(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xE58208;
            this.defaultWaterLakes = true;
            this.defaultRarity = 10;

            // Don't inherit border properties of the Desert biome
            this.clearDefaultBorder();

            this.defaultInheritMobsBiomeName = "minecraft:desert_lakes";
        }
    }

    public static class GravellyMountains extends Mountains
    {
        public GravellyMountains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x525252;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[] {DefaultMaterial.GRAVEL, DefaultMaterial.GRAVEL, -1.0,
                DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT, 2.0, DefaultMaterial.GRAVEL, DefaultMaterial.GRAVEL, 10.0};
            this.defaultRarity = 10;

            this.defaultInheritMobsBiomeName = "minecraft:gravelly_mountains";
        }
    }

    public static class FlowerForest extends Forest
    {
        public FlowerForest(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            int flowerMultiplier = 20;

            this.defaultColor = 0x2D8E49;
            this.defaultRarity = 10;

            this.defaultDandelions = 0;
            this.defaultTallFlowers = 6;
            this.defaultPoppies = flowerMultiplier * 2;
            this.defaultAlliums = flowerMultiplier;
            this.defaultAzureBluets = flowerMultiplier;
            // Four different tulip colors, so each tulip now
            // generates as much as the other flowers
            this.defaultTulips = flowerMultiplier * 4;
            this.defaultOxeyeDaisies = flowerMultiplier;

            this.defaultInheritMobsBiomeName = "minecraft:flower_forest";
        }
    }

    public static class TaigaMountains extends Taiga
    {
        public TaigaMountains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.clearDefaultBorder();

            this.defaultColor = 0x0A5B4F;
            this.defaultRarity = 10;

            this.defaultInheritMobsBiomeName = "minecraft:taiga_mountains";
        }
    }

    public static class SwampHills extends Swampland
    {
        public SwampHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x28D29F;
            this.defaultRarity = 10;

            this.defaultInheritMobsBiomeName = "minecraft:swamp_hills";
        }
    }

    public static class IceSpikes extends SnowyTundra
    {
        public IceSpikes(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x8CB4B4;
            this.defaultRarity = 10;
            this.defaultTree = null;
            this.defaultDandelions = 0;
            this.defaultGrass = 0;
            this.defaultIceSpikes = true;
            this.defaultRareBuildingType = RareBuildingType.disabled;

            this.defaultInheritMobsBiomeName = "minecraft:ice_spikes";
        }
    }

    public static class ModifiedJungle extends Jungle
    {
        public ModifiedJungle(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x4C7009;
            this.defaultRarity = 10;

            this.defaultInheritMobsBiomeName = "minecraft:modified_jungle";
        }
    }

    public static class ModifiedJungleEdge extends ModifiedJungle
    {
        public ModifiedJungleEdge(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x5A8015;
            this.defaultSizeWhenBorder = 8;
            this.defaultBorder.add(DefaultBiome.MODIFIED_JUNGLE.Name);

            this.defaultInheritMobsBiomeName = "minecraft:modified_jungle_edge";
        }
    }

    public static class TallBirchForest extends BirchForest
    {
        public TallBirchForest(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x4E6E58;
            this.defaultRarity = 10;
            this.defaultTree = new Object[] {10, TreeType.TallBirch, 80};

            this.defaultInheritMobsBiomeName = "minecraft:tall_birch_forest";
        }
    }

    public static class TallBirchHills extends BirchForestHills
    {
        public TallBirchHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x1F502E;
            this.defaultTree = new Object[] {10, TreeType.TallBirch, 80};
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.clear();
            this.defaultIsle.add(DefaultBiome.BIRCH_FOREST_HILLS.Name);

            this.defaultInheritMobsBiomeName = "minecraft:tall_birch_hills";
        }
    }

    public static class DarkForestHills extends DarkForest
    {
        public DarkForestHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x364416;
            this.defaultRarity = 10;

            this.defaultInheritMobsBiomeName = "minecraft:dark_forest_hills";
        }
    }

    public static class SnowyTaigaMountains extends SnowyTaiga
    {
        public SnowyTaigaMountains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x2E5046;
            this.defaultRarity = 10;
            this.defaultRareBuildingType = RareBuildingType.disabled;

            this.defaultInheritMobsBiomeName = "minecraft:snowy_taiga_mountains";
        }
    }

    public static class GiantSpruceTaiga extends GiantTreeTaiga
    {
        public GiantSpruceTaiga(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x818E79;
            this.defaultRarity = 10;
            this.defaultTree = new Object[] {10, TreeType.HugeTaiga2, 8, TreeType.HugeTaiga1, 30, TreeType.Taiga1, 33, TreeType.Taiga2, 100};

            this.defaultInheritMobsBiomeName = "minecraft:giant_spruce_taiga";
        }
    }

    public static class GiantSpruceTaigaHills extends GiantSpruceTaiga
    {
        public GiantSpruceTaigaHills(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x475141;

            this.defaultIsle.add(DefaultBiome.GIANT_SPRUCE_TAIGA.Name);

            this.defaultInheritMobsBiomeName = "minecraft:giant_spruce_taiga_hills";
        }
    }

    public static class ModifiedGravellyMountains extends GravellyMountains
    {
        public ModifiedGravellyMountains(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x466246;
            this.defaultRarity = 10;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[] {DefaultMaterial.GRAVEL, DefaultMaterial.GRAVEL, -1.0,
                DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT, 2.0, DefaultMaterial.GRAVEL, DefaultMaterial.GRAVEL, 10.0};
            // Override IsleInBiome: Extreme Hills of Extreme Hills+
            this.defaultIsle.clear();
            this.defaultIsle.add(DefaultBiome.GRAVELLY_MOUNTAINS.Name);

            this.defaultInheritMobsBiomeName = "minecraft:modified_gravelly_mountains";
        }
    }

    public static class ShatteredSavanna extends Savanna
    {
        public ShatteredSavanna(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x5B8015;
            this.defaultRarity = 10;
            this.defaultGrass = 60;
            this.defaultDoubleGrass = 0;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[] {DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT, -0.5,
                DefaultMaterial.DIRT + ":1", DefaultMaterial.DIRT, 1.75, DefaultMaterial.STONE, DefaultMaterial.STONE, 10};

            this.defaultInheritMobsBiomeName = "minecraft:shattered_savanna";
        }
    }

    public static class ShatteredSavannaPlateau extends ShatteredSavanna
    {
        public ShatteredSavannaPlateau(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0x99905C;
            this.defaultSizeWhenIsle = 6;
            this.defaultRarityWhenIsle = 97;
            this.defaultIsle.add(DefaultBiome.SHATTERED_SAVANNA.Name);

            this.defaultInheritMobsBiomeName = "minecraft:shattered_savanna_plateau";
        }
    }

    public static class ErodedBadlands extends Badlands
    {
        public ErodedBadlands(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xE45627;
            this.defaultRarity = 10;
            this.defaultSurfaceSurfaceAndGroundControl = new Object[] {MesaSurfaceGenerator.NAME_BRYCE};
            this.defaultIsle.add(DefaultBiome.BADLANDS.Name);
            this.defaultSizeWhenIsle = 5;
            this.defaultRarityWhenIsle = 90;

            this.defaultInheritMobsBiomeName = "minecraft:eroded_badlands";
        }
    }

    public static class ModifiedWoodedBadlandsPlateau extends WoodedBadlandsPlateau
    {
        public ModifiedWoodedBadlandsPlateau(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xA68F5F;
            this.defaultRarityWhenIsle = 90;

            this.defaultInheritMobsBiomeName = "minecraft:modified_wooded_badlands_plateau";
        }
    }

    public static class ModifiedBadlandsPlateau extends BadlandsPlateau
    {
        public ModifiedBadlandsPlateau(MojangSettings mojangSettings, int worldHeight)
        {
            super(mojangSettings, worldHeight);
            this.defaultColor = 0xB77F5C;
            this.defaultRarityWhenIsle = 90;

            this.defaultInheritMobsBiomeName = "minecraft:modified_badlands_plateau";
        }
    }
}
