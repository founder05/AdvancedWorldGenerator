package com.marc_val_96.advancedworldgenerator.generator.biome;

import com.marc_val_96.advancedworldgenerator.LocalWorld;


public abstract class BiomeGenerator {
    protected final LocalWorld world;

    public BiomeGenerator(LocalWorld world) {
        this.world = world;
    }

    /**
     * Calculates the biome ids array used in terrain generation.
     *
     * @param biomeArray Cache will fill this array if is large enough and not
     *                   null. Otherwise, it will create a fresh array.
     * @param x          X start in blocks.
     * @param z          Y start in blocks.
     * @param xSize      X size in blocks.
     * @param zSize      Y size in blocks.
     * @param type       The output type.
     * @return The array. The biome generator is not allowed to do anything
     * with the array after the method has finished executing; the caller is
     * free to reuse the array for other purposes. This means that the biome
     * generator is not allowed to return internal arrays, or to store this
     * array.
     */
    public int[] getBiomesUnZoomed(int[] biomeArray, int x, int z, int xSize, int zSize, OutputType type) {
        // Fall back on getBiomes
        // When overriding this method to actually generate unzoomed biomes,
        // make sure to also override canGenerateUnZoomed so that it returns
        // true.
        return getBiomes(biomeArray, x, z, xSize, zSize, type);
    }

    /**
     * Gets the detailed biomes. Each column in the world is represented by
     * one position in the array.
     *
     * @param biomeArray Cache will fill this array if is large enough and not
     *                   null. Otherwise, it will create a fresh array.
     * @param x          X start in blocks.
     * @param z          Y start in blocks.
     * @param xSize      X size in blocks.
     * @param zSize      Y size in blocks.
     * @param type       The output type.
     * @return The array. The biome generator is not allowed to do anything
     * with the array after the method has finished executing; the caller is
     * free to reuse the array for other purposes. This means that the biome
     * generator is not allowed to return internal arrays, or to store this
     * array.
     */
    public abstract int[] getBiomes(int[] biomeArray, int x, int z, int xSize, int zSize, OutputType type);

    /**
     * Gets the biome of a single column. Only available for cached biome
     * generators, as the method would be way too slow otherwise.
     *
     * @param blockX X coord of the column.
     * @param blockZ Z coord of the column.
     * @return The biome id.
     * @throws UnsupportedOperationException If {@link #isCached()} == false.
     */
    public int getBiome(int blockX, int blockZ) throws UnsupportedOperationException {
        if (isCached()) {
            // Implementation has a bug
            throw new AssertionError("isCached() == true, but getBiome is not overridden");
        } else {
            throw new UnsupportedOperationException("isCached() == false, so no single biome lookups");
        }
    }

    /**
     * Cleans up the cache.
     *
     * @throws UnsupportedOperationException If {@link #isCached()} == false.
     */
    public void cleanupCache() throws UnsupportedOperationException {
        if (isCached()) {
            // Implementation has a bug
            throw new AssertionError("isCached() == true, but cleanupCache is not overridden");
        } else {
            throw new UnsupportedOperationException("isCached() == false, so no cache to cleanup");
        }
    }

    public boolean canGenerateUnZoomed() {
        return false;
    }

    /**
     * Gets whether this biome generator is cached. Cached biome generators
     * have an implementation for {@link #getBiome(int, int)} and {@link #cleanupCache()}.
     *
     * @return True if this biome generator is cached, false otherwise.
     */
    public boolean isCached() {
        return false;
    }

    /**
     * Biome generators can be wrapped, for example to cache another biome
     * generator. This method returns the unwrapped biome generator. If this
     * biome generator doesn't wrap another biome generator, it returns itself.
     *
     * @return The unwrapped biome generator.
     */
    public BiomeGenerator unwrap() {
        return this;
    }

}