package me.marc_val_96.bukkit.advancedworldgenerator.util;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGBiomeBase;
import net.minecraft.server.v1_12_R1.BiomeBase;

public abstract class WorldHelper {

    private WorldHelper() {
    }

    public static LocalWorld toLocalWorld(net.minecraft.server.v1_12_R1.World world) {
        return AWG.getWorld(world.getWorld().getName());
    }


    public static LocalWorld toLocalWorld(org.bukkit.World world) {
        return AWG.getWorld(world.getName());
    }


    public static int getGenerationId(BiomeBase biomeBase) {
        if (biomeBase instanceof AWGBiomeBase) {
            return ((AWGBiomeBase) biomeBase).generationId;
        }
        return BiomeBase.a(biomeBase);
    }

    /**
     * Gets the saved id of the given biome.
     *
     * @param biomeBase The biome.
     * @return The id.
     */
    public static int getSavedId(BiomeBase biomeBase) {
        return BiomeBase.a(biomeBase);
    }
}
