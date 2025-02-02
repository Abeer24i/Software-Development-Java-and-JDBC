package ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import ui.common.PaddingManager;
import org.kordamp.ikonli.Ikon;
import ui.util.AppColor;

public class ListCard extends JPanel {

    private String title;
    private Ikon titleIcon;
    private String[] items;
    private Ikon itemIcon;

    public ListCard(String title, Ikon titleIcon, String[] items, Ikon itemIcon) {
        this.title = title;
        this.titleIcon = titleIcon;
        this.items = items;
        this.itemIcon = itemIcon;

        init();
    }

    private void init() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        PaddingManager.setPaddingWithRadiusBorder(this, 20);

    }

    public void addItems() throws NumberFormatException {
        this.removeAll();
        add(new TextWithIcon(title, Color.decode(AppColor.TITLE), 24, Font.BOLD, titleIcon, Color.decode(AppColor.TITLE)));
        add(Box.createRigidArea(new Dimension(0, 20)));
        for (String item : items) {
            add(new TextWithIcon(item, Color.decode(AppColor.TITLE), 16, Font.PLAIN, itemIcon, Color.decode(AppColor.TITLE)));
            add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

}
