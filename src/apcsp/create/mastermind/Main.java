package apcsp.create.mastermind;

import apcsp.create.mastermind.window.MastermindWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MastermindWindow window = new MastermindWindow();
    }
}
