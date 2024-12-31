package com.duelco.managers;

import com.duelco.obj.general.Player;

import java.util.List;

public class CharacterMapperManager {
    private static List<Player> players;

    public static void addMapping(String playerName, String characterName) {
        players.add(new Player(playerName, characterName));
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static Player getPlayer(String playerName) {
        return players.stream().filter(player -> player.getPlayerName().equals(playerName)).findFirst().orElse(null);
    }
}
