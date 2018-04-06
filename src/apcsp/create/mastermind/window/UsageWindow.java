package apcsp.create.mastermind.window;

import javax.swing.*;
import java.awt.*;

public class UsageWindow extends JFrame {

    public UsageWindow() {
        super();
        this.setTitle("How to Use");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        setSize(textArea, 600, 200);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("How to Use\n" +
                "\n" +
                " - The four large buttons on the bottom allow you to enter your guess. Click on them to change the color of that peg to the currently selected color.\n" +
                " - To change the currently selected color, click one of the colored buttons on the right.\n" +
                " - When you are ready to submit your guess, press the submit button. This will lock in your guess and show you the response pegs.\n" +
                " - Then, press \"Next Guess\" to try and guess again. This will put your previous guess into the history panel above the guess.\n" +
                " - You can scroll through the history panel to see your previous guesses.");

        this.add(textArea);

        this.pack();

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new RulesWindow();
    }

    //TODO: Make everything use setSize
    private static <T extends JComponent> T setSize(T comp, int width, int height) {
        Dimension dim = new Dimension(width, height);
        comp.setMinimumSize(dim);
        comp.setPreferredSize(dim);
        comp.setMaximumSize(dim);
        return comp;
    }
}
