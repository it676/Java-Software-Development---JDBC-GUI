package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.omponents.RoundedCornerBorder;
import online.visionacademy.ui.omponents.Text;
import online.visionacademy.ui.omponents.TextWithIcon;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class CommingSoonView extends JPanel {

    public CommingSoonView() {
        init();
    }

    private void init() {
        setName("Comming Soon");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        JPanel container = PanelFactory.createPanel(Color.decode(AppColor.WHITE));
        container.add(new TextWithIcon("Comming soon..", Color.decode(AppColor.SUBTITLE), 20, Font.BOLD, MaterialDesign.MDI_CLOCK));

        //padding with radius border
        PaddingManager.setPaddingWithRadiusBorder(container, 20);
        container.setPreferredSize(new Dimension(400, 250));
        add(container);
    }
}
