package com.marc_val_96.advancedworldgenerator.customobjects;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalBiome;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.io.SettingsMap;
import com.marc_val_96.advancedworldgenerator.configuration.settingType.Setting;
import com.marc_val_96.advancedworldgenerator.configuration.settingType.Settings;
import com.marc_val_96.advancedworldgenerator.generator.SpawnableObject;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.Rotation;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.TreeType;

import java.util.Map;
import java.util.Random;

/**
 * A Minecraft tree, viewed as a custom object.
 *
 * <p>For historical reasons, TreeObject implements {@link CustomObject} instead
 * of just {@link SpawnableObject}. We can probably refactor the Tree resource
 * to accept {@link SpawnableObject}s instead of {@link CustomObject}s, so that
 * all the extra methods are no longer needed.
 */
public class TreeObject extends SimpleObject {
    private TreeType type;
    private int minHeight = AWG.WORLD_DEPTH;
    private int maxHeight = AWG.WORLD_HEIGHT;

    public TreeObject(TreeType type) {
        this.type = type;
    }

    public TreeObject(TreeType type, SettingsMap settings) {
        this.type = type;
        this.minHeight = settings.getSetting(TreeSettings.MIN_HEIGHT, TreeSettings.MIN_HEIGHT.getDefaultValue());
        this.maxHeight = settings.getSetting(TreeSettings.MAX_HEIGHT, TreeSettings.MAX_HEIGHT.getDefaultValue());
    }

    @Override
    public void onEnable(Map<String, CustomObject> otherObjectsInDirectory) {
        // Stub method
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public boolean canSpawnAsTree() {
        return true;
    }

    @Override
    public boolean canSpawnAsObject() {
        return false;
    }

    @Override
    public boolean spawnForced(LocalWorld world, Random random, Rotation rotation, int x, int y, int z) {
        return world.placeTree(type, random, x, y, z);
    }

    @Override
    public boolean process(LocalWorld world, Random random, ChunkCoordinate chunkCoord) {
        // A tree has no frequency or rarity, so spawn it once in the chunk
        int x = chunkCoord.getBlockXCenter() + random.nextInt(ChunkCoordinate.CHUNK_X_SIZE);
        int z = chunkCoord.getBlockZCenter() + random.nextInt(ChunkCoordinate.CHUNK_Z_SIZE);
        int y = world.getHighestBlockYAt(x, z);
        if (canSpawnAt(world, Rotation.NORTH, x, y, z)) {
            return spawnForced(world, random, Rotation.NORTH, x, y, z);
        }
        return false;
    }

    @Override
    public CustomObject applySettings(SettingsMap settings) {
        return new TreeObject(type, settings);
    }

    @Override
    public boolean hasPreferenceToSpawnIn(LocalBiome biome) {
        return true;
    }

    @Override
    public boolean canSpawnAt(LocalWorld world, Rotation rotation, int x, int y, int z) {
        return y >= minHeight && y <= maxHeight;
    }

    private static class TreeSettings extends Settings {
        static final Setting<Integer> MIN_HEIGHT = intSetting("MinHeight",
                AWG.WORLD_DEPTH, AWG.WORLD_DEPTH, AWG.WORLD_HEIGHT);
        static final Setting<Integer> MAX_HEIGHT = intSetting("MaxHeight",
                AWG.WORLD_HEIGHT, AWG.WORLD_DEPTH, AWG.WORLD_HEIGHT);
    }

}
