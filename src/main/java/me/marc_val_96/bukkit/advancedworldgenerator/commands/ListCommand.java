package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObject;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObjectCollection;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPerm;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public final class ListCommand extends BaseCommand {
    public ListCommand(AWGPlugin plugin) {
        super(plugin);
        name = "list";
        perm = AWGPerm.CMD_LIST.node;
        usage = "list [-w World] [page]";
        workOnConsole = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {

        int page = 1;

        if (args.size() > 1 && args.get(0).equals("-w")) {
            String worldName = args.get(1);
            if (args.size() > 2) {
                try {
                    page = Integer.parseInt(args.get(2));
                } catch (Exception e) {
                    sender.sendMessage(ERROR_COLOR + "Wrong page number " + args.get(2));
                }
            }
            LocalWorld world = this.getWorld(sender, worldName);

            if (world != null) {
                if (world.getConfigs().getCustomObjects().isEmpty())
                    sender.sendMessage(MESSAGE_COLOR + "This world does not have custom objects");

                List<String> pluginList = new ArrayList<>();
                for (CustomObject object : world.getConfigs().getCustomObjects()) {
                    pluginList.add(VALUE_COLOR + object.getName());
                }

                this.ListMessage(sender, pluginList, page, "World objects");

            } else
                sender.sendMessage(ERROR_COLOR + "World not found " + worldName);
            return true;

        }
        if (args.size() > 0) {
            try {
                page = Integer.parseInt(args.get(0));
            } catch (Exception e) {
                sender.sendMessage(ERROR_COLOR + "Wrong page number " + args.get(0));
            }
        }

        CustomObjectCollection globalObjects = AWG.getCustomObjectManager().getGlobalObjects();

        if (globalObjects.isEmpty())
            sender.sendMessage(MESSAGE_COLOR + "This global directory does not have custom objects");

        List<String> pluginList = new ArrayList<>();
        for (CustomObject object : globalObjects) {
            if (object.canSpawnAsObject()) {
                pluginList.add(VALUE_COLOR + object.getName());
            }
        }

        this.ListMessage(sender, pluginList, page, "Global objects", "Use /awg list -w [world] for world objects");

        return true;

    }
}