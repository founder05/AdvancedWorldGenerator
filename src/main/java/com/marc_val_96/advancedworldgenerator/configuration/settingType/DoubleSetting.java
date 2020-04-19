package com.marc_val_96.advancedworldgenerator.configuration.settingType;

import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;

/**
 * Reads and writes a single double number.
 *
 * <p>Numbers are limited to the given min and max values.
 */
class DoubleSetting extends Setting<Double> {
    private final double defaultValue;
    private final double minValue;
    private final double maxValue;

    DoubleSetting(String name, double defaultValue, double minValue, double maxValue) {
        super(name);
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Double getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Double read(String string) throws InvalidConfigException {
        return StringHelper.readDouble(string, minValue, maxValue);
    }

}
