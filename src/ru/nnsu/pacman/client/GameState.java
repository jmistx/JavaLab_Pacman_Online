package ru.nnsu.pacman.client;

import ru.nnsu.pacman.common.GameEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import javax.swing.JOptionPane;
import ru.nnsu.pacman.common.Map;
import ru.nnsu.pacman.common.MapCell;

class GameState extends Observable {

    public final static int MOVE_RIGHT = 1;
    public final static int MOVE_LEFT = 2;
    public final static int MOVE_UP = 3;
    public final static int MOVE_DOWN = 4;

    private final Map map;
    public int selfPacmanX;
    public int selfPacmanY;
    public int score;
    private final GameClient gameClient;

    GameState(Map map, GameClient gameClient) {
        this.map = map;
        this.gameClient = gameClient;
    }

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
        GameEvent gameEvent = new GameEvent();
        gameEvent.setType(GameEvent.MOVE);
        gameEvent.setPosition(selfPacmanX, selfPacmanY);
        gameClient.SendEvent(gameEvent);
        setChanged();
        notifyObservers();
    }

    Map getMap() {
        return map;
    }

    void dispatchEvent(GameEvent event) {
        JOptionPane.showMessageDialog(null, event.toString());
    }
}
