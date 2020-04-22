package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import me.marc_val_96.bukkit.advancedworldgenerator.util.WorldHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.List;

public abstract class BaseCommand {
    public static final String ERROR_COLOR = ChatColor.RED.toString();
    public static final String MESSAGE_COLOR = ChatColor.GREEN.toString();
    public static final String VALUE_COLOR = ChatColor.DARK_GREEN.toString();
    public String name;
    public String perm;
    public String usage;
    public boolean workOnConsole;
    protected AWGPlugin plugin;


    public BaseCommand(AWGPlugin _plugin) {
        this.plugin = _plugin;
    }

    public abstract boolean onCommand(CommandSender sender, List<String> args);

    protected LocalWorld getWorld(CommandSender sender, String worldName) {
        if (worldName.isEmpty()) {
            Location location = getLocation(sender);
            if (location != null) {
                LocalWorld world = WorldHelper.toLocalWorld(location.getWorld());
                if (world != null) {
                    return world;
                }
            }
        }

        return AWG.getWorld(worldName);
    }

    protected Location getLocation(CommandSender sender) {
        if (sender instanceof Player) {
            return ((Player) sender).getLocation();
        }

        if (sender instanceof BlockCommandSender) {
            return ((BlockCommandSender) sender).getBlock().getLocation();
        }

        return null;
    }

    protected void ListMessage(CommandSender sender, List<String> lines, int page, String... headers) {
        int pageCount = (lines.size() >> 3) + 1;
        if (page > pageCount) {
            page = pageCount;
        }

        sender.sendMessage(ChatColor.AQUA.toString() + headers[0] + " - page " + page + "/" + pageCount);
        for (int headerId = 1; headerId < headers.length; headerId++) {
            // Send all remaining headers
            sender.sendMessage(ChatColor.AQUA + headers[headerId]);
        }

        page--;

        for (int i = page * 8; i < lines.size() && i < (page * 8 + 8); i++) {
            sender.sendMessage(lines.get(i));
        }
    }

    public String getHelp() {
        String ret = "do that";
        Permission permission = Bukkit.getPluginManager().getPermission(perm);
        if (permission != null) {
            String desc = permission.getDescription();
            if (desc != null && desc.trim().length() > 0) {
                ret = desc.trim();
            }
        }
        return ret;
    }
}