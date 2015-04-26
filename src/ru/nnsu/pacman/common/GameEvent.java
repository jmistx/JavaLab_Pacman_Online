package ru.nnsu.pacman.common;

import java.io.Serializable;

public class GameEvent implements Serializable{
    public final static int MOVE = 1;
    int type;
    int x;
    int y;
    
    public void Move(int selfPacmanX, int selfPacmanY) {
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getType() {
        return type;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    @Override
    public String toString() {
        return String.valueOf(type) + " " + String.valueOf(x) + " " + String.valueOf(y);
    }

}
