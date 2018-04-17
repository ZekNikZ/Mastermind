package apcsp.create.mastermind.window;

import apcsp.create.mastermind.util.*;

import javax.swing.*;
import java.awt.*;

public class MastermindWindow extends JFrame {

    private PegColor currentColor = PegColor.RED;
    private PegColor[] moveColors;
    private PegColor[] responseColors;

    private Box historyPanel;
    private JScrollPane historyScrollPane;
    private JButton[] moveButtons;
    private JButton[] responseButtons;
    private JButton submitButton;
    private int submitButtonState = 0;

    private MastermindGame game;
    private boolean moveButtonsEnabled = true;
    private GameOverWindow gameOverWindow;

    public MastermindWindow() {
        super();
        this.setTitle("Mastermind");
        this.setSize(620, 500);
        this.setJMenuBar(this.createMenuBar());
        Box container = Box.createVerticalBox();


        Box topPanel = Box.createHorizontalBox();

        this.historyPanel = Box.createVerticalBox();
        this.historyPanel.setPreferredSize(new Dimension(520, 0));

        historyScrollPane = new JScrollPane(this.historyPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        historyScrollPane.setPreferredSize(new Dimension(520, 400));
        topPanel.add(historyScrollPane);

        Box colorPanel = Box.createVerticalBox();
        colorPanel.setPreferredSize(new Dimension(100, 400));

        JLabel label = new JLabel("Colors", SwingConstants.CENTER);
        label.setMaximumSize(new Dimension(100, 50));
        colorPanel.add(label);

        for (int i = 0; i < PegColor.values().length; i++) {
            JButton button = new JButton();
            button.setBackground(PegColor.values()[i].color);
            button.setMaximumSize(new Dimension(100, 50));
            final int j = i;
            button.addActionListener(e -> this.currentColor = PegColor.values()[j]);
            colorPanel.add(button);
        }
        topPanel.add(colorPanel);
        container.add(topPanel);


        Box bottomPanel = Box.createHorizontalBox();

        this.moveButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton button = new JButton(new PegIcon(PegIcon.LARGE, Color.WHITE));
            Util.setSize(button, 100, 100);
            final int j = i;
            button.addActionListener(e -> {
                this.moveColors[j] = this.currentColor;
                updateMoveButtons();
            });
            bottomPanel.add(button);
            this.moveButtons[i] = button;
        }

        JPanel responsePegPanel = new JPanel();
        responsePegPanel.setLayout(new GridLayout(2, 2));
        Util.setSize(responsePegPanel, 100, 100);
        this.responseButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton button = new JButton(new PegIcon(PegIcon.SMALL, Color.WHITE));
            Util.setSize(button, 50, 50);
            button.setEnabled(false);
            responsePegPanel.add(button);
            this.responseButtons[i] = button;
        }
        bottomPanel.add(responsePegPanel);

        bottomPanel.add(Box.createHorizontalStrut(10));

        this.submitButton = new JButton("Submit");
        Util.setSize(this.submitButton, 100, 50);
        this.submitButton.setMargin(new Insets(2, 10, 2, 10));
        this.submitButton.addActionListener(e -> {
            switch (this.submitButtonState) {
                case 0:
                    Move move = new Move(this.moveColors);
                    this.responseColors = this.game.submitMove(move);
                    this.moveButtonsEnabled = false;
                    updateMoveButtons();
                    if (this.game.wasWon()) {
                        gameOverWindow = new GameOverWindow(this, game);
                        this.submitButton.setText("New Game");
                        this.submitButtonState = 2;
                    } else {
                        this.submitButton.setText("Next Guess");
                        this.submitButtonState = 1;
                    }
                    break;
                case 1:
                    this.submitButton.setText("Submit");
                    this.responseColors = null;
                    this.moveButtonsEnabled = true;
                    updateMoveButtons();
                    this.submitButtonState = 0;
                    Box box = createPreviousMoveBox(this.game.lastMove);
                    addToHistoryPanel(box);
                    break;
                case 2:
                    if (gameOverWindow != null) gameOverWindow.dispose();
                    newGame();
                    break;
            }
        });
        bottomPanel.add(this.submitButton);

        container.add(bottomPanel);


        this.add(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.moveColors = new PegColor[]{PegColor.RED, PegColor.BLUE, PegColor.GREEN, PegColor.YELLOW};
        this.game = new MastermindGame();
        this.responseColors = null;
        updateMoveButtons();

        this.setLocationRelativeTo(null);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> {
            resetHistoryPanel();
            this.game = new MastermindGame();
        });
        gameMenu.add(newGame);

        JMenuItem restartGame = new JMenuItem("Restart Game");
        restartGame.addActionListener(e -> {
            resetHistoryPanel();
            this.game = this.game.restart();
        });
        gameMenu.add(restartGame);

        JMenuItem endGame = new JMenuItem("Quit Game");
        endGame.addActionListener(e -> System.exit(0));
        gameMenu.add(endGame);

        menuBar.add(gameMenu);


        JMenu helpMenu = new JMenu("Help");

        JMenuItem usage = new JMenuItem("How to Use");
        usage.addActionListener(e -> new UsageWindow());
        helpMenu.add(usage);

        JMenuItem rules = new JMenuItem("Rules");
        rules.addActionListener(e -> new RulesWindow());
        helpMenu.add(rules);

        menuBar.add(helpMenu);

        return menuBar;
    }

    private static Box createPreviousMoveBox(Move move) {
        Box panel = Box.createHorizontalBox();

        for (PegColor peg : move.pegs) {
            JButton button = new JButton(peg.icon_large);
            Util.setSize(button, 100, 100);
            button.setEnabled(false);
            panel.add(button);
        }

        JPanel responsePegPanel = new JPanel();
        responsePegPanel.setLayout(new GridLayout(2, 2));
        Util.setSize(responsePegPanel, 100, 100);

        for (PegColor responsePeg : move.responsePegs) {
            JButton button;
            if (responsePeg == null) {
                button = new JButton(PegIcon.WHITE_ICON_SMALL);
            } else {
                button = new JButton(responsePeg.icon_small);
            }
            Util.setSize(button, 50, 50);
            button.setEnabled(false);
            responsePegPanel.add(button);
        }

        panel.add(responsePegPanel);
        Util.setSize(panel, 500, 100);

        return panel;
    }

    private void addToHistoryPanel(JComponent component) {
        component.setAlignmentX(LEFT_ALIGNMENT);
        this.historyPanel.setPreferredSize(new Dimension(520, this.historyPanel.getPreferredSize().height + 100));
        this.historyPanel.add(component);
        this.revalidate();
        this.repaint();
        JScrollBar bar = historyScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void resetHistoryPanel() {
        this.historyPanel.removeAll();
        this.historyPanel.setPreferredSize(new Dimension(520, 0));
        this.revalidate();
        this.repaint();
    }

    private void updateMoveButtons() {
        for (int i = 0; i < 4; i++) {
            this.moveButtons[i].setIcon(this.moveColors[i].icon_large);
            this.moveButtons[i].setEnabled(this.moveButtonsEnabled);
        }
        if (this.responseColors != null) {
            for (int i = 0; i < 4; i++) {
                if (this.responseColors[i] == null) continue;
                this.responseButtons[i].setIcon(this.responseColors[i].icon_small);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                this.responseButtons[i].setIcon(PegIcon.WHITE_ICON_SMALL);
            }
        }
    }

    void newGame() {
        resetHistoryPanel();
        this.game = new MastermindGame();
        this.submitButton.setText("Submit");
        this.responseColors = null;
        this.moveButtonsEnabled = true;
        updateMoveButtons();
        this.submitButtonState = 0;
    }
}