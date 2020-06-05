package me.marc_val_96.bukkit.advancedworldgenerator.selection;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import me.marc_val_96.bukkit.advancedworldgenerator.commands.BaseCommand;
import me.thevipershow.geomvectorlib.pairs.GenericPair;
import me.thevipershow.geomvectorlib.triples.GenericTriple;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public final class TridimensionalSelection implements SelectionCalculator {
    private final UUID sender;
    private final String playerName;
    private final SelectionOptions selectionOptions;
    private GenericTriple<Integer, Integer, Integer> firstPosition, secondPosition;
    private World world;
    private final Set<GenericPair<GenericTriple<Integer, Integer, Integer>, Material>> selection = new HashSet<>();

    public TridimensionalSelection(final Player sender,final SelectionOptions selectionOptions,final World world) {
        this.sender = sender.getUniqueId();
        this.playerName = sender.getName();
        this.selectionOptions = selectionOptions;
        this.world = world;
    }

    public final UUID getSender() {
        return sender;
    }

    public final SelectionOptions getSelectionOptions() {
        return selectionOptions;
    }

    public final GenericTriple<Integer, Integer, Integer> getFirstPosition() {
        return firstPosition;
    }

    public final GenericTriple<Integer, Integer, Integer> getSecondPosition() {
        return secondPosition;
    }

    public final void setFirstPosition(GenericTriple<Integer, Integer, Integer> firstPosition) {
        this.firstPosition = firstPosition;
    }

    public final void setSecondPosition(GenericTriple<Integer, Integer, Integer> secondPosition) {
        this.secondPosition = secondPosition;
    }

    private boolean toAdd(Material toAdd) {
        return ((selectionOptions.isAir() && toAdd == Material.AIR)
                || (selectionOptions.isLava() && (toAdd == Material.LAVA))
                || (selectionOptions.isWater() && (toAdd == Material.WATER)));
    }

    @SuppressWarnings("ConstantConditions")
    private Set<GenericPair<GenericTriple<Integer, Integer, Integer>, Material>> calculate(final World world, final GenericTriple<Integer, Integer, Integer> firstPosition, final GenericTriple<Integer, Integer, Integer> secondPosition) {
        final Set<GenericPair<GenericTriple<Integer, Integer, Integer>, Material>> selection = new HashSet<>();
        final int x1, x2, y1, y2, z1, z2;
        x1 = firstPosition.getFirst();
        x2 = secondPosition.getFirst();
        y1 = firstPosition.getSecond();
        y2 = secondPosition.getSecond();
        z1 = firstPosition.getThird();
        z2 = secondPosition.getThird();
        final int highestYPoint = SelectionUtils.max(firstPosition, secondPosition, SelectionUtils.PositionTriple.SECOND);
        final int lowestYPoint = SelectionUtils.min(firstPosition, secondPosition, SelectionUtils.PositionTriple.SECOND);
        final int highestXPoint = SelectionUtils.max(firstPosition, secondPosition, SelectionUtils.PositionTriple.FIRST);
        final int lowestXPoint = SelectionUtils.min(firstPosition, secondPosition, SelectionUtils.PositionTriple.FIRST);
        final int highestZPoint = SelectionUtils.max(firstPosition, secondPosition, SelectionUtils.PositionTriple.THIRD);
        final int lowestZPoint = SelectionUtils.min(firstPosition, secondPosition, SelectionUtils.PositionTriple.THIRD);
        for (int y = highestYPoint; y <= lowestYPoint; y--) {
            for (int x = lowestXPoint; x <= highestXPoint; x++) {
                for (int z = lowestZPoint; z <= highestZPoint; z++) {
                    final Block block = world.getBlockAt(x, y, z);
                    final Material blockMaterial = block.getType();
                    if (!toAdd(blockMaterial)) {
                        selection.add(new GenericPair<>(new GenericTriple<>(x, y, z), blockMaterial));
                    }
                }
            }
        }
        return selection;
    }

    @Override
    public final Set<GenericPair<GenericTriple<Integer, Integer, Integer>, Material>> calculateSelection(final World world, final GenericTriple<Integer, Integer, Integer> firstPosition, final GenericTriple<Integer, Integer, Integer> secondPosition) {
        if (firstPosition != null && secondPosition != null) {
            //TODO: return here
            return calculate(world, firstPosition, secondPosition);
        } else {
            final Player player = Bukkit.getPlayer(sender);
            if (player != null) {
                player.sendMessage(BaseCommand.ERROR_COLOR + "Invalid selection!");
            }
        }
        return selection;
    }

    @Override
    public final void clearSelection() {
        selection.clear();
    }

    public final World getWorld() {
        return world;
    }

    public final void setWorld(World world) {
        this.world = world;
    }

    public final String getPlayerName() {
        return playerName;
    }
}
