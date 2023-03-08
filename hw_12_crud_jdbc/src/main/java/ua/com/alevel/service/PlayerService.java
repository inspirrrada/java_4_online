package ua.com.alevel.service;

import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

import java.util.Collection;
import java.util.List;

public interface PlayerService {
    void addPlayer(Player player);
    Player getPlayerById(String id);
    Collection<Player> getAllPlayers();
    void updatePlayerAge(String id, int age);
    void updatePlayerEmail(String id, String email);
    void updatePlayerNickname(String id, String nickname);
    boolean deletePlayer(String id);
    Collection<Player> getPlayersByGame(String gameId);
}
