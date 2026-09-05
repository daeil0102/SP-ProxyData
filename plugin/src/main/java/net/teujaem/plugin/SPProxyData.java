package net.teujaem.plugin;

import net.teujaem.plugin.broadcast.command.BroadcastCmd;
import net.teujaem.plugin.broadcast.listener.BroadcastListener;
import net.teujaem.plugin.config.ConfigManager;
import net.teujaem.plugin.config.LoadConfig;
import net.teujaem.plugin.player.listener.DataLoadingListener;
import net.teujaem.plugin.player.listener.LoadData;
import net.teujaem.plugin.player.listener.SaveData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SPProxyData extends JavaPlugin {

    private static SPProxyData instance;
    private static ConfigManager configManager;

    @Override
    public void onEnable() {

        instance = this;

        reload();

    }

    @Override
    public void onDisable() {

    }

    public static SPProxyData getInstance() {
        return instance;
    }


    public ConfigManager getConfigManager() {
        return configManager;
    }

    private void reload() {
        Objects.requireNonNull(getCommand("broadcast")).setExecutor(new BroadcastCmd());
        getServer().getPluginManager().registerEvents(new BroadcastListener(), this);
        getServer().getPluginManager().registerEvents(new LoadData(), this);
        getServer().getPluginManager().registerEvents(new SaveData(), this);
        getServer().getPluginManager().registerEvents(new DataLoadingListener(), this);

        configManager = LoadConfig.load(this);
    }

}
