package com.duelco.obj.general;

public class Player {
    private String playerName;
    private String characterName;

    public Player(String playerName, String characterName) {
        this.playerName = playerName;
        this.characterName = characterName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
