package online.visionacademy.ui.routes;

import java.util.Stack;
import javax.swing.JPanel;

public class Navigator {

    private final Stack<JPanel> stack = new Stack<>();

    private static Navigator instance;

    private Navigator() {
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(JPanel view) {
        stack.push(view);
    }

    public JPanel top() {

        if (isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public JPanel pop() {
        JPanel panel = stack.pop();
        return panel;
    }

    public int size() {
        return stack.size();
    }

    public String[] getNavigationPath() {

        String[] elements = new String[size()];
        for (int i = 0; i < elements.length; i++) {
            JPanel panel = stack.get(i);
            elements[i] = panel.getName();
        }
        return elements;
    }

    public static Navigator getInstance() {

        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

}
