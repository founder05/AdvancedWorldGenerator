package com.marc_val_96.advancedworldgenerator.generator.resource;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.ConfigFunction;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObject;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObjectCoordinate;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObjectStructure;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//TODO Rewrite This Class
public abstract class CustomStructureGen extends Resource {
    private final List<CustomObject> objects;
    private final List<Double> objectChances;
    private final List<String> objectNames;

    public CustomStructureGen(BiomeConfig biomeConfig, List<String> args) throws InvalidConfigException {
        super(biomeConfig);
        objects = new ArrayList<CustomObject>();
        objectNames = new ArrayList<String>();
        objectChances = new ArrayList<Double>();
        for (int i = 0; i < args.size() - 1; i += 2) {
            CustomObject object = getHolder().worldConfig.worldObjects.parseCustomObject(args.get(i));
            if (object == null || !object.canSpawnAsObject()) {
                throw new InvalidConfigException("No custom object found with the name " + args.get(i));
            }
            object.getBranches(
                Rotation.NORTH);//throw new InvalidConfigException("The object " + args.get(i) + " isn't a structure: it has no branches");
            objects.add(object);
            objectNames.add(args.get(i));
            objectChances.add(readRarity(args.get(i + 1)));
        }

        // Inject ourselves in the BiomeConfig
        if (getHolder().structureGen != null) {
            //throw new InvalidConfigException("There can only be one CustomStructure resource in each BiomeConfig");
        }
        getHolder().structureGen = this;
    }

    @Override
    public void spawn(LocalWorld world, Random random, boolean villageInChunk, int x, int z) {
        // Left blank, as spawnInChunk(..) already handles this.
    }

    @Override
    protected void spawnInChunk(LocalWorld world, Random random, boolean villageInChunk, ChunkCoordinate chunkCoord) {
        // Find all structures that reach this chunk, and spawn them
        int searchRadius = world.getConfigs().getWorldConfig().maximumCustomStructureRadius;

        int currentChunkX = chunkCoord.getChunkX();
        int currentChunkZ = chunkCoord.getChunkZ();
        for (int searchChunkX = currentChunkX - searchRadius; searchChunkX < currentChunkX + searchRadius; searchChunkX++) {
            for (int searchChunkZ = currentChunkZ - searchRadius; searchChunkZ < currentChunkZ + searchRadius; searchChunkZ++) {

                }
            }
        }


    @Override
    public String toString() {
        if (objects.isEmpty()) {
            return "CustomStructure()";
        }
        String output = "CustomStructure(" + objectNames.get(0) + "," + objectChances.get(0);
        for (int i = 1; i < objectNames.size(); i++) {
            output += "," + objectNames.get(i) + "," + objectChances.get(i);
        }
        return output + ")";
    }

    public CustomObjectCoordinate getRandomObjectCoordinate(Random random, int chunkX, int chunkZ) {
        if (objects.isEmpty()) {
            return null;
        }
        for (int objectNumber = 0; objectNumber < objects.size(); objectNumber++) {
            if (random.nextDouble() * 100.0 < objectChances.get(objectNumber)) {
                return objects.get(objectNumber).makeCustomObjectCoordinate(random, chunkX, chunkZ);
            }
        }
        return null;
    }

    @Override
    public boolean isAnalogousTo(ConfigFunction<BiomeConfig> other) {
        if (getClass() == other.getClass()) {
            try {
                CustomStructureGen otherO = (CustomStructureGen) other;
                return otherO.objectNames.size() == this.objectNames.size() && otherO.objectNames.containsAll(this.objectNames);
            } catch (Exception ex) {
                AWG.log(LogMarker.WARN, ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + super.hashCode();
        hash = 61 * hash + (this.objects != null ? this.objects.hashCode() : 0);
        hash = 61 * hash + (this.objectChances != null ? this.objectChances.hashCode() : 0);
        hash = 61 * hash + (this.objectNames != null ? this.objectNames.hashCode() : 0);
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
        final CustomStructureGen compare = (CustomStructureGen) other;
        return (this.objects == null ? this.objects == compare.objects
                : this.objects.equals(compare.objects))
                && (this.objectChances == null ? this.objectChances == compare.objectChances
                : this.objectChances.equals(compare.objectChances))
                && (this.objectNames == null ? this.objectNames == compare.objectNames
                : this.objectNames.equals(compare.objectNames));
    }

    @Override
    public int getPriority() {
        return -41;
    }

}
