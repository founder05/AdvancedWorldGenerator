package com.marc_val_96.advancedworldgenerator.generator.surface;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.generator.ChunkBuffer;
import com.marc_val_96.advancedworldgenerator.generator.GeneratingChunk;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultipleLayersSurfaceGenerator extends SimpleSurfaceGenerator {

    // Must be sorted based on the noise field
    private List<LayerChoice> layerChoices;

    public MultipleLayersSurfaceGenerator(String[] args) throws InvalidConfigException {
        if (args.length < 2) {
            throw new InvalidConfigException("Needs at least two arguments");
        }

        layerChoices = new ArrayList<LayerChoice>();
        for (int i = 0; i < args.length - 2; i += 3) {
            LocalMaterialData surfaceBlock = AWG.readMaterial(args[i]);
            LocalMaterialData groundBlock = AWG.readMaterial(args[i + 1]);
            float maxNoise = (float) StringHelper.readDouble(args[i + 2], -20, 20);
            layerChoices.add(new LayerChoice(surfaceBlock, groundBlock, maxNoise));
        }
        Collections.sort(layerChoices);
    }

    @Override
    public void spawn(GeneratingChunk generatingChunkInfo, ChunkBuffer chunkBuffer, BiomeConfig config, int xInWorld, int zInWorld) {
        int x = xInWorld & 0xf;
        int z = zInWorld & 0xf;
        double noise = generatingChunkInfo.getNoise(x, z);

        for (LayerChoice layer : this.layerChoices) {
            if (noise <= layer.maxNoise) {
                spawnColumn(layer.surfaceBlock, layer.groundBlock, generatingChunkInfo, chunkBuffer, config, x, z);
                return;
            }
        }

        // Fall back on normal column
        spawnColumn(config.surfaceBlock, config.groundBlock, generatingChunkInfo, chunkBuffer, config, x, z);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LayerChoice groundLayer : this.layerChoices) {
            stringBuilder.append(groundLayer.surfaceBlock);
            stringBuilder.append(',').append(' ');
            stringBuilder.append(groundLayer.groundBlock);
            stringBuilder.append(',').append(' ');
            stringBuilder.append(groundLayer.maxNoise);
            stringBuilder.append(',').append(' ');
        }
        // Delete last ", "
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    public static class LayerChoice implements Comparable<LayerChoice> {
        public final LocalMaterialData surfaceBlock;
        public final LocalMaterialData groundBlock;
        public final float maxNoise;

        public LayerChoice(LocalMaterialData surfaceBlock, LocalMaterialData groundBlock, float maxNoise) {
            this.surfaceBlock = surfaceBlock;
            this.groundBlock = groundBlock;
            this.maxNoise = maxNoise;
        }

        @Override
        public int compareTo(LayerChoice that) {
            float delta = this.maxNoise - that.maxNoise;
            // The number 65565 is just randomly chosen, any positive number
            // works fine as long as it can represent the floating point delta
            // as an integer
            return (int) (delta * 65565);
        }
    }

}
