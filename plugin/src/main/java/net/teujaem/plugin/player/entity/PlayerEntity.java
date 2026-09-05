package net.teujaem.plugin.player.entity;

import jakarta.persistence.*;
import net.teujaem.plugin.player.converter.ItemStackBase64;
import net.teujaem.plugin.player.converter.MapJsonConverter;
import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "spproxydata_player")
public class PlayerEntity {

    @Id
    private UUID id;

    @Convert(converter = MapJsonConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private Map<Integer, String> inventory;

    @Convert(converter = MapJsonConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private Map<Integer, String> enderChest;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private double hp;

    @Column(nullable = false)
    private int food;

    @Column(nullable = false)
    private String world;

    @Column(nullable = false)
    private double locationX;

    @Column(nullable = false)
    private double locationY;

    @Column(nullable = false)
    private double locationZ;

    @Column(nullable = false)
    private String gamemode;

    protected PlayerEntity() {
    }

    public PlayerEntity(UUID id) {
        this.id = id;
        this.inventory = new HashMap<>();
        this.enderChest = new HashMap<>();
    }

    public UUID getId() {
        return id;
    }

    public void setInventory(Map<Integer, ItemStack> inventory) {
        this.inventory = serialize(inventory);
    }

    public Map<Integer, ItemStack> getInventory() {
        return deserialize(inventory);
    }

    public void setEnderChest(Map<Integer, ItemStack> enderChest) {
        this.enderChest = serialize(enderChest);
    }

    public Map<Integer, ItemStack> getEnderChest() {
        return deserialize(enderChest);
    }

    private Map<Integer, String> serialize(Map<Integer, ItemStack> items) {
        Map<Integer, String> result = new HashMap<>();

        if (items == null) {
            return result;
        }

        items.forEach((slot, item) -> {
            if (item != null && !item.getType().isAir()) {
                result.put(slot, ItemStackBase64.serialize(item));
            }
        });

        return result;
    }

    private Map<Integer, ItemStack> deserialize(Map<Integer, String> items) {
        Map<Integer, ItemStack> result = new HashMap<>();

        if (items == null) {
            return result;
        }

        items.forEach((slot, data) -> {
            if (data != null) {
                result.put(slot, ItemStackBase64.deserialize(data));
            }
        });

        return result;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public double getLocationZ() {
        return locationZ;
    }

    public void setLocationZ(double locationZ) {
        this.locationZ = locationZ;
    }

    public String getGamemode() {
        return gamemode;
    }

    public void setGamemode(GameMode gamemode) {
        this.gamemode = gamemode.name();
    }

    public GameMode getGameMode() {
        return GameMode.valueOf(gamemode);
    }
}