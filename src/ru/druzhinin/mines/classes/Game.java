package ru.druzhinin.mines.classes;

import java.util.*;

public class Game {
    private final GameFiled gameFiled;
    private List<GameObject> bombFlagged = new ArrayList<>();
    private GameState gameState;

    public Game(int columns, int rows, int mines) {
        this.gameFiled = new GameFiled(columns, rows, mines);
        this.gameState = GameState.BEGIN;
    }

    public void startGame() {
        this.gameState = GameState.PLAYING;
    }

    public GameState getGameState() {
        return gameState;
    }

    public GameFiled getGameFiled() {
        return gameFiled;
    }


    public void open(GameObjectPosition position) {
        GameObject gameObject = this.gameFiled.getGameObjectList().get(position);
        if(getGameState() == GameState.PLAYING){
            if (gameObject.open() == GameObjectStarge.OPENED) {
                if (gameObject.getContext() == GameObjectContext.BOMB) {
                    this.lose();
                }
                if (gameObject.getContext() == GameObjectContext.EMPTY) {
                    this.openAllEmpty(gameObject);
                }
            }
        }
    }

    public void flag(GameObjectPosition position) {
        GameObject gameObject = this.gameFiled.getGameObjectList().get(position);
        if(getGameState() == GameState.PLAYING) {
            gameObject.flag();
            switch (gameObject.getStage()){
                case FLAGGED :
                    if(gameObject.getContext() == GameObjectContext.BOMB)this.bombFlagged.add(gameObject);
                    break;
                case CLOSED :
                    if(gameObject.getContext() == GameObjectContext.BOMB)this.bombFlagged.remove(gameObject);
                    break;
            }
        }

        checkWin();

    }

    public boolean checkWin() {
        int i = 0;
        for (Map.Entry<GameObjectPosition, GameObject> o : gameFiled.getGameObjectList().entrySet()
        ) {
            if(o.getValue().getStage() == GameObjectStarge.CLOSED || o.getValue().getStage() == GameObjectStarge.FLAGGED) i++;
        }
        if(gameFiled.getCountMines() == i || this.bombFlagged.size() == gameFiled.getCountMines()) {
            this.gameState = GameState.WIN;
            return true;
        }
        return false;
    }

    private void openAllEmpty(GameObject gameObject) {

        if (gameObject.getContext() == GameObjectContext.EMPTY) {
            Deque<GameObject> gameObjectQueue = new ArrayDeque<>();
            gameObjectQueue.add(gameObject);

            while (!gameObjectQueue.isEmpty()) {
                GameObject gameObjectPop = gameObjectQueue.pop();

                if (gameObjectPop.getContext() == GameObjectContext.EMPTY) {
                    gameObjectQueue.addAll(getAroundGameObjects(gameObjectPop));
                    gameObjectPop.open();
                }
                if (gameObjectPop.getContext() == GameObjectContext.NUMBER) {
                    gameObjectPop.open();
                }
            }
        }
    }

    private void openAll() {
        for (Map.Entry<GameObjectPosition, GameObject> o : gameFiled.getGameObjectList().entrySet()
             ) {
            o.getValue().open();
        }
    }

    private Collection<? extends GameObject> getAroundGameObjects(GameObject object) {
        List<GameObject> gameObjects = new ArrayList<>();
        for (int i = object.getPosition().getX() - 1; i < object.getPosition().getX() + 2; i++) {
            for (int j = object.getPosition().getY() - 1; j < object.getPosition().getY() + 2; j++) {
                if (i < 0 || j < 0 || i > this.gameFiled.getColumns() - 1 || j > this.gameFiled.getRows() - 1) continue;
                if (this.gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)).getStage() != GameObjectStarge.OPENED)
                    gameObjects.add(this.gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)));
            }
        }
        return gameObjects;
    }

    private void lose() {
        openAll();
        this.gameState = GameState.LOSE;
    }
}
