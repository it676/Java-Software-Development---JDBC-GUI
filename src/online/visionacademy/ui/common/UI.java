package online.visionacademy.ui.common;

import java.awt.Dimension;
import java.awt.Toolkit;

public class UI {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    private static Dimension screenSize = new Dimension(900,768) ;

    public static Dimension getScreenSize() {
        return screenSize;
    }

    public static double getHeaderHeight() {
        return screenSize.height * 15.0 / 100.0;
    }

    public static double getContentHeight() {
        return screenSize.height * 75.0 / 100.0;
    }

    public static double getFooterHeight() {
        return screenSize.height * 10.0 / 100.0;
    }

    public static Dimension getContentViewSize() {
        Dimension contentViewSize = new Dimension(screenSize.width, (int) getContentHeight());
        return contentViewSize;
    }

    public static int getWidth() {
        return getContentViewSize().width;
    }
}
