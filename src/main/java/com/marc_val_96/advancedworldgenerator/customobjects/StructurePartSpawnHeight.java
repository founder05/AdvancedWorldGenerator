package com.marc_val_96.advancedworldgenerator.customobjects;

import com.marc_val_96.advancedworldgenerator.LocalWorld;

public interface StructurePartSpawnHeight {
    /**
     * Use the y position provided in this object .
     */
    StructurePartSpawnHeight PROVIDED = new StructurePartSpawnHeight() {

        @Override
        public int getCorrectY(LocalWorld world, int x, int y, int z) {
            return y;
        }
    };

    /**
     * Use the highest block on the x,z column
     */
    StructurePartSpawnHeight HIGHEST_BLOCK = new StructurePartSpawnHeight() {

        @Override
        public int getCorrectY(LocalWorld world, int x, int y, int z) {
            return world.getHighestBlockYAt(x, z);
        }

    };

    /**
     * Use the highest solid block on the x,z column
     */
    StructurePartSpawnHeight HIGHEST_SOLID_BLOCK = new StructurePartSpawnHeight() {

        @Override
        public int getCorrectY(LocalWorld world, int x, int y, int z) {
            return world.getSolidHeight(x, z);
        }
    };

    /**
     * Gets the correct y position for this part of the structure. An y
     * position based on the coordinates of the object this object is attached
     * to is already given (see the y parameter of the Branch function of BO3s
     * for example). This can be ignored, for example to let all objects spawn
     * on the surface no matter what.
     *
     * @param world The world the object is spawning in.
     * @param x     The x position the object is spawning on.
     * @param y     The guessed y position the object is spawning on.
     * @param z     The z position the object is spawning on.
     * @return The y position the object should spawn on instead.
     */
    int getCorrectY(LocalWorld world, int x, int y, int z);
}
