package com.marc_val_96.advancedworldgenerator.util;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.configuration.settingType.Setting;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;

import java.util.ArrayList;

/**
 * Reads and writes a material. Materials are read using
 * {@link AWG#readMaterial(String)} and written using
 * {@link LocalMaterialData#toString()}.
 *
 */
public class MaterialListSetting extends Setting<ArrayList<LocalMaterialData>>
{
    private final DefaultMaterial[] defaultValue;

    public MaterialListSetting(String name, DefaultMaterial[] defaultValue)
    {
        super(name);
        this.defaultValue = defaultValue;
    }

    @Override
    public ArrayList<LocalMaterialData> getDefaultValue()
    {
        ArrayList<LocalMaterialData> materials = new ArrayList<LocalMaterialData>();
        for(DefaultMaterial defaultMaterial : defaultValue)
        {
            materials.add(MaterialHelper.toLocalMaterialData(defaultMaterial));
        }
        return materials;
    }

    @Override
    public ArrayList<LocalMaterialData> read(String string) throws InvalidConfigException
    {
        String[] materialNames = string.split(",");
        ArrayList<LocalMaterialData> materials = new ArrayList<LocalMaterialData>();
        for(String materialName : materialNames)
        {
            LocalMaterialData material = MaterialHelper.readMaterial(materialName.trim());
            materials.add(material);
        }
        return materials;
    }

    @Override
    public String write(ArrayList<LocalMaterialData> value)
    {
        return StringHelper.join(value, ", ");
    }
}
