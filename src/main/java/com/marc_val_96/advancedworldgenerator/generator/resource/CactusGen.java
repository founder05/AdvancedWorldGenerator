package com.marc_val_96.advancedworldgenerator.generator.resource;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.standard.PluginStandardValues;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.MaterialSet;
import com.marc_val_96.advancedworldgenerator.util.helpers.RandomHelper;

import java.util.List;
import java.util.Random;

import java.util.List;
import java.util.Random;

public abstract class CactusGen extends Resource
{
    private final int minAltitude;
    private final int maxAltitude;
    private final MaterialSet sourceBlocks;

    public CactusGen(BiomeConfig biomeConfig, List<String> args) throws InvalidConfigException
    {
        super(biomeConfig);
        assureSize(6, args);

        material = readMaterial(args.get(0));
        frequency = readInt(args.get(1), 1, 100);
        rarity = readRarity(args.get(2));
        minAltitude = readInt(args.get(3), PluginStandardValues.WORLD_DEPTH,
            PluginStandardValues.WORLD_HEIGHT - 1);
        maxAltitude = readInt(args.get(4), minAltitude,
            PluginStandardValues.WORLD_HEIGHT - 1);
        sourceBlocks = readMaterials(args, 5);
    }


    public void spawn(LocalWorld world, Random rand, boolean villageInChunk, int x, int z, ChunkCoordinate chunkBeingPopulated)
    {
        // Make sure we stay within population bounds, anything outside won't be spawned (unless it's in an existing chunk).

        int y = RandomHelper.numberInRange(rand, minAltitude, maxAltitude);

        parseMaterials(world, material, sourceBlocks);
        LocalMaterialData worldMaterial;
        int cactusX;
        int cactusBaseY;
        int cactusZ;

        for (int i = 0; i < 10; i++)
        {
            cactusX = x + rand.nextInt(8) - rand.nextInt(8);
            cactusBaseY = y + rand.nextInt(4) - rand.nextInt(4);
            cactusZ = z + rand.nextInt(8) - rand.nextInt(8);

            worldMaterial = world.getMaterial(cactusX, cactusBaseY, cactusZ,  chunkBeingPopulated);
            if(worldMaterial == null || !worldMaterial.isAir())
            {
                continue;
            }

            // Check foundation
            worldMaterial = world.getMaterial(cactusX, cactusBaseY - 1, cactusZ, chunkBeingPopulated);
            if (worldMaterial == null || !sourceBlocks.contains(worldMaterial))
            {
                continue;
            }

            // Check neighbors
            worldMaterial = world.getMaterial(cactusX - 1, cactusBaseY, cactusZ, chunkBeingPopulated);
            if (worldMaterial == null || !worldMaterial.isAir())
            {
                continue;
            }

            worldMaterial = world.getMaterial(cactusX + 1, cactusBaseY, cactusZ, chunkBeingPopulated);
            if (worldMaterial == null || !worldMaterial.isAir())
            {
                continue;
            }

            worldMaterial = world.getMaterial(cactusX, cactusBaseY, cactusZ - 1, chunkBeingPopulated);
            if (worldMaterial == null || !worldMaterial.isAir())
            {
                continue;
            }

            worldMaterial = world.getMaterial(cactusX, cactusBaseY, cactusZ + 1, chunkBeingPopulated);
            if (worldMaterial == null || !worldMaterial.isAir())
            {
                continue;
            }

            // Spawn cactus
            int cactusHeight = 1 + rand.nextInt(rand.nextInt(3) + 1);
            for (int dY = 0; dY < cactusHeight; dY++)
            {
                world.setBlock(cactusX, cactusBaseY + dY, cactusZ, material, null, chunkBeingPopulated);
            }
        }
    }

    protected abstract void parseMaterials(LocalWorld world, LocalMaterialData material, MaterialSet sourceBlocks);

    @Override
    public String toString()
    {
        return "Cactus(" + material + "," + frequency + "," + rarity + "," + minAltitude + "," + maxAltitude + makeMaterials(sourceBlocks) + ")";
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + super.hashCode();
        hash = 79 * hash + this.minAltitude;
        hash = 79 * hash + this.maxAltitude;
        hash = 79 * hash + (this.sourceBlocks != null ? this.sourceBlocks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object other)
    {
        if (!super.equals(other))
            return false;
        if (other == this)
            return true;
        if (getClass() != other.getClass())
            return false;
        final CactusGen compare = (CactusGen) other;
        return this.minAltitude == compare.minAltitude
            && this.maxAltitude == compare.maxAltitude
            && (this.sourceBlocks == null ? this.sourceBlocks == compare.sourceBlocks
            : this.sourceBlocks.equals(compare.sourceBlocks));
    }

    @Override
    public int getPriority()
    {
        return -35;
    }
}
