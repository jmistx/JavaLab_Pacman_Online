package ru.nnsu.pacman.client;

import ru.nnsu.pacman.common.Map;

class StartGameDto {
    private final Map selectedMap;

    StartGameDto(Map selectedMap) {
        this.selectedMap = selectedMap;
    }
    
    public Map getMap() {
        return selectedMap;
    }

}