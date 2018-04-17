package apcsp.create.mastermind.window;

import apcsp.create.mastermind.util.Util;

import javax.swing.*;

class RulesWindow extends JFrame {

    RulesWindow() {
        super();
        this.setTitle("Mastermind Rules");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        Util.setSize(textArea, 600, 200);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("Welcome to the game of Mastermind!\n" +
                "\n" +
                " - The goal of the game is to break the code in as few guesses as possible.\n" +
                " - Use the four large pegs to choose a code to submit.\n" +
                " - Once you submit a code, you are given a response of four RED, WHITE, and BLACK pegs. These pegs may be in an arbitrary order.\n" +
                " - RED response pegs indicate that one of your pegs is the right color and in the right location.\n" +
                " - BLACK response pegs indicate that one of your pegs is the right color, but in the wrong location.\n" +
                " - WHITE response pegs indicate that one of your pegs is the wrong color.");

        this.add(textArea);

        this.pack();

        this.setVisible(true);

        this.setLocationRelativeTo(null);
    }
}
