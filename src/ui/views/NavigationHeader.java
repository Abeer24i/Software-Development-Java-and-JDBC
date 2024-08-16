package ui.views;

import ui.common.ButtonFactory;
import ui.common.PanelFactory;
import ui.components.Text;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import ui.util.AppColor;
import ui.routes.Router;

public class NavigationHeader extends JPanel implements PropertyChangeListener {

    private String text = "";
    private Text navigationTitle;
    private JButton backBtn;

    public NavigationHeader() {
        init();
        Router.getInstance().addPropertyChangeListener(this);


    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.LEADING, 30, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        navigationTitle = new Text(text, Color.decode(AppColor.TITLE),24, Font.BOLD);

        JPanel panel = PanelFactory.createBoxPanel(BoxLayout.X_AXIS, Color.decode(AppColor.BACKGROUND));

        backBtn = ButtonFactory.createButton("<<", Color.decode(AppColor.SEC_BACKGROUND),
                Color.decode(AppColor.BACKGROUND), 24);

        backBtn.addActionListener((ActionEvent e) -> {
            Router.getInstance().back();
        });

        panel.add(backBtn);
        add(panel);

        panel.add(navigationTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(Box.createRigidArea(new Dimension(0, 8)));

    }


    public void setTitleLbl(String title) {
        navigationTitle.setText(title);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Router.getInstance().isBackBtnActive()) {
            backBtn.setVisible(true);
        } else {
            backBtn.setVisible(false);
        }
    }
}
