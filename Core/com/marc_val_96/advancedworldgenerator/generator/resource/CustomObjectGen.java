package com.marc_val_96.advancedworldgenerator.generator.resource;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.ConfigFunction;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObject;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomObjectGen extends Resource {
    private List<CustomObject> objects;
    private List<String> objectNames;

    public CustomObjectGen(BiomeConfig biomeConfig, List<String> args) throws InvalidConfigException {
        super(biomeConfig);
        if (args.isEmpty() || (args.size() == 1 && args.get(0).trim().isEmpty())) {
            // Backwards compatibility
            args = new ArrayList<String>();
            args.add("UseWorld");
        }
        objects = new ArrayList<CustomObject>();
        objectNames = new ArrayList<String>();
        for (String arg : args) {
            CustomObject object = getHolder().worldConfig.worldObjects.parseCustomObject(arg);
            if (object == null || !object.canSpawnAsObject()) {
                throw new InvalidConfigException("No custom object found with the name " + arg);
            }
            objects.add(object);
            objectNames.add(arg);
        }
    }

    @Override
    public void spawn(LocalWorld world, Random random, boolean villageInChunk, int x, int z) {
        // Left blank, as process(..) already handles this.
    }

    @Override
    protected void spawnInChunk(LocalWorld world, Random random, boolean villageInChunk, ChunkCoordinate chunkCoord) {
        for (CustomObject object : objects) {
            object.process(world, random, chunkCoord);
        }
    }

    @Override
    public String toString() {
        return "CustomObject(" + StringHelper.join(objectNames, ",") + ")";
    }

    @Override
    public boolean isAnalogousTo(ConfigFunction<BiomeConfig> other) {
        if (getClass() == other.getClass()) {
            try {
                CustomObjectGen otherO = (CustomObjectGen) other;
                return otherO.objectNames.size() == this.objectNames.size() && otherO.objectNames.containsAll(this.objectNames);
            } catch (Exception ex) {
                AWG.log(LogMarker.WARN, ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + super.hashCode();
        hash = 41 * hash + (this.objects != null ? this.objects.hashCode() : 0);
        hash = 41 * hash + (this.objectNames != null ? this.objectNames.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other))
            return false;
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (getClass() != other.getClass())
            return false;
        final CustomObjectGen compare = (CustomObjectGen) other;
        return (this.objects == null ? this.objects == compare.objects
                : this.objects.equals(compare.objects))
                && (this.objectNames == null ? this.objectNames == compare.objectNames
                : this.objectNames.equals(compare.objectNames));
    }

    @Override
    public int getPriority() {
        return -40;
    }

}
