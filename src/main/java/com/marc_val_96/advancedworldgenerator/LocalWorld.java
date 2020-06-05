package com.marc_val_96.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeLoadInstruction;
import com.marc_val_96.advancedworldgenerator.configuration.ConfigProvider;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObjectStructureCache;
import com.marc_val_96.advancedworldgenerator.customobjects.bo3.EntityFunction;
import com.marc_val_96.advancedworldgenerator.exception.BiomeNotFoundException;
import com.marc_val_96.advancedworldgenerator.generator.ChunkBuffer;
import com.marc_val_96.advancedworldgenerator.generator.ObjectSpawner;
import com.marc_val_96.advancedworldgenerator.generator.SpawnableObject;
import com.marc_val_96.advancedworldgenerator.generator.biome.BiomeGenerator;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.NamedBinaryTag;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.MobNames;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.TreeType;

import java.util.Collection;
import java.util.Random;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public abstract class LocalWorld
{
    public abstract String getName();

    public abstract String getWorldSettingsName();

    public abstract int getDimensionId();

    public abstract long getSeed();

    public abstract File getWorldSaveDir();

    public abstract ConfigProvider getConfigs();

    public abstract ObjectSpawner getObjectSpawner();

    public abstract CustomStructureCache getStructureCache();

    public abstract WorldSession getWorldSession();

    public abstract void deleteWorldSessionData();

    /**
     * Gets the height the base terrain of the world is capped at. Resources
     * ignore this limit.
     *
     * @return The height the base terrain of the world is capped at.
     */
    public abstract int getHeightCap();

    /**
     * Returns the vertical scale of the world. 128 blocks is the normal
     * scale, 256 doubles the scale, 64 halves the scale, etc. Only powers of
     * two will be returned.
     *
     * @return The vertical scale of the world.
     */
    public abstract int getHeightScale();


    // Biomes

    /**
     * Gets the biome generator.
     * @return The biome generator.
     */
    public abstract BiomeGenerator getBiomeGenerator();

    /**
     * Creates a LocalBiome instance for the given biome.
     * @param biomeConfig The settings for the biome, which are saved in
     * the LocalBiome instance.
     * @return The LocalBiome instance.
     */
    public abstract LocalBiome createBiomeFor(BiomeConfig biomeConfig, int otgBiomeId, ConfigProvider configProvider, boolean isReload);

    public abstract ArrayList<LocalBiome> getAllBiomes();

    public abstract LocalBiome getBiomeByOTGIdOrNull(int id);

    public abstract LocalBiome getFirstBiomeOrNull();

    public abstract LocalBiome getBiomeByNameOrNull(String name);

    /**
     * Calculates the biome at the given coordinates. This is usually taken
     * from the biome generator, but this can be changed using the
     * configuration files. In that case it is read from the chunk data.
     *
     * @param x The block x.
     * @param z The block z.
     * @return The biome at the given coordinates.
     * @throws BiomeNotFoundException If the biome id is invalid.
     */
    public abstract LocalBiome getBiome(int x, int z) throws BiomeNotFoundException;

    // Default generators

    public abstract void prepareDefaultStructures(int chunkX, int chunkZ, boolean dry);

    public abstract boolean placeDungeon(Random rand, int x, int y, int z);

    public abstract boolean placeFossil(Random rand, ChunkCoordinate chunkCoord);

    public abstract boolean placeTree(TreeType type, Random rand, int x, int y, int z);

    public abstract boolean placeDefaultStructures(Random rand, ChunkCoordinate chunkCoord);

    /**
     * Gets a structure part in Mojang's structure format.
     * @param name Full name of the structure.
     * @return The structure, or null if it does not exist.
     */
    public abstract SpawnableObject getMojangStructurePart(String name);

    public abstract boolean chunkHasDefaultStructure(Random rand, ChunkCoordinate chunk);

    // Mobs / entities

    /**
     * Since Minecraft Beta 1.8, friendly mobs are mainly spawned during the
     * terrain generation. Calling this method will place the mobs.
     * @param biome      Biome to place the mobs of.
     * @param random     Random number generator.
     * @param chunkCoord The chunk to spawn the mobs in.
     */
    public abstract void placePopulationMobs(LocalBiome biome, Random random, ChunkCoordinate chunkCoord);

    public abstract void spawnEntity(EntityFunction<?> entityData, ChunkCoordinate chunkBeingPopulated);

    // Population start and end

    /**
     * Marks the given chunks as being populated. No new chunks may be created. Implementations may cache the chunk.
     * @param chunkCoord The chunk being populated.
     * @throws IllegalStateException If another chunks is being populated. Call {@link #endPopulation()} first.
     * @see #endPopulation()
     */
    public abstract void startPopulation(ChunkCoordinate chunkCoord);

    /**
     * Stops the population step. New chunks may be created again. Implementations may cache the chunk.
     * @throws IllegalStateException If no chunk was being populated.
     * @see #startPopulation(ChunkCoordinate)
     */
    public abstract void endPopulation();

    // Blocks

    public abstract LocalMaterialData getMaterial(int x, int y, int z, ChunkCoordinate chunkBeingPopulated);

    public abstract int getBlockAboveLiquidHeight(int x, int z, ChunkCoordinate chunkBeingPopulated);

    public abstract int getBlockAboveSolidHeight(int x, int z, ChunkCoordinate chunkBeingPopulated);

    public abstract int getHighestBlockAboveYAt(int x, int z, ChunkCoordinate chunkBeingPopulated);

    public abstract int getHighestBlockYAt(int x, int z, boolean findSolid, boolean findLiquid, boolean ignoreLiquid, boolean ignoreSnow, boolean ignoreLeaves, ChunkCoordinate chunkBeingPopulated);

    public abstract int getHeightMapHeight(int x, int z, ChunkCoordinate chunkBeingPopulated);

    public abstract int getLightLevel(int x, int y, int z, ChunkCoordinate chunkBeingPopulated);

    public abstract void setBlock(int x, int y, int z, LocalMaterialData material, NamedBinaryTag metaDataTag, ChunkCoordinate chunkBeingPopulated);

    public abstract LocalMaterialData[] getBlockColumnInUnloadedChunk(int x, int z);

    /**
     * Executes ReplacedBlocks.
     *
     * <p>
     * During terrain population, four chunks are guaranteed to be loaded:
     * (chunkX, chunkZ), (chunkX + 1, chunkZ), (chunkX, chunkZ + 1) and
     * (chunkX + 1, chunkZ + 1). All populators use an 8-block offset from the
     * top left chunk, and populate an area of 16x16 blocks from there. This
     * allows them to extend 8 blocks from their population area without
     * hitting potentially unloaded chunks.
     *
     * <p>
     * Populators may place blocks in already populated chunks, which would
     * cause those blocks to be never replaced. ReplacedBlocks uses the same
     * 8-block offset to minimize this risk.
     * @see ChunkCoordinate#getPopulatingChunk(int, int) Explanation about the
     * population offset.
     * @param chunkCoord The top left chunk for ReplacedBlocks.
     */
    public abstract void replaceBlocks(ChunkCoordinate chunkCoord);

    // Chunks

    public abstract boolean isInsidePregeneratedRegion(ChunkCoordinate chunk);

    public abstract ChunkCoordinate getSpawnChunk();

    public abstract boolean generateModdedCaveGen(int x, int z, ChunkBuffer chunkBuffer);

    public abstract boolean isInsideWorldBorder(ChunkCoordinate chunkCoordinate);

    public abstract LocalMaterialData getMaterial(int x, int y, int z);
}

