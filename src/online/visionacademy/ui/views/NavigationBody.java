package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.util.AppColor;

public class NavigationBody extends JPanel {
    
    public NavigationBody() {
        
        init();
    }
    
    private void init() {
        setName("Navigation Body");
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setBackground(Color.decode(AppColor.BACKGROUND));
        PaddingManager.setPadding(this, 0, 0, 40, 0);
    }
}
