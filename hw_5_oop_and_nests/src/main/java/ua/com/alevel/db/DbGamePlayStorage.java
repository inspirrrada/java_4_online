package ua.com.alevel.db;

import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;
import ua.com.alevel.service.GamePlayService;

import java.util.*;

public class DbGamePlayStorage {

    private List<Player> players = new ArrayList<>();
    private List<Game> games = new ArrayList<>();
    private static DbGamePlayStorage instance;

    private DbGamePlayStorage() {}

    public static DbGamePlayStorage getInstance() {
        if (instance == null) {
            instance = new DbGamePlayStorage();
        }
        return instance;
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
        player.setId(generatePlayerId());
        players.add(player);
    }

    public void addGame(Game game) {
        game.setId(generateGameId());
        games.add(game);
    }

    /**
     * ------------------------------------
     * operations read from CRUD
     */
    public Player getPlayerByIdOrNull(String id) {
        Player player = null;
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
        return players;
    }

    public Game getGameByIdOrNull(String id) {
        Game game = null;
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
        return games;
    }


    /**
     * ------------------------------------
     * operations update from CRUD
     */
    public void updatePlayerAge(String id, int age) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setAge(age);
                break;
            }
        }
    }

    public void updatePlayerEmail(String id, String email) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setEmail(email);
                break;
            }
        }
    }

    public void updatePlayerNickname(String id, String nickname) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setNickname(nickname);
                break;
            }
        }

    }

    public void updateGameName(String id, String name) {
        for (Game game : games) {
            if (game.getId().equals(id)) {
                game.setName(name);
                break;
            }
        }
    }

    public void updateGameType(String id, boolean isCommandGame) {
        for (Game game : games) {
            if (game.getId().equals(id)) {
                game.setCommandGame(isCommandGame);
                break;
            }
        }
    }


    /**
     * ------------------------------------
     * operations delete from CRUD
     */
    public boolean deletePlayer(String id) {
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
        return wasDeletedEverywhere;
    }

    public boolean deleteGame(String id) {
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
                System.out.println(GamePlayService.getBlueText().format("\nGame was successfully attached to the player."));
            } else {
                System.out.println(GamePlayService.getRedText().format("This game can't be added to this player in automatic mode. Please contact with support service."));
            }
        } else {
            System.out.println(GamePlayService.getRedText().format("We have already game with such id for this player!"));
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
                    System.out.println(GamePlayService.getBlueText().format("\nGame was successfully deleted from player."));
                } else {
                    System.out.println(GamePlayService.getRedText().format("\nGame can't be deleted from player in automatic mode. Please contact with support service."));
                }
                break;
            } else {
                System.out.println(GamePlayService.getRedText().format("We don't have game with such id for this player. Please check your info."));
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
        for (Player player : players) {
            if (player != null) {
                if (player.getEmail().equalsIgnoreCase(email)) {
                    hasTheSameEmail = true;
                    break;
                }
            }
        }
        return hasTheSameEmail;
    }

    public boolean hasTheSameNickname(String nickname) {
        boolean hasTheSameNickname = false;
        for (Player player : players) {
            if (player != null) {
                if (player.getNickname().equalsIgnoreCase(nickname)) {
                    hasTheSameNickname = true;
                    break;
                }
            }
        }
        return hasTheSameNickname;
    }

    public boolean hasTheSameGameName(String gameName) {
        boolean hasTheSameGameName = false;
        for (Game game : games) {
            if (game != null) {
                if (game.getName().equalsIgnoreCase(gameName)) {
                    hasTheSameGameName = true;
                    break;
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
}
