package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import java.util.List;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPerm;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import org.bukkit.command.CommandSender;

public final class SelectionCommand extends BaseCommand{
    public SelectionCommand(AWGPlugin awgPlugin) {
        super(awgPlugin);
        super.name = "selection";
        super.perm = AWGPerm.CMD_SELECTION.node;
        super.usage = "selection [position|clear] [1|2] [-air|-water|-lava]";
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
