package online.visionacademy.ui.common;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.Border;
import online.visionacademy.ui.util.Direction;

public class PaddingManager {

    private PaddingManager() {

    }

    public static void setPadding(JPanel target, int size) {
        Border padding = PaddingFactory.createPadding(size);
        target.setBorder(padding);
    }

    public static void setPadding(JPanel target, int top, int left, int bottom, int right) {
        Border padding = PaddingFactory.createPadding(top, left, bottom, right);
        target.setBorder(padding);
    }

    public static void setPaddingWithBorder(JPanel target, int paddingSize, Direction direction, int size, Color color) {
        Border paddingWithBorder = PaddingFactory.createPaddingWithBorder(paddingSize, direction, size, color);
        target.setBorder(paddingWithBorder);
    }

    public static void setPaddingWithRadiusBorder(JPanel target, int paddingSize) {
        Border paddingWithRadius = PaddingFactory.createPaddingWithRadiusBorder(paddingSize);
        target.setBorder(paddingWithRadius);
    }
}
