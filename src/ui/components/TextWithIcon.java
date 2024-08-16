package ui.components;

import java.awt.Color;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.swing.FontIcon;

public class TextWithIcon extends Text {

    private Ikon icon;
    private Color colorIcon;

    public TextWithIcon(String text, Color colorText, int size, int style, Ikon icon, Color colorIcon) {
        super(text, colorText, size, style);
        this.icon = icon;
        this.colorIcon = colorIcon;
        init();
    }

    private void init() {
        FontIcon icon = FontIcon.of(this.icon);
        icon.setIconSize(size);
        icon.setIconColor(colorIcon);
        setIcon(icon);
    }

}
