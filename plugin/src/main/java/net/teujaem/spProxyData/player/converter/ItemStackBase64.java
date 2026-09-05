package net.teujaem.spProxyData.player.converter;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemStackBase64 {

    public static String serialize(ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(output);

            dataOutput.writeObject(itemStack);
            dataOutput.close();

            return Base64.getEncoder().encodeToString(output.toByteArray());
        } catch (IOException e) {
            throw new IllegalArgumentException("ItemStack을 Base64로 변환할 수 없습니다.", e);
        }
    }

    public static ItemStack deserialize(String data) {
        if (data == null || data.isBlank()) {
            return null;
        }

        try {
            byte[] bytes = Base64.getDecoder().decode(data);

            BukkitObjectInputStream dataInput =
                    new BukkitObjectInputStream(new ByteArrayInputStream(bytes));

            ItemStack itemStack = (ItemStack) dataInput.readObject();
            dataInput.close();

            return itemStack;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Base64를 ItemStack으로 변환할 수 없습니다.", e);
        }
    }
}