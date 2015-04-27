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


    public String getAction() {
        return this.action;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    
    public Map getMap(){
        return this.map;
    }

    public void setGameEvent(GameEvent gameEvent) {
        this.action = "Game_Event";
        this.gameEvent = gameEvent;
    }
    
    public GameEvent getGameEvent() {
        return gameEvent;
    }

    

}
