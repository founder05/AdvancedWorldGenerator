package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPerm;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import me.marc_val_96.bukkit.advancedworldgenerator.BukkitWorld;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public final class ReloadCommand extends BaseCommand {

    public ReloadCommand(AWGPlugin awgPlugin) {
        super(awgPlugin);
        name = "reload";
        perm = AWGPerm.CMD_RELOAD.node;
        usage = "reload [world_name]";
        worksFromConsole = true;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        BukkitWorld world = (BukkitWorld) this.getWorld(sender, args.size() > 0 ? args.get(0) : "");
        if (world == null) {
            sender.sendMessage(ERROR_COLOR + "World not found. Either you are not in a world with Terrain Control, or you are the console.");
            return false;
        }

        world.reloadSettings();

        sender.sendMessage(MESSAGE_COLOR + "Configs for world '" + world.getName() + "' reloaded");
        if (sender instanceof Player) {
            AWG.log(LogMarker.INFO, "{} reloaded the config files for world '{}'.", sender.getName(), world.getName());
        }
        return true;
    }

}