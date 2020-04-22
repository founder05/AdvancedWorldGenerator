package me.marc_val_96.bukkit.advancedworldgenerator.generator;


import com.google.common.base.Preconditions;
import com.marc_val_96.advancedworldgenerator.configuration.WorldConfig;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.StructureNames;
import java.util.List;
import me.marc_val_96.bukkit.advancedworldgenerator.BukkitWorld;
import net.minecraft.server.v1_12_R1.BiomeBase;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.EnumCreatureType;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.craftbukkit.v1_12_R1.generator.CustomChunkGenerator;
import org.bukkit.generator.ChunkGenerator;

public class AWGInternalChunkGenerator extends CustomChunkGenerator {

    private final BukkitWorld localWorld;

    public AWGInternalChunkGenerator(BukkitWorld world, ChunkGenerator generator) {
        super(world.getWorld(), world.getSeed(), generator);
        Preconditions.checkArgument(generator instanceof AWGChunkGenerator, "Generator must be of the plugin");

        this.localWorld = world;
    }

    @Override
    public List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType type, BlockPosition position) {
        WorldConfig worldConfig = localWorld.getConfigs().getWorldConfig();
        BiomeBase biomebase = localWorld.getWorld().getBiome(position);
        if (type == EnumCreatureType.MONSTER
                && worldConfig.rareBuildingsEnabled
                && localWorld.rareBuildingGen.isWitchHutAt(position)) {
            return localWorld.rareBuildingGen.getWitchHutMobs();
        }

        if (type == EnumCreatureType.MONSTER
                && worldConfig.oceanMonumentsEnabled
                && localWorld.oceanMonumentGen.a(localWorld.getWorld(), position)) {
            return localWorld.oceanMonumentGen.getMobs();
        }

        return biomebase.getMobs(type);
    }

    @Override
    public BlockPosition findNearestMapFeature(World mcWorld, String type, BlockPosition position, boolean bool) {
        WorldConfig worldConfig = localWorld.getConfigs().getWorldConfig();

        switch (type) {
            case StructureNames.MANSION:
                if (worldConfig.mansionsEnabled)
                    return localWorld.mansionGen.getNearestGeneratedFeature(mcWorld, position, bool);
                break;
            case StructureNames.MINESHAFT:
                if (worldConfig.mineshaftsEnabled)
                    return localWorld.mineshaftGen.getNearestGeneratedFeature(mcWorld, position, bool);
                break;
            case StructureNames.NETHER_FORTRESS:
                if (worldConfig.netherFortressesEnabled)
                    return localWorld.netherFortressGen.getNearestGeneratedFeature(mcWorld, position, bool);
                break;
            case StructureNames.OCEAN_MONUMENT:
                if (worldConfig.oceanMonumentsEnabled)
                    return localWorld.oceanMonumentGen.getNearestGeneratedFeature(mcWorld, position, bool);
                break;
            case StructureNames.RARE_BUILDING:
                if (worldConfig.rareBuildingsEnabled)
                    return localWorld.rareBuildingGen.getNearestGeneratedFeature(mcWorld, position, bool);
                break;
            case StructureNames.STRONGHOLD:
                if (worldConfig.strongholdsEnabled)
                    return localWorld.strongholdGen.getNearestGeneratedFeature(mcWorld, position, bool);
                break;
            case StructureNames.VILLAGE:
                if (worldConfig.villagesEnabled)
                    return localWorld.villageGen.getNearestGeneratedFeature(mcWorld, position, bool);
                break;
        }
        return null;
    }

}
