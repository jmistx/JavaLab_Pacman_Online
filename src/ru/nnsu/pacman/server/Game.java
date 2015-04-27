package ru.nnsu.pacman.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import ru.nnsu.pacman.common.GameEvent;
import ru.nnsu.pacman.common.Map;

class Game extends Observable {
    private final Map map;
    private final int maxUsers = 2;
    private int usersInGame;
    private ArrayList<GameEvent> history;

    Game(Map map) {
        this.usersInGame = 1;
        this.history = new ArrayList<>(300);
        this.map = map;
    }
    
    boolean hasFreeSlot(){
        return maxUsers - usersInGame > 0;
    }

    Map getMap() {
        return map;
    }

    void join() {
        usersInGame+=1;
    }

    void dispatchEvent(GameEvent gameEvent) {
        history.add(gameEvent);
        setChanged();
        notifyObservers(gameEvent);
    }

    void disconnect() {
        usersInGame -= 1;
    }

    boolean isFinished() {
        return usersInGame == 0;
    }

    List<GameEvent> getHistory() {
        return history;
    }
}
