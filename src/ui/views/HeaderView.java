package ui.views;

import ui.common.PaddingManager;
import ui.common.PanelFactory;
import ui.components.SquareImage;
import ui.components.Text;
import ui.components.TextWithIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import ui.util.AppColor;
import ui.util.Direction;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class HeaderView extends JPanel {

    public HeaderView() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.decode(AppColor.SEC_BACKGROUND));

        PaddingManager.setPaddingWithBorder(this, 20, Direction.BOTTOM, 3, Color.decode("#DDDDDD"));

        JPanel basicInfoPanel = PanelFactory.createPanel(Color.decode(AppColor.SEC_BACKGROUND), 5, FlowLayout.LEADING, 10, 10);
        SquareImage userAvatar = createCircleImage("../images/Avatar.png", 90, 1, Color.decode(AppColor.BACKGROUND));
        basicInfoPanel.add(userAvatar);

        JPanel welcomeUserPanel = createUserPanel();
        basicInfoPanel.add(welcomeUserPanel);

        JPanel quckActionsPanel = createQuickActionsPanel();
        PaddingManager.setPadding(quckActionsPanel, 30);

        add(basicInfoPanel, BorderLayout.WEST);
        add(quckActionsPanel, BorderLayout.EAST);

    }

    public SquareImage createCircleImage(String src, int wh, int borderSize, Color borderColor) {
        SquareImage img = new SquareImage(src, borderSize, borderColor);
        img.setPreferredSize(new Dimension(wh, wh));
        return img;
    }

    public JPanel createUserPanel() {
        JPanel welcomeUserPanel = PanelFactory.createBoxPanel(BoxLayout.Y_AXIS, Color.decode(AppColor.SEC_BACKGROUND));
        welcomeUserPanel.add(new Text("Abeer,", Color.decode(AppColor.WHITE)));
        welcomeUserPanel.add(new TextWithIcon("The best IS student ..", Color.decode(AppColor.BACKGROUND), 14, Font.PLAIN, MaterialDesign.MDI_CHECK, Color.decode(AppColor.BACKGROUND)));
        return welcomeUserPanel;
    }

    public JPanel createQuickActionsPanel() {
        JPanel quickActionsPanel = PanelFactory.createBoxPanel(BoxLayout.X_AXIS, Color.decode(AppColor.SEC_BACKGROUND));
        quickActionsPanel.add(new TextWithIcon("  ", Color.WHITE, 30, Font.PLAIN, MaterialDesign.MDI_MAGNIFY, Color.WHITE));
        quickActionsPanel.add(new TextWithIcon("", Color.WHITE, 30, Font.PLAIN, MaterialDesign.MDI_SETTINGS, Color.WHITE));

        return quickActionsPanel;
    }
}
