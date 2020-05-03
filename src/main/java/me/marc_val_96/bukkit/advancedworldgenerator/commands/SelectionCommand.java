package me.marc_val_96.bukkit.advancedworldgenerator.commands;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPerm;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import me.marc_val_96.bukkit.advancedworldgenerator.selection.SelectionOptions;
import me.marc_val_96.bukkit.advancedworldgenerator.selection.TridimensionalSelection;
import me.thevipershow.geomvectorlib.triples.GenericTriple;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class SelectionCommand extends BaseCommand {
    private final Map<UUID, TridimensionalSelection> playerSelections;

    public SelectionCommand(AWGPlugin awgPlugin, Map<UUID, TridimensionalSelection> playerSelections) {
        super(awgPlugin);
        super.name = "selection";
        super.perm = AWGPerm.CMD_SELECTION.node;
        super.usage = "selection [position|clear] [1|2] [-air|-water|-lava]";
        super.worksFromConsole = false;
        this.playerSelections = playerSelections;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            final World world = player.getWorld();
            final UUID playerUUID = player.getUniqueId();
            if (!args.isEmpty()) {
                final String firstArgument = args.get(0);
                if (firstArgument.equalsIgnoreCase("position")) {
                    if (args.size() >= 2) {
                        try {
                            int selectedPosition = Integer.parseInt(args.get(1));
                            if (selectedPosition != 1 && selectedPosition != 2) {
                                throw new NumberFormatException();
                            }
                            final SelectionOptions selectionOptions = new SelectionOptions(
                                    args.stream().anyMatch(s -> s.equalsIgnoreCase("-air")),
                                    args.stream().anyMatch(s -> s.equalsIgnoreCase("-water")),
                                    args.stream().anyMatch(s -> s.equalsIgnoreCase("-lava")),
                                    playerUUID
                            );
                            TridimensionalSelection currentSelection = playerSelections.get(playerUUID);
                            if (currentSelection == null) {
                                currentSelection = new TridimensionalSelection(player, selectionOptions, world);
                            }
                            final Location currentLocation = player.getLocation();
                            final int x = currentLocation.getBlockX(), y = currentLocation.getBlockY(), z = currentLocation.getBlockZ();
                            final GenericTriple<Integer, Integer, Integer> genericTriplePosition = new GenericTriple<>(x, y, z);
                            if (selectedPosition == 1) {
                                currentSelection.setFirstPosition(genericTriplePosition);
                            } else {
                                currentSelection.setSecondPosition(genericTriplePosition);
                            }
                            currentLocation.setWorld(world);
                            playerSelections.put(playerUUID, currentSelection);
                            // TODO: finish here ^
                        } catch (NumberFormatException exception) {
                            player.sendMessage(ERROR_COLOR + "The position was invalid, must be 1 or 2");
                        }
                    }
                } else if (firstArgument.equalsIgnoreCase("clear")) {
                    if (playerSelections.remove(playerUUID) != null) {
                        player.sendMessage(MESSAGE_COLOR + "You successfully cleared your selection.");
                    } else {
                        player.sendMessage(ERROR_COLOR + "You do not have a selection, hence it could not be removed");
                    }
                }
            }
        }
        return true;
    }
}
