package net.teujaem.plugin.chat.listener;

import net.teujaem.plugin.SPProxyData;
import net.teujaem.plugin.api.ProxyData;
import net.teujaem.plugin.websoket.PluginMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class ChatFromLocalListener implements Listener {


    @EventHandler
    public void onChatEvent(PlayerChatEvent event) {

        SPProxyData plugin = SPProxyData.getInstance();

        Map<String, Object> data = new HashMap<>();
        data.put("eventname", "OnChat");
        data.put("value", event.getMessage());
        data.put("server", plugin.getConfigManager().getName());

        PluginMessage pluginMessage = new PluginMessage(
                "server",
                plugin.getName(),
                event.getPlayer().getUniqueId(),
                event.getPlayer().getName(),
                data
        );

        ProxyData.sendToServer(pluginMessage);

        event.setCancelled(true);
    }
}
