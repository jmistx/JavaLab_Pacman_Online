package ru.nnsu.pacman.common;

import java.io.Serializable;

public class ServerMessage implements Serializable{
    private Map map;
    private GameEvent gameEvent;

    public void setMap(Map map) {
        this.map = map;
    }
    
    public Map getMap() {
        return this.map;
    }

    public GameEvent getGameEvent() {
        return gameEvent;
    }

    public void setGameEvent(GameEvent event) {
        this.gameEvent = event;
    }

}
