package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import online.visionacademy.entities.Student;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.services.StudentService;
import online.visionacademy.services.StudentServiceImpl;
import online.visionacademy.ui.common.ButtonFactory;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class StudentListView extends JPanel {

    private DefaultTableModel dataModel;
    private JTable table;

    public StudentListView() {
        init();
    }

    private void init() {
        setName("Student List");
        setBackground(Color.decode(AppColor.BACKGROUND));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        PaddingManager.setPadding(this, 20);

        JPanel toolContainer = PanelFactory.createBoxPanel(BoxLayout.X_AXIS, Color.decode(AppColor.BACKGROUND));
        JButton printBtn = ButtonFactory.createButton("Print", Color.WHITE, Color.decode(AppColor.SEC_BACKGROUND), MaterialDesign.MDI_PRINTER);
        JButton exportBtn = ButtonFactory.createButton("Export", Color.WHITE, Color.decode(AppColor.SEC_BACKGROUND), MaterialDesign.MDI_PRINTER);

        toolContainer.add(printBtn);
        toolContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        toolContainer.add(exportBtn);
        toolContainer.add(Box.createHorizontalGlue());

        initDataModel();

        table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);

        add(toolContainer);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(scrollPane);

    }

    private void initDataModel() {
        setupDataModel();

        setupColumns(new String[]{"Id", "Nationality Id", "First Name",
            "Last Name", "Birth Date", "Registered Coruses"});

        fillTable();
    }

    private JTable createTable() {

        JTable table = new JTable(dataModel);

        table.setAutoCreateRowSorter(true);
        table.setRowHeight(25);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.GRAY);

        table.setFont(new Font("SF Pro", Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(Color.decode(AppColor.SEC_BACKGROUND));
        table.setSelectionForeground(Color.WHITE);
        table.setFocusable(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        centerRenderer.setBackground(Color.decode(AppColor.SEC_BACKGROUND));
        centerRenderer.setForeground(Color.WHITE);
        centerRenderer.setFont(new Font("SF Pro", Font.BOLD, 14));

        table.getTableHeader().setDefaultRenderer(centerRenderer);
//        table.setDefaultRenderer(String.class, centerRenderer);

        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        table.getTableHeader().setReorderingAllowed(false);

        return table;
    }

    private void setupDataModel() {
        dataModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
    }

    private void setupColumns(String[] cols) {

        for (String col : cols) {
            dataModel.addColumn(col);
        }

    }

    private void fillTable() {

        //TODO: REFACTOR THIS METHOD AS I TOLD IN THE VIDEO
        StudentService stdService = new StudentServiceImpl();
        try {
            for (Student std : stdService.getAll()) {
                dataModel.addRow(new Object[]{std.getId(), std.getNationalityId(), std.getFirstName(), std.getLastName(), std.getDob(), stdService.courseCount(std.getId())});
            }
        } catch (ServiceException ex) {
            System.out.println(ex);
        }
    }
}
