package apcsp.create.mastermind.util;

import java.awt.*;

public enum PegColor {
    RED(Color.RED),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    BLACK(Color.BLACK);

    public Color color;
    public PegIcon icon_small;
    public PegIcon icon_large;

    PegColor(Color color) {
        this.color = color;
        this.icon_small = new PegIcon(PegIcon.SMALL, color);
        this.icon_large = new PegIcon(PegIcon.LARGE, color);
    }
}