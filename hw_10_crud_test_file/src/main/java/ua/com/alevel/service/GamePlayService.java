package ua.com.alevel.service;

import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;
import ua.com.alevel.utils.ColorUtils;

import java.util.List;

public interface GamePlayService {

    /**
     * ------------------------------------
     * single operations of CRUD
     */
    void addPlayer(Player player);
    void addGame(Game game);
    Player getPlayerByIdOrNull(String id);
    List<Player> getAllPlayers();
    Game getGameByIdOrNull(String id);
    List<Game> getAllGames();
    void updatePlayerAge(String id, int age);
    void updatePlayerEmail(String id, String email);
    void updatePlayerNickname(String id, String nickname);
    void updateGameName(String id, String name);
    void updateGameType(String id, boolean isCommandGame);
    boolean deletePlayer(String id);
    boolean deleteGame(String id);

    /**
     * ------------------------------------
     * relation operations of CRUD
     */
    void addGameToPlayerInAllDb(String gameId, String playerId);
    List<Player> getPlayersByGame(String gameId);
    List<Game> getGamesByPlayer(String playerId);
    boolean deleteGameFromPlayerInAllDb(String gameId, String playerId);

    /**
     * ------------------------------------
     * additional methods
     */
    boolean hasTheSameEmail(String email);
    boolean hasTheSameNickname(String nickname);
    boolean hasTheSameGameName(String gameName);
    boolean existPlayerId(String playerId);
    boolean existGameId(String gameId);
    boolean isCorrectAgeFormat(String ageValue);
    boolean isAgePermissible(int age);
    boolean isCorrectNickname(String nickname);
    boolean isCorrectEmail(String email);
    boolean isCorrectGameName(String gameName);
    String getPlayersFile();
    void setPlayersFile(String playersFile);
    String getGamesFile();
    void setGamesFile(String gamesFile);
}
