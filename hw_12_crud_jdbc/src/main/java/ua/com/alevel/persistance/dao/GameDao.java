package ua.com.alevel.persistance.dao;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
import java.util.Collection;
import java.util.Optional;

public interface GameDao {

    void addGame(Game game);
    Optional<Game> getGameById(Long id);
    Collection<Game> getAllGames();
    void updateGameName(Long id, String name);
    void updateGameType(Long id, boolean isCommandGame);
    boolean deleteGame(Long id);
    void addGameToPlayer(Long gameId, Long playerId);
    Collection<Game> getGamesByPlayer(Long playerId);
    Collection<GameDto> getPlayersCountOfAllGames();
    boolean deleteGameFromPlayer(Long gameId, Long playerId);
    boolean hasRecordsInGeneralTable(Long gameId, Long playerId);
}
