package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import java.util.List;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPerm;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import org.bukkit.command.CommandSender;

public class ExportCommand extends BaseCommand {
    public ExportCommand(AWGPlugin awgPlugin) {
        super(awgPlugin);
        super.name = "export";
        super.perm = AWGPerm.CMD_EXPORT.node;
        super.usage = "export <structure_name>";
        super.worksFromConsole = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if (!args.isEmpty()) {

        }
        return true;
    }
}
