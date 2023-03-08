package ua.com.alevel.service;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface GameService {

    void addGame(Game game);
    Game getGameByIdOrNull(String id);
    List<Game> getAllGames();
    void updateGameName(String id, String name);
    void updateGameType(String id, boolean isCommandGame);
    boolean deleteGame(String id);
    void addGameToPlayerInAllDb(String gameId, String playerId);
    Collection<Game> getGamesByPlayer(String playerId);
    Collection<GameDto> getPlayersCountByGame();
    boolean deleteGameFromPlayerInAllDb(String gameId, String playerId);
}
