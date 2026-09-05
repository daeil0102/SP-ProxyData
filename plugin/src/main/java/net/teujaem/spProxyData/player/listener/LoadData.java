package net.teujaem.spProxyData.player.listener;

import net.teujaem.spProxyData.SPProxyData;
import net.teujaem.plugin.api.DataBase;
import net.teujaem.spProxyData.config.ConfigManager;
import net.teujaem.spProxyData.player.api.event.PlayerDataLoadEvent;
import net.teujaem.spProxyData.player.entity.PlayerEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LoadData implements Listener {

    private static final DataBase DATABASE = new DataBase(PlayerEntity.class);
    private static final Map<UUID, PlayerEntity> CACHE = new ConcurrentHashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        ConfigManager config = SPProxyData.getInstance().getConfigManager();

        DataLoadingListener.add(uuid);

        DATABASE.find(PlayerEntity.class, uuid).thenAccept(entity -> {
            Bukkit.getScheduler().runTask(SPProxyData.getInstance(), () -> {
                PlayerEntity playerEntity = entity;

                if (playerEntity == null) {
                    playerEntity = create(player, config);
                    CACHE.put(uuid, playerEntity);
                    DATABASE.save(playerEntity);
                } else {
                    CACHE.put(uuid, playerEntity);
                    apply(player, playerEntity, config);
                }

                Bukkit.getPluginManager().callEvent(
                        new PlayerDataLoadEvent(player, playerEntity)
                );
            });
        });
    }

    private PlayerEntity create(Player player, ConfigManager config) {
        PlayerEntity entity = new PlayerEntity(player.getUniqueId());

        if (config.isInventory()) {
            entity.setInventory(toMap(player.getInventory().getContents()));
        }

        if (config.isEnderChest()) {
            entity.setEnderChest(toMap(player.getEnderChest().getContents()));
        }

        if (config.isLevel()) {
            entity.setLevel(player.getLevel());
        }

        if (config.isHp()) {
            entity.setHp(player.getHealth());
        }

        if (config.isFood()) {
            entity.setFood(player.getFoodLevel());
        }

        if (config.isWorld()) {
            entity.setWorld(player.getWorld().getName());
        }

        if (config.isLocation()) {
            entity.setLocationX(player.getLocation().getX());
            entity.setLocationY(player.getLocation().getY());
            entity.setLocationZ(player.getLocation().getZ());
        }

        if (config.isGamemode()) {
            entity.setGamemode(player.getGameMode());
        }

        return entity;
    }

    private void apply(Player player, PlayerEntity entity, ConfigManager config) {
        if (config.isInventory()) {
            player.getInventory().clear();

            entity.getInventory().forEach(
                    (slot, item) -> player.getInventory().setItem(slot, item)
            );
        }

        if (config.isEnderChest()) {
            player.getEnderChest().clear();

            entity.getEnderChest().forEach(
                    (slot, item) -> player.getEnderChest().setItem(slot, item)
            );
        }

        if (config.isLevel()) {
            player.setLevel(entity.getLevel());
        }

        if (config.isHp()) {
            player.setHealth(Math.min(entity.getHp(), player.getMaxHealth()));
        }

        if (config.isFood()) {
            player.setFoodLevel(entity.getFood());
        }

        if (config.isGamemode()) {
            player.setGameMode(entity.getGameMode());
        }
    }

    private Map<Integer, ItemStack> toMap(ItemStack[] items) {
        Map<Integer, ItemStack> map = new HashMap<>();

        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];

            if (item != null && !item.getType().isAir()) {
                map.put(i, item);
            }
        }

        return map;
    }

    public static PlayerEntity get(UUID uuid) {
        return CACHE.get(uuid);
    }

    public static void remove(UUID uuid) {
        CACHE.remove(uuid);
        DataLoadingListener.remove(uuid);
    }
}