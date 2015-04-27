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
    public int score;
    private final GameClient gameClient;
    private final int playerNumber;
    
    public Player player1;
    public Player player2;

    GameState(Map map, int playerNumber, GameClient gameClient) {
        this.map = map;
        this.playerNumber = playerNumber;
        this.gameClient = gameClient;
        player1 = new Player();
        player2 = new Player();
    }
    public void MoveCharacter(int direction) {
        Player player = playerNumber == 0? player1 : player2;
        
        int newX = player.getX();
        int newY = player.getY();

        if (direction == MOVE_LEFT) {

            newX = player.getX() - 1;
            if (newX == -1) {
                newX = map.getWidth() - 1;
            }
        }
        if (direction == MOVE_UP) {
            newY = player.getY() - 1;
            if (newY == -1) {
                newY = map.getHeight() - 1;
            }
        }
        if (direction == MOVE_RIGHT) {
            newX = player.getX() + 1;
            if (newX == map.getWidth()) {
                newX = 0;
            }
        }
        if (direction == MOVE_DOWN) {
            newY = player.getY() + 1;
            if (newY == map.getHeight()) {
                newY = 0;
            }
        }
        if (map.getCellValue(newX, newY) != MapCell.WALL) {
            player.setX(newX);
            player.setY(newY);
        }
        if (map.getCellValue(player.getX(), player.getY()) == MapCell.PILL) {
            map.SetCellValue(player.getX(), player.getY(), MapCell.EMPTY);
            score += 1;
        }
        
        GameEvent gameEvent = new GameEvent();
        gameEvent.setPlayerNumber(playerNumber);
        gameEvent.setType(GameEvent.MOVE);
        gameEvent.setPosition(player.getX(), player.getY());
        
        gameClient.SendEvent(gameEvent);
        setChanged();
        notifyObservers();
    }
    

    Map getMap() {
        return map;
    }

    void dispatchEvent(GameEvent event) {
        Player player = event.getPlayerNumber() == 0? player1 : player2;
        player.setX(event.getX());
        player.setY(event.getY());
        
        setChanged();
        notifyObservers();
    }
}
