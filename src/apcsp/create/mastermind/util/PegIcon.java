package apcsp.create.mastermind.util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PegIcon implements Icon {

    public static final int SMALL = 32;
    public static final int LARGE = 64;
    public static final PegIcon WHITE_ICON_SMALL = new PegIcon(SMALL, Color.WHITE);
    @SuppressWarnings("UnusedDeclaration")
    public static final PegIcon WHITE_ICON_LARGE = new PegIcon(LARGE, Color.WHITE);

    private int size;
    private Color color;

    public PegIcon(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D canvas = (Graphics2D) g.create();

        canvas.setColor(color);
        canvas.fill(new Ellipse2D.Double(x, y, this.size, this.size));

        canvas.dispose();
    }

    @Override
    public int getIconWidth() {
        return this.size;
    }

    @Override
    public int getIconHeight() {
        return this.size;
    }
}
