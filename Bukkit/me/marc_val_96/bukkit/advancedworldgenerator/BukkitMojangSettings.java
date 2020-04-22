package me.marc_val_96.bukkit.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.configuration.WeightedMobSpawnGroup;
import com.marc_val_96.advancedworldgenerator.configuration.standard.MojangSettings;
import me.marc_val_96.bukkit.advancedworldgenerator.util.MobSpawnGroupHelper;
import net.minecraft.server.v1_12_R1.BiomeBase;

import java.util.List;


public final class BukkitMojangSettings implements MojangSettings {
    private final BiomeBase biomeBase;

    private BukkitMojangSettings(BiomeBase biomeBase) {
        this.biomeBase = biomeBase;
    }

    /**
     * Creates an instance that provides access to the default settings of the
     * vanilla biome with the given id.
     *
     * @param biomeId The id of the biome.
     * @return The settings.
     */
    public static MojangSettings fromId(int biomeId) {
        return fromBiomeBase(BiomeBase.getBiome(biomeId));
    }

    /**
     * Creates an instance that provides access to the default settings of the
     * vanilla biome.
     *
     * @param biomeBase The biome.
     * @return The settings.
     */
    public static MojangSettings fromBiomeBase(BiomeBase biomeBase) {
        return new BukkitMojangSettings(biomeBase);
    }

    @Override
    public float getTemperature() {
        return biomeBase.getTemperature();
    }

    @Override
    public float getWetness() {
        return biomeBase.getHumidity();
    }

    @Override
    public float getSurfaceHeight() {
        return biomeBase.j();
    }

    @Override
    public float getSurfaceVolatility() {
        return biomeBase.m();
    }

    @Override
    public LocalMaterialData getSurfaceBlock() {
        return BukkitMaterialData.ofMinecraftBlockData(biomeBase.q);
    }

    @Override
    public LocalMaterialData getGroundBlock() {
        return BukkitMaterialData.ofMinecraftBlockData(biomeBase.r);
    }

    @Override
    public List<WeightedMobSpawnGroup> getMobSpawnGroup(EntityCategory mobType) {
        return MobSpawnGroupHelper.getListFromMinecraftBiome(biomeBase, mobType);
    }

}
