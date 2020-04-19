package com.marc_val_96.advancedworldgenerator.configuration;

import com.marc_val_96.advancedworldgenerator.LocalBiome;

import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public final class ConfigToNetworkSender {

    public static void send(ConfigProvider configProvider, DataOutput stream) throws IOException {
        WorldConfig worldConfig = configProvider.getWorldConfig();
        LocalBiome[] biomes = configProvider.getBiomeArray();

        // General information
        ConfigFile.writeStringToStream(stream, worldConfig.getName());

        stream.writeInt(worldConfig.WorldFog);
        stream.writeInt(worldConfig.WorldNightFog);

        // Fetch all non-virtual biomes
        Collection<LocalBiome> nonVirtualBiomes = new ArrayList<LocalBiome>();
        Collection<LocalBiome> nonVirtualCustomBiomes = new ArrayList<LocalBiome>();
        for (LocalBiome biome : biomes) {
            if (biome == null)
                continue;

            if (!biome.getIds().isVirtual()) {
                nonVirtualBiomes.add(biome);
                if (biome.isCustom()) {
                    nonVirtualCustomBiomes.add(biome);
                }
            }
        }

        // Write them to the stream
        stream.writeInt(nonVirtualCustomBiomes.size());
        for (LocalBiome biome : nonVirtualCustomBiomes) {
            ConfigFile.writeStringToStream(stream, biome.getName());
            stream.writeInt(biome.getIds().getSavedId());
        }

        // BiomeConfigs
        stream.writeInt(nonVirtualBiomes.size());
        for (LocalBiome biome : nonVirtualBiomes) {
            if (biome == null) {
                continue;
            }
            stream.writeInt(biome.getIds().getSavedId());
            biome.getBiomeConfig().writeToStream(stream);
        }
    }
}
