package ru.nnsu.pacman.server;

import ru.nnsu.pacman.common.Map;

class Game {
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
    
    

}
