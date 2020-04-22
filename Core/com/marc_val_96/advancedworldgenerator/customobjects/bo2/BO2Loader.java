package com.marc_val_96.advancedworldgenerator.customobjects.bo2;

import com.marc_val_96.advancedworldgenerator.customobjects.CustomObject;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObjectLoader;

import java.io.File;

public class BO2Loader implements CustomObjectLoader {
    @Override
    public CustomObject loadFromFile(String objectName, File file) {
        return new BO2(objectName, file);
    }

    @Override
    public void onShutdown() {
        // Stub method
    }
}
