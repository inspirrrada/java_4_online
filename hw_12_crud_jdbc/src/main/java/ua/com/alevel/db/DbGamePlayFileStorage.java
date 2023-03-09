package ua.com.alevel.db;

import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DbGamePlayFileStorage {

    private List<Player> players;
    private List<Game> games;
    private String playersFile;
    private String gamesFile;

    public DbGamePlayFileStorage(String playersFile, String gamesFile) {
        this.playersFile = playersFile;
        this.gamesFile = gamesFile;
        Path playersPath = Paths.get(playersFile);
        Path gamesPath = Paths.get(gamesFile);
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
        }
        games = readGamesFromFile();
        for (Game gameCurrent : games) {
            if (gameCurrent.getId().equals(gameId)) {
                gameCurrent.setPlayerIdList(playersIdList);
                break;
            }
        }
        writeToFile(games.toString(), gamesFile);
        return successfullyAdded;
    }

    public void addGameToPlayerInAllDb(String gameId, String playerId) {
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
                players = readPlayersFromFile();
                for (Player playerCurrent : players) {
                    if (playerCurrent.getId().equals(playerId)) {
                        playerCurrent.setGameIdList(gamesIdList);
                        break;
                    }
                }
                writeToFile(players.toString(), playersFile);
                System.out.println(ColorUtils.BLUE_TEXT.format("\nGame was successfully attached to the player."));
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("This game can't be added to this player in automatic mode. Please contact with support service."));
            }
        } else {
            System.out.println(ColorUtils.RED_TEXT.format("We have already game with such id for this player!"));
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
        games = readGamesFromFile();
        for (Game gameCurrent : games) {
            if (gameCurrent.getId().equals(gameId)) {
                gameCurrent.setPlayerIdList(playersIdList);
                break;
            }
        }
        writeToFile(games.toString(), gamesFile);
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
                    players = readPlayersFromFile();
                    for (Player playerCurrent : players) {
                        if (playerCurrent.getId().equals(playerId)) {
                            playerCurrent.setGameIdList(gameIdList);
                            break;
                        }
                    }
                    writeToFile(players.toString(), playersFile);
                    System.out.println(ColorUtils.BLUE_TEXT.format("\nGame was successfully deleted from player."));
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("\nGame can't be deleted from player in automatic mode. Please contact with support service."));
                }
                break;
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("We don't have game with such id for this player. Please check your info."));
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

    public String getPlayersFile() {
        return playersFile;
    }

    public void setPlayersFile(String playersFile) {
        this.playersFile = playersFile;
    }

    public String getGamesFile() {
        return gamesFile;
    }

    public void setGamesFile(String gamesFile) {
        this.gamesFile = gamesFile;
    }

    @Override
    public String toString() {
        return "DbGamePlayStorage{" +
                "players=" + players +
                ", games=" + games +
                '}';
    }
}
