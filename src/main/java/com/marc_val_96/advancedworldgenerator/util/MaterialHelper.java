package com.marc_val_96.advancedworldgenerator.util;

import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;
import com.marc_val_96.advancedworldgenerator.AWG;

public class MaterialHelper
{
    private static final FifoMap<String, LocalMaterialData> CachedMaterials = new FifoMap<String, LocalMaterialData>(4096);

    public static LocalMaterialData readMaterial(String name) throws InvalidConfigException
    {
        if(name == null)
        {
            return null;
        }
        // TODO: Make sure it won't cause problems to return the same material object multiple times, is it not changed anywhere?
        LocalMaterialData material = CachedMaterials.get(name);
        if(material != null)
        {
            return material;
        }
        else if(CachedMaterials.containsKey(name))
        {
            throw new InvalidConfigException("Cannot read block: " + name);
        }

        String originalName = name;

        // Spigot interprets snow as SNOW_LAYER and that's how TC has always seen it too so keep it that way (even though minecraft:snow is actually a snow block).
        switch (name.toLowerCase()) {
            case "snow":
                name = "SNOW_LAYER";
                break;
            // Spigot interprets water as FLOWING_WATER and that's how TC has always seen it too so keep it that way (even though minecraft:water is actually stationary water).
            case "water":
                name = "FLOWING_WATER";
                break;
            // Spigot interprets lava as FLOWING_LAVA and that's how TC has always seen it too so keep it that way (even though minecraft:lava is actually stationary lava).
            case "lava":
                name = "FLOWING_LAVA";
                break;
        }

        try
        {
            material = AWG.getEngine().readMaterial(name);
        }
        catch(InvalidConfigException ex)
        {
            //TODO this shouldn't happen anymore
        }

        CachedMaterials.put(originalName, material);

        return material;
    }


    public static LocalMaterialData toLocalMaterialData(DefaultMaterial defaultMaterial)
    {
        return AWG.getEngine().toLocalMaterialData(defaultMaterial);
    }
}
