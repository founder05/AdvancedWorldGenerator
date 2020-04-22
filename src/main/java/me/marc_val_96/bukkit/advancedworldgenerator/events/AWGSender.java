package me.marc_val_96.bukkit.advancedworldgenerator.events;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.configuration.ConfigProvider;
import com.marc_val_96.advancedworldgenerator.configuration.ConfigToNetworkSender;
import com.marc_val_96.advancedworldgenerator.configuration.standard.PluginStandardValues;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import me.marc_val_96.bukkit.advancedworldgenerator.AWGPlugin;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AWGSender {

    private AWGPlugin plugin;

    public AWGSender(AWGPlugin awgPlugin) {
        this.plugin = awgPlugin;
    }

    public void send(Player player) {
        // Send the configs
        World world = player.getWorld();

        if (plugin.worlds.containsKey(world.getName())) {
            ConfigProvider configs = plugin.worlds.get(world.getName()).getConfigs();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream stream = new DataOutputStream(outputStream);

            try {
                stream.writeInt(PluginStandardValues.ProtocolVersion);
                ConfigToNetworkSender.send(configs, stream);
                stream.flush();
            } catch (IOException e) {
                AWG.printStackTrace(LogMarker.FATAL, e);
            }

            byte[] data = outputStream.toByteArray();

            player.sendPluginMessage(plugin, PluginStandardValues.ChannelName, data);
        }
    }

}
