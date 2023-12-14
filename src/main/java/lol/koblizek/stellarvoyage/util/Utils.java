package lol.koblizek.stellarvoyage.util;

import org.joml.Vector3f;

import java.awt.*;
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
}
