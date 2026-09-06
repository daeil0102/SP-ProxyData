package net.teujaem.spProxyData.player.listener;

import net.teujaem.spProxyData.SPProxyData;
import net.teujaem.spFramework.api.DataBase;
import net.teujaem.spProxyData.config.ConfigManager;
import net.teujaem.spProxyData.player.entity.PlayerEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SaveData implements Listener {

    private static final DataBase DATABASE = new DataBase(PlayerEntity.class);

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        PlayerEntity entity = LoadData.get(uuid);

        if (entity == null) {
            return;
        }

        ConfigManager config = SPProxyData.getInstance().getConfigManager();

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

        DATABASE.save(entity);
        LoadData.remove(uuid);
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
}