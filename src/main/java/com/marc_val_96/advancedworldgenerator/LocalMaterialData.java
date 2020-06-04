package com.marc_val_96.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;

public interface LocalMaterialData
{
    /**
     * Gets whether this material can be used as an anchor point for a smooth area
     *
     * @return True if this material is a solid block, false if it is a tile-entity, half-slab, stairs(?), water, wood or leaves
     */
    public boolean isSmoothAreaAnchor(boolean allowWood, boolean ignoreWater);

    /**
     * Gets the name of this material. If a {@link #toDefaultMaterial()
     * DefaultMaterial is available,} that name is used, otherwise it's up to
     * the mod that provided this block to name it. Block data is appended to
     * the name, separated with a colon, like "WOOL:2".
     *
     * @return The name of this material.
     */
    String getName();

    /**
     * Same as {@link #getName()}.
     *
     * @return The name of this material.
     */
    @Override
    String toString();

    /**
     * Gets whether this material is a liquid, like water or lava.
     *
     * @return True if this material is a liquid, false otherwise.
     */
    boolean isLiquid();

    /**
     * Gets whether this material is solid. If there is a
     * {@link #toDefaultMaterial() DefaultMaterial available}, this property is
     * defined by {@link DefaultMaterial#isSolid()}. Otherwise, it's up to the
     * mod that provided this block to say whether it's solid or not.
     *
     * @return True if this material is solid, false otherwise.
     */
    boolean isSolid();

    /**
     * Gets whether this material is air. This is functionally equivalent to
     * {@code isMaterial(DefaultMaterial.AIR)}, but may yield better
     * performance.
     * @return True if this material is air, false otherwise.
     */
    boolean isEmptyOrAir();

    boolean isAir();

    boolean isEmpty();

    /**
     * Gets the default material belonging to this material. The block data will
     * be lost. If the material is not one of the vanilla Minecraft materials,
     * {@link DefaultMaterial#UNKNOWN_BLOCK} is returned.
     *
     * @return The default material.
     */
    DefaultMaterial toDefaultMaterial();

    /**
     * Gets whether snow can fall on this block.
     *
     * @return True if snow can fall on this block, false otherwise.
     */
    boolean canSnowFallOn();

    /**
     * Gets whether the block is of the given material. Block data is ignored,
     * as {@link DefaultMaterial} doesn't include block data.
     *
     * @param material
     *            The material to check.
     * @return True if this block is of the given material, false otherwise.
     */
    boolean isMaterial(DefaultMaterial material);

    /**
     * Gets whether this material equals another material. The block data is
     * taken into account.
     *
     * @param other
     *            The other material.
     * @return True if the materials are equal, false otherwise.
     */
    @Override
    boolean equals(Object other);

    /**
     * Gets a new material that is rotated 90 degrees. North -> west -> south ->
     * east. If this material cannot be rotated, the material itself is
     * returned.
     *
     * @return The rotated material.
     */
    LocalMaterialData rotate();

    /**
     * Gets a new material that is rotated 90 degrees. North -> west -> south ->
     * east. If this material cannot be rotated, the material itself is
     * returned.
     *
     * @return The rotated material.
     */
    LocalMaterialData rotate(int rotateTimes);

    /**
     * Parses this material through the fallback system of world if required.
     *
     * @param world The world this material will be parsed through, each world may have different fallbacks.
     */
    void parseForWorld(LocalWorld world);

    /**
     * Gets whether this material falls down when no other block supports this
     * block, like gravel and sand do.
     * @return True if this material can fall, false otherwise.
     */
    boolean canFall();

    /**
     * Check whether this material has been parsed if needed.
     * @return
     */
    public boolean isParsed();
}
