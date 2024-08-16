package ui.views;

import ui.common.PaddingManager;
import ui.common.UI;
import ui.components.TextWithIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import ui.util.AppColor;
import ui.util.Direction;

import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class FooterView extends JPanel {

    public FooterView() {
        init();
    }

    private void init() {

        setBackground(Color.decode(AppColor.BACKGROUND));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        PaddingManager.setPaddingWithBorder(this, 20, Direction.TOP, 1, Color.decode("#DDDDDD"));

        setPreferredSize(new Dimension(UI.getWidth(), (int) UI.getFooterHeight()));

        add(new TextWithIcon("Designed with ", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_LAPTOP_MAC, Color.decode(AppColor.SUBTITLE)));
        add(new TextWithIcon("and ", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_HEART, Color.RED));
        add(new TextWithIcon(" ", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_COFFEE, Color.decode("#65451F")));

    }
}
