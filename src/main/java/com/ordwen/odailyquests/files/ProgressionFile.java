package com.ordwen.odailyquests.files;

import com.ordwen.odailyquests.ODailyQuests;
import com.ordwen.odailyquests.tools.PluginLogger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ProgressionFile {

    private static File progressionFile;
    private static FileConfiguration progression;
    /**
     * Getting instance of main class.
     */
    private final ODailyQuests oDailyQuests;
    /**
     * Main class instance constructor.
     *
     * @param oDailyQuests main class.
     */
    public ProgressionFile(ODailyQuests oDailyQuests) {
        this.oDailyQuests = oDailyQuests;
    }

    /**
     * Get the configuration file.
     *
     * @return config file.
     */
    public static FileConfiguration getProgressionFileConfiguration() {
        return progression;
    }

    /**
     * Get the file.
     *
     * @return file.
     */
    public static File getProgressionFile() {
        return progressionFile;
    }

    /**
     * Init progression file.
     */
    public void loadProgressionFile() {

        progressionFile = new File(oDailyQuests.getDataFolder(), "progression.yml");

        if (!progressionFile.exists()) {
            oDailyQuests.saveResource("progression.yml", false);
            PluginLogger.info("Progression file created (YAML).");
        }

        progression = new YamlConfiguration();

        try {
            progression.load(progressionFile);
            PluginLogger.fine("Progression file successfully loaded (YAML).");
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occurred on the load of the progression file.");
            PluginLogger.error("Please inform the developer.");
            PluginLogger.error(e.getMessage());
        }
    }
}
