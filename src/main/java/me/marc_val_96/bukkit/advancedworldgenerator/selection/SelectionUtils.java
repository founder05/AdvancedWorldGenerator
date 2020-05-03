package me.marc_val_96.bukkit.advancedworldgenerator.selection;

import me.thevipershow.geomvectorlib.triples.GenericTriple;

public final class SelectionUtils {

    public enum PositionTriple {
        FIRST, SECOND, THIRD
    }

    public static <T extends Number & Comparable<T>, S extends GenericTriple<T, T, T>> T max(S first, S second, PositionTriple position) {
        switch (position) {
            case FIRST:
                return (first.getFirst().compareTo(second.getFirst()) >= 0 ? first.getFirst() : second.getFirst());
            case SECOND:
                return (first.getSecond().compareTo(second.getSecond()) >= 0 ? first.getSecond() : second.getSecond());
            case THIRD:
                return (first.getThird().compareTo(second.getThird()) >= 0 ? first.getThird() : second.getThird());
            default:
                return null;
        }
    }

    public static <T extends Number & Comparable<T>, S extends GenericTriple<T, T, T>> T min(S first, S second, PositionTriple position) {
        switch (position) {
            case FIRST:
                return (first.getFirst().compareTo(second.getFirst()) <= 0 ? first.getFirst() : second.getFirst());
            case SECOND:
                return (first.getSecond().compareTo(second.getSecond()) <= 0 ? first.getSecond() : second.getSecond());
            case THIRD:
                return (first.getThird().compareTo(second.getThird()) <= 0 ? first.getThird() : second.getThird());
            default:
                return null;
        }
    }

}