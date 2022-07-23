package online.visionacademy.ui.presenters;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import online.visionacademy.ui.routes.NavigationItem;
import online.visionacademy.ui.routes.Router;
import online.visionacademy.ui.views.ContentView;
import online.visionacademy.ui.views.MainView;
import online.visionacademy.ui.views.NavigationBody;

public class MainViewPresenter implements MainViewListener, PropertyChangeListener {

    private MainView view;

    public MainViewPresenter(MainView view) {
        this.view = view;
        Router.getInstance().addPropertyChangeListener(this);
        Router.getInstance().navigate(NavigationItem.DASHBOARD);
        view.addListener(this);
    }

    @Override
    public void back() {
        Router.getInstance().back();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JPanel activeView = Router.getInstance().getActiveView();

        ContentView contentView = view.getContentView();
        NavigationBody navigationBody = contentView.getNavigationBody();

        navigationBody.removeAll();
        navigationBody.add(activeView);
        navigationBody.repaint();

        activeView.setPreferredSize(new Dimension(view.getWidth() - 100, (view.getHeight() * 70 / 100)));

        String[] path = Router.getInstance().getNavigationPath();

        view.setTitleLbl(path[path.length - 1]);
        view.setNavigationPath(path);

    }
}
