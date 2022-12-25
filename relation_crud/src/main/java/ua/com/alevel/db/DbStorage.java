package ua.com.alevel.db;

import com.diogonunes.jcolor.AnsiFormat;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.diogonunes.jcolor.Attribute.*;

public class DbStorage {

    private static List<Player> players = new ArrayList<>();
    private static List<Game> games = new ArrayList<>();

    private static AnsiFormat redText = new AnsiFormat(BRIGHT_RED_TEXT());
    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat greenText = new AnsiFormat(GREEN_TEXT());
    private static AnsiFormat cyanText = new AnsiFormat(CYAN_TEXT());
    private static AnsiFormat cyanBrightText = new AnsiFormat(BRIGHT_CYAN_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());
    private static AnsiFormat overlinedText = new AnsiFormat(OVERLINED());
    private static AnsiFormat italicText = new AnsiFormat(ITALIC());

    private DbStorage() {}

    //set unique id for players and games
    private static String generatePlayerId() {
        String id = UUID.randomUUID().toString();

        for(int i = 0; i < players.size(); i++) {
            if (players.get(i) != null) {
                if (players.get(i).getId().equals(id)) {
                    return generatePlayerId();
                }
            }
        }

        return id;
    }

    private static String generateGameId() {
        String id = UUID.randomUUID().toString();

        for(int i = 0; i < games.size(); i++) {
            //if (games.get(i) != null) {
                if (games.get(i).getId().equals(id)) {
                    return generateGameId();
                }
            //}
        }

        return id;
    }

    //operations create from CRUD
    public static void addPlayer(Player player) {
            player.setId(generatePlayerId());
            players.add(player);
    }

    public static void addGame(Game game) {
        game.setId(generateGameId());
        games.add(game);
    }

    //operations read from CRUD
    public static Player getPlayer(String id) {
        Player player = null;

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != null) {

                if (players.get(i).getId().equals(id)) {
                    player = players.get(i);
                    break;
                }

            }
        }

        return player;
    }

    public static List<Player> getAllPlayers() {
        return players;
    }

    public static Game getGame(String id) {
        Game game = null;

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i) != null) {

                if (games.get(i).getId().equals(id)) {
                    game = games.get(i);
                    break;
                }

            }
        }

        return game;
    }

    public static List<Game> getAllGames() {
        return games;
    }

    //operations update from CRUD
    public static void updatePlayerAge(String id, int age) {
        if (age < 18) {
            System.out.println(redText.format("Age is too young for this game. Your changes of age weren't saved."));
        } else if (age > 100) {
            System.out.println(redText.format("Age is too great for this game. Your changes of age weren't saved."));
        } else {

            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getId().equals(id)) {
                    players.get(i).setAge(age);
                    break;
                }
            }

        }
    }

    public static void updatePlayerEmail(String id, String email) {
        if (email.matches("^(.+)@(.+)$")) {

            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getId().equals(id)) {
                    players.get(i).setEmail(email);
                    break;
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

            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getId().equals(id)) {
                    players.get(i).setNickname(nickname);
                    break;
                }
            }

        }
    }

    public static void updateGameName(String id, String name) {
        if (name.matches("\\d+")) {
            System.out.println(redText.format("Game name can't contain only digits!"));
        } else {

            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).getId().equals(id)) {
                    games.get(i).setName(name);
                    break;
                }
            }

        }
    }

    public static void updateGameType(String id, boolean isCommandGame) {

            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).getId().equals(id)) {
                    games.get(i).setCommandGame(isCommandGame);
                    break;
                }
            }
    }

    //operations delete from CRUD
    public static boolean deletePlayer(String id) {
        boolean wasDeletedEverywhere = false;

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != null) {

                if (players.get(i).getId().equals(id)) {
                    players.remove(i);

                    //if player was deleted from Players, we should also delete it at once from all Games
                    //List<Game> allGames = getAllGames();
                    for (int j = 0; j < games.size(); j++) {
                        Game currentGame = games.get(j);

                        if (currentGame != null) {
                            Set<String> playerIdList = currentGame.getPlayerIdList();

                            for (String playerId : playerIdList) {
                                if (playerId.equals(id)) {
                                    playerIdList.remove(id);
                                    wasDeletedEverywhere = true;
                                    break;
                                }
                            }

                        }
                    }
                    break;
                }
            }
        }

        return wasDeletedEverywhere;

    }

    public static boolean deleteGame(String id) {
        boolean wasDeletedEverywhere = false;

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i) != null) {

                if (games.get(i).getId().equals(id)) {
                    games.remove(i);

                    //List<Player> allPlayers = getAllPlayers();
                    for (int j = 0; j < players.size(); j++) {
                        Player currentPlayer = players.get(j);

                        if (currentPlayer != null) {
                            Set<String> gameIdList = currentPlayer.getGameIdList();

                            for (String gameId : gameIdList) {
                                if (gameId.equals(id)) {
                                    gameIdList.remove(id);
                                    break;
                                }
                            }

                        }
                    }

                    break;
                }
            }
        }

        return wasDeletedEverywhere;
    }

    //relation operations create from CRUD
    public static boolean addPlayerToGame(String playerId, String gameId) {
            boolean successfullyAdded = false;

            Game game = getGame(gameId);

                Set<String> playersIdList = game.getPlayerIdList();
                int count = 0;

                for (String currentPlayerId : playersIdList) {
                    if (currentPlayerId.equals(playerId)) {
                        count++;
                    }
                }

                //check if playersIdList already has such player id
                if (count == 0) {
                    playersIdList.add(playerId);
                    successfullyAdded = true;
                } else {
                    //System.out.println(redText.format("We have already player with such id in this game!"));
                }

                return successfullyAdded;

    }

    public static void addGameToPlayer(String gameId, String playerId) {

            Player player = getPlayer(playerId);

                Set<String> gamesIdList = player.getGameIdList();
                int count = 0;

                for (String currentGameId : gamesIdList) {
                    if (currentGameId.equals(gameId)) {
                        count++;
                    }
                }

                //check if gamesIdList already has such game id
                if (count == 0) {
                    gamesIdList.add(gameId);

                    //when we add gameId to the player, at once we have to add also playerId to the game
                    boolean wasAddedPlayerToGame = addPlayerToGame(playerId, gameId);
                        if (wasAddedPlayerToGame) {
                            System.out.println(blueText.format("\nGame was successfully attached to the player."));
                        } else {
                            System.out.println(redText.format("This game can't be added to this player in automatic mode. Please contact with support service."));
                        }

                } else {
                    System.out.println(redText.format("We have already game with such id for this player!"));
                }

    }

    public static List<Player> findPlayersByGame(String gameId) {
        Game game = getGame(gameId);
        Set<String> playersIds = game.getPlayerIdList();
        List<Player> playersList = new ArrayList<>();

        for (String playerId : playersIds) {
            Player player = getPlayer(playerId);

            if (player != null) {
                playersList.add(player);
            }
        }

        return playersList;
    }

    public static List<Game> findGamesByPlayer(String playerId) {
        Player player = getPlayer(playerId);
        Set<String> gamesIds = player.getGameIdList();
        List<Game> gamesList = new ArrayList<>();

        for (String gameId : gamesIds) {
            Game game = getGame(gameId);

            if (game != null) {
                gamesList.add(game);
            }
        }

        return gamesList;
    }

    public static boolean deletePlayerFromGame(String playerId, String gameId) {
        boolean successfullyDeleted = false;
        Game game = getGame(gameId);

        Set<String> playersIdList = game.getPlayerIdList();

        for (String currentPlayerId : playersIdList) {

            if (currentPlayerId.equals(playerId)) {
                playersIdList.remove(playerId);
                successfullyDeleted = true;
                break;
            } else {
                System.out.println(redText.format("We don't have player with such id in this game. Please check your info."));
            }

        }

        return successfullyDeleted;

    }

    public static void deleteGameFromPlayer(String gameId, String playerId) {
        Player player = getPlayer(playerId);

        Set<String> gameIdList = player.getGameIdList();

        for (String currentGameId : gameIdList) {
            if (currentGameId.equals(gameId)) {
                gameIdList.remove(gameId);

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

    public static AnsiFormat getGreenText() {
        return greenText;
    }

    public static AnsiFormat getCyanText() {
        return cyanText;
    }

    public static AnsiFormat getCyanBrightText() {
        return cyanBrightText;
    }

    public static AnsiFormat getReverse() {
        return reverse;
    }

    public static AnsiFormat getUnderlinedText() {
        return underlinedText;
    }

    public static AnsiFormat getOverlinedText() {
        return overlinedText;
    }

    public static AnsiFormat getItalicText() {
        return italicText;
    }
}
