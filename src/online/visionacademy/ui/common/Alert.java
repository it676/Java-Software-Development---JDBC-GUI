package online.visionacademy.ui.common;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Alert {

    private Alert() {

    }

    public static void info(Component target, String message) {
        JOptionPane.showMessageDialog(target, message);
    }

    public static void info(Component target, String message, String title, Icon icon) {
        JOptionPane.showMessageDialog(
                target,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE,
                icon);
    }

    public static void warning(Component target, String message, String title) {
        JOptionPane.showMessageDialog(
                target,
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }

    public static void error(Component target, String message, String title) {
        JOptionPane.showMessageDialog(
                target,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    //confirmation
    public static int confirm(Component target, String message, String title, Icon icon) {
        return JOptionPane.showConfirmDialog(
                target,
                message,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon);

    }

}
