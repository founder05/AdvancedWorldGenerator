package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPerm;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import me.marc_val_96.bukkit.advancedworldgenerator.BukkitBiome;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TPCommand extends BaseCommand
{
    TPCommand(AWGPlugin _plugin)
    {
        super(_plugin);
        name = "tp";
        perm = AWGPerm.CMD_TP.node;
        usage = "tp <biome name or id>";
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args)
    {
        Location location = this.getLocation(sender);
        int playerX = location.getBlockX();
        int playerZ = location.getBlockZ();

        LocalWorld world = this.getWorld(sender, "");

        if (world == null)
        {
            sender.sendMessage(ERROR_COLOR + "Plugin is not enabled for this world.");
            return true;
        }

        StringBuilder biomeName = new StringBuilder();
        for (String arg : args) {
            biomeName.append(arg).append(" ");
        }
        biomeName = new StringBuilder(biomeName.toString().trim());
        if(biomeName.length() > 0)
        {
            int biomeId = -1;
            try
            {
                biomeId = Integer.parseInt(biomeName.toString().replace(" ", ""));
            }
            catch(NumberFormatException ignored) { }

            ChunkCoordinate playerChunk = ChunkCoordinate.fromBlockCoords(playerX, playerZ);

            Player player = (Player) sender;
            Location playerLoc = player.getLocation();

            int maxRadius = 500;

            if(biomeId == -1)
            {
                BukkitBiome targetBiome = (BukkitBiome)world.getBiomeByName(biomeName.toString());
                if(targetBiome != null)
                {
                    biomeId = targetBiome.getIds().getSavedId();
                }
            }

            if(biomeId != -1)
            {
                for(int cycle = 1; cycle < maxRadius; cycle++)
                {
                    for(int x1 = playerX - cycle; x1 <= playerX + cycle; x1++)
                    {
                        for(int z1 = playerZ - cycle; z1 <= playerZ + cycle; z1++)
                        {
                            if(x1 == playerX - cycle || x1 == playerX + cycle || z1 == playerZ - cycle || z1 == playerZ + cycle)
                            {
                                ChunkCoordinate chunkCoord = ChunkCoordinate.fromChunkCoords(playerChunk.getChunkX() + (x1 - playerX), playerChunk.getChunkZ() + (z1 - playerZ));

                                BukkitBiome biome = (BukkitBiome)world.getBiome(chunkCoord.getBlockXCenter(), chunkCoord.getBlockZCenter());

                                if(
                                        biome != null &&
                                                biome.getIds().getGenerationId() == biomeId
                                )
                                {
                                    Location loc = new Location(playerLoc.getWorld(), chunkCoord.getBlockXCenter(), (double)world.getHighestBlockAboveYAt(chunkCoord.getBlockXCenter(), chunkCoord.getBlockZCenter(), null), (double)chunkCoord.getBlockZCenter());
                                    sender.sendMessage("Teleporting to \"" + biomeName + "\".");
                                    player.teleport(loc);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            sender.sendMessage(ERROR_COLOR + "Could not find biome \"" + biomeName + "\".");
        }

        return true;
    }
}