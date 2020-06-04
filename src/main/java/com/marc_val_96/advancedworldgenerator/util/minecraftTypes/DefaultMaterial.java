package com.marc_val_96.advancedworldgenerator.util.minecraftTypes;

import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.LocalWorld;

import java.util.Map;
import java.util.TreeMap;

import java.util.Map;
import java.util.TreeMap;

/**
 * Enum of the materials the server will at least support.
 *
 * @see LocalMaterialData
 */
public enum DefaultMaterial
{
    AIR("air", false),
    STONE("stone"),
    GRANITE("granite"),
    POLISHED_GRANITE("polished_granite"),
    DIORITE("diorite"),
    POLISHED_DIORITE("polished_diorite"),
    ANDESITE("andesite"),
    POLISHED_ANDESITE("polished_andesite"),
    GRASS_BLOCK("grass_block"),
    DIRT("dirt"),
    COARSE_DIRT("coarse_dirt"),
    PODZOL("podzol"),
    COBBLESTONE("cobblestone"),
    OAK_PLANKS("oak_planks"),
    SPRUCE_PLANKS("spruce_planks"),
    BIRCH_PLANKS("birch_planks"),
    JUNGLE_PLANKS("jungle_planks"),
    ACACIA_PLANKS("acacia_planks"),
    DARK_OAK_PLANKS("dark_oak_planks"),
    OAK_SAPLING("oak_sapling", false),
    SPRUCE_SAPLING("spruce_sapling", false),
    BIRCH_SAPLING("birch_sapling", false),
    JUNGLE_SAPLING("jungle_sapling", false),
    ACACIA_SAPLING("acacia_sapling", false),
    DARK_OAK_SAPLING("dark_oak_sapling", false),
    BEDROCK("bedrock"),
    WATER("water", false),
    LAVA("lava", false),
    SAND("sand"),
    RED_SAND("red_sand"),
    GRAVEL("gravel"),
    GOLD_ORE("gold_ore"),
    IRON_ORE("iron_ore"),
    COAL_ORE("coal_ore"),
    OAK_LOG("oak_log"),
    SPRUCE_LOG("spruce_log"),
    BIRCH_LOG("birch_log"),
    JUNGLE_LOG("jungle_log"),
    ACACIA_LOG("acacia_log"),
    DARK_OAK_LOG("dark_oak_log"),
    STRIPPED_SPRUCE_LOG("stripped_spruce_log"),
    STRIPPED_BIRCH_LOG("stripped_birch_log"),
    STRIPPED_JUNGLE_LOG("stripped_jungle_log"),
    STRIPPED_ACACIA_LOG("stripped_acacia_log"),
    STRIPPED_DARK_OAK_LOG("stripped_dark_oak_log"),
    STRIPPED_OAK_LOG("stripped_oak_log"),
    OAK_WOOD("oak_wood"),
    SPRUCE_WOOD("spruce_wood"),
    BIRCH_WOOD("birch_wood"),
    JUNGLE_WOOD("jungle_wood"),
    ACACIA_WOOD("acacia_wood"),
    DARK_OAK_WOOD("dark_oak_wood"),
    STRIPPED_OAK_WOOD("stripped_oak_wood"),
    STRIPPED_SPRUCE_WOOD("stripped_spruce_wood"),
    STRIPPED_BIRCH_WOOD("stripped_birch_wood"),
    STRIPPED_JUNGLE_WOOD("stripped_jungle_wood"),
    STRIPPED_ACACIA_WOOD("stripped_acacia_wood"),
    STRIPPED_DARK_OAK_WOOD("stripped_dark_oak_wood"),
    OAK_LEAVES("oak_leaves", false),
    SPRUCE_LEAVES("spruce_leaves", false),
    BIRCH_LEAVES("birch_leaves", false),
    JUNGLE_LEAVES("jungle_leaves", false),
    ACACIA_LEAVES("acacia_leaves", false),
    DARK_OAK_LEAVES("dark_oak_leaves", false),
    SPONGE("sponge"),
    WET_SPONGE("wet_sponge"),
    GLASS("glass"),
    LAPIS_ORE("lapis_ore"),
    LAPIS_BLOCK("lapis_block"),
    DISPENSER("dispenser"),
    SANDSTONE("sandstone"),
    CHISELED_SANDSTONE("chiseled_sandstone"),
    CUT_SANDSTONE("cut_sandstone"),
    NOTE_BLOCK("note_block"),
    WHITE_BED("white_bed", false),
    ORANGE_BED("orange_bed", false),
    MAGENTA_BED("magenta_bed", false),
    LIGHT_BLUE_BED("light_blue_bed", false),
    YELLOW_BED("yellow_bed", false),
    LIME_BED("lime_bed", false),
    PINK_BED("pink_bed", false),
    GRAY_BED("gray_bed", false),
    LIGHT_GRAY_BED("light_gray_bed", false),
    CYAN_BED("cyan_bed", false),
    PURPLE_BED("purple_bed", false),
    BLUE_BED("blue_bed", false),
    BROWN_BED("brown_bed", false),
    GREEN_BED("green_bed", false),
    RED_BED("red_bed", false),
    BLACK_BED("black_bed", false),
    POWERED_RAIL("powered_rail", false),
    DETECTOR_RAIL("detector_rail", false),
    STICKY_PISTON("sticky_piston", false),
    COBWEB("cobweb", false),
    GRASS("grass", false),
    FERN("fern", false),
    DEAD_BUSH("dead_bush", false),
    SEAGRASS("seagrass", false),
    TALL_SEAGRASS("tall_seagrass", false),
    PISTON("piston"),
    PISTON_HEAD("piston_head", false),
    WHITE_WOOL("white_wool"),
    ORANGE_WOOL("orange_wool"),
    MAGENTA_WOOL("magenta_wool"),
    LIGHT_BLUE_WOOL("light_blue_wool"),
    YELLOW_WOOL("yellow_wool"),
    LIME_WOOL("lime_wool"),
    PINK_WOOL("pink_wool"),
    GRAY_WOOL("gray_wool"),
    LIGHT_GRAY_WOOL("light_gray_wool"),
    CYAN_WOOL("cyan_wool"),
    PURPLE_WOOL("purple_wool"),
    BLUE_WOOL("blue_wool"),
    BROWN_WOOL("brown_wool"),
    GREEN_WOOL("green_wool"),
    RED_WOOL("red_wool"),
    BLACK_WOOL("black_wool"),
    MOVING_PISTON("moving_piston", false),
    DANDELION("dandelion", false),
    POPPY("poppy", false),
    BLUE_ORCHID("blue_orchid", false),
    ALLIUM("allium", false),
    AZURE_BLUET("azure_bluet", false),
    RED_TULIP("red_tulip", false),
    ORANGE_TULIP("orange_tulip", false),
    WHITE_TULIP("white_tulip", false),
    PINK_TULIP("pink_tulip", false),
    OXEYE_DAISY("oxeye_daisy", false),
    CORNFLOWER("cornflower", false),
    WITHER_ROSE("wither_rose", false),
    LILY_OF_THE_VALLEY("lily_of_the_valley", false),
    BROWN_MUSHROOM("brown_mushroom", false),
    RED_MUSHROOM("red_mushroom", false),
    GOLD_BLOCK("gold_block"),
    IRON_BLOCK("iron_block"),
    BRICKS("bricks"),
    TNT("tnt"),
    BOOKSHELF("bookshelf"),
    MOSSY_COBBLESTONE("mossy_cobblestone"),
    OBSIDIAN("obsidian"),
    TORCH("torch", false),
    WALL_TORCH("wall_torch", false),
    FIRE("fire", false),
    SPAWNER("spawner"),
    OAK_STAIRS("oak_stairs", false),
    CHEST("chest", false),
    REDSTONE_WIRE("redstone_wire", false),
    DIAMOND_ORE("diamond_ore"),
    DIAMOND_BLOCK("diamond_block"),
    CRAFTING_TABLE("crafting_table"),
    WHEAT("wheat", false),
    FARMLAND("farmland"),
    FURNACE("furnace"),
    OAK_SIGN("oak_sign", false),
    SPRUCE_SIGN("spruce_sign", false),
    BIRCH_SIGN("birch_sign", false),
    ACACIA_SIGN("acacia_sign", false),
    JUNGLE_SIGN("jungle_sign", false),
    DARK_OAK_SIGN("dark_oak_sign", false),
    OAK_DOOR("oak_door", false),
    LADDER("ladder", false),
    RAIL("rail", false),
    COBBLESTONE_STAIRS("cobblestone_stairs"),
    OAK_WALL_SIGN("oak_wall_sign", false),
    SPRUCE_WALL_SIGN("spruce_wall_sign", false),
    BIRCH_WALL_SIGN("birch_wall_sign", false),
    ACACIA_WALL_SIGN("acacia_wall_sign", false),
    JUNGLE_WALL_SIGN("jungle_wall_sign", false),
    DARK_OAK_WALL_SIGN("dark_oak_wall_sign", false),
    LEVER("lever", false),
    STONE_PRESSURE_PLATE("stone_pressure_plate", false),
    IRON_DOOR("iron_door", false),
    OAK_PRESSURE_PLATE("oak_pressure_plate", false),
    SPRUCE_PRESSURE_PLATE("spruce_pressure_plate", false),
    BIRCH_PRESSURE_PLATE("birch_pressure_plate", false),
    JUNGLE_PRESSURE_PLATE("jungle_pressure_plate", false),
    ACACIA_PRESSURE_PLATE("acacia_pressure_plate", false),
    DARK_OAK_PRESSURE_PLATE("dark_oak_pressure_plate", false),
    REDSTONE_ORE("redstone_ore"),
    REDSTONE_TORCH("redstone_torch", false),
    REDSTONE_WALL_TORCH("redstone_wall_torch", false),
    STONE_BUTTON("stone_button", false),
    SNOW("snow", false),
    ICE("ice"),
    SNOW_BLOCK("snow_block"),
    CACTUS("cactus", false),
    CLAY("clay"),
    SUGAR_CANE("sugar_cane", false),
    JUKEBOX("jukebox"),
    OAK_FENCE("oak_fence", false),
    PUMPKIN("pumpkin"),
    NETHERRACK("netherrack"),
    SOUL_SAND("soul_sand"),
    GLOWSTONE("glowstone"),
    NETHER_PORTAL("nether_portal", false),
    CARVED_PUMPKIN("carved_pumpkin"),
    JACK_O_LANTERN("jack_o_lantern"),
    CAKE("cake", false),
    REPEATER("repeater", false),
    WHITE_STAINED_GLASS("white_stained_glass"),
    ORANGE_STAINED_GLASS("orange_stained_glass"),
    MAGENTA_STAINED_GLASS("magenta_stained_glass"),
    LIGHT_BLUE_STAINED_GLASS("light_blue_stained_glass"),
    YELLOW_STAINED_GLASS("yellow_stained_glass"),
    LIME_STAINED_GLASS("lime_stained_glass"),
    PINK_STAINED_GLASS("pink_stained_glass"),
    GRAY_STAINED_GLASS("gray_stained_glass"),
    LIGHT_GRAY_STAINED_GLASS("light_gray_stained_glass"),
    CYAN_STAINED_GLASS("cyan_stained_glass"),
    PURPLE_STAINED_GLASS("purple_stained_glass"),
    BLUE_STAINED_GLASS("blue_stained_glass"),
    BROWN_STAINED_GLASS("brown_stained_glass"),
    GREEN_STAINED_GLASS("green_stained_glass"),
    RED_STAINED_GLASS("red_stained_glass"),
    BLACK_STAINED_GLASS("black_stained_glass"),
    OAK_TRAPDOOR("oak_trapdoor", false),
    SPRUCE_TRAPDOOR("spruce_trapdoor", false),
    BIRCH_TRAPDOOR("birch_trapdoor", false),
    JUNGLE_TRAPDOOR("jungle_trapdoor", false),
    ACACIA_TRAPDOOR("acacia_trapdoor", false),
    DARK_OAK_TRAPDOOR("dark_oak_trapdoor", false),
    STONE_BRICKS("stone_bricks"),
    MOSSY_STONE_BRICKS("mossy_stone_bricks"),
    CRACKED_STONE_BRICKS("cracked_stone_bricks"),
    CHISELED_STONE_BRICKS("chiseled_stone_bricks"),
    INFESTED_STONE("infested_stone"),
    INFESTED_COBBLESTONE("infested_cobblestone"),
    INFESTED_STONE_BRICKS("infested_stone_bricks"),
    INFESTED_MOSSY_STONE_BRICKS("infested_mossy_stone_bricks"),
    INFESTED_CRACKED_STONE_BRICKS("infested_cracked_stone_bricks"),
    INFESTED_CHISELED_STONE_BRICKS("infested_chiseled_stone_bricks"),
    BROWN_MUSHROOM_BLOCK("brown_mushroom_block"),
    RED_MUSHROOM_BLOCK("red_mushroom_block"),
    MUSHROOM_STEM("mushroom_stem"),
    IRON_BARS("iron_bars", false),
    GLASS_PANE("glass_pane", false),
    MELON("melon"),
    ATTACHED_PUMPKIN_STEM("attached_pumpkin_stem", false),
    ATTACHED_MELON_STEM("attached_melon_stem", false),
    PUMPKIN_STEM("pumpkin_stem", false),
    MELON_STEM("melon_stem", false),
    VINE("vine", false),
    OAK_FENCE_GATE("oak_fence_gate", false),
    BRICK_STAIRS("brick_stairs"),
    STONE_BRICK_STAIRS("stone_brick_stairs"),
    MYCELIUM("mycelium"),
    LILY_PAD("lily_pad", false),
    NETHER_BRICKS("nether_bricks"),
    NETHER_BRICK_FENCE("nether_brick_fence", false),
    NETHER_BRICK_STAIRS("nether_brick_stairs"),
    NETHER_WART("nether_wart", false),
    ENCHANTING_TABLE("enchanting_table", false),
    BREWING_STAND("brewing_stand", false),
    CAULDRON("cauldron"),
    END_PORTAL("end_portal", false),
    END_PORTAL_FRAME("end_portal_frame"),
    END_STONE("end_stone"),
    DRAGON_EGG("dragon_egg", false),
    REDSTONE_LAMP("redstone_lamp"),
    COCOA("cocoa", false),
    SANDSTONE_STAIRS("sandstone_stairs"),
    EMERALD_ORE("emerald_ore"),
    ENDER_CHEST("ender_chest", false),
    TRIPWIRE_HOOK("tripwire_hook", false),
    TRIPWIRE("tripwire", false),
    EMERALD_BLOCK("emerald_block"),
    SPRUCE_STAIRS("spruce_stairs", false),
    BIRCH_STAIRS("birch_stairs", false),
    JUNGLE_STAIRS("jungle_stairs", false),
    COMMAND_BLOCK("command_block"),
    BEACON("beacon", false),
    COBBLESTONE_WALL("cobblestone_wall", false),
    MOSSY_COBBLESTONE_WALL("mossy_cobblestone_wall"),
    FLOWER_POT("flower_pot", false),
    POTTED_OAK_SAPLING("potted_oak_sapling", false),
    POTTED_SPRUCE_SAPLING("potted_spruce_sapling", false),
    POTTED_BIRCH_SAPLING("potted_birch_sapling", false),
    POTTED_JUNGLE_SAPLING("potted_jungle_sapling", false),
    POTTED_ACACIA_SAPLING("potted_acacia_sapling", false),
    POTTED_DARK_OAK_SAPLING("potted_dark_oak_sapling", false),
    POTTED_FERN("potted_fern", false),
    POTTED_DANDELION("potted_dandelion", false),
    POTTED_POPPY("potted_poppy", false),
    POTTED_BLUE_ORCHID("potted_blue_orchid", false),
    POTTED_ALLIUM("potted_allium", false),
    POTTED_AZURE_BLUET("potted_azure_bluet", false),
    POTTED_RED_TULIP("potted_red_tulip", false),
    POTTED_ORANGE_TULIP("potted_orange_tulip", false),
    POTTED_WHITE_TULIP("potted_white_tulip", false),
    POTTED_PINK_TULIP("potted_pink_tulip", false),
    POTTED_OXEYE_DAISY("potted_oxeye_daisy", false),
    POTTED_CORNFLOWER("potted_cornflower", false),
    POTTED_LILY_OF_THE_VALLEY("potted_lily_of_the_valley", false),
    POTTED_WITHER_ROSE("potted_wither_rose", false),
    POTTED_RED_MUSHROOM("potted_red_mushroom", false),
    POTTED_BROWN_MUSHROOM("potted_brown_mushroom", false),
    POTTED_DEAD_BUSH("potted_dead_bush", false),
    POTTED_CACTUS("potted_cactus", false),
    CARROTS("carrots", false),
    POTATOES("potatoes", false),
    OAK_BUTTON("oak_button", false),
    SPRUCE_BUTTON("spruce_button", false),
    BIRCH_BUTTON("birch_button", false),
    JUNGLE_BUTTON("jungle_button", false),
    ACACIA_BUTTON("acacia_button", false),
    DARK_OAK_BUTTON("dark_oak_button", false),
    SKELETON_SKULL("skeleton_skull", false),
    SKELETON_WALL_SKULL("skeleton_wall_skull", false),
    WITHER_SKELETON_SKULL("wither_skeleton_skull", false),
    WITHER_SKELETON_WALL_SKULL("wither_skeleton_wall_skull", false),
    ZOMBIE_HEAD("zombie_head", false),
    ZOMBIE_WALL_HEAD("zombie_wall_head", false),
    PLAYER_HEAD("player_head", false),
    PLAYER_WALL_HEAD("player_wall_head", false),
    CREEPER_HEAD("creeper_head", false),
    CREEPER_WALL_HEAD("creeper_wall_head", false),
    DRAGON_HEAD("dragon_head", false),
    DRAGON_WALL_HEAD("dragon_wall_head", false),
    ANVIL("anvil", false),
    CHIPPED_ANVIL("chipped_anvil", false),
    DAMAGED_ANVIL("damaged_anvil", false),
    TRAPPED_CHEST("trapped_chest", false),
    LIGHT_WEIGHTED_PRESSURE_PLATE("light_weighted_pressure_plate", false),
    HEAVY_WEIGHTED_PRESSURE_PLATE("heavy_weighted_pressure_plate", false),
    COMPARATOR("comparator", false),
    DAYLIGHT_DETECTOR("daylight_detector", false),
    REDSTONE_BLOCK("redstone_block"),
    NETHER_QUARTZ_ORE("nether_quartz_ore"),
    HOPPER("hopper", false),
    QUARTZ_BLOCK("quartz_block"),
    CHISELED_QUARTZ_BLOCK("chiseled_quartz_block"),
    QUARTZ_PILLAR("quartz_pillar"),
    QUARTZ_STAIRS("quartz_stairs", false),
    ACTIVATOR_RAIL("activator_rail", false),
    DROPPER("dropper", false),
    WHITE_TERRACOTTA("white_terracotta"),
    ORANGE_TERRACOTTA("orange_terracotta"),
    MAGENTA_TERRACOTTA("magenta_terracotta"),
    LIGHT_BLUE_TERRACOTTA("light_blue_terracotta"),
    YELLOW_TERRACOTTA("yellow_terracotta"),
    LIME_TERRACOTTA("lime_terracotta"),
    PINK_TERRACOTTA("pink_terracotta"),
    GRAY_TERRACOTTA("gray_terracotta"),
    LIGHT_GRAY_TERRACOTTA("light_gray_terracotta"),
    CYAN_TERRACOTTA("cyan_terracotta"),
    PURPLE_TERRACOTTA("purple_terracotta"),
    BLUE_TERRACOTTA("blue_terracotta"),
    BROWN_TERRACOTTA("brown_terracotta"),
    GREEN_TERRACOTTA("green_terracotta"),
    RED_TERRACOTTA("red_terracotta"),
    BLACK_TERRACOTTA("black_terracotta"),
    WHITE_STAINED_GLASS_PANE("white_stained_glass_pane", false),
    ORANGE_STAINED_GLASS_PANE("orange_stained_glass_pane", false),
    MAGENTA_STAINED_GLASS_PANE("magenta_stained_glass_pane", false),
    LIGHT_BLUE_STAINED_GLASS_PANE("light_blue_stained_glass_pane", false),
    YELLOW_STAINED_GLASS_PANE("yellow_stained_glass_pane", false),
    LIME_STAINED_GLASS_PANE("lime_stained_glass_pane", false),
    PINK_STAINED_GLASS_PANE("pink_stained_glass_pane", false),
    GRAY_STAINED_GLASS_PANE("gray_stained_glass_pane", false),
    LIGHT_GRAY_STAINED_GLASS_PANE("light_gray_stained_glass_pane", false),
    CYAN_STAINED_GLASS_PANE("cyan_stained_glass_pane", false),
    PURPLE_STAINED_GLASS_PANE("purple_stained_glass_pane", false),
    BLUE_STAINED_GLASS_PANE("blue_stained_glass_pane", false),
    BROWN_STAINED_GLASS_PANE("brown_stained_glass_pane", false),
    GREEN_STAINED_GLASS_PANE("green_stained_glass_pane", false),
    RED_STAINED_GLASS_PANE("red_stained_glass_pane", false),
    BLACK_STAINED_GLASS_PANE("black_stained_glass_pane", false),
    ACACIA_STAIRS("acacia_stairs"),
    DARK_OAK_STAIRS("dark_oak_stairs"),
    SLIME_BLOCK("slime_block"),
    BARRIER("barrier", false),
    IRON_TRAPDOOR("iron_trapdoor", false),
    PRISMARINE("prismarine"),
    PRISMARINE_BRICKS("prismarine_bricks"),
    DARK_PRISMARINE("dark_prismarine"),
    PRISMARINE_STAIRS("prismarine_stairs"),
    PRISMARINE_BRICK_STAIRS("prismarine_brick_stairs"),
    DARK_PRISMARINE_STAIRS("dark_prismarine_stairs"),
    PRISMARINE_SLAB("prismarine_slab"),
    PRISMARINE_BRICK_SLAB("prismarine_brick_slab"),
    DARK_PRISMARINE_SLAB("dark_prismarine_slab"),
    SEA_LANTERN("sea_lantern"),
    HAY_BLOCK("hay_block"),
    WHITE_CARPET("white_carpet", false),
    ORANGE_CARPET("orange_carpet", false),
    MAGENTA_CARPET("magenta_carpet", false),
    LIGHT_BLUE_CARPET("light_blue_carpet", false),
    YELLOW_CARPET("yellow_carpet", false),
    LIME_CARPET("lime_carpet", false),
    PINK_CARPET("pink_carpet", false),
    GRAY_CARPET("gray_carpet", false),
    LIGHT_GRAY_CARPET("light_gray_carpet", false),
    CYAN_CARPET("cyan_carpet", false),
    PURPLE_CARPET("purple_carpet", false),
    BLUE_CARPET("blue_carpet", false),
    BROWN_CARPET("brown_carpet", false),
    GREEN_CARPET("green_carpet", false),
    RED_CARPET("red_carpet", false),
    BLACK_CARPET("black_carpet", false),
    TERRACOTTA("terracotta"),
    COAL_BLOCK("coal_block"),
    PACKED_ICE("packed_ice"),
    SUNFLOWER("sunflower", false),
    LILAC("lilac", false),
    ROSE_BUSH("rose_bush", false),
    PEONY("peony", false),
    TALL_GRASS("tall_grass", false),
    LARGE_FERN("large_fern", false),
    WHITE_BANNER("white_banner", false),
    ORANGE_BANNER("orange_banner", false),
    MAGENTA_BANNER("magenta_banner", false),
    LIGHT_BLUE_BANNER("light_blue_banner", false),
    YELLOW_BANNER("yellow_banner", false),
    LIME_BANNER("lime_banner", false),
    PINK_BANNER("pink_banner", false),
    GRAY_BANNER("gray_banner", false),
    LIGHT_GRAY_BANNER("light_gray_banner", false),
    CYAN_BANNER("cyan_banner", false),
    PURPLE_BANNER("purple_banner", false),
    BLUE_BANNER("blue_banner", false),
    BROWN_BANNER("brown_banner", false),
    GREEN_BANNER("green_banner", false),
    RED_BANNER("red_banner", false),
    BLACK_BANNER("black_banner", false),
    WHITE_WALL_BANNER("white_wall_banner", false),
    ORANGE_WALL_BANNER("orange_wall_banner", false),
    MAGENTA_WALL_BANNER("magenta_wall_banner", false),
    LIGHT_BLUE_WALL_BANNER("light_blue_wall_banner", false),
    YELLOW_WALL_BANNER("yellow_wall_banner", false),
    LIME_WALL_BANNER("lime_wall_banner", false),
    PINK_WALL_BANNER("pink_wall_banner", false),
    GRAY_WALL_BANNER("gray_wall_banner", false),
    LIGHT_GRAY_WALL_BANNER("light_gray_wall_banner", false),
    CYAN_WALL_BANNER("cyan_wall_banner", false),
    PURPLE_WALL_BANNER("purple_wall_banner", false),
    BLUE_WALL_BANNER("blue_wall_banner", false),
    BROWN_WALL_BANNER("brown_wall_banner", false),
    GREEN_WALL_BANNER("green_wall_banner", false),
    RED_WALL_BANNER("red_wall_banner", false),
    BLACK_WALL_BANNER("black_wall_banner", false),
    RED_SANDSTONE("red_sandstone"),
    CHISELED_RED_SANDSTONE("chiseled_red_sandstone"),
    CUT_RED_SANDSTONE("cut_red_sandstone"),
    RED_SANDSTONE_STAIRS("red_sandstone_stairs"),
    OAK_SLAB("oak_slab"),
    SPRUCE_SLAB("spruce_slab"),
    BIRCH_SLAB("birch_slab"),
    JUNGLE_SLAB("jungle_slab"),
    ACACIA_SLAB("acacia_slab"),
    DARK_OAK_SLAB("dark_oak_slab"),
    STONE_SLAB("stone_slab"),
    SMOOTH_STONE_SLAB("smooth_stone_slab"),
    SANDSTONE_SLAB("sandstone_slab"),
    CUT_SANDSTONE_SLAB("cut_sandstone_slab"),
    PETRIFIED_OAK_SLAB("petrified_oak_slab"),
    COBBLESTONE_SLAB("cobblestone_slab"),
    BRICK_SLAB("brick_slab"),
    STONE_BRICK_SLAB("stone_brick_slab"),
    NETHER_BRICK_SLAB("nether_brick_slab"),
    QUARTZ_SLAB("quartz_slab"),
    RED_SANDSTONE_SLAB("red_sandstone_slab"),
    CUT_RED_SANDSTONE_SLAB("cut_red_sandstone_slab"),
    PURPUR_SLAB("purpur_slab"),
    SMOOTH_STONE("smooth_stone"),
    SMOOTH_SANDSTONE("smooth_sandstone"),
    SMOOTH_QUARTZ("smooth_quartz"),
    SMOOTH_RED_SANDSTONE("smooth_red_sandstone"),
    SPRUCE_FENCE_GATE("spruce_fence_gate", false),
    BIRCH_FENCE_GATE("birch_fence_gate", false),
    JUNGLE_FENCE_GATE("jungle_fence_gate", false),
    ACACIA_FENCE_GATE("acacia_fence_gate", false),
    DARK_OAK_FENCE_GATE("dark_oak_fence_gate", false),
    SPRUCE_FENCE("spruce_fence", false),
    BIRCH_FENCE("birch_fence", false),
    JUNGLE_FENCE("jungle_fence", false),
    ACACIA_FENCE("acacia_fence", false),
    DARK_OAK_FENCE("dark_oak_fence", false),
    SPRUCE_DOOR("spruce_door", false),
    BIRCH_DOOR("birch_door", false),
    JUNGLE_DOOR("jungle_door", false),
    ACACIA_DOOR("acacia_door", false),
    DARK_OAK_DOOR("dark_oak_door", false),
    END_ROD("end_rod", false),
    CHORUS_PLANT("chorus_plant", false),
    CHORUS_FLOWER("chorus_flower", false),
    PURPUR_BLOCK("purpur_block"),
    PURPUR_PILLAR("purpur_pillar"),
    PURPUR_STAIRS("purpur_stairs"),
    END_STONE_BRICKS("end_stone_bricks"),
    BEETROOTS("beetroots", false),
    GRASS_PATH("grass_path", false),
    END_GATEWAY("end_gateway", false),
    REPEATING_COMMAND_BLOCK("repeating_command_block"),
    CHAIN_COMMAND_BLOCK("chain_command_block"),
    FROSTED_ICE("frosted_ice"),
    MAGMA_BLOCK("magma_block"),
    NETHER_WART_BLOCK("nether_wart_block"),
    RED_NETHER_BRICKS("red_nether_bricks"),
    BONE_BLOCK("bone_block"),
    STRUCTURE_VOID("structure_void"),
    OBSERVER("observer"),
    SHULKER_BOX("shulker_box"),
    WHITE_SHULKER_BOX("white_shulker_box", false),
    ORANGE_SHULKER_BOX("orange_shulker_box", false),
    MAGENTA_SHULKER_BOX("magenta_shulker_box", false),
    LIGHT_BLUE_SHULKER_BOX("light_blue_shulker_box", false),
    YELLOW_SHULKER_BOX("yellow_shulker_box", false),
    LIME_SHULKER_BOX("lime_shulker_box", false),
    PINK_SHULKER_BOX("pink_shulker_box", false),
    GRAY_SHULKER_BOX("gray_shulker_box", false),
    LIGHT_GRAY_SHULKER_BOX("light_gray_shulker_box", false),
    CYAN_SHULKER_BOX("cyan_shulker_box", false),
    PURPLE_SHULKER_BOX("purple_shulker_box", false),
    BLUE_SHULKER_BOX("blue_shulker_box", false),
    BROWN_SHULKER_BOX("brown_shulker_box", false),
    GREEN_SHULKER_BOX("green_shulker_box", false),
    RED_SHULKER_BOX("red_shulker_box", false),
    BLACK_SHULKER_BOX("black_shulker_box", false),
    WHITE_GLAZED_TERRACOTTA("white_glazed_terracotta"),
    ORANGE_GLAZED_TERRACOTTA("orange_glazed_terracotta"),
    MAGENTA_GLAZED_TERRACOTTA("magenta_glazed_terracotta"),
    LIGHT_BLUE_GLAZED_TERRACOTTA("light_blue_glazed_terracotta"),
    YELLOW_GLAZED_TERRACOTTA("yellow_glazed_terracotta"),
    LIME_GLAZED_TERRACOTTA("lime_glazed_terracotta"),
    PINK_GLAZED_TERRACOTTA("pink_glazed_terracotta"),
    GRAY_GLAZED_TERRACOTTA("gray_glazed_terracotta"),
    LIGHT_GRAY_GLAZED_TERRACOTTA("light_gray_glazed_terracotta"),
    CYAN_GLAZED_TERRACOTTA("cyan_glazed_terracotta"),
    PURPLE_GLAZED_TERRACOTTA("purple_glazed_terracotta"),
    BLUE_GLAZED_TERRACOTTA("blue_glazed_terracotta"),
    BROWN_GLAZED_TERRACOTTA("brown_glazed_terracotta"),
    GREEN_GLAZED_TERRACOTTA("green_glazed_terracotta"),
    RED_GLAZED_TERRACOTTA("red_glazed_terracotta"),
    BLACK_GLAZED_TERRACOTTA("black_glazed_terracotta"),
    WHITE_CONCRETE("white_concrete"),
    ORANGE_CONCRETE("orange_concrete"),
    MAGENTA_CONCRETE("magenta_concrete"),
    LIGHT_BLUE_CONCRETE("light_blue_concrete"),
    YELLOW_CONCRETE("yellow_concrete"),
    LIME_CONCRETE("lime_concrete"),
    PINK_CONCRETE("pink_concrete"),
    GRAY_CONCRETE("gray_concrete"),
    LIGHT_GRAY_CONCRETE("light_gray_concrete"),
    CYAN_CONCRETE("cyan_concrete"),
    PURPLE_CONCRETE("purple_concrete"),
    BLUE_CONCRETE("blue_concrete"),
    BROWN_CONCRETE("brown_concrete"),
    GREEN_CONCRETE("green_concrete"),
    RED_CONCRETE("red_concrete"),
    BLACK_CONCRETE("black_concrete"),
    WHITE_CONCRETE_POWDER("white_concrete_powder"),
    ORANGE_CONCRETE_POWDER("orange_concrete_powder"),
    MAGENTA_CONCRETE_POWDER("magenta_concrete_powder"),
    LIGHT_BLUE_CONCRETE_POWDER("light_blue_concrete_powder"),
    YELLOW_CONCRETE_POWDER("yellow_concrete_powder"),
    LIME_CONCRETE_POWDER("lime_concrete_powder"),
    PINK_CONCRETE_POWDER("pink_concrete_powder"),
    GRAY_CONCRETE_POWDER("gray_concrete_powder"),
    LIGHT_GRAY_CONCRETE_POWDER("light_gray_concrete_powder"),
    CYAN_CONCRETE_POWDER("cyan_concrete_powder"),
    PURPLE_CONCRETE_POWDER("purple_concrete_powder"),
    BLUE_CONCRETE_POWDER("blue_concrete_powder"),
    BROWN_CONCRETE_POWDER("brown_concrete_powder"),
    GREEN_CONCRETE_POWDER("green_concrete_powder"),
    RED_CONCRETE_POWDER("red_concrete_powder"),
    BLACK_CONCRETE_POWDER("black_concrete_powder"),
    KELP("kelp", false),
    KELP_PLANT("kelp_plant", false),
    DRIED_KELP_BLOCK("dried_kelp_block"),
    TURTLE_EGG("turtle_egg", false),
    DEAD_TUBE_CORAL_BLOCK("dead_tube_coral_block"),
    DEAD_BRAIN_CORAL_BLOCK("dead_brain_coral_block"),
    DEAD_BUBBLE_CORAL_BLOCK("dead_bubble_coral_block"),
    DEAD_FIRE_CORAL_BLOCK("dead_fire_coral_block"),
    DEAD_HORN_CORAL_BLOCK("dead_horn_coral_block"),
    TUBE_CORAL_BLOCK("tube_coral_block"),
    BRAIN_CORAL_BLOCK("brain_coral_block"),
    BUBBLE_CORAL_BLOCK("bubble_coral_block"),
    FIRE_CORAL_BLOCK("fire_coral_block"),
    HORN_CORAL_BLOCK("horn_coral_block"),
    DEAD_TUBE_CORAL("dead_tube_coral", false),
    DEAD_BRAIN_CORAL("dead_brain_coral", false),
    DEAD_BUBBLE_CORAL("dead_bubble_coral", false),
    DEAD_FIRE_CORAL("dead_fire_coral", false),
    DEAD_HORN_CORAL("dead_horn_coral", false),
    TUBE_CORAL("tube_coral", false),
    BRAIN_CORAL("brain_coral", false),
    BUBBLE_CORAL("bubble_coral", false),
    FIRE_CORAL("fire_coral", false),
    HORN_CORAL("horn_coral", false),
    DEAD_TUBE_CORAL_FAN("dead_tube_coral_fan", false),
    DEAD_BRAIN_CORAL_FAN("dead_brain_coral_fan", false),
    DEAD_BUBBLE_CORAL_FAN("dead_bubble_coral_fan", false),
    DEAD_FIRE_CORAL_FAN("dead_fire_coral_fan", false),
    DEAD_HORN_CORAL_FAN("dead_horn_coral_fan", false),
    TUBE_CORAL_FAN("tube_coral_fan", false),
    BRAIN_CORAL_FAN("brain_coral_fan", false),
    BUBBLE_CORAL_FAN("bubble_coral_fan", false),
    FIRE_CORAL_FAN("fire_coral_fan", false),
    HORN_CORAL_FAN("horn_coral_fan", false),
    DEAD_TUBE_CORAL_WALL_FAN("dead_tube_coral_wall_fan", false),
    DEAD_BRAIN_CORAL_WALL_FAN("dead_brain_coral_wall_fan", false),
    DEAD_BUBBLE_CORAL_WALL_FAN("dead_bubble_coral_wall_fan", false),
    DEAD_FIRE_CORAL_WALL_FAN("dead_fire_coral_wall_fan", false),
    DEAD_HORN_CORAL_WALL_FAN("dead_horn_coral_wall_fan", false),
    TUBE_CORAL_WALL_FAN("tube_coral_wall_fan", false),
    BRAIN_CORAL_WALL_FAN("brain_coral_wall_fan", false),
    BUBBLE_CORAL_WALL_FAN("bubble_coral_wall_fan", false),
    FIRE_CORAL_WALL_FAN("fire_coral_wall_fan", false),
    HORN_CORAL_WALL_FAN("horn_coral_wall_fan", false),
    SEA_PICKLE("sea_pickle", false),
    BLUE_ICE("blue_ice"),
    CONDUIT("conduit", false),
    BAMBOO_SAPLING("bamboo_sapling", false),
    BAMBOO("bamboo", false),
    POTTED_BAMBOO("potted_bamboo", false),
    VOID_AIR("void_air", false),
    CAVE_AIR("cave_air", false),
    BUBBLE_COLUMN("bubble_column", false),
    POLISHED_GRANITE_STAIRS("polished_granite_stairs"),
    SMOOTH_RED_SANDSTONE_STAIRS("smooth_red_sandstone_stairs"),
    MOSSY_STONE_BRICK_STAIRS("mossy_stone_brick_stairs"),
    POLISHED_DIORITE_STAIRS("polished_diorite_stairs"),
    MOSSY_COBBLESTONE_STAIRS("mossy_cobblestone_stairs"),
    END_STONE_BRICK_STAIRS("end_stone_brick_stairs"),
    STONE_STAIRS("stone_stairs"),
    SMOOTH_SANDSTONE_STAIRS("smooth_sandstone_stairs"),
    SMOOTH_QUARTZ_STAIRS("smooth_quartz_stairs"),
    GRANITE_STAIRS("granite_stairs"),
    ANDESITE_STAIRS("andesite_stairs"),
    RED_NETHER_BRICK_STAIRS("red_nether_brick_stairs"),
    POLISHED_ANDESITE_STAIRS("polished_andesite_stairs"),
    DIORITE_STAIRS("diorite_stairs"),
    POLISHED_GRANITE_SLAB("polished_granite_slab"),
    SMOOTH_RED_SANDSTONE_SLAB("smooth_red_sandstone_slab"),
    MOSSY_STONE_BRICK_SLAB("mossy_stone_brick_slab"),
    POLISHED_DIORITE_SLAB("polished_diorite_slab"),
    MOSSY_COBBLESTONE_SLAB("mossy_cobblestone_slab"),
    END_STONE_BRICK_SLAB("end_stone_brick_slab"),
    SMOOTH_SANDSTONE_SLAB("smooth_sandstone_slab"),
    SMOOTH_QUARTZ_SLAB("smooth_quartz_slab"),
    GRANITE_SLAB("granite_slab"),
    ANDESITE_SLAB("andesite_slab"),
    RED_NETHER_BRICK_SLAB("red_nether_brick_slab"),
    POLISHED_ANDESITE_SLAB("polished_andesite_slab"),
    DIORITE_SLAB("diorite_slab"),
    BRICK_WALL("brick_wall", false),
    PRISMARINE_WALL("prismarine_wall", false),
    RED_SANDSTONE_WALL("red_sandstone_wall", false),
    MOSSY_STONE_BRICK_WALL("mossy_stone_brick_wall", false),
    GRANITE_WALL("granite_wall", false),
    STONE_BRICK_WALL("stone_brick_wall", false),
    NETHER_BRICK_WALL("nether_brick_wall", false),
    ANDESITE_WALL("andesite_wall", false),
    RED_NETHER_BRICK_WALL("red_nether_brick_wall", false),
    SANDSTONE_WALL("sandstone_wall", false),
    END_STONE_BRICK_WALL("end_stone_brick_wall", false),
    DIORITE_WALL("diorite_wall", false),
    SCAFFOLDING("scaffolding", false),
    LOOM("loom"),
    BARREL("barrel"),
    SMOKER("smoker"),
    BLAST_FURNACE("blast_furnace"),
    CARTOGRAPHY_TABLE("cartography_table"),
    FLETCHING_TABLE("fletching_table"),
    GRINDSTONE("grindstone", false),
    LECTERN("lectern", false),
    SMITHING_TABLE("smithing_table"),
    STONECUTTER("stonecutter", false),
    BELL("bell", false),
    LANTERN("lantern", false),
    CAMPFIRE("campfire", false),
    SWEET_BERRY_BUSH("sweet_berry_bush", false),
    STRUCTURE_BLOCK("structure_block"),
    JIGSAW("jigsaw"),
    COMPOSTER("composter", false),
    BEE_NEST("bee_nest"),
    BEEHIVE("beehive"),
    HONEY_BLOCK("honey_block"),
    HONEYCOMB_BLOCK("honeycomb_block"),
    UNKNOWN_BLOCK("unknown_block");

    /**
     * The ID of the material
     */
    public final String resourceLocation;
    /**
     * Whether or not a material is solid. If set to false, it will prevent
     * snowfall. Note: this isn't always equal to what Minecraft calls solid.
     */
    private final boolean solid;

    /**
     * Creates a new material.
     *
     * @param resourceLocation Id of the material.
     * @param solid Whether the material is solid. If set to false, it will
     *            prevent snowfall. Note: this isn't always equal to what
     *            Minecraft calls solid.
     */
    private DefaultMaterial(String resourceLocation, boolean solid)
    {
        this.resourceLocation = "minecraft:" + resourceLocation;
        this.solid = solid;
    }

    /**
     * Creates a new solid material where snow will fall on.
     *
     * @param resourceLocation Id of the material.
     */
    private DefaultMaterial(String resourceLocation)
    {
        this.resourceLocation = "minecraft:" + resourceLocation;
        this.solid = true;
    }

    public boolean isAir()
    {
        return this == AIR;
    }

    /**
     * Returns true only if this material is flowing or stationary Water
     *
     * @return boolean whether or not this material is flowing or stationary
     *         Water
     */
    public boolean isLiquid()
    {
        return this == WATER || this == LAVA;
    }

    /**
     * Gets whether this material is solid. Materials that aren't solid are
     * nonexistant for {@link LocalWorld#getSolidHeight(int, int)}. Note: this
     * isn't always equal to what Minecraft calls solid.
     *
     * @return boolean Whether or not the material is considered solid
     */
    public boolean isSolid()
    {
        return this.solid;
    }

    public boolean isLog()
    {
        return
            this == OAK_LOG ||
                this == SPRUCE_LOG ||
                this == BIRCH_LOG ||
                this == JUNGLE_LOG ||
                this == ACACIA_LOG ||
                this == DARK_OAK_LOG ||
                this == STRIPPED_SPRUCE_LOG ||
                this == STRIPPED_BIRCH_LOG ||
                this == STRIPPED_JUNGLE_LOG ||
                this == STRIPPED_ACACIA_LOG ||
                this == STRIPPED_DARK_OAK_LOG ||
                this == STRIPPED_OAK_LOG;
    }

    public boolean isLeaves()
    {
        return
            this == OAK_LEAVES ||
                this == SPRUCE_LEAVES ||
                this == BIRCH_LEAVES ||
                this == JUNGLE_LEAVES ||
                this == ACACIA_LEAVES ||
                this == DARK_OAK_LEAVES;
    }

    public boolean isSapling()
    {
        return
            this == OAK_SAPLING ||
                this == SPRUCE_SAPLING ||
                this == BIRCH_SAPLING ||
                this == JUNGLE_SAPLING ||
                this == ACACIA_SAPLING ||
                this == DARK_OAK_SAPLING ||
                this == BAMBOO_SAPLING;
    }

    /**
     * Gets whether snow can fall on this block.
     *
     * @return Whether snow can fall on this block.
     */
    public boolean canSnowFallOn()
    {
        // Exceptions for nonsolid leaves
        // IF we get much more exceptions, we may want to make this a
        // parameter in the constructor instead.
        return this == OAK_LEAVES || this == SPRUCE_LEAVES || this == BIRCH_LEAVES || this == JUNGLE_LEAVES || this == ACACIA_LEAVES || this == DARK_OAK_LEAVES || this.solid;
    }

    /**
     * A DefaultMaterial lookup table with the material name as the index
     */
    private static Map<String, DefaultMaterial> lookupName;

    static
    {
        lookupName = new TreeMap<String, DefaultMaterial>(String.CASE_INSENSITIVE_ORDER);

        for (DefaultMaterial material : DefaultMaterial.values())
        {
            lookupName.put(material.resourceLocation, material);
        }
    }

    /**
     * Returns a DefaultMaterial object with the given material name. Name is
     * case insensitive.
     *
     * @param blockName the Name of the DefaultMaterial that is to be
     *            returned. May not contain block data.
     * @return A DefaultMaterial with the given name, or UNKNOWN_BLOCK if not
     *         found.
     */
    public static DefaultMaterial getMaterial(String blockName)
    {
        if(blockName == null)
        {
            return UNKNOWN_BLOCK;
        }
        DefaultMaterial defaultMaterial = lookupName.get(blockName);
        return defaultMaterial;
    }

    /**
     * Returns true or false depending on if this DefaultMaterial has the
     * given name
     * <p/>
     *
     * @return Boolean whether or not this DefaultMaterial has the given name
     */
    public static boolean contains(String resourceLocation)
    {
        if(resourceLocation == null)
        {
            return false;
        }
        return lookupName.containsKey(resourceLocation);
    }
}
