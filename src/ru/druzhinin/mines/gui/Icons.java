package ru.druzhinin.mines.gui;

import javax.swing.*;

public class Icons {
    private final ImageIcon closed = new ImageIcon("src/ru/druzhinin/mines/resources/closed.png");
    private final ImageIcon empty = new ImageIcon("src/ru/druzhinin/mines/resources/empty.png");
    private final ImageIcon flag = new ImageIcon("src/ru/druzhinin/mines/resources/flag.png");
    private final ImageIcon bomb = new ImageIcon("src/ru/druzhinin/mines/resources/bomb.png");

    public ImageIcon getClosed() {
        return closed;
    }

    public ImageIcon getEmpty() {
        return empty;
    }

    public ImageIcon getFlag() {
        return flag;
    }

    public ImageIcon getBomb() {
        return bomb;
    }

    public ImageIcon getIconByNumber(int number) {
        return new ImageIcon("src/ru/druzhinin/mines/resources/"+number+".png");
    }
}
