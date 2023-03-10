package ua.com.alevel.service;

import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

import java.util.Collection;
import java.util.List;

public interface PlayerService {
    void addPlayer(Player player);
    Player getPlayerById(Long id);
    Collection<Player> getAllPlayers();
    void updatePlayerAge(Long id, int age);
    void updatePlayerEmail(Long id, String email);
    void updatePlayerNickname(Long id, String nickname);
    boolean deletePlayer(Long id);
    Collection<Player> getPlayersByGame(Long gameId);
}
