package com.marc_val_96.advancedworldgenerator.customobjects.bo3;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.configuration.settingType.Setting;
import com.marc_val_96.advancedworldgenerator.configuration.settingType.Settings;
import com.marc_val_96.advancedworldgenerator.customobjects.StructurePartSpawnHeight;
import com.marc_val_96.advancedworldgenerator.util.MaterialSet;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;

import java.util.List;

import static com.marc_val_96.advancedworldgenerator.AWG.WORLD_DEPTH;
import static com.marc_val_96.advancedworldgenerator.AWG.WORLD_HEIGHT;

public class BO3Settings extends Settings {
    public static final Setting<Boolean>
            TREE = booleanSetting("Tree", true),
            ROTATE_RANDOMLY = booleanSetting("RotateRandomly", false);

    public static final Setting<Double> RARITY = doubleSetting("Rarity", 100, 0.000001, 100);

    public static final Setting<Integer>
            FREQUENCY = intSetting("Frequency", 1, 1, 200),
            MIN_HEIGHT = intSetting("MinHeight", 0, WORLD_DEPTH, WORLD_HEIGHT),
            MAX_HEIGHT = intSetting("MaxHeight", 256, WORLD_DEPTH, WORLD_HEIGHT),
            MAX_BRANCH_DEPTH = intSetting("MaxBranchDepth", 10, 1, 100),
            MAX_PERCENTAGE_OUTSIDE_SOURCE_BLOCK = intSetting("MaxPercentageOutsideSourceBlock", 100, 0, 100),
            SPAWN_HEIGHT_OFFSET = intSetting("SpawnHeightOffset", 0, -255, 255),
            SPAWN_HEIGHT_VARIANCE = intSetting("SpawnHeightVariance", 0, -255, 255);

    public static final Setting<String>
            AUTHOR = stringSetting("Author", "Unknown"),
            DESCRIPTION = stringSetting("Description", "No description given"),
            VERSION = stringSetting("Version", "3");

    public static final Setting<List<String>> EXCLUDED_BIOMES = stringListSetting("ExcludedBiomes", "All");

    public static final Setting<MaterialSet>
            SOURCE_BLOCKS = materialSetSetting("SourceBlocks", DefaultMaterial.AIR),
            EXTRUDE_THROUGH_BLOCKS = materialSetSetting("ExtrudeThroughBlocks", DefaultMaterial.AIR);

    // Enum settings
    public static final Setting<OutsideSourceBlock> OUTSIDE_SOURCE_BLOCK = enumSetting("OutsideSourceBlock", OutsideSourceBlock.placeAnyway);
    public static final Setting<SpawnHeightEnum> SPAWN_HEIGHT = enumSetting("SpawnHeight", SpawnHeightEnum.highestBlock);
    public static final Setting<ExtrudeMode> EXTRUDE_MODE = enumSetting("ExtrudeMode", ExtrudeMode.None);

    // The spawn height
    public enum SpawnHeightEnum {
        randomY(StructurePartSpawnHeight.PROVIDED),
        atMinY(StructurePartSpawnHeight.PROVIDED),
        highestBlock(StructurePartSpawnHeight.HIGHEST_BLOCK),
        highestSolidBlock(StructurePartSpawnHeight.HIGHEST_SOLID_BLOCK);

        private StructurePartSpawnHeight height;

        SpawnHeightEnum(StructurePartSpawnHeight height) {
            this.height = height;
        }

        public StructurePartSpawnHeight toStructurePartSpawnHeight() {
            return height;
        }
    }

    // How an object should be extended to a surface
    public enum ExtrudeMode {
        None(-1, -1),
        BottomDown(AWG.WORLD_HEIGHT, AWG.WORLD_DEPTH),
        TopUp(AWG.WORLD_DEPTH, AWG.WORLD_HEIGHT);

        /**
         * Defines where calculation should begin
         */
        private int startingHeight = 0;

        /**
         * Defines where calculation should end
         */
        private int endingHeight = 0;

        ExtrudeMode(int heightStart, int heightEnd) {
            this.startingHeight = heightStart;
            this.endingHeight = heightEnd;
        }

        public int getStartingHeight() {
            return startingHeight;
        }

        public int getEndingHeight() {
            return endingHeight;
        }
    }

    // What to do when outside the source block
    public enum OutsideSourceBlock {
        dontPlace,
        placeAnyway
    }

}
