package com.marc_val_96.advancedworldgenerator.configuration.settingType;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.configuration.ReplacedBlocksMatrix;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;

/**
 * Setting that handles {@link ReplacedBlocksMatrix}.
 */
class ReplacedBlocksSetting extends Setting<ReplacedBlocksMatrix> {

    ReplacedBlocksSetting(String name) {
        super(name);
    }

    @Override
    public ReplacedBlocksMatrix getDefaultValue() {
        return ReplacedBlocksMatrix.createEmptyMatrix(AWG.WORLD_HEIGHT);
    }

    @Override
    public ReplacedBlocksMatrix read(String string) throws InvalidConfigException {
        return new ReplacedBlocksMatrix(string, AWG.WORLD_HEIGHT);
    }

}
