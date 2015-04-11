package ru.nnsu.pacman.common;

import java.io.Serializable;

public class Map implements Serializable {

    private final int mapGrid[][];
    
    public Map() {
        mapGrid = new int[getWeight()][getHeight()];
    }
    
    public final int getWeight(){
        return 10;
    }
    
    public final int getHeight(){
        return 10;
    }

    public void SetCellAsWall(int x, int y) {
        mapGrid[x][y] = 1;
    }
    public int GetCellValue(int x,int y){
        return mapGrid[x][y];
    }
}
