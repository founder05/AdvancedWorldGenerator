package me.marc_val_96.bukkit.advancedworldgenerator.generator;


import com.marc_val_96.advancedworldgenerator.generator.ObjectSpawner;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import me.marc_val_96.bukkit.advancedworldgenerator.BukkitWorld;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class AWGBlockPopulator extends BlockPopulator {
    private ObjectSpawner spawner;

    public AWGBlockPopulator(BukkitWorld world) {
        this.spawner = new ObjectSpawner(world.getConfigs(), world);
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        this.spawner.populate(ChunkCoordinate.fromChunkCoords(chunk.getX(), chunk.getZ()));
    }
}
