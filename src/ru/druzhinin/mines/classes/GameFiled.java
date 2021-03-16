package ru.druzhinin.mines.classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameFiled {
    private Map<GameObjectPosition, GameObject> gameObjectList = new HashMap<>();
    private int columns;
    private int rows;
    private int countMines;
    private int countFlags;


    public GameFiled(int columns, int rows, int countMines) {
        this.columns = columns;
        this.rows = rows;
        this.countMines = countMines;
        this.countFlags = countMines;
        fillField();
    }

    private void fillField() {
        if (this.columns > 0 && this.rows > 0 && this.countMines > 0 && this.countMines < (this.columns * this.rows) / 2) {
            Random rndCol = new Random();
            Random rndRow = new Random();
            int finalCountDown = this.countMines;
            int mine = 9;

            //Заливаем поле минами
            while (finalCountDown > 0) {
                int col = rndCol.nextInt(this.columns);
                int row = rndRow.nextInt(this.rows);

                if (this.gameObjectList.get(new GameObjectPosition(col, row)) == null) {
                    this.gameObjectList.put(new GameObjectPosition(col, row), new GameObject(new GameObjectPosition(col, row), mine));
                    finalCountDown--;
                }
            }

            //Описываем цифры вокруг мин.
            for (int i = 0; i < this.columns; i++) {
                for (int j = 0; j < this.rows; j++) {
                    if (this.gameObjectList.get(new GameObjectPosition(i, j)) == null) {
                        this.gameObjectList.put(new GameObjectPosition(i, j), new GameObject(new GameObjectPosition(i, j), 0));
                        continue;
                    } else if (this.gameObjectList.get(new GameObjectPosition(i, j)).getContext() == GameObjectContext.EMPTY
                            || this.gameObjectList.get(new GameObjectPosition(i, j)).getContext() == GameObjectContext.NUMBER) {
                        continue;
                    }
                    for (int k = i - 1; k < i + 2; k++) {
                        for (int l = j - 1; l < j + 2; l++) {
                            if ((k == i && l == j) || k < 0 || l < 0 || k > this.columns - 1 || l > this.rows - 1)
                                continue;
                            int countMines = 0;
                            for (int m = k - 1; m < k + 2; m++) {
                                for (int n = l - 1; n < l + 2; n++) {
                                    if (m < 0 || n < 0 || m > this.columns - 1 || n > this.rows - 1) continue;
                                    if (this.gameObjectList.get(new GameObjectPosition(m, n)) != null && this.gameObjectList.get(new GameObjectPosition(m, n)).getContext() == GameObjectContext.BOMB)
                                        countMines++;
                                }
                            }
                            if (this.gameObjectList.get(new GameObjectPosition(k, l)) == null || this.gameObjectList.get(new GameObjectPosition(k, l)).getContext() == GameObjectContext.EMPTY) {
                                this.gameObjectList.put(new GameObjectPosition(k, l), new GameObject(new GameObjectPosition(k, l), countMines));
                            }
                        }
                    }
                }
            }
        }
    }

    public Map<GameObjectPosition, GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public void setGameObjectList(Map<GameObjectPosition, GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCountMines() {
        return countMines;
    }

    public void setCountMines(int countMines) {
        this.countMines = countMines;
    }

    public int getCountFlags() {
        return countFlags;
    }

    public void setCountFlags(int countFlags) {
        this.countFlags = countFlags;
    }
}
