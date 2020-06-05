package com.marc_val_96.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.configuration.ConfigFunctionsManager;
import com.marc_val_96.advancedworldgenerator.configuration.PluginConfig;
import com.marc_val_96.advancedworldgenerator.configuration.io.FileSettingsReader;
import com.marc_val_96.advancedworldgenerator.configuration.io.FileSettingsWriter;
import com.marc_val_96.advancedworldgenerator.configuration.standard.PluginStandardValues;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObject;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObjectManager;
import com.marc_val_96.advancedworldgenerator.events.EventHandler;
import com.marc_val_96.advancedworldgenerator.events.EventPriority;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.generator.biome.BiomeModeManager;
import com.marc_val_96.advancedworldgenerator.generator.resource.Resource;
import com.marc_val_96.advancedworldgenerator.logging.Logger;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.NamedBinaryTag;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AWGEngine {

    private BiomeModeManager biomeManagers;
    private List<EventHandler> cancelableEventHandlers = new ArrayList<EventHandler>(5);
    private ConfigFunctionsManager configFunctionsManager;
    private CustomObjectManager customObjectManager;
    private List<EventHandler> monitoringEventHandlers = new ArrayList<EventHandler>(5);
    private PluginConfig pluginConfig;
    private final Logger logger;

    public AWGEngine(Logger logger) {
        this.logger = logger;
    }


    public boolean fireCanCustomObjectSpawnEvent(CustomObject object, LocalWorld world, int x, int y, int z) {
        boolean success = true;
        for (EventHandler handler : cancelableEventHandlers) {
            if (!handler.canCustomObjectSpawn(object, world, x, y, z, !success)) {
                success = false;
            }
        }
        for (EventHandler handler : monitoringEventHandlers) {
            handler.canCustomObjectSpawn(object, world, x, y, z, !success);
        }
        return success;
    }

    /**
     * Fires the onPopulateEnd event.
     * <p>
     *
     * @see EventHandler#onPopulateEnd(LocalWorld, Random, boolean, int, int)
     */
    public void firePopulationEndEvent(LocalWorld world, Random random, boolean villageInChunk, ChunkCoordinate chunkCoord) {
        for (EventHandler handler : cancelableEventHandlers)
            handler.onPopulateEnd(world, random, villageInChunk, chunkCoord.getChunkX(), chunkCoord.getChunkZ());
        for (EventHandler handler : monitoringEventHandlers)
            handler.onPopulateEnd(world, random, villageInChunk, chunkCoord.getChunkX(), chunkCoord.getChunkZ());
    }

    /**
     * Fires the onPopulateStart event.
     * <p>
     *
     * @see EventHandler#onPopulateStart(LocalWorld, Random, boolean, int,
     * int)
     */
    public void firePopulationStartEvent(LocalWorld world, Random random, boolean villageInChunk, ChunkCoordinate chunkCoord) {
        for (EventHandler handler : cancelableEventHandlers)
            handler.onPopulateStart(world, random, villageInChunk, chunkCoord.getChunkX(), chunkCoord.getChunkZ());
        for (EventHandler handler : monitoringEventHandlers)
            handler.onPopulateStart(world, random, villageInChunk, chunkCoord.getChunkX(), chunkCoord.getChunkZ());
    }

    /**
     * Fires the onResourceProcess event.
     * <p>
     *
     * @return True if the event handlers allow that the resource is spawned,
     * false otherwise.
     * @see EventHandler#onResourceProcess(Resource, LocalWorld, Random,
     * boolean, int, int, boolean)
     */
    public boolean fireResourceProcessEvent(Resource resource, LocalWorld world, Random random, boolean villageInChunk, int chunkX,
                                            int chunkZ) {
        boolean success = true;
        for (EventHandler handler : cancelableEventHandlers) {
            if (!handler.onResourceProcess(resource, world, random, villageInChunk, chunkX, chunkZ, !success)) {
                success = false;
            }
        }
        for (EventHandler handler : monitoringEventHandlers) {
            handler.onResourceProcess(resource, world, random, villageInChunk, chunkX, chunkZ, !success);
        }
        return success;
    }

    /**
     * Returns the biome managers. Register your own biome manager here.
     * <p/>
     * <p>
     *
     * @return The biome managers.
     */
    public BiomeModeManager getBiomeModeManager() {
        return biomeManagers;
    }

    /**
     * Returns the Resource manager.
     * <p/>
     * <p>
     *
     * @return The Resource manager.
     */
    public ConfigFunctionsManager getConfigFunctionsManager() {
        return configFunctionsManager;
    }

    /**
     * Returns the CustomObject manager, with hooks to spawn CustomObjects.
     * <p/>
     * <p>
     *
     * @return The CustomObject manager.
     */
    public CustomObjectManager getCustomObjectManager() {
        return customObjectManager;
    }

    /**
     * Returns the folder where the global objects are stored in.
     * <p/>
     * <p>
     *
     * @return Folder where the global objects are stored.
     */
    public abstract File getGlobalObjectsDirectory();

    /**
     * Gets the logger to which all messages should be logged.
     *
     * @return The logger.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Returns the global config file.
     * <p>
     *
     * @return The global config file.
     */
    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    /**
     * Returns the root data folder for AWG.
     * <p/>
     * <p>
     *
     * @return The root data folder for AWG.
     */
    public abstract File getTCDataFolder();

    /**
     * Returns the world object with the given name.
     * <p/>
     * <p>
     *
     * @param name The name of the world.
     *             <p/>
     * @return The world object.
     */
    public abstract LocalWorld getWorld(String name);

    public void onShutdown() {
        // Shutdown all loaders
        customObjectManager.shutdown();

        // Null out values to help the garbage collector
        customObjectManager = null;
        configFunctionsManager = null;
        biomeManagers = null;
        pluginConfig = null;
        cancelableEventHandlers.clear();
        monitoringEventHandlers.clear();
        cancelableEventHandlers = null;
        monitoringEventHandlers = null;
    }

    public void onStart() {
        // Start the engine
        configFunctionsManager = new ConfigFunctionsManager();
        customObjectManager = new CustomObjectManager();
        biomeManagers = new BiomeModeManager();

        // Do pluginConfig loading and then log anything that happened
        // LogManager and PluginConfig are now decoupled, thank the lord!
        File pluginConfigFile = new File(getTCDataFolder(), PluginStandardValues.ConfigFilename);
        pluginConfig = new PluginConfig(FileSettingsReader.read("PluginConfig", pluginConfigFile));
        FileSettingsWriter.writeToFile(pluginConfig.getSettingsAsMap(), pluginConfigFile, pluginConfig.SettingsMode);
        logger.setLevel(pluginConfig.getLogLevel().getLevel());

        // Fire start event
        for (EventHandler handler : cancelableEventHandlers) {
            handler.onStart();
        }
        for (EventHandler handler : monitoringEventHandlers) {
            handler.onStart();
        }

        // Load global objects after the event has been fired, so that custom
        // object types are also taken into account
        customObjectManager.loadGlobalObjects();
    }

    /**
     * Register your event handler here with normal priority. You can do this
     * before AWG is started.
     * <p/>
     * <p>
     *
     * @param handler The handler that will receive the events.
     */
    public void registerEventHandler(EventHandler handler) {
        cancelableEventHandlers.add(handler);
    }

    /**
     * Register you event handler here with the given priority. You can do
     * this before AWG is started.
     * <p/>
     * <p>
     *
     * @param handler  The handler that will receive the events.
     * @param priority The priority of the event.
     */
    public void registerEventHandler(EventHandler handler, EventPriority priority) {
        if (priority == EventPriority.CANCELABLE) {
            cancelableEventHandlers.add(handler);
        } else {
            monitoringEventHandlers.add(handler);
        }
    }

    /**
     * Gets the material with the given name. The name can be one of
     * Minecraft's material names, a modded material name, one of the names
     * from {@link DefaultMaterial} or a block id (deprecated). Block data can
     * be included
     * in the name using the "blockName:blockData" syntax or the "blockName.id"
     * syntax (deprecated).
     * <p>
     * Examples of valid block names:
     * <ul>
     * <li>STONE</li>
     * <li>minecraft:stone</li>
     *
     * @param name The name of the material.
     * @return The material, or null if not found.
     * <p>
     * @throws InvalidConfigException If no material with that name exists.
     */
    public abstract LocalMaterialData readMaterial(String name) throws InvalidConfigException;

    /**
     * Creates a {@link LocalMaterialData} based on the given
     * {@link DefaultMaterial} and block data.
     * <p>
     *
     * @param defaultMaterial The block type.
     * @param blockData       The block data.
     * @return The materialData.
     */
    public abstract LocalMaterialData toLocalMaterialData(DefaultMaterial defaultMaterial, int blockData);

    /**
     * Creates a {@link NamedBinaryTag} by parsing the given input string. The
     * string is "Mojangson"-formatted string, which looks something like JSON
     * with optional quotes: <code>{Name:Bob,Id:3}</code>.
     *
     * @param mojangson The Mojangson.
     * @return The tag.
     * @throws InvalidConfigException If the provided Mojangson could not be
     *                                parsed.
     */
    public abstract NamedBinaryTag toNamedBinaryTag(String mojangson) throws InvalidConfigException;

    public abstract LocalMaterialData toLocalMaterialData(DefaultMaterial defaultMaterial);
}
