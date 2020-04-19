package com.marc_val_96.advancedworldgenerator.customobjects;

import com.marc_val_96.advancedworldgenerator.LocalWorld;

import java.util.Random;

/**
 * Represents a branch of a CustomObject.
 */
public interface Branch {
    /**
     * Makes a CustomObjectCoordinate out of this branch. Is allowed
     * to return null if based on the random number generator no
     * branch should spawn here.
     *
     * @param world  The world.
     * @param random The random number generator.
     * @param x      X coordinate of the origin of the object.
     * @param y      Y coordinate of the origin of the object.
     * @param z      Z coordinate of the origin of the object.
     * @return The CustomObjectCoordinate of this branch.
     */
    CustomObjectCoordinate toCustomObjectCoordinate(LocalWorld world, Random random, int x, int y, int z);
}
