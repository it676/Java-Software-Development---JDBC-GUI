package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.omponents.TextWithIcon;
import online.visionacademy.ui.util.AppColor;
import online.visionacademy.ui.util.Direction;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class FooterView extends JPanel {

    public FooterView() {
        init();
    }

    private void init() {

        setBackground(Color.decode(AppColor.BACKGROUND));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //padding and border
        PaddingManager.setPaddingWithBorder(this, 20, Direction.TOP, 1, Color.decode("#DDDDDD"));

        //size
        setPreferredSize(new Dimension(UI.getWidth(), (int) UI.getFooterHeight()));

        add(new TextWithIcon("Developed By Eng.Naif AlShehri", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_LAPTOP_MAC));
        add(new TextWithIcon("VisionAcademy | 2022", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_COPYRIGHT));

    }
}
