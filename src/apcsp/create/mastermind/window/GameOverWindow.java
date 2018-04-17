package apcsp.create.mastermind.window;

import apcsp.create.mastermind.util.MastermindGame;
import apcsp.create.mastermind.util.Util;

import javax.swing.*;
import java.awt.*;

class GameOverWindow extends JFrame {

    GameOverWindow(MastermindWindow parentWindow, MastermindGame game) {
        super();
        this.setTitle("Game Over");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Box container = Box.createVerticalBox();
        Util.setSize(container, 400, 300);

        JLabel youWinLabel = new JLabel("You Win!", SwingConstants.CENTER);
        youWinLabel.setFont(new Font(youWinLabel.getFont().getName(), Font.PLAIN, 60));
        youWinLabel.setAlignmentX(CENTER_ALIGNMENT);
        Util.setSize(youWinLabel, 400, 150);
        container.add(youWinLabel);

        JLabel moveCountLabel = new JLabel("You took " + game.moveCount + " moves to break the code!", SwingConstants.CENTER);
        moveCountLabel.setFont(new Font(moveCountLabel.getFont().getName(), Font.PLAIN, 20));
        moveCountLabel.setAlignmentX(CENTER_ALIGNMENT);
        Util.setSize(moveCountLabel, 400, 100);
        container.add(moveCountLabel);

        Box buttonBox = Box.createHorizontalBox();
        Util.setSize(buttonBox, 400, 50);

        JButton quitGameButton = new JButton("Quit Game");
        Util.setSize(quitGameButton, 200, 50);
        quitGameButton.addActionListener(e -> System.exit(0));
        buttonBox.add(quitGameButton);

        JButton newGameButton = new JButton("New Game");
        Util.setSize(newGameButton, 200, 50);
        newGameButton.addActionListener(e -> {
            parentWindow.newGame();
            this.dispose();
        });
        buttonBox.add(newGameButton);

        container.add(buttonBox);

        this.add(container);

        this.revalidate();
        this.repaint();

        this.pack();

        this.setVisible(true);

        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new GameOverWindow(new MastermindWindow(), new MastermindGame());
    }
}