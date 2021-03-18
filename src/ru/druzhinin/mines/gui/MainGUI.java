package ru.druzhinin.mines.gui;

import ru.druzhinin.mines.classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class MainGUI {
    private JPanel rootPanel;
    private JButton startButton;
    private JPanel gameFieldPanel;
    private Game game;
    private JPanel buttonFieldPanel;
    private JLabel timerFieldLabel;
    private MyTimer timer;
    private JFormattedTextField a32FormattedTextField;
    private JFormattedTextField a32FormattedTextField1;
    private JFormattedTextField a64FormattedTextField;
    private final Icons icons = new Icons();

    public MainGUI() {
        super();
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                start();
            }
        });
        timer = new MyTimer();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void start() {
        startButton.setText("Restart");
        this.game = new Game(Integer.valueOf(a32FormattedTextField.getText()), Integer.valueOf(a32FormattedTextField1.getText()), Integer.valueOf(a64FormattedTextField.getText()));
        this.game.startGame();
        gameFieldPanel.removeAll();
        gameFieldPanel.revalidate();
        gameFieldPanel.setLayout(new GridLayout(game.getGameFiled().getRows(), game.getGameFiled().getColumns(), 3, 3));
        timer.stopTimer();
        timer.startTimer(timerFieldLabel, game);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                JLabel iconButton = new JLabel(icons.getClosed());
                GameObject gameObject = game.getGameFiled().getGameObjectList().get(new GameObjectPosition(i,j));
                gameObject.setLabel(iconButton);
                iconButton.addMouseListener(new MyMouseListener(new GameObjectPosition(i,j)));
                gameFieldPanel.add(iconButton);
            }
        }
    }

    private class MyMouseListener extends MouseAdapter {
        private GameObjectPosition gameObjectPosition;

        public MyMouseListener(GameObjectPosition gameObjectPosition) {
            this.gameObjectPosition = gameObjectPosition;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (e.getButton()) {
                case 1:
                    leftClicked();
                    break;
                case 3:
                    rightClicked();
                    break;
                default:
            }
        }

        private void leftClicked() {
            game.open(gameObjectPosition);
        }

        private void rightClicked() {
            game.flag(gameObjectPosition);
        }
    }
}
