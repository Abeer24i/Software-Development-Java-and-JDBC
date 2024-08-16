package ui.views;

import ui.common.PaddingManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import ui.util.AppColor;

public class ContentView extends JPanel {

    private NavigationHeader navigationHeader;
    private NavigationBody navigationBody;

    public ContentView() {
        init();
    }

    private void init() {

        navigationHeader = new NavigationHeader();
        navigationBody = new NavigationBody();

        setName("Content View");
        setLayout(new BorderLayout());
        setBackground(Color.decode(AppColor.BACKGROUND));

        PaddingManager.setPadding(this, 30, 40, 20, 20);

        add(navigationHeader, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(navigationBody);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

    }

    public NavigationHeader getNavigationHeader() {
        return navigationHeader;
    }

    public NavigationBody getNavigationBody() {
        return navigationBody;
    }

}
