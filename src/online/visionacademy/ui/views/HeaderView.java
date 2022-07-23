package online.visionacademy.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.omponents.CircleImage;
import online.visionacademy.ui.omponents.Text;
import online.visionacademy.ui.omponents.TextWithIcon;
import online.visionacademy.ui.util.AppColor;
import online.visionacademy.ui.util.Direction;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class HeaderView extends JPanel {

    public HeaderView() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.decode(AppColor.SEC_BACKGROUND));

        //padding and border
        PaddingManager.setPaddingWithBorder(this, 20, Direction.BOTTOM, 1, Color.decode("#DDDDDD"));

        JPanel basicInfoPanel = PanelFactory.createPanel(Color.decode(AppColor.SEC_BACKGROUND), 5, FlowLayout.LEADING, 10, 10);
        CircleImage userAvatar = createCircleImage("../images/img.jpeg", 70, 3, Color.WHITE);
        basicInfoPanel.add(userAvatar);

        JPanel welcomeUserPanel = createWelcomeUserPanel();
        basicInfoPanel.add(welcomeUserPanel);

        JPanel quckActionsPanel = createQuickActionsPanel();
        PaddingManager.setPadding(quckActionsPanel, 30);
        
        //size
//        setPreferredSize(new Dimension(250, (int) UI.getHeaderHeight()));

        //add to the main panel
        add(basicInfoPanel, BorderLayout.WEST);
        add(quckActionsPanel, BorderLayout.EAST);

    }

    public CircleImage createCircleImage(String src, int wh, int borderSize, Color borderColor) {
        CircleImage img = new CircleImage(src, borderSize, borderColor);
        img.setPreferredSize(new Dimension(wh, wh));
        return img;
    }

    public JPanel createWelcomeUserPanel() {
        JPanel welcomeUserPanel = PanelFactory.createBoxPanel(BoxLayout.Y_AXIS, Color.decode(AppColor.SEC_BACKGROUND));
        welcomeUserPanel.add(new Text("Welcome Naif,", Color.decode(AppColor.WHITE)));
        welcomeUserPanel.add(new TextWithIcon("Last login 21/09/2022 at 09:35 PM", Color.decode(AppColor.BACKGROUND), 14, Font.PLAIN, MaterialDesign.MDI_CLOCK));
        return welcomeUserPanel;
    }

    public JPanel createQuickActionsPanel() {
        JPanel quickActionsPanel = PanelFactory.createBoxPanel(BoxLayout.Y_AXIS, Color.decode(AppColor.SEC_BACKGROUND));
        quickActionsPanel.add(new Text("Quick Actions", Color.WHITE, 20));
        quickActionsPanel.add(new Text("Add | Edit | Remove | Logout ", Color.WHITE, 14, Font.PLAIN));
        return quickActionsPanel;
    }
}
