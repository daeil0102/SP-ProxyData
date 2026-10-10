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
        if (plugin == null || !plugin.isEnabled()) return;

        try {
            if (!plugin.getName().equalsIgnoreCase(event.getPluginName())) return;
            if (!"OnChat".equalsIgnoreCase(event.getEventName()) || event.getValue() == null) return;

            PluginMessage pluginMessage = event.getRawMessage();
            if (pluginMessage == null || pluginMessage.username() == null) return;

            plugin.getServer().broadcastMessage(pluginMessage.username() + ": " + event.getValue());
        } catch (Exception | LinkageError exception) {
            plugin.getLogger().warning("프록시 채팅 수신 처리 실패: " + exception.getMessage());
        }
    }
}
