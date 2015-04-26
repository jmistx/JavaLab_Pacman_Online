package ru.nnsu.pacman.common;

import java.io.Serializable;

public class PlayerMessage implements Serializable {
    private String nickName;
    private String action;
    private Map map;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setActionCreateGame() {
        this.action = "Create_Game";
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

    public void setActionJoinGame() {
        this.action = "Join_Game";
    }

}
