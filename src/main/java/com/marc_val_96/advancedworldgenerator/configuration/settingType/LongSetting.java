package com.marc_val_96.advancedworldgenerator.configuration.settingType;

import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;

/**
 * Reads and writes a single long.
 *
 * <p>Numbers are limited to the given min and max values.
 */
class LongSetting extends Setting<Long> {
    private final long defaultValue;
    private final long minValue;
    private final long maxValue;

    LongSetting(String name, long defaultValue, long minValue, long maxValue) {
        super(name);
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Long getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Long read(String string) throws InvalidConfigException {
        return StringHelper.readLong(string, minValue, maxValue);
    }

}
