package ua.com.alevel.service;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface GameService {

    void addGame(Game game);
    Game getGameById(Long id);
    Collection<Game> getAllGames();
    void updateGameName(Long id, String name);
    void updateGameType(Long id, boolean isCommandGame);
    boolean deleteGame(Long id);
    void addGameToPlayer(Long gameId, Long playerId);
    Collection<Game> getGamesByPlayer(Long playerId);
    Collection<GameDto> getPlayersCountByGame();
    boolean deleteGameFromPlayer(Long gameId, Long playerId);
}
