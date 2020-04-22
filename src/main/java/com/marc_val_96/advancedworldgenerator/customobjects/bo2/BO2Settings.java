package com.marc_val_96.advancedworldgenerator.customobjects.bo2;

import com.marc_val_96.advancedworldgenerator.configuration.settingType.Setting;
import com.marc_val_96.advancedworldgenerator.configuration.settingType.Settings;
import com.marc_val_96.advancedworldgenerator.util.MaterialSet;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;

import java.util.List;

import static com.marc_val_96.advancedworldgenerator.AWG.WORLD_DEPTH;
import static com.marc_val_96.advancedworldgenerator.AWG.WORLD_HEIGHT;

public class BO2Settings extends Settings {
    public static final Setting<Boolean>
            SPAWN_SUNLIGHT = booleanSetting("spawnSunlight", true),
            SPAWN_DARKNESS = booleanSetting("spawnDarkness", true),
            SPAWN_WATER = booleanSetting("spawnWater", false),
            SPAWN_LAVA = booleanSetting("spawnLava", false),
            SPAWN_ABOVE_GROUND = booleanSetting("spawnAboveGround", false),
            SPAWN_UNDER_GROUND = booleanSetting("spawnUnderGround", false),
            UNDER_FILL = booleanSetting("underFill", true),
            RANDON_ROTATION = booleanSetting("randomRotation", true),
            DIG = booleanSetting("dig", false),
            TREE = booleanSetting("tree", false),
            BRANCH = booleanSetting("branch", false),
            DIGGING_BRANCH = booleanSetting("diggingBranch", false),
            NEEDS_FOUNDATION = booleanSetting("needsFoundation", true);

    public static final Setting<Double> COLLISION_PERCENTAGE = doubleSetting("collisionPercentage", 2, 0, 100);

    public static final Setting<Integer>
            RARITY = intSetting("rarity", 100, 1, 1000000),
            SPAWN_ELEVATION_MIN = intSetting("spawnElevationMin", 0, WORLD_DEPTH, WORLD_HEIGHT),
            SPAWN_ELEVATION_MAX = intSetting("spawnElevationMax", 128, WORLD_DEPTH, WORLD_HEIGHT),
            GROUP_FREQUENCY_MIN = intSetting("groupFrequencyMin", 1, 1, 100),
            GROUP_FREQUENCY_MAX = intSetting("groupFrequencyMax", 5, 1, 100),
            GROUP_SEPERATION_MIN = intSetting("groupSeparationMin", 0, 0, 100),
            GROUP_SEPERATION_MAX = intSetting("groupSeparationMax", 5, 0, 100),
            BRANCH_LIMIT = intSetting("branchLimit", 6, 0, 100);

    public static final Setting<MaterialSet>
            SPAWN_ON_BLOCK_TYPE = materialSetSetting("spawnOnBlockType", DefaultMaterial.GRASS),
            COLLISTION_BLOCK_TYPE = materialSetSetting("collisionBlockType", "All");

    public static final Setting<String> VERSION = stringSetting("version", "2.0");

    public static final Setting<List<String>>
            GROUP_ID = stringListSetting("groupId"),
            SPAWN_IN_BIOME = stringListSetting("spawnInBiome", "All");
}
