package com.marc_val_96.advancedworldgenerator.configuration.settingType;

import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.generator.surface.MesaSurfaceGenerator;
import com.marc_val_96.advancedworldgenerator.generator.surface.MultipleLayersSurfaceGenerator;
import com.marc_val_96.advancedworldgenerator.generator.surface.SimpleSurfaceGenerator;
import com.marc_val_96.advancedworldgenerator.generator.surface.SurfaceGenerator;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;

/**
 * Setting that handles the {@link SurfaceGenerator}.
 */
class SurfaceGeneratorSetting extends Setting<SurfaceGenerator> {

    SurfaceGeneratorSetting(String name) {
        super(name);
    }

    @Override
    public SurfaceGenerator getDefaultValue() {
        return new SimpleSurfaceGenerator();
    }

    @Override
    public SurfaceGenerator read(String string) throws InvalidConfigException {
        if (string.length() > 0) {
            SurfaceGenerator mesa = MesaSurfaceGenerator.getFor(string);
            if (mesa != null) {
                return mesa;
            }
            String[] parts = StringHelper.readCommaSeperatedString(string);
            return new MultipleLayersSurfaceGenerator(parts);
        }
        return new SimpleSurfaceGenerator();
    }

}
