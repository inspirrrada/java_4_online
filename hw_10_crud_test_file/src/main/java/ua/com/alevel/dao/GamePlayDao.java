package ua.com.alevel.dao;

import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;
import java.util.List;

public interface GamePlayDao {

    /**
     * ------------------------------------
     * operations create from CRUD
     */
    void addPlayer(Player player);
    void addGame(Game game);

    /**
     * ------------------------------------
     * operations read from CRUD
     */
    Player getPlayerByIdOrNull(String id);
    List<Player> getAllPlayers();
    Game getGameByIdOrNull(String id);
    List<Game> getAllGames();

    /**
     * ------------------------------------
     * operations update from CRUD
     */
    void updatePlayerAge(String id, int age);
    void updatePlayerEmail(String id, String email);
    void updatePlayerNickname(String id, String nickname);
    void updateGameName(String id, String name);
    void updateGameType(String id, boolean isCommandGame);

    /**
     * ------------------------------------
     * operations delete from CRUD
     */
    boolean deletePlayer(String id);
    boolean deleteGame(String id);

    /**
     * ------------------------------------
     * relation operations create from CRUD
     */
    void addGameToPlayerInAllDb(String gameId, String playerId);

    /**
     * ------------------------------------
     * relation operations read from CRUD
     */
    List<Player> getPlayersByGame(String gameId);
    List<Game> getGamesByPlayer(String playerId);

    /**
     * ------------------------------------
     * relation operations delete from CRUD
     */
    boolean deleteGameFromPlayerInAllDb(String gameId, String playerId);

    /**
     * ------------------------------------
     * check data
     */
    boolean hasTheSameEmail(String email);
    boolean hasTheSameNickname(String nickname);
    boolean hasTheSameGameName(String gameName);
    boolean existPlayerId(String playerId);
    boolean existGameId(String gameId);
    String getPlayersFile();
    void setPlayersFile(String playersFile);
    String getGamesFile();
    void setGamesFile(String gamesFile);
}
