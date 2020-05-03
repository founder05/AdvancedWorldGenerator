package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import me.marc_val_96.bukkit.advancedworldgenerator.AWGPerm;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public final class HelpCommand extends BaseCommand {
    public HelpCommand(AWGPlugin awgPlugin) {
        super(awgPlugin);
        super.name = "help";
        super.perm = AWGPerm.CMD_HELP.node;
        super.usage = "help";
        super.worksFromConsole = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        List<String> lines = new ArrayList<>();
        plugin.commandExecutor.commandHashMap.values().forEach(cmd -> lines.add(MESSAGE_COLOR + "/awg " + cmd.usage + " - " + cmd.getHelp()));

        int page = 1;
        try {
            if (!args.isEmpty())
                page = Integer.parseInt(args.get(0));
        } catch (NumberFormatException e) {
            sender.sendMessage(ERROR_COLOR + "Wrong page number " + args.get(0));
        }

        this.listMessages(sender, lines, page, "Available commands");
        return true;
    }
}
