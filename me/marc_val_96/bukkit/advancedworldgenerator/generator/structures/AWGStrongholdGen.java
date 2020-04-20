package me.marc_val_96.bukkit.advancedworldgenerator.generator.structures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.marc_val_96.advancedworldgenerator.LocalBiome;
import com.marc_val_96.advancedworldgenerator.configuration.ServerConfigProvider;
import com.marc_val_96.advancedworldgenerator.util.helpers.ReflectionHelper;
import me.marc_val_96.bukkit.advancedworldgenerator.BukkitBiome;
import net.minecraft.server.v1_12_R1.BiomeBase;
import net.minecraft.server.v1_12_R1.WorldGenStronghold;

import java.util.List;

public class AWGStrongholdGen extends WorldGenStronghold {

    public AWGStrongholdGen(ServerConfigProvider configs) {
        super(ImmutableMap.of(
                "distance", String.valueOf(configs.getWorldConfig().strongholdDistance),
                "count", String.valueOf(configs.getWorldConfig().strongholdCount),
                "spread", String.valueOf(configs.getWorldConfig().strongholdSpread)));

        // Modify in which biomes the stronghold is allowed to spawn
        List<BiomeBase> allowedBiomes = Lists.newArrayList();

        for (LocalBiome biome : configs.getBiomeArray()) {
            if (biome == null)
                continue;

            if (biome.getBiomeConfig().strongholdsEnabled) {
                allowedBiomes.add(((BukkitBiome) biome).getHandle());
            }
        }

        ReflectionHelper.setValueInFieldOfType(this, List.class, allowedBiomes);
    }

}
