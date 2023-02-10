package ua.com.alevel.service;

import com.diogonunes.jcolor.AnsiFormat;
import ua.com.alevel.dao.GamePlayDao;
import ua.com.alevel.dao.GamePlayDaoJson;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.util.List;

import static com.diogonunes.jcolor.Attribute.*;

public class GamePlayServiceImpl {

//    private GamePlayDao gamePlayDao = new GamePlayDaoJson();

    private GamePlayDaoJson gamePlayDao = new GamePlayDaoJson(); //TODO change type to GamePlayDao

    private static AnsiFormat redText = new AnsiFormat(BRIGHT_RED_TEXT()); //TODO change to ColorUtils
    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());

    /**
     * FROM STORAGE
     * ------------------------------------
     * operations create from CRUD
     */
    public void addPlayer(Player player) {
        if (isAgePermissible(player.getAge()) &&
                isCorrectEmail(player.getEmail()) && !hasTheSameEmail(player.getEmail()) &&
                isCorrectNickname(player.getNickname()) && !hasTheSameNickname(player.getNickname())) {
            gamePlayDao.addPlayer(player);
        }
    }

    public void addGame(Game game) {
        if (!hasTheSameGameName(game.getName()) && isCorrectGameName(game.getName())) {
            gamePlayDao.addGame(game);
        }
    }

    /**
     * ------------------------------------
     * operations read from CRUD
     */
    public Player getPlayerByIdOrNull(String id) {
        boolean existSuchPlayerId = existPlayerId(id);
        Player player = null;
        if (existSuchPlayerId) {
            player = gamePlayDao.getPlayerByIdOrNull(id);
        }
        return player;
    }

    public List<Player> getAllPlayers() {
        return gamePlayDao.getAllPlayers();
    }

    public Game getGameByIdOrNull(String id) {
        boolean existSuchGameId = existGameId(id);
        Game game = null;
        if (existSuchGameId) {
            game = gamePlayDao.getGameByIdOrNull(id);
        }
        return game;
    }

    public List<Game> getAllGames() {
        return gamePlayDao.getAllGames();
    }

    /**
     * ------------------------------------
     * operations update from CRUD
     */
    public void updatePlayerAge(String id, int age) {
        Player player = getPlayerByIdOrNull(id);
        if (player != null) {
            if (isAgePermissible(age)) {
                gamePlayDao.updatePlayerAge(id, age);
            }
        }
    }

    public void updatePlayerEmail(String id, String email) {
        Player player = getPlayerByIdOrNull(id);
        if (player != null) {
            if (isCorrectEmail(email) && !hasTheSameEmail(email)) {
                gamePlayDao.updatePlayerEmail(id, email);
            }
        }
    }

    public void updatePlayerNickname(String id, String nickname) {
        Player player = getPlayerByIdOrNull(id);
        if (player != null) {
            if (isCorrectNickname(nickname) && !hasTheSameNickname(nickname)) {
                gamePlayDao.updatePlayerNickname(id, nickname);
            }
        }
    }

    public void updateGameName(String id, String name) {
        Game game = getGameByIdOrNull(id);
        if (game != null) {
            if (isCorrectGameName(name) && !hasTheSameGameName(name)) {
                gamePlayDao.updateGameName(id, name);
            }
        }
    }

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
    public boolean deletePlayer(String id) {
        return gamePlayDao.deletePlayer(id);
    }

    public boolean deleteGame(String id) {
        return gamePlayDao.deleteGame(id);
    }

    /**
     * ------------------------------------
     * relation operations create from CRUD
     */
    public boolean addOnlyPlayerToGame(String playerId, String gameId) {
        return gamePlayDao.addOnlyPlayerToGame(playerId, gameId);

    }

    public void addGameToPlayerInAllDb(String gameId, String playerId) {
        if (existPlayerId(playerId) && existGameId(gameId)) {
            gamePlayDao.addGameToPlayerInAllDb(gameId, playerId);
        }
    }

    /**
     * ------------------------------------
     * relation operations read from CRUD
     */
    public List<Player> getPlayersByGame(String gameId) {
        return gamePlayDao.getPlayersByGame(gameId);
    }

    public List<Game> getGamesByPlayer(String playerId) {
        return gamePlayDao.getGamesByPlayer(playerId);
    }

//    /**
//     * ------------------------------------
//     * relation operations delete from CRUD
//     */
//    public boolean deleteOnlyPlayerFromGame(String playerId, String gameId) {
//        return gamePlayDao.deleteOnlyPlayerFromGame(playerId, gameId);
//    }
//
//    public boolean deleteGameFromPlayerInAllDb(String gameId, String playerId) {
//        return gamePlayDao.deleteGameFromPlayerInAllDb(gameId, playerId);
//    }

    /**
     * ------------------------------------
     * check data
     */
    public boolean hasTheSameEmail(String email) {
        //boolean hasTheSameEmail = false;
        return gamePlayDao.hasTheSameEmail(email);
    }

    public boolean hasTheSameNickname(String nickname) {
        return gamePlayDao.hasTheSameNickname(nickname);
    }

    public boolean hasTheSameGameName(String gameName) {
        return gamePlayDao.hasTheSameGameName(gameName);
    }

    /**
     * ------------------------------------
     * check if exist object with such id
     */
    public boolean existPlayerId(String playerId) {
        boolean existPlayerId = gamePlayDao.existPlayerId(playerId);
        if (!existPlayerId) {
            System.out.println(redText.format("We can't find player with such id!"));
        }
        return existPlayerId;
    }

    public boolean existGameId(String gameId) {
        boolean existGameId = gamePlayDao.existGameId(gameId);
        if (!existGameId) {
            System.out.println(redText.format("We can't find game with such id!"));
        }
        return existGameId;
    }

    /**
     * ------------------------------------
     * check format
     */
    public boolean isCorrectAgeFormat(String ageValue) {
        boolean correctAgeFormat;
        //age has to be only from digits
        if (ageValue.matches("\\d+")) {
            correctAgeFormat = true;
        } else {
            System.out.println(GamePlayServiceImpl.getRedText().format("Invalid value! Please enter number NOT string!"));
            correctAgeFormat = false;
        }
        return correctAgeFormat;
    }

    public boolean isAgePermissible(int age) {
        boolean agePermissible;
        if (age < 18) {
            agePermissible = false;
            System.out.println(GamePlayServiceImpl.getRedText().format("Grow up first and come later."));
            //System.out.println(redText.format("Unacceptable value!"));
        } else if (age > 100) {
            agePermissible = false;
            System.out.println(GamePlayServiceImpl.getRedText().format("Your age is fantastic! You're too good for this game."));
        } else {
            agePermissible = true;
        }
        return agePermissible;
    }

    public boolean isCorrectNickname(String nickname) {
        boolean correctNickname = true;
        //nickname can't have only digits
        if (nickname.matches("\\d+")) {
            System.out.println(GamePlayServiceImpl.getRedText().format("Nickname can't contain only digits!"));
            correctNickname = false;
        }
        return correctNickname;
    }

    public boolean isCorrectEmail(String email) {
        boolean correctEmail;
        if (email.matches("^(.+)@(.+)$")) {
            correctEmail = true;
        } else {
            System.out.println(GamePlayServiceImpl.getRedText().format("Invalid value!"));
            correctEmail = false;
        }
        return correctEmail;
    }

    public boolean isCorrectGameName(String gameName) {
        boolean correctGameName = true;
        //name of game can't have only digits
        if (gameName.matches("\\d+")) {
            System.out.println(getRedText().format("Name of game can't contain only digits!"));
            correctGameName = false;
        }
        return correctGameName;
    }

    /**
     * ------------------------------------
     * formatting
     */
    public static AnsiFormat getRedText() {
        return redText;
    }

    public static AnsiFormat getBlueText() {
        return blueText;
    }

    public static AnsiFormat getYellowText() {
        return yellowText;
    }

    public static AnsiFormat getReverse() {
        return reverse;
    }

    public static AnsiFormat getUnderlinedText() {
        return underlinedText;
    }
}
