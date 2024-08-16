package ui.components;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class SquareImage extends JComponent {

    private Icon image;
    private int borderSize = 2;
    private Color borderColor = new Color(60, 60, 60);

    public SquareImage(String path) {
        this(path, 0, null);
    }

    public SquareImage(String path, int borderSize, Color borderColor) {
        image = createImageIcon(path, "");
        this.borderSize = borderSize;
        this.borderColor = borderColor;
    }

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            int width = image.getIconWidth();
            int height = image.getIconHeight();
            int size = Math.min(width, height);
            BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = mask.createGraphics();
            // Set rendering hints for image smoothness
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            // Fill a rounded rectangle shape instead of a rectangle
            g2d.fillRoundRect(0, 0, size - 1, size - 1, 10, 10); // The last two parameters determine the amount of rounding
            g2d.dispose();
            BufferedImage masked = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            g2d = masked.createGraphics();
            // Set rendering hints for image smoothness
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            int x = (size - width) / 2;
            int y = (size - height) / 2;
            g2d.drawImage(toImage(image), x, y, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
            g2d.drawImage(mask, 0, 0, null);
            g2d.dispose();
            Icon icon = new ImageIcon(masked);
            Rectangle sizeRect = getAutoSize(icon);
            Graphics2D g2 = (Graphics2D) g;
            // Set rendering hints for image smoothness
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(toImage(icon), sizeRect.getLocation().x, sizeRect.getLocation().y, sizeRect.getSize().width, sizeRect.getSize().height, null);
            if (borderSize > 0) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(borderSize));
                g2.drawRoundRect(sizeRect.x = (borderSize / 2), sizeRect.y + (borderSize / 2), sizeRect.width - borderSize, sizeRect.height - borderSize, 10, 10); // The last two parameters determine the amount of rounding
            }
        }
        super.paint(g);
    }

    private Rectangle getAutoSize(Icon image) {
        int w = getWidth();
        int h = getHeight();
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }

    protected final ImageIcon createImageIcon(String path, String description) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        }
        return null;
    }
}
