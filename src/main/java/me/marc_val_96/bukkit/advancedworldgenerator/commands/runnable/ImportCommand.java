package me.marc_val_96.bukkit.advancedworldgenerator.commands.runnable;

import java.util.List;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPerm;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import me.marc_val_96.bukkit.advancedworldgenerator.commands.BaseCommand;
import org.bukkit.command.CommandSender;

public class ImportCommand extends BaseCommand {
    public ImportCommand(AWGPlugin awgPlugin) {
        super(awgPlugin);
        super.name = "export";
        super.perm = AWGPerm.CMD_EXPORT.node;
        super.usage = "export structure_name";
        super.workOnConsole = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if (!args.isEmpty()) {

        } else {

        }
        return true;
    }
}
