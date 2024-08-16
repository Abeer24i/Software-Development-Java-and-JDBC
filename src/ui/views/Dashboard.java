package ui.views;

import ui.components.NavigatablePanel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import ui.routes.NavigationItem;

public class Dashboard extends JPanel {

    public Dashboard() {
        init();
    }

    private void init() {

        setName("Dashboard");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        NavigatablePanel studentsCard = new NavigatablePanel("Information", "Personal information", Color.WHITE, MaterialDesign.MDI_ACCOUNT, NavigationItem.COMMING_SOON);
        NavigatablePanel coursesCard = new NavigatablePanel("My Courses", "Courses for the current semester", Color.WHITE, MaterialDesign.MDI_BOOK, NavigationItem.COURSE_DASHBOARD);

        add(studentsCard);
        add(coursesCard);
        

    }
}
