package ru.nnsu.pacman.common;

import java.io.Serializable;

public class PlayerMessage implements Serializable {
    private String nickName;
    private String action;
    private Map map;
    private GameEvent gameEvent;
    private final String createGame = "Create_Game";
    private final String joinGame = "Join_Game";
    private final String observeGame = "Observe_Game";
    private final String eventInGame = "Game_Event";
    private final String getGames = "Get_Games";
    
    
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setActionCreateGame() {
        
        this.action = createGame;
    }
    
    public boolean isActionCreateGame() {
        return this.action.equals(createGame);
    }
    
    public void setActionJoinGame() {
        this.action = joinGame;
    }
    
    public boolean isActionJoinGame() {
        return this.action.equals(joinGame);
    }
    
    public void setActionObserveGame() {
        this.action = observeGame;
    }
    
    public boolean isActionObserveGame() {
        return this.action.equals(observeGame);
    }
    
    public boolean isGameEvent() {
        return this.action.equals(eventInGame);
    }

    public void setMap(Map map) {
        this.map = map;
    }
    
    public Map getMap(){
        return this.map;
    }

    public void setGameEvent(GameEvent gameEvent) {
        this.action = eventInGame;
        this.gameEvent = gameEvent;
    }
    
    public GameEvent getGameEvent() {
        return gameEvent;
    }

    public void setActionGetGames() {
        this.action = getGames;
    }

    public boolean isActionGetGames() {
        return this.action.equals(getGames);
    }
}
