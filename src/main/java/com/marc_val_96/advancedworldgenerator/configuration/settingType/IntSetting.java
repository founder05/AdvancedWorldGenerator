package com.marc_val_96.advancedworldgenerator.configuration.settingType;

import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;

/**
 * Reads and writes a single integer.
 *
 * <p>Numbers are limited to the given min and max values.
 */
class IntSetting extends Setting<Integer> {
    private final int defaultValue;
    private final int minValue;
    private final int maxValue;

    IntSetting(String name, int defaultValue, int minValue, int maxValue) {
        super(name);
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Integer getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Integer read(String string) throws InvalidConfigException {
        return StringHelper.readInt(string, minValue, maxValue);
    }

}
