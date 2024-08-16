package ui.views;

import entities.Course;
import exceptions.ServiceException;
import ui.common.PaddingManager;
import ui.common.PanelFactory;
import ui.components.Text;
import ui.components.TextWithIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import services.CourseService;
import services.CourseServiceImpl;

public class CoursetListView extends JPanel {

    private DefaultTableModel dataModel;
    private JTable table;

    public CoursetListView() {
        init();
    }

    private void init() {
        setName("Course List");
        setBackground(Color.decode(AppColor.BACKGROUND));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        PaddingManager.setPadding(this, 20);

        JPanel toolContainer = PanelFactory.createBoxPanel(BoxLayout.X_AXIS, Color.decode(AppColor.BACKGROUND));

       // toolContainer.add(new Text("Sometimes we have to ignore something because it is not the measure of my abilities ..", Color.decode(AppColor.SUBTITLE), 20, Font.BOLD));
       // toolContainer.add(new TextWithIcon("", Color.decode(AppColor.SUBTITLE), 20, Font.BOLD, MaterialDesign.MDI_RUN, Color.decode(AppColor.SUBTITLE)));

        toolContainer.add(Box.createRigidArea(new Dimension(10, 0)));
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

        setupColumns(new String[]{"Code", "Course Name", "Course Hours",
            "Grade"});

        fillTable();
        setCursor(new Cursor(Cursor.HAND_CURSOR));

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
        table.setDefaultRenderer(String.class, centerRenderer);

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
        CourseService cosService = new CourseServiceImpl();
        try {
            for (Course cos : cosService.getAll()) {
                dataModel.addRow(new Object[]{cos.getId(), cos.getCode(), cos.getName(), cos.getHours()});
            }
        } catch (ServiceException ex) {
            System.out.println(ex);
        }
    }
}
