package com.marc_val_96.advancedworldgenerator.generator;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalBiome;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.ConfigFunction;
import com.marc_val_96.advancedworldgenerator.configuration.ConfigProvider;
import com.marc_val_96.advancedworldgenerator.configuration.WorldConfig;
import com.marc_val_96.advancedworldgenerator.generator.noise.NoiseGeneratorNewOctaves;
import com.marc_val_96.advancedworldgenerator.generator.resource.Resource;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;

import java.util.Random;

public class ObjectSpawner {

    private final ConfigProvider configProvider;
    private final Random rand;
    private final LocalWorld world;

    public ObjectSpawner(ConfigProvider configProvider, LocalWorld localWorld) {
        this.configProvider = configProvider;
        this.rand = new Random();
        this.world = localWorld;
        new NoiseGeneratorNewOctaves(new Random(world.getSeed()), 4);
    }

    public void populate(ChunkCoordinate chunkCoord) {
        // Get the corner block coords
        int x = chunkCoord.getChunkX() * 16;
        int z = chunkCoord.getChunkZ() * 16;

        // Get the biome of the other corner
        LocalBiome biome = world.getBiome(x + 15, z + 15);

        // Null check
        if (biome == null) {
            AWG.log(LogMarker.DEBUG, "Unknown biome at {},{}  (chunk {}). Population failed.", x + 15, z + 15, chunkCoord);
            return;
        }

        BiomeConfig biomeConfig = biome.getBiomeConfig();

        // Get the random generator
        WorldConfig worldConfig = configProvider.getWorldConfig();
        long resourcesSeed = worldConfig.resourcesSeed != 0L ? worldConfig.resourcesSeed : world.getSeed();
        this.rand.setSeed(resourcesSeed);
        long l1 = this.rand.nextLong() / 2L * 2L + 1L;
        long l2 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkCoord.getChunkX() * l1 + chunkCoord.getChunkZ() * l2 ^ resourcesSeed);

        // Generate structures
        boolean hasVillage = world.placeDefaultStructures(rand, chunkCoord);

        // Mark population started
        world.startPopulation(chunkCoord);
        AWG.firePopulationStartEvent(world, rand, hasVillage,
                chunkCoord);

        // Resource sequence
        for (ConfigFunction<BiomeConfig> res : biomeConfig.resourceSequence) {
            if (res instanceof Resource)
                ((Resource) res).process(world, rand, hasVillage, chunkCoord);
        }

        // Animals
        world.placePopulationMobs(biome, rand, chunkCoord);

        // Snow and ice
        new FrozenSurfaceHelper(world).freezeChunk(chunkCoord);

        // Replace blocks
        world.replaceBlocks(chunkCoord);

        // Mark population ended
        AWG.firePopulationEndEvent(world, rand, hasVillage, chunkCoord);
        world.endPopulation();
    }

}