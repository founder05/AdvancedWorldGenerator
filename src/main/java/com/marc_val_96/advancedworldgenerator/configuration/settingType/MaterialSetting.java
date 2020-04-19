package com.marc_val_96.advancedworldgenerator.configuration.settingType;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;

/**
 * Reads and writes a material. Materials are read using
 * {@link AWG#readMaterial(String)} and written using
 * {@link LocalMaterialData#toString()}.
 */
public class MaterialSetting extends Setting<LocalMaterialData> {
    private final DefaultMaterial defaultValue;

    public MaterialSetting(String name, DefaultMaterial defaultValue) {
        super(name);
        this.defaultValue = defaultValue;
    }

    @Override
    public LocalMaterialData getDefaultValue() {
        return AWG.toLocalMaterialData(defaultValue, 0);
    }

    @Override
    public LocalMaterialData read(String string) throws InvalidConfigException {
        return AWG.readMaterial(string);
    }

}
