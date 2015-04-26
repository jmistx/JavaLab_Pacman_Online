package ru.nnsu.pacman.client;

import ru.nnsu.pacman.common.Map;

class DtoStartGame {
    private final Map selectedMap;
    private final GameClient gameClient;
    
    DtoStartGame(Map selectedMap, GameClient gameClient) {
        this.selectedMap = selectedMap;
        this.gameClient = gameClient;
    }
    
    public Map getMap() {
        return selectedMap;
    }
    
    public GameClient getGameClient() {
        return gameClient;
    }

}
