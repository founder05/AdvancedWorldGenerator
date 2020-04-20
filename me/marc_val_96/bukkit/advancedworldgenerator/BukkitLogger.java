package me.marc_val_96.bukkit.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.configuration.standard.PluginStandardValues;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import com.marc_val_96.advancedworldgenerator.logging.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Implementation of {@link Logger} for Bukkit.
 */
final class BukkitLogger extends Logger {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PluginStandardValues.PLUGIN_NAME);

    BukkitLogger(java.util.logging.Logger logger) {

    }

    @Override
    public void log(LogMarker level, String message, Object... params) {
        if (minimumLevel.compareTo(level) < 0) {
            // Only log messages that we want to see...
            return;
        }
        String logPrefix = "[" + PluginStandardValues.PLUGIN_NAME + "] ";
        switch (level) {
            case FATAL:
                logger.fatal(logPrefix + message, params);
                break;
            case ERROR:
                logger.error(logPrefix + message, params);
                break;
            case WARN:
                logger.warn(logPrefix + message, params);
                break;
            case INFO:
                logger.info(logPrefix + message, params);
                break;
            case DEBUG:
                logger.info(logPrefix + "[Debug] " + message, params);
                break;
            case TRACE:
                logger.trace(logPrefix + "[Trace] " + message, params);
                break;
            default:
                // Unknown log level, should never happen
                logger.info(logPrefix + message, params); // Still log the message
                throw new RuntimeException("Unknown log marker: " + level);
        }
    }
}
