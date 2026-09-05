package net.teujaem.plugin.broadcast.handler;

import net.teujaem.plugin.api.ProxyData;
import net.teujaem.plugin.SPProxyData;
import net.teujaem.plugin.websoket.PluginMessage;

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
