package com.marc_val_96.advancedworldgenerator.configuration.standard;

import com.marc_val_96.advancedworldgenerator.configuration.PluginConfig.LogLevels;
import com.marc_val_96.advancedworldgenerator.configuration.settingType.Setting;
import com.marc_val_96.advancedworldgenerator.configuration.settingType.Settings;

public class PluginStandardValues extends Settings {

    //>> Files
    //>>	Main Plugin Config
    public static final String ConfigFilename = "AWG.ini";

    //>> Folders
    public static final String BiomeConfigDirectoryName = "GlobalBiomes";
    public static final String BO_DirectoryName = "GlobalObjects";

    //>>  Network
    public static final String ChannelName = "AWG";
    public static final int ProtocolVersion = 5;

    //>>  Plugin Defaults
    public static final Setting<LogLevels> LogLevel = enumSetting("LogLevel", LogLevels.Standard);

    /**
     * Name of the plugin, "AWG".
     */
    public static final String PLUGIN_NAME = "AWG";

}