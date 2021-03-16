package ru.druzhinin.mines.classes;

public class PrinterFiled {
    public PrinterFiled(GameFiled gameFiled) {
        //Заливаем лист объектов поля из массива
        for (int i = 0; i < gameFiled.getColumns(); i++) {
            System.out.println();
            for (int j = 0; j < gameFiled.getRows(); j++) {
                if (gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)).getStage() == GameObjectStarge.CLOSED)
                    System.out.print(" O");
                if (gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)).getStage() == GameObjectStarge.FLAGGED)
                    System.out.print(" F");
                if (gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)).getStage() == GameObjectStarge.OPENED)
                    if (gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)).getContext() == GameObjectContext.EMPTY)
                        System.out.print("  ");
                    else if (gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)).getContext() == GameObjectContext.NUMBER)
                        System.out.print(" " + gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)).getValue());
                    else if (gameFiled.getGameObjectList().get(new GameObjectPosition(i, j)).getContext() == GameObjectContext.BOMB)
                        System.out.print(" B");
            }
        }
    }
}
