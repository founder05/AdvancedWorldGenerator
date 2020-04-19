package com.marc_val_96.advancedworldgenerator.configuration.settingType;

import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;

/**
 * Reads and writes a string. Surrounding whitespace is stripped using
 * {@link String#trim()}.
 */
class StringSetting extends Setting<String> {
    private final String defaultValue;

    StringSetting(String name, String defaultValue) {
        super(name);
        this.defaultValue = defaultValue;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String read(String string) throws InvalidConfigException {
        return string.trim();
    }

}
