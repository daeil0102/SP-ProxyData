package net.teujaem.plugin.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class LoadConfig {

    public static ConfigManager load(Plugin plugin) {

        File file = new File(plugin.getDataFolder(), "config.yaml");

        if (!file.exists()) {
            plugin.saveResource("config.yaml", false);
        }

        FileConfiguration config =
                YamlConfiguration.loadConfiguration(file);

        ConfigManager configManager = new ConfigManager();

        configManager.setName(
                config.getString("name", getTopFileName())
        );

        configManager.setStyle(
                config.getString("broadcast.style", "style")
        );

        configManager.setInventory(
                config.getBoolean("datalink.inventory", true)
        );

        configManager.setEnderChest(
                config.getBoolean("datalink.ender-chest", true)
        );

        configManager.setLevel(
                config.getBoolean("datalink.level", true)
        );

        configManager.setHp(
                config.getBoolean("datalink.hp", true)
        );

        configManager.setFood(
                config.getBoolean("datalink.food", true)
        );

        configManager.setWorld(
                config.getBoolean("datalink.world", true)
        );

        configManager.setLocation(
                config.getBoolean("datalink.location", true)
        );

        configManager.setGamemode(
                config.getBoolean("datalink.gamemode", true)
        );

        return configManager;
    }

    private static String getTopFileName() {
        try {
            return new File(".").getCanonicalFile().getName();
        } catch (IOException e) {
            return "";
        }
    }
}