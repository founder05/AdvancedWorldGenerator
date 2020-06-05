package com.marc_val_96.advancedworldgenerator.generator.resource;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.standard.PluginStandardValues;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.MaterialSet;

import java.util.List;
import java.util.Random;

import java.util.List;
import java.util.Random;

public abstract class BoulderGen extends Resource
{
    private final MaterialSet sourceBlocks;
    private final int minAltitude;
    private final int maxAltitude;

    public BoulderGen(BiomeConfig config, List<String> args) throws InvalidConfigException
    {
        super(config);
        assureSize(6, args);

        material = readMaterial(args.get(0));
        frequency = readInt(args.get(1), 1, 5000);
        rarity = readRarity(args.get(2));
        minAltitude = readInt(args.get(3), PluginStandardValues.WORLD_DEPTH,
            PluginStandardValues.WORLD_HEIGHT - 1);
        maxAltitude = readInt(args.get(4), minAltitude,
            PluginStandardValues.WORLD_HEIGHT - 1);
        sourceBlocks = readMaterials(args, 5);
    }

    
    public void spawn(LocalWorld world, Random random, boolean villageInChunk, int x, int z, ChunkCoordinate chunkBeingPopulated)
    {
        // Make sure we stay within population bounds, anything outside won't be spawned (unless it's in an existing chunk).

        int y = world.getHighestBlockAboveYAt(x, z, chunkBeingPopulated);
        if (y < this.minAltitude || y > this.maxAltitude) {
            return;
        }

        parseMaterials(world, material, sourceBlocks);
        LocalMaterialData material;

        while (y > 3)
        {
            material = world.getMaterial(x, y - 1, z, chunkBeingPopulated);
            if (sourceBlocks.contains(material)) {
                break;
            }
            y--;
        }
        if (y <= 3)
        {
            return;
        }

        int i = 0;
        int j = 0;
        while (j < 3)
        {
            int k = i + random.nextInt(2);
            int m = i + random.nextInt(2);
            int n = i + random.nextInt(2);
            float f1 = (k + m + n) * 0.333F + 0.5F;
            for (int i1 = x - k; i1 <= x + k; i1++)
            {
                for (int i2 = z - n; i2 <= z + n; i2++)
                {
                    for (int i3 = y - m; i3 <= y + m; i3++)
                    {
                        float f2 = i1 - x;
                        float f3 = i2 - z;
                        float f4 = i3 - y;
                        if (f2 * f2 + f3 * f3 + f4 * f4 <= f1 * f1)
                        {
                            world.setBlock(i1, i3, i2, this.material, null, chunkBeingPopulated);
                        }
                    }
                }
            }
            x += random.nextInt(2 + i * 2) - 1 - i;
            z += random.nextInt(2 + i * 2) - 1 - i;
            y -= random.nextInt(2);
            j++;
        }
    }

    protected abstract void parseMaterials(LocalWorld world, LocalMaterialData material, MaterialSet sourceBlocks);

    @Override
    public String toString()
    {
        return "Boulder(" + material + "," + frequency + "," + rarity + "," + minAltitude + "," + maxAltitude + makeMaterials(sourceBlocks) + ")";
    }

    @Override
    public int getPriority()
    {
        return -22;
    }
}
