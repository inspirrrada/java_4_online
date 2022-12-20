package ua.com.alevel.db;

import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.util.ArrayList;
import java.util.List;
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
    private static void addPlayer(Player player) {
            player.setId(generatePlayerId());
            players.add(player);
    }

    private static void addGame(Game game) {
        game.setId(generateGameId());
        games.add(game);
    }

    //operations read from CRUD
    private static Player getPlayer(String id) {
        Player player = null;

        for (int i = 0; i < players.size(); i++) {
            if (games.get(i).getId().equals(id)) {
                player = players.get(i);
                break;
            }
        }

        return player;
    }

    private static List<Player> getAllPlayers() {
        return players;
    }

    private static Game getGame(String id) {
        Game game = null;

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId().equals(id)) {
                game = games.get(i);
                break;
            }
        }

        return game;
    }

    private static List<Game> getAllGames() {
        return games;
    }

    //operations update from CRUD
    private static void updatePlayerAge(String id, int age) {
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

    private static void updatePlayerEmail(String id, String email) {
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

    private static void updatePlayerNickname(String id, String nickname) {
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

    private static void updateGameName(String id, String name) {
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

    private static void updateGameType(String id, boolean isCommandGame) {

            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).getId().equals(id)) {
                    games.get(i).setCommandGame(isCommandGame);
                    break;
                }
            }
    }

    //operations delete from CRUD
    private static void deletePlayer(String id) {

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId().equals(id)) {
                players.remove(i);
                break;
            }
        }
    }

    private static void deleteGame(String id) {

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId().equals(id)) {
                games.remove(i);
                break;
            }
        }
    }

    //relation operations create from CRUD
    private static void addPlayerToGame(String playerId, String gameId) {

    }

}
