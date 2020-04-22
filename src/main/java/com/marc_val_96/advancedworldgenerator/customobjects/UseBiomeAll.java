package com.marc_val_96.advancedworldgenerator.customobjects;

import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;

import java.util.Random;

public class UseBiomeAll extends UseBiome {
    @Override
    public String getName() {
        return "UseBiomeAll";
    }

    @Override
    public boolean process(LocalWorld world, Random random, ChunkCoordinate chunkCoord) {
        boolean spawnedAtLeastOneObject = false;

        for (CustomObject object : getPossibleObjectsAt(world, chunkCoord.getBlockXCenter(), chunkCoord.getBlockZCenter())) {
            if (object.process(world, random, chunkCoord)) {
                spawnedAtLeastOneObject = true;
            }
        }

        return spawnedAtLeastOneObject;
    }
}
