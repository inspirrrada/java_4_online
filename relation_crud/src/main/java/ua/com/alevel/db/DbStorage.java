package ua.com.alevel.db;

import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DbStorage {

    private static List<Player> players = new ArrayList<>();
    private static List<Game> games = new ArrayList<>();

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
            if (games.get(i).getId().equals(id)) {
                player = players.get(i);
                break;
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
            if (games.get(i).getId().equals(id)) {
                game = games.get(i);
                break;
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
            System.out.println("Age is too young for this game. Your changes of age weren't saved.");
        } else if (age > 100) {
            System.out.println("Age is too great for this game. Your changes of age weren't saved.");
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
            System.out.println("Invalid value!");
        }
    }

    public static void updatePlayerNickname(String id, String nickname) {
        if (nickname.matches("\\d+")) {
            System.out.println("Nickname can't contain only digits!");
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
            System.out.println("Game name can't contain only digits!");
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
    public static void deletePlayer(String id) {

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId().equals(id)) {
                players.remove(i);
                break;
            }
        }
    }

    public static void deleteGame(String id) {

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId().equals(id)) {
                games.remove(i);
                break;
            }
        }
    }

    //relation operations create from CRUD
    public static void addPlayerToGame(String playerId, String gameId) {
        Game game = getGame(gameId);
        Set<String> players = game.getPlayerIdList();
        players.add(playerId);
    }

    public static void addGameToPlayer(String gameId, String playerId) {
        Player player = getPlayer(playerId);
        Set<String> games = player.getGameIdList();
        games.add(gameId);
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


}
