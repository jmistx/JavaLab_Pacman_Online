package ru.nnsu.pacman.server;

import java.util.Observable;
import ru.nnsu.pacman.common.GameEvent;
import ru.nnsu.pacman.common.Map;

class Game extends Observable {
    private final Map map;
    private int countFreeSlots = 1;

    Game(Map map) {
        this.map = map;
    }
    
    boolean hasFreeSlot(){
        return countFreeSlots > 0;
    }

    Map getMap() {
        return map;
    }

    void join() {
        countFreeSlots--;
    }

    void dispatchEvent(GameEvent gameEvent) {
        setChanged();
        notifyObservers(gameEvent);
    }
}
