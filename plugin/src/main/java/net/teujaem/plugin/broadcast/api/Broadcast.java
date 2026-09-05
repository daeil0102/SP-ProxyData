package net.teujaem.plugin.broadcast.api;

import net.teujaem.plugin.broadcast.handler.BroadcastHandler;

public class Broadcast {

    public static void toServer(String message) {
        BroadcastHandler broadcastHandler = new BroadcastHandler();
        broadcastHandler.toServer(message);
    }
}
