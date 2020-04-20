package me.marc_val_96.bukkit.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.configuration.ServerConfigProvider;
import com.marc_val_96.advancedworldgenerator.configuration.standard.PluginStandardValues;
import com.marc_val_96.advancedworldgenerator.generator.biome.VanillaBiomeGenerator;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.StructureNames;
import me.marc_val_96.bukkit.advancedworldgenerator.commands.AWGCommandExecutor;
import me.marc_val_96.bukkit.advancedworldgenerator.events.AWGListener;
import me.marc_val_96.bukkit.advancedworldgenerator.generator.AWGChunkGenerator;
import me.marc_val_96.bukkit.advancedworldgenerator.generator.BukkitVanillaBiomeGenerator;
import me.marc_val_96.bukkit.advancedworldgenerator.generator.structures.AWGRareBuildingGen;
import me.marc_val_96.bukkit.advancedworldgenerator.generator.structures.AWGVillageGen;
import net.minecraft.server.v1_12_R1.WorldGenFactory;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class AWGPlugin extends JavaPlugin {

    public final HashMap<String, BukkitWorld> worlds = new HashMap<String, BukkitWorld>();
    private final HashMap<String, BukkitWorld> notInitedWorlds = new HashMap<String, BukkitWorld>();
    public AWGListener listener;
    public AWGCommandExecutor commandExecutor;
    /*
     * Debug setting. Set it to true to make AWG try to disable
     * itself. However, terrain generators aren't cleaned up properly by
     * Bukkit, so this won't really work until that bug is fixed.
     */
    public boolean cleanupOnDisable = false;

    @Override
    public void onDisable() {
        if (cleanupOnDisable) {
            // Cleanup worlds
            for (BukkitWorld world : worlds.values()) {
                world.disable();
            }
            worlds.clear();

            AWG.stopEngine();
        }
    }

    @Override
    public void onEnable() {
        AWG.setEngine(new BukkitEngine(this));
        if (!Bukkit.getWorlds().isEmpty() && !cleanupOnDisable) {
            // Reload "handling"
            // (worlds are already loaded and AWG didn't clean up itself)
            AWG.log(LogMarker.FATAL, Arrays.asList(
                    "The server was just /reloaded! AWG has problems handling this, ",
                    "as old parts from before the reload have not been cleaned up. ",
                    "Unexpected things may happen! Please restart the server! ",
                    "In the future, instead of /reloading, please restart the server, ",
                    "or reload a plugin using it's built-in command (like /awg reload), ",
                    "or use a plugin managing plugin that can reload one plugin at a time."));
            setEnabled(false);
        } else {
            // Register vanilla generator
            AWG.getBiomeModeManager().register(VanillaBiomeGenerator.GENERATOR_NAME, BukkitVanillaBiomeGenerator.class);

            // Register structures
            try {
                Method registerStructure = WorldGenFactory.class.getDeclaredMethod("b", Class.class, String.class);
                registerStructure.setAccessible(true);
                registerStructure.invoke(null, AWGRareBuildingGen.RareBuildingStart.class, StructureNames.RARE_BUILDING);
                registerStructure.invoke(null, AWGVillageGen.VillageStart.class, StructureNames.VILLAGE);
            } catch (Exception e) {
                AWG.log(LogMarker.FATAL, "Failed to register structures:");
                AWG.printStackTrace(LogMarker.FATAL, e);
            }

            // Start the engine
            this.commandExecutor = new AWGCommandExecutor(this);
            this.listener = new AWGListener(this);
            Bukkit.getMessenger().registerOutgoingPluginChannel(this, PluginStandardValues.ChannelName);

            AWG.log(LogMarker.INFO, "Global objects loaded, waiting for worlds to load");

        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return this.commandExecutor.onCommand(sender, command, label, args);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        if (worldName.isEmpty()) {
            AWG.log(LogMarker.DEBUG, "Ignoring empty world name. Is some generator plugin checking if \"AWG\" is a valid world name?");
            return new AWGChunkGenerator(this);
        }

        // Check if not already enabled
        BukkitWorld world = worlds.get(worldName);
        if (world != null) {
            AWG.log(LogMarker.DEBUG, "Already enabled for ''{}''", worldName);
            return world.getChunkGenerator();
        }

        AWG.log(LogMarker.INFO, "Starting to enable world ''{}''...", worldName);

        // Create BukkitWorld instance
        BukkitWorld localWorld = new BukkitWorld(worldName);

        // Load settings
        File baseFolder = getWorldSettingsFolder(worldName);
        ServerConfigProvider configs = new ServerConfigProvider(baseFolder, localWorld);
        localWorld.setSettings(configs);

        // Add the world to the to-do list
        this.notInitedWorlds.put(worldName, localWorld);

        // Get the right chunk generator
        AWGChunkGenerator generator = null;
        switch (configs.getWorldConfig().ModeTerrain) {
            case Normal:
            case TerrainTest:
            case OldGenerator:
            case NotGenerate:
                generator = new AWGChunkGenerator(this);
                break;
            case Default:
                break;
        }

        // Set and return the generator
        localWorld.setChunkGenerator(generator);
        return generator;
    }

    public File getWorldSettingsFolder(String worldName) {
        File baseFolder = new File(this.getDataFolder(), "worlds" + File.separator + worldName);
        if (!baseFolder.exists()) {
            if (!baseFolder.mkdirs())
                AWG.log(LogMarker.FATAL, "Can't create folder ", baseFolder.getName());
        }
        return baseFolder;
    }

    public void onWorldInit(World world) {
        if (this.notInitedWorlds.containsKey(world.getName())) {
            // Remove the world from the to-do list
            BukkitWorld bukkitWorld = this.notInitedWorlds.remove(world.getName());

            // Enable and register the world
            bukkitWorld.enable(world);
            this.worlds.put(world.getName(), bukkitWorld);

            // Show message
            AWG.log(LogMarker.INFO, "World {} is now enabled!", bukkitWorld.getName());
        }
    }

    public void onWorldUnload(World world) {
        // Remove the world from the to-do list
        this.notInitedWorlds.remove(world.getName());
        if (this.worlds.containsKey(world.getName())) {
            // Disable and Remove the world from enabled list
            this.worlds.get(world.getName()).disable();
            this.worlds.remove(world.getName());
        }
        // Show message
        AWG.log(LogMarker.INFO, "World {} is now unloaded!", world.getName());
    }

}
