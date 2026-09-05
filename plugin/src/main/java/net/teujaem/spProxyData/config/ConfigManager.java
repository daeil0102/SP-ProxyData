package net.teujaem.spProxyData.config;

public class ConfigManager {

    private String name = "name";
    private String style = "style";

    private boolean inventory;
    private boolean enderChest;
    private boolean level;
    private boolean hp;
    private boolean food;
    private boolean world;
    private boolean location;
    private boolean gamemode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isInventory() {
        return inventory;
    }

    public void setInventory(boolean inventory) {
        this.inventory = inventory;
    }

    public boolean isEnderChest() {
        return enderChest;
    }

    public void setEnderChest(boolean enderChest) {
        this.enderChest = enderChest;
    }

    public boolean isLevel() {
        return level;
    }

    public void setLevel(boolean level) {
        this.level = level;
    }

    public boolean isHp() {
        return hp;
    }

    public void setHp(boolean hp) {
        this.hp = hp;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWorld() {
        return world;
    }

    public void setWorld(boolean world) {
        this.world = world;
    }

    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean location) {
        this.location = location;
    }

    public boolean isGamemode() {
        return gamemode;
    }

    public void setGamemode(boolean gamemode) {
        this.gamemode = gamemode;
    }
}