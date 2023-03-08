package ua.com.alevel.persistance.dao;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GameDao {
    void addGame(Game game);
    Optional<Game> getGameById(String id);
    Collection<Game> getAllGames();
    void updateGameName(String id, String name);
    void updateGameType(String id, boolean isCommandGame);
    boolean deleteGame(String id);
    void addGameToPlayerInAllDb(String gameId, String playerId);
    Collection<Game> getGamesByPlayer(String playerId);
    Collection<GameDto> getPlayersCountByGame();
    boolean deleteGameFromPlayerInAllDb(String gameId, String playerId);
}
