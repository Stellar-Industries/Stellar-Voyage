package lol.koblizek.stellarvoyage.util;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.joml.Vector3f;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Utils {
    public static Vector3f colorToVec(Color color) {
        return new Vector3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
    }

    public static int random(int min, int max) {
        return new Random().nextInt(min , max - 1);
    }

    public static <T> T random(T[] array) {
        return array[random(0, array.length-1)];
    }

    public static ItemStack findItemInInventory(Item item, PlayerEntity player) {
        var inv = player.getInventory();
        for (int i = 0; i < inv.size(); i++) {
            var stack = inv.getStack(i);
            if (stack == item.getDefaultStack()) {
                return stack;
            }
        }
        return null;
    }
}
