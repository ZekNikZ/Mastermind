package apcsp.create.mastermind;

import apcsp.create.mastermind.window.MastermindWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Make sure to set a look and feel that works on all platforms.
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the main Mastermind window.
        new MastermindWindow();
    }
}
