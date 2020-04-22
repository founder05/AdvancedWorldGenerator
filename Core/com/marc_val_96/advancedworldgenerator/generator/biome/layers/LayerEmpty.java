package com.marc_val_96.advancedworldgenerator.generator.biome.layers;

import com.marc_val_96.advancedworldgenerator.generator.biome.ArraysCache;

public class LayerEmpty extends Layer {

    public LayerEmpty(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(ArraysCache cache, int x, int z, int xSize, int zSize) {
        int[] thisInts = cache.getArray(xSize * zSize);
        for (int i = 0; i < thisInts.length; i++)
            thisInts[i] = 0;
        return thisInts;
    }

}
