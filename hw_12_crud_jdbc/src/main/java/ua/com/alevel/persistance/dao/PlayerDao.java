package ua.com.alevel.persistance.dao;

import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Player;
import java.util.Collection;
import java.util.Optional;

public interface PlayerDao {

    void addPlayer(Player player);
    Optional<Player> getPlayerById(Long id);
    Collection<Player> getAllPlayers();
    void updatePlayerAge(Long id, int age);
    void updatePlayerEmail(Long id, String email);
    void updatePlayerNickname(Long id, String nickname);
    boolean deletePlayer(Long id);
    Collection<Player> getPlayersByGame(Long gameId);
    Collection<PlayerDto> getGamesCountOfAllPlayers();
}
