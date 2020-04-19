package me.marc_val_96.bukkit.advancedworldgenerator.generator;

import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.generator.ChunkBuffer;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import me.marc_val_96.bukkit.advancedworldgenerator.BukkitMaterialData;
import org.bukkit.generator.ChunkGenerator.ChunkData;

final class BukkitChunkBuffer implements ChunkBuffer {

    private final ChunkCoordinate chunkCoord;
    private final ChunkData chunkData;

    /**
     * Creates a new {@code BukkitChunkBuffer}.
     *
     * @param chunkCoord The coordinate of the chunk that will be generated.
     * @param chunkData  Object to place the chunk data in.
     */
    BukkitChunkBuffer(ChunkCoordinate chunkCoord, ChunkData chunkData) {
        this.chunkCoord = chunkCoord;
        this.chunkData = chunkData;
    }

    @Override
    public LocalMaterialData getBlock(int blockX, int blockY, int blockZ) {
        @SuppressWarnings("deprecation")
        int blockId = chunkData.getTypeId(blockX, blockY, blockZ);
        @SuppressWarnings("deprecation")
        byte blockData = chunkData.getData(blockX, blockY, blockZ);
        return BukkitMaterialData.ofIds(blockId, blockData);
    }

    @Override
    public ChunkCoordinate getChunkCoordinate() {
        return chunkCoord;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setBlock(int blockX, int blockY, int blockZ, LocalMaterialData material) {
        chunkData.setBlock(blockX, blockY, blockZ, material.getBlockId(), material.getBlockData());
    }

}
