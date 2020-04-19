package com.marc_val_96.advancedworldgenerator.generator.biome;

import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.generator.biome.layers.Layer;
import com.marc_val_96.advancedworldgenerator.generator.biome.layers.LayerFactory;

/**
 * Generates biomes from the image specified by the WorldConfig.
 */
public class FromImageBiomeGenerator extends LayeredBiomeGenerator {
    public FromImageBiomeGenerator(LocalWorld world) {
        super(world);
    }

    @Override
    protected Layer[] initLayers() {
        return LayerFactory.createFromImage(world);
    }
}
