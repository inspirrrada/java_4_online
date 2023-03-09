package ua.com.alevel.service;

import ua.com.alevel.persistance.dao.GamePlayDao;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

import java.util.List;

public class GamePlayServiceImpl implements GamePlayService {

    private String playersFile;
    private String gamesFile;
    private GamePlayDao gamePlayDao;

    public GamePlayServiceImpl(String playersFile, String gamesFile) {
        this.playersFile = playersFile;
        this.gamesFile = gamesFile;
        this.gamePlayDao = new GamePlayDaoJson(playersFile, gamesFile);
    }

    /**
     * FROM FILE STORAGE
     * ------------------------------------
     * operations create from CRUD
     */
    @Override
    public void addPlayer(Player player) {
        if (isAgePermissible(player.getAge()) &&
                isCorrectEmail(player.getEmail()) && !hasTheSameEmail(player.getEmail()) &&
                isCorrectNickname(player.getNickname()) && !hasTheSameNickname(player.getNickname())) {
            gamePlayDao.addPlayer(player);
        }
    }

    @Override
    public void addGame(Game game) {
        if (!hasTheSameGameName(game.getName()) && isCorrectGameName(game.getName())) {
            gamePlayDao.addGame(game);
        }
    }

    /**
     * ------------------------------------
     * operations read from CRUD
     */
    @Override
    public Player getPlayerByIdOrNull(String id) {
        boolean existSuchPlayerId = existPlayerId(id);
        Player player = null;
        if (existSuchPlayerId) {
            player = gamePlayDao.getPlayerByIdOrNull(id);
        }
        return player;
    }

    @Override
    public List<Player> getAllPlayers() {
        return gamePlayDao.getAllPlayers();
    }

    @Override
    public Game getGameByIdOrNull(String id) {
        boolean existSuchGameId = existGameId(id);
        Game game = null;
        if (existSuchGameId) {
            game = gamePlayDao.getGameByIdOrNull(id);
        }
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return gamePlayDao.getAllGames();
    }

    /**
     * ------------------------------------
     * operations update from CRUD
     */
    @Override
    public void updatePlayerAge(String id, int age) {
        Player player = getPlayerByIdOrNull(id);
        if (player != null) {
            if (isAgePermissible(age)) {
                gamePlayDao.updatePlayerAge(id, age);
            }
        }
    }

    @Override
    public void updatePlayerEmail(String id, String email) {
        Player player = getPlayerByIdOrNull(id);
        if (player != null) {
            if (isCorrectEmail(email) && !hasTheSameEmail(email)) {
                gamePlayDao.updatePlayerEmail(id, email);
            }
        }
    }

    @Override
    public void updatePlayerNickname(String id, String nickname) {
        Player player = getPlayerByIdOrNull(id);
        if (player != null) {
            if (isCorrectNickname(nickname) && !hasTheSameNickname(nickname)) {
                gamePlayDao.updatePlayerNickname(id, nickname);
            }
        }
    }

    @Override
    public void updateGameName(String id, String name) {
        Game game = getGameByIdOrNull(id);
        if (game != null) {
            if (isCorrectGameName(name) && !hasTheSameGameName(name)) {
                gamePlayDao.updateGameName(id, name);
            }
        }
    }

    @Override
    public void updateGameType(String id, boolean isCommandGame) {
        Game game = getGameByIdOrNull(id);
        if (game != null) {
            gamePlayDao.updateGameType(id, isCommandGame);
        }
    }

    /**
     * ------------------------------------
     * operations delete from CRUD
     */
    @Override
    public boolean deletePlayer(String id) {
        return gamePlayDao.deletePlayer(id);
    }

    @Override
    public boolean deleteGame(String id) {
        return gamePlayDao.deleteGame(id);
    }

    /**
     * ------------------------------------
     * relation operations create from CRUD
     */
    @Override
    public void addGameToPlayerInAllDb(String gameId, String playerId) {
        if (existPlayerId(playerId) && existGameId(gameId)) {
            gamePlayDao.addGameToPlayerInAllDb(gameId, playerId);
        }
    }

    /**
     * ------------------------------------
     * relation operations read from CRUD
     */
    @Override
    public List<Player> getPlayersByGame(String gameId) {
        return gamePlayDao.getPlayersByGame(gameId);
    }

    @Override
    public List<Game> getGamesByPlayer(String playerId) {
        return gamePlayDao.getGamesByPlayer(playerId);
    }

    /**
     * ------------------------------------
     * relation operations delete from CRUD
     */
    @Override
    public boolean deleteGameFromPlayerInAllDb(String gameId, String playerId) {
        return gamePlayDao.deleteGameFromPlayerInAllDb(gameId, playerId);
    }

    /**
     * ------------------------------------
     * check data
     */
    @Override
    public boolean hasTheSameEmail(String email) {
        return gamePlayDao.hasTheSameEmail(email);
    }

    @Override
    public boolean hasTheSameNickname(String nickname) {
        return gamePlayDao.hasTheSameNickname(nickname);
    }

    @Override
    public boolean hasTheSameGameName(String gameName) {
        return gamePlayDao.hasTheSameGameName(gameName);
    }

    /**
     * ------------------------------------
     * check if exist object with such id
     */
    @Override
    public boolean existPlayerId(String playerId) {
        boolean existPlayerId = gamePlayDao.existPlayerId(playerId);
        if (!existPlayerId) {
            System.out.println(ColorUtils.RED_TEXT.format("We can't find player with such id!"));
        }
        return existPlayerId;
    }

    @Override
    public boolean existGameId(String gameId) {
        boolean existGameId = gamePlayDao.existGameId(gameId);
        if (!existGameId) {
            System.out.println(ColorUtils.RED_TEXT.format("We can't find game with such id!"));
        }
        return existGameId;
    }

    /**
     * ------------------------------------
     * check format
     */
    @Override
    public boolean isCorrectAgeFormat(String ageValue) {
        boolean correctAgeFormat;
        //age has to be only from digits
        if (ageValue.matches("\\d+")) {
            correctAgeFormat = true;
        } else {
            System.out.println(ColorUtils.RED_TEXT.format("Invalid value! Please enter number NOT string!"));
            correctAgeFormat = false;
        }
        return correctAgeFormat;
    }

    @Override
    public boolean isAgePermissible(int age) {
        boolean agePermissible;
        if (age < 18) {
            agePermissible = false;
            System.out.println(ColorUtils.RED_TEXT.format("Grow up first and come later."));
        } else if (age > 100) {
            agePermissible = false;
            System.out.println(ColorUtils.RED_TEXT.format("Your age is fantastic! You're too good for this game."));
        } else {
            agePermissible = true;
        }
        return agePermissible;
    }

    @Override
    public boolean isCorrectNickname(String nickname) {
        boolean correctNickname = true;
        //nickname can't have only digits
        if (nickname.matches("\\d+")) {
            System.out.println(ColorUtils.RED_TEXT.format("Nickname can't contain only digits!"));
            correctNickname = false;
        }
        return correctNickname;
    }

    @Override
    public boolean isCorrectEmail(String email) {
        boolean correctEmail;
        if (email.matches("^(.+)@(.+)$")) {
            correctEmail = true;
        } else {
            System.out.println(ColorUtils.RED_TEXT.format("Invalid value!"));
            correctEmail = false;
        }
        return correctEmail;
    }

    @Override
    public boolean isCorrectGameName(String gameName) {
        boolean correctGameName = true;
        //name of game can't have only digits
        if (gameName.matches("\\d+")) {
            System.out.println(ColorUtils.RED_TEXT.format("Name of game can't contain only digits!"));
            correctGameName = false;
        }
        return correctGameName;
    }

    @Override
    public String getPlayersFile() {
        return gamePlayDao.getPlayersFile();
    }

    @Override
    public void setPlayersFile(String playersFile) {
        gamePlayDao.setPlayersFile(playersFile);
    }

    @Override
    public String getGamesFile() {
        return gamePlayDao.getGamesFile();
    }

    @Override
    public void setGamesFile(String gamesFile) {
        gamePlayDao.setGamesFile(gamesFile);
    }
}
