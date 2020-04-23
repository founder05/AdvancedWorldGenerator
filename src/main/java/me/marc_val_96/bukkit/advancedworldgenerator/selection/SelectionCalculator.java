package me.marc_val_96.bukkit.advancedworldgenerator.selection;

import java.util.Set;
import me.thevipershow.geomvectorlib.pairs.GenericPair;
import me.thevipershow.geomvectorlib.triples.GenericTriple;
import org.bukkit.Material;
import org.bukkit.World;

public interface SelectionCalculator {
    Set<GenericPair<GenericTriple<Integer, Integer, Integer>, Material>> calculateSelection(final World world,
                                                                                            final GenericTriple<Integer, Integer, Integer> firstPosition,
                                                                                            final GenericTriple<Integer, Integer, Integer> secondPosition);
    void clearSelection();
}
