package me.marc_val_96.bukkit.advancedworldgenerator.selection;

import java.util.UUID;

public final class SelectionOptions {
    private final boolean air, water, lava;
    private final UUID sender;

    public SelectionOptions(boolean air, boolean water, boolean lava, UUID sender) {
        this.air = air;
        this.water = water;
        this.lava = lava;
        this.sender = sender;
    }

    public boolean isAir() {
        return air;
    }

    public boolean isWater() {
        return water;
    }

    public boolean isLava() {
        return lava;
    }

    public UUID getSender() {
        return sender;
    }
}
