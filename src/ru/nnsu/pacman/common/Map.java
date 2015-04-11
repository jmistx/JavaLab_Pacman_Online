package ru.nnsu.pacman.common;

import java.io.Serializable;

public class Map implements Serializable {

    private final int mapGrid[][];
    private int width;
    private int height;
    
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        mapGrid = new int[width][height];
    }
    
    public final int getWidth(){
        return width;
    }
    
    public final int getHeight(){
        return height;
    }

    public void SetCellAsWall(int x, int y) {
        mapGrid[x][y] = 1;
    }
    public int GetCellValue(int x,int y){
        return mapGrid[x][y];
    }

    public void DeleteWall(int x, int y) {
        mapGrid[x][y] = 0;
    }
}
