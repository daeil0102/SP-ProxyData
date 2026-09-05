package net.teujaem.plugin.player.api.event;

import net.teujaem.plugin.player.entity.PlayerEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDataLoadEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final PlayerEntity entity;

    public PlayerDataLoadEvent(Player player, PlayerEntity entity) {
        this.player = player;
        this.entity = entity;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerEntity getEntity() {
        return entity;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}