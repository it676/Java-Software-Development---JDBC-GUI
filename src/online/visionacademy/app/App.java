package online.visionacademy.app;

import javax.swing.SwingUtilities;
import online.visionacademy.ui.presenters.MainViewPresenter;
import online.visionacademy.ui.views.MainView;

public class App {

    public App() {

        MainView mv = new MainView();
        new MainViewPresenter(mv);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
