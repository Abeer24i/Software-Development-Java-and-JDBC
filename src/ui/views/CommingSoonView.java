package ui.views;

import ui.common.PaddingManager;
import ui.common.PanelFactory;
import ui.components.TextWithIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPanel;
import ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class CommingSoonView extends JPanel {

    public CommingSoonView() {
        init();
    }

    private void init() {
        setName("Comming Soon");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        JPanel container = PanelFactory.createPanel(Color.decode(AppColor.WHITE));
        container.add(new TextWithIcon("Comming soon..", Color.decode(AppColor.SUBTITLE), 20, Font.BOLD, MaterialDesign.MDI_CLOCK, Color.decode(AppColor.SUBTITLE)));

        PaddingManager.setPaddingWithRadiusBorder(container, 20);
        container.setPreferredSize(new Dimension(400, 250));
        add(container);
    }
}
