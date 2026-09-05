package net.teujaem.spProxyData;

import net.teujaem.spProxyData.broadcast.command.BroadcastCmd;
import net.teujaem.spProxyData.broadcast.listener.BroadcastListener;
import net.teujaem.spProxyData.chat.listener.ChatFromLocalListener;
import net.teujaem.spProxyData.chat.listener.ChatFromProxyListener;
import net.teujaem.spProxyData.config.ConfigManager;
import net.teujaem.spProxyData.config.LoadConfig;
import net.teujaem.spProxyData.player.listener.DataLoadingListener;
import net.teujaem.spProxyData.player.listener.LoadData;
import net.teujaem.spProxyData.player.listener.SaveData;
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
        getServer().getPluginManager().registerEvents(new ChatFromLocalListener(), this);
        getServer().getPluginManager().registerEvents(new ChatFromProxyListener(), this);

        configManager = LoadConfig.load(this);
    }

}
