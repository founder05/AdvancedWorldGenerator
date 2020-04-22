package me.marc_val_96.bukkit.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.BiomeIds;
import com.marc_val_96.advancedworldgenerator.LocalBiome;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import me.marc_val_96.bukkit.advancedworldgenerator.util.WorldHelper;
import net.minecraft.server.v1_12_R1.BiomeBase;
import net.minecraft.server.v1_12_R1.BlockPosition;


public class BukkitBiome implements LocalBiome {
    private final BiomeBase biomeBase;
    private final boolean isCustom;

    private final BiomeIds biomeIds;
    private final BiomeConfig biomeConfig;

    protected BukkitBiome(BiomeConfig biomeConfig, BiomeBase biome) {
        this.biomeBase = biome;
        int savedBiomeId = BiomeBase.a(biomeBase);
        this.biomeIds = new BiomeIds(WorldHelper.getGenerationId(biomeBase), savedBiomeId);
        this.biomeConfig = biomeConfig;
        this.isCustom = biome instanceof AWGBiomeBase;
    }

    /**
     * Wraps the vanilla biome into a LocalBiome instance.
     *
     * @param biomeConfig Configuration file of the biome.
     * @param biome       The vanilla biome to wrap.
     * @return The wrapped biome.
     */
    public static BukkitBiome forVanillaBiome(BiomeConfig biomeConfig, BiomeBase biome) {
        return new BukkitBiome(biomeConfig, biome);
    }

    /**
     * Creates and registers a new custom biome with the config and ids.
     *
     * @param biomeConfig Config of the custom biome.
     * @param biomeIds    Ids of the custom biome.
     * @return The custom biome.
     */
    public static BukkitBiome forCustomBiome(BiomeConfig biomeConfig, BiomeIds biomeIds) {
        return new BukkitBiome(biomeConfig, AWGBiomeBase.createInstance(biomeConfig, biomeIds));
    }

    @Override
    public boolean isCustom() {
        return this.isCustom;
    }

    public BiomeBase getHandle() {
        return biomeBase;
    }

    @Override
    public String getName() {
        return this.biomeConfig.getName();
    }

    @Override
    public BiomeIds getIds() {
        return this.biomeIds;
    }

    @Override
    public float getTemperatureAt(int x, int y, int z) {
        return this.biomeBase.a(new BlockPosition(x, y, z));
    }

    @Override
    public BiomeConfig getBiomeConfig() {
        return this.biomeConfig;
    }

    @Override
    public String toString() {
        return getName();
    }
}