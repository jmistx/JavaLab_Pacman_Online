package ru.nnsu.pacman.client;

import java.awt.event.KeyEvent;
import ru.nnsu.pacman.common.Map;
import ru.nnsu.pacman.common.MapCell;

class GameState{

    public final static int MOVE_RIGHT = 1;
    public final static int MOVE_LEFT = 2;
    public final static int MOVE_UP = 3;
    public final static int MOVE_DOWN = 4;

    private final Map map;
    public int selfPacmanX;
    public int selfPacmanY;
    public int score;

    public void MoveCharacter(int direction) {
        int newSelfPacmanX = selfPacmanX;
        int newSelfPacmanY = selfPacmanY;

        if (direction == MOVE_LEFT) {

            newSelfPacmanX = selfPacmanX - 1;
            if (newSelfPacmanX == -1) {
                newSelfPacmanX = map.getWidth() - 1;
            }
        }
        if (direction == MOVE_UP) {
            newSelfPacmanY = selfPacmanY - 1;
            if (newSelfPacmanY == -1) {
                newSelfPacmanY = map.getHeight() - 1;
            }
        }
        if (direction == MOVE_RIGHT) {
            newSelfPacmanX = selfPacmanX + 1;
            if (newSelfPacmanX == map.getWidth()) {
                newSelfPacmanX = 0;
            }
        }
        if (direction == MOVE_DOWN) {
            newSelfPacmanY = selfPacmanY + 1;
            if (newSelfPacmanY == map.getHeight()) {
                newSelfPacmanY = 0;
            }
        }
        if (map.getCellValue(newSelfPacmanX, newSelfPacmanY) != MapCell.WALL) {
            selfPacmanX = newSelfPacmanX;
            selfPacmanY = newSelfPacmanY;
        }
        if (map.getCellValue(selfPacmanX, selfPacmanY) == MapCell.PILL) {
            map.SetCellValue(selfPacmanX, selfPacmanY, MapCell.EMPTY);
            score += 1;
        }
    }

    GameState(Map map) {
        this.map = map;
    }

    Map getMap() {
        return map;
    }

}
