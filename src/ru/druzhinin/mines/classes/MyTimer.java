package ru.druzhinin.mines.classes;

import ru.druzhinin.mines.gui.MainGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyTimer {

    private int counter;
    private Timer timer;
    private ActionListener timerEvent;
    private boolean started;

    public MyTimer() {
        System.out.println("Start Timer");
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public MyTimer startTimer(JLabel timerFieldLabel, Game game) {
        if(this.started)stopTimer();
        this.started = true;
        this.timerEvent = evt -> {
            if(game.getGameState() != GameState.PLAYING){
                stopTimer();
            } else {
                timerFieldLabel.setText("" + counter++);
            }
        };
        this.timer = new Timer(1000, timerEvent);
        this.timer.start();
        return this;
    }

    public int stopTimer() {
        int lastCounter = this.counter;
        if(this.started) {
            this.timer.stop();
            this.counter = 0;
            this.timer.removeActionListener(timerEvent);
            this.started = false;
        }
        return lastCounter;
    }
}
