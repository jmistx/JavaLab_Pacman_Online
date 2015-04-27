package ru.nnsu.pacman.client;

import ru.nnsu.pacman.common.Map;

class DtoStartGame {
    private final Map selectedMap;
    private final GameClient gameClient;
    private final int playerNumber;
    
    DtoStartGame(Map selectedMap, int playerNumber, GameClient gameClient) {
        this.selectedMap = selectedMap;
        this.gameClient = gameClient;
        this.playerNumber = playerNumber;
    }
    
    public Map getMap() {
        return selectedMap;
    }
    
    public GameClient getGameClient() {
        return gameClient;
    }
    
    public int getPlayerNumber() {
        return playerNumber;
    }

}
