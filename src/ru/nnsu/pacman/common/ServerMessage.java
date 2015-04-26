package ru.nnsu.pacman.common;

import java.io.Serializable;

public class ServerMessage implements Serializable{
    private Map map;

    public void setMap(Map map) {
        this.map = map;
    }
    
    public Map getMap() {
        return this.map;
    }

}
