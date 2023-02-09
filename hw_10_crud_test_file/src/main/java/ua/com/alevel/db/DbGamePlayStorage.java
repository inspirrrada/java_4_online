package ua.com.alevel.db;

import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;
import ua.com.alevel.service.GamePlayService;
import ua.com.alevel.utils.ColorUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DbGamePlayStorage {

    private List<Player> players;
    private List<Game> games;
    private String playersFile = "players.json";
    private String gamesFile = "games.json";
    private Path playersPath = Paths.get("players.json"); // nio
    private Path gamesPath = Paths.get("games.json"); // nio

    private static DbGamePlayStorage instance;

    public DbGamePlayStorage() {
        try {
            if (!Files.exists(playersPath)) {
                Files.createFile(playersPath);
                players = new ArrayList<>();
                writeToFile(players.toString(), playersFile);
            }
            if (!Files.exists(gamesPath)) {
                Files.createFile(gamesPath);
                games = new ArrayList<>();
                writeToFile(games.toString(), gamesFile);
            }
        } catch (IOException e) {
            System.out.println("Oops...Something went wrong:( Please contact our client support.");
            e.printStackTrace();
        }
    }

//    public static DbGamePlayStorage getInstance() {
//        if (instance == null) {
//            instance = new DbGamePlayStorage();
//        }
//        return instance;
//    }

    /**
     * ------------------------------------
     * methods for work with files
     */

    public List<Player> readPlayersFromFile() {
        players = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(playersFile))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line.length() < 3) {
                    continue;
                }
                line = line.replaceAll("\"", "");
                String playerString = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
                String[] playerArr = playerString.split(", ");
                for (String s : playerArr) {
                    String[] playerField = s.split(":");
                    map.put(playerField[0], playerField[1]);
                }
                Player player = new Player();
                map.forEach((k,v) -> {
                    switch (k) {
                        case "id" -> player.setId((String) v);
                        case "age" -> player.setAge(Integer.parseInt((String) v));
                        case "email" -> player.setEmail((String) v);
                        case "nickname" -> player.setNickname((String) v);
                        case "gameIdList" -> {
                            Set<String> set = new HashSet<>();
                            if (v.equals("[]")) {
                                player.setGameIdList(set);
                            } else {
                                String value = (String) v;
                                value = value.substring(1, value.length() - 1).replace("\"", "");
                                String[] valueArr = value.split(",");
                                for (String s : valueArr) {
                                    set.add(s);
                                }
                                player.setGameIdList(set);
                            }
                        }
                    }
                });
                players.add(player);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        System.out.println("players from file: " + players);
        return players;
    }

    public List<Game> readGamesFromFile() {
        games = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(gamesFile))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line.length() < 3) {
                    continue;
                }
                line = line.replaceAll("\"", "");
                String gameString = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
                String[] gameArr = gameString.split(", ");
                for (String s : gameArr) {
                    String[] gameField = s.split(":");
                    map.put(gameField[0], gameField[1]);
                }
                Game game = new Game();
                map.forEach((k,v) -> {
                    switch (k) {
                        case "id" -> game.setId((String) v);
                        case "name" -> game.setName((String) v);
                        case "commandGame" -> game.setCommandGame(Boolean.valueOf((String) v));
                        case "playerIdList" -> {
                            Set<String> set = new HashSet<>();
                            if (v.equals("[]")) {
                                game.setPlayerIdList(set);
                            } else {
                                String value = (String) v;
                                value = value.substring(1, value.length() - 1).replace("\"", "");
                                String[] valueArr = value.split(",");
                                for (String s : valueArr) {
                                    set.add(s);
                                }
                                game.setPlayerIdList(set);
                            }
                        }
                    }
                });
                games.add(game);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        System.out.println("games from file: " + games);
        return games;
    }

    public void writeToFile(String object, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(object);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * ------------------------------------
     * set unique id for players and games
     */
    private String generatePlayerId() {
        String id = UUID.randomUUID().toString();
        for (Player player : players) {
            if (player != null) {
                if (player.getId().equals(id)) {
                    return generatePlayerId();
                }
            }
        }
        return id;
    }

    private String generateGameId() {
        String id = UUID.randomUUID().toString();
        for (Game game : games) {
            if (game != null) {
                if (game.getId().equals(id)) {
                    return generateGameId();
                }
            }
        }
        return id;
    }

    /**
     * ------------------------------------
     * operations create from CRUD
     */
    public void addPlayer(Player player) {
        players = readPlayersFromFile();
        player.setId(generatePlayerId());
        players.add(player);
        writeToFile(players.toString(), playersFile);
    }

    public void addGame(Game game) {
        games = readGamesFromFile();
        game.setId(generateGameId());
        games.add(game);
        writeToFile(games.toString(), gamesFile);
    }

    /**
     * ------------------------------------
     * operations read from CRUD
     */
    public Player getPlayerByIdOrNull(String id) {
        Player player = null;
        players = readPlayersFromFile();
        for (Player value : players) {
            if (value != null) {
                if (value.getId().equals(id)) {
                    player = value;
                    break;
                }
            }
        }
        return player;
    }

    public List<Player> getAllPlayers() {
        players = readPlayersFromFile();
        return players;
    }

    public Game getGameByIdOrNull(String id) {
        Game game = null;
        games = readGamesFromFile();
        for (Game value : games) {
            if (value != null) {
                if (value.getId().equals(id)) {
                    game = value;
                    break;
                }
            }
        }
        return game;
    }
    public List<Game> getAllGames() {
        games = readGamesFromFile();
        return games;
    }


    /**
     * ------------------------------------
     * operations update from CRUD
     */
    public void updatePlayerAge(String id, int age) {
        players = readPlayersFromFile();
        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setAge(age);
                break;
            }
        }
        writeToFile(players.toString(), playersFile);
    }

    public void updatePlayerEmail(String id, String email) {
        players = readPlayersFromFile();
        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setEmail(email);
                break;
            }
        }
        writeToFile(players.toString(), playersFile);
    }

    public void updatePlayerNickname(String id, String nickname) {
        players = readPlayersFromFile();
        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setNickname(nickname);
                break;
            }
        }
        writeToFile(players.toString(), playersFile);
    }

    public void updateGameName(String id, String name) {
        games = readGamesFromFile();
        for (Game game : games) {
            if (game.getId().equals(id)) {
                game.setName(name);
                break;
            }
        }
        writeToFile(games.toString(), gamesFile);
    }

    public void updateGameType(String id, boolean isCommandGame) {
        games = readGamesFromFile();
        for (Game game : games) {
            if (game.getId().equals(id)) {
                game.setCommandGame(isCommandGame);
                break;
            }
        }
        writeToFile(games.toString(), gamesFile);
    }


    /**
     * ------------------------------------
     * operations delete from CRUD
     */
    public boolean deletePlayer(String id) {
        players = readPlayersFromFile();
        games = readGamesFromFile();
        boolean wasDeletedEverywhere = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != null) {
                if (players.get(i).getId().equals(id)) {
                    players.remove(i);
                    //if player was deleted from Players, we should also delete it at once from all Games
                    for (Game currentGame : games) {
                        if (currentGame != null) {
                            Set<String> playerIdList = currentGame.getPlayerIdList();
                            for (String playerId : playerIdList) {
                                if (playerId.equals(id)) {
                                    playerIdList.remove(id);
                                    break;
                                }
                            }
                        }
                    }
                    wasDeletedEverywhere = true;
                    break;
                }
            }
        }
        writeToFile(players.toString(), playersFile);
        writeToFile(games.toString(), gamesFile);
        return wasDeletedEverywhere;
    }

    public boolean deleteGame(String id) {
        games = readGamesFromFile();
        players = readPlayersFromFile();
        boolean wasDeletedEverywhere = false;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i) != null) {
                if (games.get(i).getId().equals(id)) {
                    games.remove(i);
                    //if game was deleted from Games, we should also delete it at once from all Players
                    for (Player currentPlayer : players) {
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
                    wasDeletedEverywhere = true;
                    break;
                }
            }
        }
        writeToFile(games.toString(), gamesFile);
        writeToFile(players.toString(), playersFile);
        return wasDeletedEverywhere;
    }

    /**
     * ------------------------------------
     * relation operations create from CRUD
     */
    public boolean addOnlyPlayerToGame(String playerId, String gameId) {
        boolean successfullyAdded = false;
        Game game = getGameByIdOrNull(gameId);
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

    public void addGameToPlayerInAllDb(String gameId, String playerId) {
        //boolean successfullyAdded = false;
        Player player = getPlayerByIdOrNull(playerId);
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
            boolean wasAddedPlayerToGame = addOnlyPlayerToGame(playerId, gameId);
            if (wasAddedPlayerToGame) {
                //successfullyAdded = true;
                System.out.println(ColorUtils.getBlueText().format("\nGame was successfully attached to the player."));
            } else {
                System.out.println(ColorUtils.getRedText().format("This game can't be added to this player in automatic mode. Please contact with support service."));
            }
        } else {
            System.out.println(ColorUtils.getRedText().format("We have already game with such id for this player!"));
        }
    }

    /**
     * ------------------------------------
     * relation operations read from CRUD
     */
    public List<Player> getPlayersByGame(String gameId) {
        Game game = getGameByIdOrNull(gameId);
        Set<String> playersIds = game.getPlayerIdList();
        List<Player> players = new ArrayList<>();
        for (String playerId : playersIds) {
            Player player = getPlayerByIdOrNull(playerId);
            if (player != null) {
                players.add(player);
            }
        }
        return players;
    }

    public List<Game> getGamesByPlayer(String playerId) {
        Player player = getPlayerByIdOrNull(playerId);
        Set<String> gamesIds = player.getGameIdList();
        List<Game> games = new ArrayList<>();
        for (String gameId : gamesIds) {
            Game game = getGameByIdOrNull(gameId);
            if (game != null) {
                games.add(game);
            }
        }
        return games;
    }

    /**
     * ------------------------------------
     * relation operations delete from CRUD
     */
    public boolean deleteOnlyPlayerFromGame(String playerId, String gameId) {
        boolean successfullyDeleted = false;
        Game game = getGameByIdOrNull(gameId);
        Set<String> playersIdList = game.getPlayerIdList();
        for (String currentPlayerId : playersIdList) {
            if (currentPlayerId.equals(playerId)) {
                playersIdList.remove(playerId);
                successfullyDeleted = true;
                break;
            }
        }
        return successfullyDeleted;
    }

    public boolean deleteGameFromPlayerInAllDb(String gameId, String playerId) {
        boolean successfullyDeleted = false;
        Player player = getPlayerByIdOrNull(playerId);
        Set<String> gameIdList = player.getGameIdList();
        for (String currentGameId : gameIdList) {
            if (currentGameId.equals(gameId)) {
                gameIdList.remove(gameId);
                //when we delete gameId from player, at one time we have to delete playerId from game
                boolean wasDeletedPlayerFromGame = deleteOnlyPlayerFromGame(playerId, gameId);
                if (wasDeletedPlayerFromGame) {
                    successfullyDeleted = true;
                    System.out.println(ColorUtils.getBlueText().format("\nGame was successfully deleted from player."));
                } else {
                    System.out.println(ColorUtils.getRedText().format("\nGame can't be deleted from player in automatic mode. Please contact with support service."));
                }
                break;
            } else {
                System.out.println(ColorUtils.getRedText().format("We don't have game with such id for this player. Please check your info."));
            }
        }
        return successfullyDeleted;
    }

    /**
     * ------------------------------------
     * check data
     */
    public boolean hasTheSameEmail(String email) {
        boolean hasTheSameEmail = false;
        if (players != null) {
            for (Player player : players) {
                if (player != null) {
                    if (player.getEmail().equalsIgnoreCase(email)) {
                        hasTheSameEmail = true;
                        break;
                    }
                }
            }
        }
        return hasTheSameEmail;
    }

    public boolean hasTheSameNickname(String nickname) {
        boolean hasTheSameNickname = false;
        if (players != null) {
            for (Player player : players) {
                if (player != null) {
                    if (player.getNickname().equalsIgnoreCase(nickname)) {
                        hasTheSameNickname = true;
                        break;
                    }
                }
            }
        }
        return hasTheSameNickname;
    }

    public boolean hasTheSameGameName(String gameName) {
        boolean hasTheSameGameName = false;
        if (games != null) {
            for (Game game : games) {
                if (game != null) {
                    if (game.getName().equalsIgnoreCase(gameName)) {
                        hasTheSameGameName = true;
                        break;
                    }
                }
            }
        }
        return hasTheSameGameName;
    }

    public boolean existPlayerId(String playerId) {
        return getPlayerByIdOrNull(playerId) != null;
    }

    public boolean existGameId(String gameId) {
        return getGameByIdOrNull(gameId) != null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "DbGamePlayStorage{" +
                "players=" + players +
                ", games=" + games +
                '}';
    }
}
