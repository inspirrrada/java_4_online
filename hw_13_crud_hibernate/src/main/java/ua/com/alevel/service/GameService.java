package ua.com.alevel.service;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.util.Collection;


public interface
GameService {

    void addGame(Game game);
    Game getGameById(Long id);
    Collection<Game> getAllGames();
    void updateGameName(Long id, String name);
    void updateGameType(Long id, boolean isCommandGame);
    boolean deleteGame(Long id);
    void addGameToPlayer(Long gameId, Long playerId);
    Collection<Game> getGamesByPlayer(Long playerId);
    Collection<GameDto> getPlayersCountOfAllGames();
    boolean deleteGameFromPlayer(Long gameId, Long playerId);
    boolean hasTheSameGameName(String gameName);
    boolean existGameId(Long gameId);
    boolean isCorrectGameName(String gameName);
    boolean hasRecordsInGeneralTable(Long gameId, Long playerId);
}
