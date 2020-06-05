package com.marc_val_96.advancedworldgenerator.generator.resource;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalMaterialData;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.MaterialHelper;
import com.marc_val_96.advancedworldgenerator.util.MaterialSet;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Holds all small plants (1 or 2 blocks) of Minecraft so that users don't
 * have to use the confusing ids and data values Mojang and Bukkit gave them.
 */
public class PlantType
{
    // TODO: Update these and add new plants (underwater)

    // Builds lookup map
    private static final Map<String, PlantType> LOOKUP_MAP = new TreeMap<String, PlantType>(String.CASE_INSENSITIVE_ORDER);

    public static final PlantType Allium = register(new PlantType("Allium", DefaultMaterial.ALLIUM));
    public static final PlantType AzureBluet = register(new PlantType("AzureBluet", DefaultMaterial.AZURE_BLUET));
    public static final PlantType BlueOrchid = register(new PlantType("BlueOrchid", DefaultMaterial.BLUE_ORCHID));
    public static final PlantType BrownMushroom = register(new PlantType("BrownMushroom", DefaultMaterial.BROWN_MUSHROOM));
    public static final PlantType Dandelion = register(new PlantType("Dandelion", DefaultMaterial.DANDELION));
    public static final PlantType DeadBush = register(new PlantType("DeadBush", DefaultMaterial.DEAD_BUSH));
    public static final PlantType DoubleTallgrass = register(new PlantType("DoubleTallgrass", DefaultMaterial.GRASS, DefaultMaterial.TALL_GRASS)); // TODO: Make sure this works
    public static final PlantType Fern = register(new PlantType("Fern", DefaultMaterial.FERN));
    public static final PlantType LargeFern = register(new PlantType("LargeFern", DefaultMaterial.FERN, DefaultMaterial.LARGE_FERN));
    public static final PlantType Lilac = register(new PlantType("Lilac", DefaultMaterial.LILAC));
    public static final PlantType OrangeTulip = register(new PlantType("OrangeTulip", DefaultMaterial.ORANGE_TULIP));
    public static final PlantType OxeyeDaisy = register(new PlantType("OxeyeDaisy", DefaultMaterial.OXEYE_DAISY));
    public static final PlantType Peony = register(new PlantType("Peony", DefaultMaterial.PEONY));
    public static final PlantType PinkTulip = register(new PlantType("PinkTulip", DefaultMaterial.PINK_TULIP));
    public static final PlantType Poppy = register(new PlantType("Poppy", DefaultMaterial.POPPY));
    public static final PlantType RedMushroom = register(new PlantType("RedMushroom", DefaultMaterial.RED_MUSHROOM));
    public static final PlantType RedTulip = register(new PlantType("RedTulip", DefaultMaterial.RED_TULIP));
    public static final PlantType RoseBush = register(new PlantType("RoseBush", DefaultMaterial.ROSE_BUSH));
    public static final PlantType Sunflower = register(new PlantType("Sunflower", DefaultMaterial.SUNFLOWER));
    public static final PlantType Tallgrass = register(new PlantType("Tallgrass", DefaultMaterial.TALL_GRASS));
    public static final PlantType WhiteTulip = register(new PlantType("WhiteTulip", DefaultMaterial.WHITE_TULIP));

    /**
     * Gets the plant with the given name. The name can be one of the premade
     * plant types or a blockName:data combination.
     *
     * @param name Name of the plant type, case insensitive.
     * @return The plant type.
     * @throws InvalidConfigException If the name is invalid.
     */
    public static PlantType getPlant(String name) throws InvalidConfigException
    {
        PlantType plantType = LOOKUP_MAP.get(name);
        if (plantType == null)
        {
            LocalMaterialData material = MaterialHelper.readMaterial(name);
            // Fall back on block name + data
            plantType = new PlantType(material);
        }
        return plantType;
    }

    /**
     * Gets all registered plant types.
     *
     * @return All registered plant types.
     */
    public static Collection<PlantType> values()
    {
        return LOOKUP_MAP.values();
    }

    /**
     * Registers the plant type so that it can be retrieved using
     * {@link #getPlant(String)}.
     *
     * @param plantType The plant type.
     * @return The plant type provided.
     */
    private static PlantType register(PlantType plantType)
    {
        LOOKUP_MAP.put(plantType.toString(), plantType);
        return plantType;
    }

    private final String name;
    private LocalMaterialData topBlock;
    private LocalMaterialData bottomBlock;

    /**
     * Creates a single-block plant with the given name.
     *
     * @param name Custom name for this plant.
     * @param material The material of the block.
     */
    private PlantType(String name, DefaultMaterial material)
    {
        this.name = name;
        this.topBlock = null;
        this.bottomBlock = MaterialHelper.toLocalMaterialData(material);
    }

    /**
     * Creates a single-block plant.
     *
     * @param material Material of the plant.
     */
    private PlantType(LocalMaterialData material)
    {
        this.name = material.toString();
        this.topBlock = null;
        this.bottomBlock = material;
    }


    private PlantType(String name, DefaultMaterial materialTop, DefaultMaterial materialBottom)
    {
        this.name = name;
        this.topBlock = MaterialHelper.toLocalMaterialData(materialTop);
        this.bottomBlock = MaterialHelper.toLocalMaterialData(materialBottom);
    }

    /**
     * Gets the name of this plant type. You can get an equivalent plant back
     * type using {@link #getPlant(String)}.
     *
     * @return The name of this plant type.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Spawns this plant in the world.
     *
     * @param world The world to spawn in.
     * @param x X position of the plant.
     * @param y Y position of the lowest block of the plant.
     * @param z Z position of the plant.
     */
    public void spawn(LocalWorld world, int x, int y, int z, ChunkCoordinate chunkBeingPopulated)
    {
        parseMaterials(world, bottomBlock, null);

        world.setBlock(x, y, z, bottomBlock, null, chunkBeingPopulated);
        if (topBlock != null)
        {
            parseMaterials(world, topBlock, null);
            world.setBlock(x, y + 1, z, topBlock, null, chunkBeingPopulated);
        }
    }

    private void parseMaterials(LocalWorld world, LocalMaterialData material, MaterialSet sourceBlocks)
    {
        material.parseForWorld(world);

        if (sourceBlocks != null)
        {
            sourceBlocks.parseForWorld(world);
        }
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bottomBlock == null) ? 0 : bottomBlock.hashCode());
        result = prime * result + ((topBlock == null) ? 0 : topBlock.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof PlantType))
        {
            return false;
        }
        PlantType other = (PlantType) obj;
        if (bottomBlock == null)
        {
            if (other.bottomBlock != null)
            {
                return false;
            }
        } else if (!bottomBlock.equals(other.bottomBlock))
        {
            return false;
        }
        if (topBlock == null)
        {
            if (other.topBlock != null)
            {
                return false;
            }
        } else if (!topBlock.equals(other.topBlock))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the bottom block of this plant.
     *
     * @return The bottom block.
     */
    public LocalMaterialData getBottomMaterial()
    {
        return bottomBlock;
    }

    /**
     * Gets the top block of this plant. May be null.
     *
     * @return The top block, or null if this plant only has one block.
     */
    public LocalMaterialData getTopMaterial()
    {
        return topBlock;
    }
}
