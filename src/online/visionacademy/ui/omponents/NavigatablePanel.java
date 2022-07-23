package online.visionacademy.ui.omponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.routes.NavigationItem;
import online.visionacademy.ui.routes.Router;
import online.visionacademy.ui.util.AppColor;
import online.visionacademy.ui.util.Direction;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.swing.FontIcon;

public class NavigatablePanel extends JPanel {

    private String title;
    private String subTitle;

    private Text titleLbl;
    private Text subTitleLbl;

    private Color color;
    private Ikon icon;

    private NavigationItem destination;

    public NavigatablePanel(String title, String subTitle, Color color, Ikon icon, NavigationItem destination) {
        this.title = title;
        this.subTitle = subTitle;
        this.color = color;
        this.icon = icon;
        this.destination = destination;

        init();
    }

    private void init() {
        setBackground(color);
        setLayout(new BorderLayout());

        initSize();

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        PaddingManager.setPaddingWithBorder(this, 30, Direction.LEFT, 5, Color.decode("#CCCCCC"));

        titleLbl = new Text(title, Color.decode(AppColor.TITLE));
        subTitleLbl = new Text(subTitle, Color.decode(AppColor.SUBTITLE), 16, Font.PLAIN);

        JPanel row = PanelFactory.createBoxPanel(BoxLayout.X_AXIS, color);
        row.add(titleLbl);

        //Spacer
        row.add(Box.createHorizontalGlue());

        JLabel icon = getIcon();
        row.add(icon);

        setMouseListener();

        add(row, BorderLayout.NORTH);
        add(subTitleLbl, BorderLayout.CENTER);
    }

    private void initSize() {
        int width = UI.getWidth();
        int by = 4;
        if (width < 1200) {
            by = 3;
        }
        setPreferredSize(new Dimension(width / by, 125));
    }

    private void setMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                Router.getInstance().navigate(destination);
            }
        });
    }

    private JLabel getIcon() {
        FontIcon icon = FontIcon.of(this.icon);
        icon.setIconSize(40);
        icon.setIconColor(Color.decode(AppColor.TITLE));
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        return lbl;
    }
}
