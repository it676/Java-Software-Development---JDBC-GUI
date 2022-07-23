package online.visionacademy.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.presenters.MainViewListener;
import online.visionacademy.ui.routes.Router;

public class MainView extends JFrame {

    private final ArrayList<MainViewListener> listeners = new ArrayList<>();
    private HeaderView headerView = new HeaderView();
    private ContentView contentView = new ContentView();
    private FooterView footerView = new FooterView();

    public MainView() {
        init();
    }

    private void init() {

        setName("Main Frame");
        setTitle("iStudent");
        getContentPane().setBackground(Color.WHITE);
        setSize(UI.getScreenSize());
        setLayout(new BorderLayout());

        add(headerView, BorderLayout.NORTH);
        add(contentView, BorderLayout.CENTER);
        add(footerView, BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent event) {
                Component comp = event.getComponent();
                Dimension contentViewSize = new Dimension((int) comp.getWidth(), (comp.getHeight() * 70 / 100));

                JPanel activeView = Router.getInstance().getActiveView();
                activeView.setPreferredSize(new Dimension(contentViewSize.width - 100,(comp.getHeight() * 70 / 100)));
            }
        });

    }

    public void addListener(MainViewListener listener) {
        listeners.add(listener);
    }

    public ContentView getContentView() {
        return contentView;
    }

    public void setTitleLbl(String title) {
        NavigationHeader navHeader = contentView.getNavigationHeader();
        navHeader.setTitleLbl(title);
    }

    public void setNavigationPath(String[] path) {
        NavigationHeader navHeader = contentView.getNavigationHeader();
        navHeader.setNavigationPath(path);
    }
}
