
package ru.nnsu.pacman.common;

import java.io.Serializable;

public class GameDescription implements Serializable {
    private final int timestamp;
    private final String owner;
    private final String mapName;
    private final int playerCount;

    public GameDescription(int timestamp, String owner, String mapName, int playerCount) {
        this.timestamp = timestamp;
        this.owner = owner;
        this.mapName = mapName;
        this.playerCount = playerCount;   
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getMapName() {
        return mapName;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public String getOwner() {
        return owner;
    }
    
    @Override
    public String toString() {
        return getMapName();
    }
}
