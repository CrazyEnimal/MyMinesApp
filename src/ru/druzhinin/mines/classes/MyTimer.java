package ru.druzhinin.mines.classes;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyTimer {
    public void MyTimer() {
        System.out.println("Test1");
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = evt -> System.out.println("Test");
        new Timer(delay, taskPerformer).start();
    }
}
