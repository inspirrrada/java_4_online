package ua.com.alevel.persistance.dao;

import ua.com.alevel.persistance.entity.Player;

import java.util.Collection;
import java.util.Optional;

public interface PlayerDao {

    void addPlayer(Player player);
    Optional<Player> getPlayerById(String id);
    Collection<Player> getAllPlayers();
    void updatePlayerAge(String id, int age);
    void updatePlayerEmail(String id, String email);
    void updatePlayerNickname(String id, String nickname);
    boolean deletePlayer(String id);
    Collection<Player> getPlayersByGame(String gameId);
}
