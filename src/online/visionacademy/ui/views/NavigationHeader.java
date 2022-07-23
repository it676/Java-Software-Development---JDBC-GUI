package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import online.visionacademy.ui.common.ButtonFactory;
import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.omponents.TextWithIcon;
import online.visionacademy.ui.routes.Router;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class NavigationHeader extends JPanel implements PropertyChangeListener {

    private String text = "";
    private TextWithIcon navigationTitle;
    private JLabel navigationPath;
    private JButton backBtn;

    public NavigationHeader() {
        init();
        Router.getInstance().addPropertyChangeListener(this);

    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.LEADING, 30, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        navigationTitle = new TextWithIcon(text, Color.decode(AppColor.TITLE),
                24, Font.BOLD, MaterialDesign.MDI_GRID);

        navigationPath = new JLabel("Dashboard---> Students---> new Student");

        JPanel panel = PanelFactory.createBoxPanel(BoxLayout.Y_AXIS, Color.decode(AppColor.BACKGROUND));
        panel.add(navigationTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(navigationPath);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));

        backBtn = ButtonFactory.createButton("Back", Color.WHITE,
                Color.decode(AppColor.SEC_BACKGROUND),
                MaterialDesign.MDI_ARROW_LEFT_BOLD_CIRCLE_OUTLINE);

        backBtn.addActionListener((ActionEvent e) -> {
            Router.getInstance().back();
        });

        panel.add(backBtn);
        add(panel);

    }

    public void setNavigationPath(String path[]) {
        String str = "";
        if (path.length > 1) {
            for (int i = 0; i < path.length; i++) {
                if (i == path.length - 1) {
                    str += path[i];
                } else {
                    str += path[i] + " >> ";
                }
            }
        }

        navigationPath.setText(str);
    }

    public void setTitleLbl(String title) {
        navigationTitle.setText(title);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Router.getInstance().isBackBtnActive()) {
            backBtn.setVisible(true);
        } else {
            backBtn.setVisible(false);
        }
    }
}
