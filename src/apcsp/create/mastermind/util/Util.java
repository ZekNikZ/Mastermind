package apcsp.create.mastermind.util;

import javax.swing.*;
import java.awt.*;

public class Util {
    @SuppressWarnings("UnusedReturnValue")
    public static <T extends JComponent> T setSize(T comp, int width, int height) {
        Dimension dim = new Dimension(width, height);
        comp.setMinimumSize(dim);
        comp.setPreferredSize(dim);
        comp.setMaximumSize(dim);
        return comp;
    }
}
