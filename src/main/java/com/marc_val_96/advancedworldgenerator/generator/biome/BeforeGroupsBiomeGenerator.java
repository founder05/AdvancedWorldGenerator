package com.marc_val_96.advancedworldgenerator.generator.biome;

import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.generator.biome.layers.Layer;
import com.marc_val_96.advancedworldgenerator.generator.biome.layers.LayerFactory;

/**
 * This is the normal biome mode, which has all of Terrain Control's features.
 */
public class BeforeGroupsBiomeGenerator extends LayeredBiomeGenerator {

    public BeforeGroupsBiomeGenerator(LocalWorld world) {
        super(world);
    }

    @Override
    protected Layer[] initLayers() {
        return LayerFactory.createBeforeGroups(world);
    }

}
