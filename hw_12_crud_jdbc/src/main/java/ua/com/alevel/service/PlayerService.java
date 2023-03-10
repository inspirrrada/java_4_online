package ua.com.alevel.service;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;
import ua.com.alevel.utils.ColorUtils;

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
    Collection<PlayerDto> getGamesCountOfAllPlayers();
    public boolean isCorrectAgeFormat(String ageValue);
    public boolean isAgePermissible(int age);
    public boolean isCorrectNickname(String nickname);
    public boolean isCorrectEmail(String email);
    public boolean hasTheSameEmail(String email);
    public boolean hasTheSameNickname(String nickname);
    public boolean existPlayerId(Long playerId);
}
