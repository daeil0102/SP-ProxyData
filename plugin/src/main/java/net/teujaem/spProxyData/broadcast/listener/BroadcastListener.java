package net.teujaem.spProxyData.broadcast.listener;

import net.teujaem.spProxyData.util.TextColorFormatter;
import net.teujaem.spFramework.api.event.ProxyEvent;
import net.teujaem.spProxyData.SPProxyData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BroadcastListener implements Listener {


    @EventHandler
    public void onProxyEvent(ProxyEvent event) {

        SPProxyData plugin = SPProxyData.getInstance();

        if (!event.getPluginName().equals(plugin.getName())) return;
        if (!event.getEventName().equals("Broadcast")) return;

        String style = plugin.getConfigManager().getStyle();
        String message = event.getValue().toString();
        String server = event.getRawMessage().data().get("server").toString();
        String localServer = plugin.getConfigManager().getName();

        plugin.getServer().broadcastMessage(TextColorFormatter.toColored(replace(style, message, server, localServer)));

    }

    private static String replace(String style, String message, String sendServer, String localServer) {
        return style
                .replaceAll("\\{message}", message)
                .replaceAll("\\{server}", sendServer)
                .replaceAll("\\{local_server}", localServer);
    }

}