package ru.nnsu.pacman.common;

import java.io.Serializable;

public class Map implements Serializable {

    private final int mapGrid[][];
    private int width;
    private int height;
    private String name;
    
    public Map(int width, int height, String name) {
        this.width = width;
        this.height = height;
        mapGrid = new int[width][height];
        this.name = name;
    }
    
    public final String getName(){
        return name;
    }
   
    public final void setName(String newName){
        this.name = newName;
    }
    public final int getWidth(){
        return width;
    }
    
    public final int getHeight(){
        return height;
    }
    
    public void SetCellValue(int x, int y, int cell) {
        mapGrid[x][y] = cell;
    }

    public int getCellValue(int x,int y){
        return mapGrid[x][y];
    }
}
