package me.marc_val_96.bukkit.advancedworldgenerator.bo3;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import me.marc_val_96.bukkit.advancedworldgenerator.selection.TridimensionalSelection;
import me.thevipershow.geomvectorlib.pairs.GenericPair;
import me.thevipershow.geomvectorlib.triples.GenericTriple;

public final class BO3FileUtils {

    public static boolean write(final String text, final File file, final PrintStream stream) throws FileNotFoundException {
        boolean success = false;
        if (file.exists() && file.canWrite()) {
            OutputStream out;
            stream.println(text);
            success = true;
        }
        return success;
    }

    public static boolean write(final String key, final String value, final File file) throws IOException {
        boolean success = false;
        if (file.exists() && file.canWrite()) {
            final PrintStream printStream = new PrintStream(file);
            printStream.println(key + ":" + value);
            success = true;
        }
        return success;
    }

    public static boolean writeAll(List<GenericPair<String, String>> keyAndValueList, final File file) throws IOException {
        boolean success = false;
        if (file.exists() && file.canWrite()) {
            final PrintStream printStream = new PrintStream(file);
            keyAndValueList.iterator().forEachRemaining(kv -> printStream.println(kv.getFirst() + ":" + kv.getSecond()));
            success = true;
        }
        return success;
    }


    public static boolean writeAtLine(final String key, final String value, final int line, final File file) throws IOException {
        boolean success = false;
        if (file.exists() && file.canRead() && file.canWrite()) {
            final LinkedList<String> orderedFileLines = new LinkedList<>(Files.readAllLines(file.toPath()));
            orderedFileLines.add(line, key + ":" + value);
            final PrintStream printStream = new PrintStream(file);
            orderedFileLines.iterator().forEachRemaining(printStream::println);
            printStream.close();
            success = true;
        }
        return success;
    }

    public static boolean writeElseUpdate(final String key, final String value, final File file) throws IOException {
        boolean success = false;
        if (file.exists() && file.canWrite() && file.canRead()) {
            final LinkedList<String> orderedFileLines = new LinkedList<>(Files.readAllLines(file.toPath()));
            final String insertionValue = key + ":" + value;
            if (!orderedFileLines.isEmpty()) {
                for (int i = 0; i < orderedFileLines.size(); i++) {
                    final String readString = orderedFileLines.get(i);
                    if (!readString.isEmpty()) {
                        if (readString.matches(key + ":" + "[a-zA-Z0-9]+")) {
                            orderedFileLines.set(i, insertionValue);
                            writeAtLine(key, value, i, file);
                            return true;
                        }
                    }
                }
            }
            orderedFileLines.push(insertionValue);
            write(key, value, file);
            success = true;
        }
        return success;
    }

    public static LinkedList<String> createSelectionStrings(final TridimensionalSelection selection) {
        final LinkedList<String> list = new LinkedList<>();
        selection.calculateSelection(selection.getWorld(), selection.getFirstPosition(), selection.getSecondPosition())
                .iterator().forEachRemaining(v -> {
            final GenericTriple<Integer, Integer, Integer> triple = v.getFirst();
            final int x = triple.getFirst(), y = triple.getSecond(), z = triple.getThird();
            list.add("B(" + x + "," + y + "," + z + "," + v.getSecond().name() + ")");
        });
        return list;
    }

    public static void writeNewSelection(final TridimensionalSelection selection, final File file) throws IOException {
        writeAll(Arrays.asList(
                new GenericPair<>("Author", selection.getPlayerName()),
                new GenericPair<>("Version", "3"),
                new GenericPair<>("SettingsMode", "WriteAll"),
                new GenericPair<>("Frequency", "1"),
                new GenericPair<>("Rarity", "100.0"),
                new GenericPair<>("RotateRandomly", "false"),
                new GenericPair<>("SpawnHeight", "highestSolidBlock"),
                new GenericPair<>("MinHeight", "40"),
                new GenericPair<>("SourceBlocks", "AIR"),
                new GenericPair<>("MaxPercentageOutsideSourceBlock", "100"),
                new GenericPair<>("OutsideSourceBlock", "placeAnyway")
        ), file);
        final PrintStream printStream = new PrintStream(file);
        createSelectionStrings(selection).forEach(printStream::println);
        printStream.close();
    }
}
