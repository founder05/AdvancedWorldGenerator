package me.marc_val_96.bukkit.advancedworldgenerator.generator;

import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.generator.biome.OutputType;
import com.marc_val_96.advancedworldgenerator.generator.biome.VanillaBiomeGenerator;
import me.marc_val_96.bukkit.advancedworldgenerator.util.WorldHelper;
import net.minecraft.server.v1_12_R1.BiomeBase;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.WorldChunkManager;


public class BukkitVanillaBiomeGenerator extends VanillaBiomeGenerator {

    private BiomeBase[] biomeGenBaseArray;
    private WorldChunkManager worldChunkManager;

    public BukkitVanillaBiomeGenerator(LocalWorld world) {
        super(world);
    }

    public void setWorldChunkManager(WorldChunkManager worldChunkManager) {
        if (worldChunkManager instanceof AWGWorldChunkManager) {
            // TCWorldChunkManager is unusable, as it just asks the
            // BiomeGenerator for the biomes, creating an infinite loop
            throw new IllegalArgumentException(getClass()
                    + " expects a vanilla WorldChunkManager, "
                    + worldChunkManager.getClass() + " given");
        }
        this.worldChunkManager = worldChunkManager;
    }

    @Override
    public int[] getBiomesUnZoomed(int[] biomeArray, int x, int z, int xSize, int zSize, OutputType outputType) {
        biomeGenBaseArray = worldChunkManager.getBiomes(biomeGenBaseArray, x, z, xSize, zSize);
        if (biomeArray == null || biomeArray.length < xSize * zSize)
            biomeArray = new int[xSize * zSize];
        for (int i = 0; i < xSize * zSize; i++)
            biomeArray[i] = WorldHelper.getSavedId(biomeGenBaseArray[i]);
        return biomeArray;
    }

    @Override
    public int[] getBiomes(int[] biomeArray, int x, int z, int xSize, int z_size, OutputType outputType) {
        biomeGenBaseArray = worldChunkManager.a(biomeGenBaseArray, x, z, xSize, z_size, true);
        if (biomeArray == null || biomeArray.length < xSize * z_size)
            biomeArray = new int[xSize * z_size];
        for (int i = 0; i < xSize * z_size; i++)
            biomeArray[i] = WorldHelper.getSavedId(biomeGenBaseArray[i]);
        return biomeArray;
    }

    @Override
    public int getBiome(int x, int z) {
        return WorldHelper.getSavedId(worldChunkManager.getBiome(new BlockPosition(x, 0, z)));
    }

    @Override
    public void cleanupCache() {
        worldChunkManager.b();
    }

    @Override
    public boolean canGenerateUnZoomed() {
        return true;
    }

}
