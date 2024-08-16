package ui.views;

import ui.components.NavigatablePanel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import ui.routes.NavigationItem;

public class CourseDashboardView extends JPanel {

    public CourseDashboardView() {
        init();
    }

    private void init() {
        setName("My Courses");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        NavigatablePanel allCourses = new NavigatablePanel("Display All", "Display all courses", Color.WHITE, MaterialDesign.MDI_FILE_DOCUMENT_BOX, NavigationItem.LIST_STUDENTS);

        add(allCourses);

    }
}
