package net.teujaem.plugin.chat.listener;

import net.teujaem.plugin.SPProxyData;
import net.teujaem.plugin.api.event.ProxyEvent;
import net.teujaem.plugin.websoket.PluginMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatFromProxyListener implements Listener {


    @EventHandler
    public void onChatEvent(ProxyEvent event) {

        SPProxyData plugin = SPProxyData.getInstance();

        if (!event.getPluginName().equals(plugin.getName())) return;
        if (!event.getEventName().equals("OnChat")) return;
        if (!event.getRawMessage().isFromClient()) return;

        PluginMessage pluginMessage = event.getRawMessage();

        String playerName = pluginMessage.username();

        if (pluginMessage.data().get("value") == null) return;


        String message = pluginMessage.data().get("value").toString();

        plugin.getServer().broadcastMessage(playerName + ": " + message);

    }

}
