package ru.druzhinin.mines.classes;

import ru.druzhinin.mines.gui.Icons;
import ru.druzhinin.mines.interfaces.GameObjectInterface;

import javax.swing.*;
import java.util.Objects;
import java.util.logging.Logger;

import static ru.druzhinin.mines.classes.GameObjectStarge.CLOSED;
import static ru.druzhinin.mines.classes.GameObjectStarge.FLAGGED;

public class GameObject implements GameObjectInterface {
    private GameObjectStarge stage;
    private GameObjectContext context;
    private final GameObjectPosition position;
    private final int value;
    private JLabel label;
    private final Icons icons = new Icons();

    public GameObject(GameObjectPosition position, int value) {
        this.stage = CLOSED;
        this.position = position;
        if (value == 0) {
            this.context = GameObjectContext.EMPTY;
        }
        if (value > 0 && value < 9) {
            this.context = GameObjectContext.NUMBER;
        }
        if (value == 9) {
            this.context = GameObjectContext.BOMB;
        }
        this.value = value;
    }

    @Override
    public GameObjectStarge open() {
        if (this.stage == CLOSED) {
            this.stage = GameObjectStarge.OPENED;
            switch (this.getContext()){
                case BOMB :
                    label.setIcon(icons.getBomb());
                    break;
                case EMPTY :
                    label.setIcon(icons.getEmpty());
                    break;
                case NUMBER :
                    label.setIcon(icons.getIconByNumber(this.getValue()));
                    break;
            }
        }
        return this.stage;
    }

    public GameObjectStarge flag() {
        switch (this.getStage()){
            case CLOSED :
                this.stage = FLAGGED;
                label.setIcon(icons.getFlag());
                break;
            case FLAGGED :
                this.stage = CLOSED;
                label.setIcon(icons.getClosed());
                break;
        }
        return this.stage;
    }

    public GameObjectStarge getStage() {
        return stage;
    }

    public void setStage(GameObjectStarge stage) {
        this.stage = stage;
    }

    public GameObjectPosition getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public GameObjectContext getContext() {
        return context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
