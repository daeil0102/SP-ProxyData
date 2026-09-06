package net.teujaem.spProxyData.broadcast.handler;

import net.teujaem.spFramework.api.ProxyData;
import net.teujaem.spProxyData.SPProxyData;
import net.teujaem.spFramework.websoket.PluginMessage;

import java.util.HashMap;
import java.util.Map;

public class BroadcastHandler {

    public void toServer(String message) {

        SPProxyData plugin = SPProxyData.getInstance();

        Map<String, Object> data = new HashMap<>();
        data.put("eventname", "Broadcast");
        data.put("value", message);
        data.put("server", plugin.getConfigManager().getName());

        PluginMessage pluginMessage = new PluginMessage(
                "server",
                plugin.getName(),
                null,
                null,
                data
        );

        ProxyData.sendToServer(pluginMessage);

    }
}
