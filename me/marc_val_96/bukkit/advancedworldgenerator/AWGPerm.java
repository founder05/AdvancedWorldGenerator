package me.marc_val_96.bukkit.advancedworldgenerator;

public enum AWGPerm {
    CMD_BIOME("cmd.biome"),
    CMD_CHECK("cmd.check"),
    CMD_HELP("cmd.help"),
    CMD_LIST("cmd.list"),
    CMD_MAP("cmd.map"),
    CMD_RELOAD("cmd.reload"),
    CMD_SPAWN("cmd.spawn");

    public final String node;

    AWGPerm(final String permissionNode) {
        this.node = "tc." + permissionNode;
    }
}
