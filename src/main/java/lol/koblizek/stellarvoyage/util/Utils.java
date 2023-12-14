package lol.koblizek.stellarvoyage.util;

import org.joml.Vector3f;

import java.awt.*;

public class Utils {
    public static Vector3f colorToVec(Color color) {
        return new Vector3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
    }
}
