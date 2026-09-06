package net.teujaem.spProxyData.chat.listener;

import net.teujaem.spProxyData.SPProxyData;
import net.teujaem.spFramework.api.event.ProxyEvent;
import net.teujaem.spFramework.websoket.PluginMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatFromProxyListener implements Listener {


    @EventHandler
    public void onChatEvent(ProxyEvent event) {

        SPProxyData plugin = SPProxyData.getInstance();

        if (!event.getPluginName().equals(plugin.getName())) return;
        if (!event.getEventName().equals("OnChat")) return;

        PluginMessage pluginMessage = event.getRawMessage();

        String playerName = pluginMessage.username();

        if (event.getValue() == null) return;


        String message = event.getValue().toString();

        plugin.getServer().broadcastMessage(playerName + ": " + message);

    }

}
