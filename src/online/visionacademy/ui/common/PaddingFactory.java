package online.visionacademy.ui.common;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import online.visionacademy.ui.omponents.RoundedCornerBorder;
import online.visionacademy.ui.util.Direction;

public class PaddingFactory {

    private PaddingFactory() {

    }

    public static Border createPadding(int size) {
        return new EmptyBorder(size, size, size, size);
    }

    public static Border createPadding(int top, int left, int bottom, int right) {
        return new EmptyBorder(top, left, bottom, right);
    }

    public static Border createPaddingWithBorder(int paddingSize, Direction direction, int size, Color color) {
        Border padding = PaddingFactory.createPadding(paddingSize);
        Border border = PaddingFactory.createMatteBorder(direction, size, color);
        Border compoundBorder = new CompoundBorder(border, padding);
        return compoundBorder;
    }

    public static Border createPaddingWithRadiusBorder(int paddingSize) {
        Border padding = PaddingFactory.createPadding(paddingSize);
        Border radiusBorder = new RoundedCornerBorder();
        Border compoundBorder = new CompoundBorder(radiusBorder, padding);
        return compoundBorder;
    }

    public static Border createMatteBorder(Direction direction, int size, Color color) {
        int top, left, bottom, right;
        top = left = bottom = right = 0;

        switch (direction) {
            case TOP:
                top = size;
                break;

            case LEFT:
                left = size;
                break;

            case BOTTOM:
                bottom = size;
                break;

            case RIGHT:
                right = size;
                break;

        }

        Border border = BorderFactory.createMatteBorder(top, left, bottom, right, color);
        return border;
    }

}
