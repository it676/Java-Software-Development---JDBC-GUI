package online.visionacademy.ui.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.swing.FontIcon;

public class ButtonFactory {

    private ButtonFactory() {

    }

    public static JButton createButton(String text, Color foreground, Color bg, Ikon icon) {
        JButton btn = new JButton(text);
        btn.setForeground(foreground);
        btn.setBackground(bg);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFont(new Font("SF Pro", Font.PLAIN, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        FontIcon ic = FontIcon.of(icon);
        ic.setIconColor(foreground);
        btn.setIcon(ic);
        return btn;
    }
}
