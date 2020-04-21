package com.marc_val_96.advancedworldgenerator.customobjects;

import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;

import java.util.Random;

public class UseWorldAll extends UseWorld {
    @Override
    public String getName() {
        return "UseWorldAll";
    }

    @Override
    public boolean process(LocalWorld world, Random rand, ChunkCoordinate chunkCoord) {
        boolean spawnedAtLeastOneObject = false;

        for (CustomObject selectedObject : world.getConfigs().getCustomObjects()) {
            if (!selectedObject.hasPreferenceToSpawnIn(world.getBiome(chunkCoord.getBlockXCenter(), chunkCoord.getBlockZCenter())))
                continue;

            // Process the object
            if (selectedObject.process(world, rand, chunkCoord)) {
                spawnedAtLeastOneObject = true;
            }
        }
        return spawnedAtLeastOneObject;
    }
}
