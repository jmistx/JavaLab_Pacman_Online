package ru.nnsu.pacman.server;

import java.util.Observable;
import ru.nnsu.pacman.common.GameEvent;
import ru.nnsu.pacman.common.Map;

class Game extends Observable {
    private final Map map;
    private final int maxUsers = 2;
    private int usersInGame;
    private int countFreeSlots = 1;

    Game(Map map) {
        this.usersInGame = 1;
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
        setChanged();
        notifyObservers(gameEvent);
    }

    void disconnect() {
        usersInGame -= 1;
    }

    boolean isFinished() {
        return usersInGame == 0;
    }
}
