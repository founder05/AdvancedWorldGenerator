package me.marc_val_96.bukkit.advancedworldgenerator.selection;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import static me.marc_val_96.bukkit.advancedworldgenerator.commands.BaseCommand.ERROR_COLOR;
import me.thevipershow.geomvectorlib.pairs.GenericPair;
import me.thevipershow.geomvectorlib.triples.GenericTriple;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import static org.bukkit.Material.*;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public final class TridimensionalSelection implements SelectionCalculator {
    private final UUID sender;
    private final SelectionOptions selectionOptions;
    private GenericTriple<Integer, Integer, Integer> firstPosition, secondPosition;
    private final Set<GenericPair<GenericTriple<Integer, Integer, Integer>, Material>> selection = new HashSet<>();

    public TridimensionalSelection(UUID sender, SelectionOptions selectionOptions) {
        this.sender = sender;
        this.selectionOptions = selectionOptions;
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
        return ((selectionOptions.isAir() && toAdd == AIR)
                || (selectionOptions.isLava() && (toAdd == LAVA || toAdd == STATIONARY_LAVA))
                || (selectionOptions.isWater() && (toAdd == WATER || toAdd == STATIONARY_WATER)));
    }

    private Set<GenericPair<GenericTriple<Integer, Integer, Integer>, Material>> calculate(final World world, final GenericTriple<Integer, Integer, Integer> firstPosition, final GenericTriple<Integer, Integer, Integer> secondPosition) {
        final Set<GenericPair<GenericTriple<Integer, Integer, Integer>, Material>> selection = new HashSet<>();
        final int x1, x2, y1, y2, z1, z2;
        x1 = firstPosition.getFirst();
        x2 = secondPosition.getFirst();
        y1 = firstPosition.getSecond();
        y2 = secondPosition.getSecond();
        z1 = firstPosition.getThird();
        z2 = secondPosition.getThird();
        final int highestYPoint = Math.max(firstPosition.getSecond(), secondPosition.getSecond());
        final int lowestYPoint = highestYPoint == firstPosition.getSecond() ? secondPosition.getSecond() : firstPosition.getSecond();
        final int highestXPoint = Math.max(firstPosition.getFirst(), secondPosition.getFirst());
        final int lowestXPoint = highestXPoint == firstPosition.getFirst() ? secondPosition.getFirst() : firstPosition.getFirst();
        final int highestZPoint = Math.max(firstPosition.getThird(), secondPosition.getThird());
        final int lowestZPoint = highestZPoint == firstPosition.getThird() ? secondPosition.getThird() : firstPosition.getThird();
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
        } else {
            final Player player = Bukkit.getPlayer(sender);
            if (player != null) {
                player.sendMessage(ERROR_COLOR + "Invalid selection!");
            }
        }
        return selection;
    }

    @Override
    public final void clearSelection() {
        selection.clear();
    }
}