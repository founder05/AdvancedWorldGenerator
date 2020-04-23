package me.marc_val_96.bukkit.advancedworldgenerator.selection;

import me.thevipershow.geomvectorlib.triples.GenericTriple;

public final class SelectionUtils {
    public static <T extends Number & Comparable<T>> GenericTriple<T, T, T> maxThird(final GenericTriple<T, T, T> first, final GenericTriple<T, T, T> second) {
        return first.getThird().compareTo(second.getThird()) >= 0 ? first : second;
    }

    public static <T extends Number & Comparable<T>> GenericTriple<T, T, T> maxSecond(final GenericTriple<T, T, T> first, final GenericTriple<T, T, T> second) {
        return first.getSecond().compareTo(second.getSecond()) >= 0 ? first : second;
    }

    public static <T extends Number & Comparable<T>> GenericTriple<T, T, T> maxFirst(final GenericTriple<T, T, T> first, final GenericTriple<T, T, T> second) {
        return first.getFirst().compareTo(second.getFirst()) >= 0 ? first : second;
    }

    public static <T extends Number & Comparable<T>> GenericTriple<T, T, T> minThird(final GenericTriple<T, T, T> first, final GenericTriple<T, T, T> second) {
        return first.getThird().compareTo(second.getThird()) >= 0 ? second : first;
    }

    public static <T extends Number & Comparable<T>> GenericTriple<T, T, T> minSecond(final GenericTriple<T, T, T> first, final GenericTriple<T, T, T> second) {
        return first.getSecond().compareTo(second.getSecond()) >= 0 ? second : first;
    }

    public static <T extends Number & Comparable<T>> GenericTriple<T, T, T> minFirst(final GenericTriple<T, T, T> first, final GenericTriple<T, T, T> second) {
        return first.getFirst().compareTo(second.getFirst()) >= 0 ? second : first;
    }
}