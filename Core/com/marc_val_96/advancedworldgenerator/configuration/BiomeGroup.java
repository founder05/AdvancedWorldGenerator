package com.marc_val_96.advancedworldgenerator.configuration;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalBiome;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.standard.WorldStandardValues;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultBiome;

import java.util.*;
import java.util.Map.Entry;


public final class BiomeGroup extends ConfigFunction<WorldConfig> {

    public int totalGroupRarity;
    private int groupId;
    private String name;
    private int groupRarity;
    private int generationDepth = 0;
    private float avgTemp = 0;
    private Map<String, LocalBiome> biomes = new LinkedHashMap<String, LocalBiome>(32);


    public BiomeGroup(WorldConfig config, List<String> args) throws InvalidConfigException {
        super(config);
        // Must have at least a GroupName and a Biome that belongs to it
        assureSize(4, args);
        this.name = args.get(0);
        this.generationDepth = readInt(args.get(1), 0, config.GenerationDepth);
        this.groupRarity = readInt(args.get(2), 1, Integer.MAX_VALUE);
        for (String biome : readBiomes(args, 3)) {
            this.biomes.put(biome, null);
        }
    }

    /**
     * Creates a new <code>BiomeGroup</code>.
     *
     * @param config    WorldConfig this biome group is part of.
     * @param groupName The name of this group.
     * @param size      Size value of this biome group.
     * @param rarity    Rarity value of this biome group.
     * @param biomes    List of names of the biomes that spawn in this group.
     */
    public BiomeGroup(WorldConfig config, String groupName, int size, int rarity, List<String> biomes) {
        super(config);
        this.name = groupName;
        this.generationDepth = size;
        this.groupRarity = rarity;
        for (String biome : biomes) {
            this.biomes.put(biome, null);
        }
    }

    /**
     * Does general post-initialization bookkeeping, like adding the
     * LocalBiome instances and initializing the average temperature and
     * group rarity.
     *
     * @param world Used to look up biomes.
     */
    public void processBiomeData(LocalWorld world) {
        float totalTemp = 0;
        this.totalGroupRarity = 0;
        for (Iterator<Entry<String, LocalBiome>> it = this.biomes.entrySet().iterator(); it.hasNext(); ) {
            Entry<String, LocalBiome> entry = it.next();
            String biomeName = entry.getKey();

            LocalBiome localBiome = world.getBiomeByName(biomeName);
            if (localBiome == null) {
                AWG.log(LogMarker.WARN, "Biome '{}' in group '{}' is not found in world '{}'", biomeName, this.name, world.getName());
                it.remove();
                continue;
            }
            entry.setValue(localBiome);

            BiomeConfig biomeConfig = localBiome.getBiomeConfig();
            totalTemp += biomeConfig.biomeTemperature;
            this.totalGroupRarity += biomeConfig.biomeRarity;
        }
        this.avgTemp = totalTemp / this.biomes.size();
    }

    @Override
    public String toString() {
        return "BiomeGroup(" + name + ", " + generationDepth + ", " + groupRarity + ", " + StringHelper.join(biomes.keySet(), ", ") + ")";
    }

    /**
     * Reads all biomes from the start position until the end of the
     * list.
     *
     * @param strings The input strings.
     * @param start   The position to start. The first element in the list
     *                has index 0, the last one size() - 1.
     * @return All biome names.
     * @throws InvalidConfigException If one of the elements in the list is
     *                                not a valid block id.
     */
    protected List<String> readBiomes(List<String> strings, int start) throws InvalidConfigException {
        return new ArrayList<String>(strings.subList(start, strings.size()));
    }

    /**
     * Gets the name of this biome group.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Filters the biomes in this group, removing all biomes that have an
     * unrecognized name.
     *
     * @param customBiomeNames Set of known custom biomes.
     */
    void filterBiomes(Set<String> customBiomeNames) {
        for (Iterator<String> it = this.biomes.keySet().iterator(); it.hasNext(); ) {
            String biomeName = it.next();
            if (DefaultBiome.Contain(biomeName) || customBiomeNames.contains(biomeName)) {
                continue;
            }
            // Invalid biome name, remove
            AWG.log(LogMarker.WARN, "Invalid biome name {} in biome group {}", biomeName, this.name);
            it.remove();
        }
    }

    /**
     * Gets whether this group contains the given biome. Biome name is case
     * sensitive.
     *
     * @param name Name of the biome.
     * @return True if this group contains the given biome, false otherwise.
     */
    public boolean containsBiome(String name) {
        return this.biomes.containsKey(name);
    }

    /**
     * Gets the numerical id for this group. Group ids are sequential and
     * based on the order they are placed in the configuration files.
     *
     * @return The numerical id.
     */
    public int getGroupId() {
        return this.groupId;
    }

    /**
     * Sets the group id to the given value. Don't use this if the group is
     * already registered in a collection.
     *
     * @param groupId The new group id.
     * @throws IllegalArgumentException If the group id is larger than
     *                                  {@link BiomeGroupManager#MAX_BIOME_GROUP_COUNT}.
     */
    void setGroupId(int groupId) {
        if (groupId > BiomeGroupManager.MAX_BIOME_GROUP_COUNT) {
            throw new IllegalArgumentException("Tried to set group id to " + groupId
                    + ", max allowed is " + BiomeGroupManager.MAX_BIOME_GROUP_COUNT);
        }

        this.groupId = groupId;
    }

    /**
     * Gets whether this group is considered cold. This is based on the
     * average temperatures of the biomes in the group.
     *
     * @return True if the group is cold, false otherwise.
     */
    public boolean isColdGroup() {
        return this.avgTemp < WorldStandardValues.ICE_GROUP_MAX_TEMP;
    }

    @Override
    public boolean isAnalogousTo(ConfigFunction<WorldConfig> other) {
        if (other instanceof BiomeGroup) {
            BiomeGroup group = (BiomeGroup) other;
            return group.name.equalsIgnoreCase(this.name);
        }
        return false;
    }

    public SortedMap<Integer, LocalBiome> getDepthMap(int depth) {
        int cumulativeBiomeRarity = 0;
        TreeMap<Integer, LocalBiome> map = new TreeMap<Integer, LocalBiome>();
        for (Entry<String, LocalBiome> biome : this.biomes.entrySet()) {                                                           //>>	When depth given is negative, include all biomes in group
            if (biome.getValue().getBiomeConfig().biomeSize == depth || depth < 0) {
                cumulativeBiomeRarity += biome.getValue().getBiomeConfig().biomeRarity;
                map.put(cumulativeBiomeRarity, biome.getValue());
            }
        }
        return map;
    }

    public int getGroupRarity() {
        return groupRarity;
    }

    public int getGenerationDepth() {
        return generationDepth;
    }

    /**
     * Gets whether this group has any biomes.
     *
     * @return True if the group has no biomes and is thus empty, false
     * if the group has biomes.
     */
    public boolean hasNoBiomes() {
        return biomes.isEmpty();
    }

}
