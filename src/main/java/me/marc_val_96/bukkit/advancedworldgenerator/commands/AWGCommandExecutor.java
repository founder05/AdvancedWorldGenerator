package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class AWGCommandExecutor implements CommandExecutor {
    protected final AWGPlugin plugin;
    protected HashMap<String, BaseCommand> commandHashMap = new HashMap<>();
    protected HelpCommand helpCommand;

    public AWGCommandExecutor(AWGPlugin plugin) {
        this.plugin = plugin;
        this.helpCommand = new HelpCommand(plugin);
        this.registerCommands();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final List<String> arg = new ArrayList<>(Arrays.asList(strings));
        BaseCommand cmd = helpCommand;
        if (!arg.isEmpty() && commandHashMap.containsKey(arg.get(0))) {
            cmd = commandHashMap.get(arg.get(0));
            arg.remove(0);
        }

        if (!commandSender.hasPermission(cmd.perm)) {
            commandSender.sendMessage(ChatColor.RED.toString() + "You don't have permission to " + cmd.getHelp() + "!");
            return true;
        }
        return cmd.onCommand(commandSender, arg);
    }

    private void registerCommands() {
        addCommand(new ReloadCommand(plugin));
        addCommand(new ListCommand(plugin));
        addCommand(new CheckCommand(plugin));
        addCommand(new BiomeCommand(plugin));
        addCommand(new MapCommand(plugin));
        addCommand(helpCommand);
        addCommand(new SelectionCommand(plugin, plugin.getPlayersSelections()));
        addCommand(new ExportCommand(plugin));
    }

    private void addCommand(BaseCommand command) {
        this.commandHashMap.put(command.name, command);
    }
}