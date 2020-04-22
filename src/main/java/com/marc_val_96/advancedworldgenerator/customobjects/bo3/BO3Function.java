package com.marc_val_96.advancedworldgenerator.customobjects.bo3;

import com.marc_val_96.advancedworldgenerator.configuration.ConfigFunction;

/**
 * Represents a BO3 function - a ConfigFunction with a BO3 as holder. It can be
 * rotated.
 */
public abstract class BO3Function extends ConfigFunction<BO3Config> {

    public BO3Function(BO3Config holder) {
        super(holder);
    }

    /**
     * Returns a new BO3Function that is rotated 90 degrees.
     * Note: the BO3Functons can have a magical link: if you change something on
     * the rotated one, it may also change on the original and vice versa.
     *
     * @return A new BO3Function that is rotated 90 degrees.
     */
    public abstract BO3Function rotate();

}
