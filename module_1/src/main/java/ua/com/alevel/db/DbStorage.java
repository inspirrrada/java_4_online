package ua.com.alevel.db;

import com.diogonunes.jcolor.AnsiFormat;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.util.*;

import static com.diogonunes.jcolor.Attribute.*;

public class DbStorage {

    private static Player[] playersArray = new Player[10];
    private static int playersCount = 0;

    private static Game[] gamesArray = new Game[10];
    private static int gamesCount = 0;

    private static AnsiFormat redText = new AnsiFormat(BRIGHT_RED_TEXT());
    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());


    private DbStorage() {}


    //set unique id for players and games
    private static String generatePlayerId() {
        String id = UUID.randomUUID().toString();

        for(int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] != null) {
                if (playersArray[i].getId().equals(id)) {
                    return generatePlayerId();
                }
            }
        }

        return id;
    }

    private static String generateGameId() {
        String id = UUID.randomUUID().toString();

        for(int i = 0; i < gamesArray.length; i++) {
            if (gamesArray[i] != null) {
                if (gamesArray[i].getId().equals(id)) {
                    return generateGameId();
                }
            }
        }

        return id;
    }

    //operations create from CRUD
    public static void addPlayer(Player player) {
        player.setId(generatePlayerId());

        if (playersCount == playersArray.length) {
            int newLength = playersArray.length * 2;
            Player[] playersArrayNew = Arrays.copyOf(playersArray, newLength);
            playersArray = playersArrayNew;
        }

        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] == null) {
                playersArray[i] = player;
                playersCount++;
                break;
            }
        }

    }

    public static void addGame(Game game) {
        game.setId(generateGameId());

        if (gamesCount == gamesArray.length) {
            int newLength = gamesArray.length * 2;
            Game[] gamesArrayNew = Arrays.copyOf(gamesArray, newLength);
            gamesArray = gamesArrayNew;
        }

        for (int i = 0; i < gamesArray.length; i++) {
            if (gamesArray[i] == null) {
                gamesArray[i] = game;
                gamesCount++;
                break;
            }
        }

    }

    //operations read from CRUD
    public static Player getPlayer(String id) {
        Player player = null;

        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] != null) {
                if (playersArray[i].getId().equals(id)) {
                    player = playersArray[i];
                    break;
                }
            }
        }

        return player;
    }

    public static Player[] getAllPlayers() {
        return playersArray;
    }

    public static Game getGame(String id) {
        Game game = null;

        for (int i = 0; i < gamesArray.length; i++) {
            if (gamesArray[i] != null) {
                if (gamesArray[i].getId().equals(id)) {
                    game = gamesArray[i];
                    break;
                }
            }
        }

        return game;
    }

    public static Game[] getAllGames() {
        return gamesArray;
    }

    //operations update from CRUD
    public static boolean updatePlayerAge(String id, int age) {
        boolean validAge = false;

        if (age < 18) {
            System.out.println(redText.format("Age is too young for this game. Your changes of age weren't saved."));
        } else if (age > 100) {
            System.out.println(redText.format("Age is too great for this game. Your changes of age weren't saved."));
        } else {
            validAge = true;
            for (int i = 0; i < playersArray.length; i++) {
                if (playersArray[i] != null) {
                    if (playersArray[i].getId().equals(id)) {
                        playersArray[i].setAge(age);
                        break;
                    }
                }
            }

        }

        return validAge;
    }

    public static void updatePlayerEmail(String id, String email) {
        if (email.matches("^(.+)@(.+)$")) {

            for (int i = 0; i < playersArray.length; i++) {
                if (playersArray[i] != null) {
                    if (playersArray[i].getId().equals(id)) {
                        playersArray[i].setEmail(email);
                        break;
                    }
                }
            }

        } else {
            System.out.println(redText.format("Invalid value!"));
        }
    }

    public static void updatePlayerNickname(String id, String nickname) {
        if (nickname.matches("\\d+")) {
            System.out.println(redText.format("Nickname can't contain only digits!"));
        } else {

            for (int i = 0; i < playersArray.length; i++) {
                if (playersArray[i] != null) {
                    if (playersArray[i].getId().equals(id)) {
                        playersArray[i].setNickname(nickname);
                        break;
                    }
                }
            }

        }
    }

    public static void updateGameName(String id, String name) {
        if (name.matches("\\d+")) {
            System.out.println(redText.format("Game name can't contain only digits!"));
        } else {

            for (int i = 0; i < gamesArray.length; i++) {
                if (gamesArray[i] != null) {
                    if (gamesArray[i].getId().equals(id)) {
                        gamesArray[i].setName(name);
                        break;
                    }
                }
            }

        }
    }

    public static void updateGameType(String id, boolean isCommandGame) {

        for (int i = 0; i < gamesArray.length; i++) {
            if (gamesArray[i] != null) {
                if (gamesArray[i].getId().equals(id)) {
                    gamesArray[i].setCommandGame(isCommandGame);
                    break;
                }
            }
        }

    }

    //operations delete from CRUD
    public static boolean deletePlayer(String id) {
        boolean wasDeletedEverywhere = false;

        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] != null) {

                if (playersArray[i].getId().equals(id)) {
                    playersArray[i] = null;

                    //if player was deleted from Players, we should also delete it at once from all Games
                    for (int j = 0; j < gamesArray.length; j++) {
                        Game currentGame = gamesArray[j];

                        if (currentGame != null) {
                            String[] playerIdList = currentGame.getPlayerIdList();

                            //check playerIdList of every Game for the availability of playerId, when found - break
                            for (int k = 0; k < playerIdList.length; k++) {
                                if (playerIdList[k] != null) {

                                    if (playerIdList[k].equals(id)) {
                                        playerIdList[k] = null;

                                        break;
                                    }

                                }
                            }

                        }
                        //when checked all games for exact playerId
                        wasDeletedEverywhere = true;
                    }

                    break;
                }

            }
        }

        return wasDeletedEverywhere;

    }

    public static boolean deleteGame(String id) {
        boolean wasDeletedEverywhere = false;
        boolean wasDeletedFromGamesArray = false;
        boolean wasDeletedFromPlayersArray = false;

        for (int i = 0; i < gamesArray.length; i++) {
            if (gamesArray[i] != null) {

                if (gamesArray[i].getId().equals(id)) {
                    gamesArray[i] = null;
                    wasDeletedFromGamesArray = true;
                    break;
                }

            }
        }

        //if game was deleted from Games, we should also delete it at once from all Players
        if(wasDeletedFromGamesArray) {
            for (int j = 0; j < playersArray.length; j++) {
                Player currentPlayer = playersArray[j];

                if (currentPlayer != null) {
                    String[] gameIdList = currentPlayer.getGameIdList();

                    //check gameIdList of every Player for the availability of gameId, when found - break
                    for (int k = 0; k < gameIdList.length; k++) {
                        if (gameIdList[k] != null) {

                            if (gameIdList[k].equals(id)) {
                                gameIdList[k] = null;
                                break;
                            }

                        }
                    }

                }
            }
            //when checked all players for exact gameId
            wasDeletedEverywhere = true;
        } else {
            System.out.println(redText.format("\n_Inside_dbStorage_Your game can't be deleted in automatic mode. Please contact with support service."));
        }

        return wasDeletedEverywhere;
    }

    //relation operations create from CRUD
    public static boolean addPlayerToGame(String playerId, String gameId) {
            boolean successfullyAdded = false;

            Game game = getGame(gameId);

            String[] playersIdList = game.getPlayerIdList();
            int count = 0;

            for (int i = 0; i < playersIdList.length; i++) {
                if (playersIdList[i] != null) {
                    if (playersIdList[i].equals(playerId)) {
                        count++;
                    }
                }
            }

                //check if playersIdList already has such player id
                if (count == 0) {
                    successfullyAdded = addPlayerIdToPlayersIdList(playerId, gameId);
                }

                return successfullyAdded;

    }

    public static boolean addPlayerIdToPlayersIdList(String playerId, String gameId) {
        boolean successfullyAdded = false;

        Game game = getGame(gameId);
        String[] playersIdList = game.getPlayerIdList();
        int playerIdCount = game.getPlayerIdCount();

        //make playersIdList array larger
        if (playerIdCount == playersIdList.length) {
            int newLength = playersIdList.length * 2;
            String[] playersIdListNew = Arrays.copyOf(playersIdList, newLength);
            playersIdList = playersIdListNew;
        }

        for (int i = 0; i < playersIdList.length; i++) {
            if (playersIdList[i] == null) {
                playersIdList[i] = playerId;
                successfullyAdded = true;

                playerIdCount = playerIdCount + 1;
                game.setPlayerIdCount(playerIdCount);

                break;
            }
        }

        return successfullyAdded;
    }

    public static boolean addGameIdToGamesIdList(String gameId, String playerId) {
        boolean successfullyAdded = false;

        Player player = getPlayer(playerId);
        String[] gamesIdList = player.getGameIdList();
        int gameIdCount = player.getGameIdCount();

        //make gamesIdList array larger
        if (gameIdCount == gamesIdList.length) {
            int newLength = gamesIdList.length * 2;
            String[] gamesIdListNew = Arrays.copyOf(gamesIdList, newLength);
            gamesIdList = gamesIdListNew;
        }

        for (int i = 0; i < gamesIdList.length; i++) {
            if (gamesIdList[i] == null) {
                gamesIdList[i] = gameId;
                successfullyAdded = true;

                gameIdCount = gameIdCount + 1;
                player.setGameIdCount(gameIdCount);

                break;
            }
        }

        return successfullyAdded;
    }

    public static void addGameToPlayer(String gameId, String playerId) {

            Player player = getPlayer(playerId);

                String[] gamesIdList = player.getGameIdList();
                int count = 0;

                for (int i = 0; i < gamesIdList.length; i++) {
                    if (gamesIdList[i] != null) {
                        if (gamesIdList[i].equals(playerId)) {
                            count++;
                        }
                    }
                }

                //check if gamesIdList already has such game id
                if (count == 0) {
                    boolean wasAddedGameIdToGamesIdList = addGameIdToGamesIdList(gameId, playerId);

                    if (wasAddedGameIdToGamesIdList) {
                        //when we add gameId to the player, at once we have to add also playerId to the game
                        boolean wasAddedPlayerToGame = addPlayerToGame(playerId, gameId);
                        if (wasAddedPlayerToGame) {
                            System.out.println(blueText.format("\nGame was successfully attached to the player."));
                        } else {
                            System.out.println(redText.format("--1--This game can't be added to this player in automatic mode. Please contact with support service."));
                        }
                    } else {
                        System.out.println(redText.format("--2--This game can't be added to this player in automatic mode. Please contact with support service."));
                    }

                } else {
                    System.out.println(redText.format("We have already game with such id for this player!"));
                }

    }

    public static Player[] findPlayersByGame(String gameId) {
        Game game = getGame(gameId);
        String[] playersIds = game.getPlayerIdList();

        Player[] playersList = new Player[playersIds.length];

        for (int i = 0; i < playersIds.length; i++) {
            if (playersIds[i] != null) {
                Player player = getPlayer(playersIds[i]);
                playersList[i] = player;
            }
        }

        return playersList;
    }

    public static Game[] findGamesByPlayer(String playerId) {
        Player player = getPlayer(playerId);
        String[] gamesIds = player.getGameIdList();

        Game[] gamesList = new Game[gamesIds.length];

        for (int i = 0; i < gamesIds.length; i++) {
            if (gamesIds[i] != null) {
                Game game = getGame(gamesIds[i]);
                gamesList[i] = game;
            }
        }

        return gamesList;
    }

    public static boolean deletePlayerFromGame(String playerId, String gameId) {
        boolean successfullyDeleted = false;
        Game game = getGame(gameId);

        String[] playersIdList = game.getPlayerIdList();

        for (int i = 0; i < playersIdList.length; i++) {
            if (playersIdList[i] != null) {

                if (playersIdList[i].equals(playerId)) {
                    playersIdList[i] = null;
                    successfullyDeleted = true;
                    break;
                }
//                else {
//                    System.out.println(redText.format("We don't have player with such id in this game. Please check your info."));
//                }

            }
        }

        return successfullyDeleted;

    }

    public static void deleteGameFromPlayer(String gameId, String playerId) {
        Player player = getPlayer(playerId);

        String[] gameIdList = player.getGameIdList();

        for (int i = 0; i < gameIdList.length; i++) {
            if (gameIdList[i] != null) {

                if (gameIdList[i].equals(gameId)) {
                    gameIdList[i] = null;

                    //when we delete gameId from player, at one time we have to delete playerId from game
                    boolean wasDeletedPlayerFromGame = deletePlayerFromGame(playerId, gameId);

                    if (wasDeletedPlayerFromGame) {
                        System.out.println(blueText.format("\nGame was successfully deleted from player."));
                    } else {
                        System.out.println(redText.format("\nGame can't be deleted from player in automatic mode. Please contact with support service."));
                    }

                    break;
                } else {
                    System.out.println(redText.format("We don't have game with such id for this player. Please check your info."));
                }

            }
        }

    }


    //check if exist object with such id
    public static boolean existPlayerId(String playerId) {
        boolean existPlayerId;

        Player player = getPlayer(playerId);

        if (player == null) {
            existPlayerId = false;
            System.out.println(redText.format("We can't find player with such id!"));
        } else {
            existPlayerId = true;
        }

        return existPlayerId;
    }

    public static boolean existGameId(String gameId) {
        boolean existGameId;

        Game game = getGame(gameId);

        if (game == null) {
            existGameId = false;
            System.out.println(redText.format("We can't find game with such id!"));
        } else {
            existGameId = true;
        }

        return existGameId;
    }


    //formatting
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
