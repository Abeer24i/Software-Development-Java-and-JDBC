package ui.views;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import ui.util.AppColor;


public class StudentDashboardView extends JPanel {

    public StudentDashboardView() {
        init();
    }

    private void init() {
        setName("Manage Students");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

      

    }
}
