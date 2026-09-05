package net.teujaem.spProxyData.broadcast.api;

import net.teujaem.spProxyData.broadcast.handler.BroadcastHandler;

public class Broadcast {

    public static void toServer(String message) {
        BroadcastHandler broadcastHandler = new BroadcastHandler();
        broadcastHandler.toServer(message);
    }
}
