package ua.com.alevel.dao;

import ua.com.alevel.db.DbGamePlayFileStorage;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;
import java.util.List;

public class GamePlayDaoJson implements GamePlayDao {

    private String playersFile;
    private String gamesFile;
    DbGamePlayFileStorage dbGamePlayFileStorage;

    public GamePlayDaoJson(String playersFile, String gamesFile) {
        this.playersFile = playersFile;
        this.gamesFile = gamesFile;
        this.dbGamePlayFileStorage = new DbGamePlayFileStorage(playersFile, gamesFile);
        System.out.println("This is GamePlayDaoJson");
    }

    /**
     * ------------------------------------
     * operations create from CRUD
     */
    @Override
    public void addPlayer(Player player) {
        dbGamePlayFileStorage.addPlayer(player);
    }

    @Override
    public void addGame(Game game) {
        dbGamePlayFileStorage.addGame(game);
    }

    /**
     * ------------------------------------
     * operations read from CRUD
     */
    @Override
    public Player getPlayerByIdOrNull(String id) {
        return dbGamePlayFileStorage.getPlayerByIdOrNull(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return dbGamePlayFileStorage.getAllPlayers();
    }

    @Override
    public Game getGameByIdOrNull(String id) {
        return dbGamePlayFileStorage.getGameByIdOrNull(id);
    }

    @Override
    public List<Game> getAllGames() {
        return dbGamePlayFileStorage.getAllGames();
    }

    /**
     * ------------------------------------
     * operations update from CRUD
     */
    @Override
    public void updatePlayerAge(String id, int age) {
        dbGamePlayFileStorage.updatePlayerAge(id, age);
    }

    @Override
    public void updatePlayerEmail(String id, String email) {
        dbGamePlayFileStorage.updatePlayerEmail(id, email);
    }

    @Override
    public void updatePlayerNickname(String id, String nickname) {
        dbGamePlayFileStorage.updatePlayerNickname(id, nickname);
    }

    @Override
    public void updateGameName(String id, String name) {
        dbGamePlayFileStorage.updateGameName(id, name);
    }

    @Override
    public void updateGameType(String id, boolean isCommandGame) {
        dbGamePlayFileStorage.updateGameType(id, isCommandGame);
    }

    /**
     * ------------------------------------
     * operations delete from CRUD
     */
    @Override
    public boolean deletePlayer(String id) {
        return dbGamePlayFileStorage.deletePlayer(id);
    }

    @Override
    public boolean deleteGame(String id) {
        return dbGamePlayFileStorage.deleteGame(id);
    }

    /**
     * ------------------------------------
     * relation operations create from CRUD
     */
    @Override
    public void addGameToPlayerInAllDb(String gameId, String playerId) {
        dbGamePlayFileStorage.addGameToPlayerInAllDb(gameId, playerId);
    }

    /**
     * ------------------------------------
     * relation operations read from CRUD
     */
    @Override
    public List<Player> getPlayersByGame(String gameId) {
        return dbGamePlayFileStorage.getPlayersByGame(gameId);
    }

    @Override
    public List<Game> getGamesByPlayer(String playerId) {
        return dbGamePlayFileStorage.getGamesByPlayer(playerId);
    }


    /**
     * ------------------------------------
     * relation operations delete from CRUD
     */
    @Override
    public boolean deleteGameFromPlayerInAllDb(String gameId, String playerId) {
        return dbGamePlayFileStorage.deleteGameFromPlayerInAllDb(gameId, playerId);
    }

    /**
     * ------------------------------------
     * check methods
     */
    @Override
    public boolean hasTheSameEmail(String email) {
        return dbGamePlayFileStorage.hasTheSameEmail(email);
    }

    @Override
    public boolean hasTheSameNickname(String nickname) {
        return dbGamePlayFileStorage.hasTheSameNickname(nickname);
    }

    @Override
    public boolean hasTheSameGameName(String gameName) {
        return dbGamePlayFileStorage.hasTheSameGameName(gameName);
    }

    @Override
    public boolean existPlayerId(String playerId) {
        return dbGamePlayFileStorage.existPlayerId(playerId);
    }

    @Override
    public boolean existGameId(String gameId) {
        return dbGamePlayFileStorage.existGameId(gameId);
    }

    @Override
    public String getPlayersFile() {
        return dbGamePlayFileStorage.getPlayersFile();
    }

    @Override
    public void setPlayersFile(String playersFile) {
        dbGamePlayFileStorage.setPlayersFile(playersFile);
    }

    @Override
    public String getGamesFile() {
        return dbGamePlayFileStorage.getGamesFile();
    }

    @Override
    public void setGamesFile(String gamesile) {
        dbGamePlayFileStorage.setGamesFile(gamesFile);
    }
}
