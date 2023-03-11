package ua.com.alevel.service;

import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Player;
import java.util.Collection;


public interface PlayerService {

    void addPlayer(Player player);
    Player getPlayerById(Long id);
    Collection<Player> getAllPlayers();
    void updatePlayerAge(Long id, int age);
    void updatePlayerEmail(Long id, String email);
    void updatePlayerNickname(Long id, String nickname);
    boolean deletePlayer(Long id);
    Collection<Player> getPlayersByGame(Long gameId);
    Collection<PlayerDto> getGamesCountOfAllPlayers();
    boolean isCorrectAgeFormat(String ageValue);
    boolean isAgePermissible(int age);
    boolean isCorrectNickname(String nickname);
    boolean isCorrectEmail(String email);
    boolean hasTheSameEmail(String email);
    boolean hasTheSameNickname(String nickname);
    boolean existPlayerId(Long playerId);
}
