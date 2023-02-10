package ua.com.alevel.dao;

import com.google.gson.Gson;
import ua.com.alevel.db.DbGamePlayStorage;
import ua.com.alevel.db.PlayersContainer;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GamePlayDaoJson {

    DbGamePlayStorage dbGamePlayStorage = new DbGamePlayStorage();

    public GamePlayDaoJson() {
        System.out.println("This is GamePlayDaoJson");
//        Path playersPath = Paths.get("players.json"); // nio
//        Path gamesPath = Paths.get("games.json"); // nio
//        try {
//            if (!Files.exists(playersPath)) {
//                Files.createFile(playersPath);
//                dbGamePlayStorage.writeToFile(dbGamePlayStorage.getPlayers().toString());
//            }
//            if (!Files.exists(gamesPath)) {
//                Files.createFile(gamesPath);
//            }
//        } catch (IOException e) {
//            System.out.println("Oops...Something went wrong:( Please contact our client support.");
//            e.printStackTrace();
//        }
    }

    //------------------------------------
    //operations create from CRUD
//    @Override
    public void addPlayer(Player player) {
        dbGamePlayStorage.addPlayer(player);
    }

//    @Override
    public void addGame(Game game) {
        dbGamePlayStorage.addGame(game);
    }

    //------------------------------------
    //operations read from CRUD
//    @Override
    public Player getPlayerByIdOrNull(String id) {
        return dbGamePlayStorage.getPlayerByIdOrNull(id);
    }
//
//    @Override
    public List<Player> getAllPlayers() {
        return dbGamePlayStorage.getAllPlayers();
    }

//    @Override
    public Game getGameByIdOrNull(String id) {
        return dbGamePlayStorage.getGameByIdOrNull(id);
    }

//    @Override
    public List<Game> getAllGames() {
        return dbGamePlayStorage.getAllGames();
    }


    //------------------------------------
    //operations update from CRUD
//    @Override
    public void updatePlayerAge(String id, int age) {
        dbGamePlayStorage.updatePlayerAge(id, age);
    }

//    @Override
    public void updatePlayerEmail(String id, String email) {
        dbGamePlayStorage.updatePlayerEmail(id, email);
    }

//    @Override
    public void updatePlayerNickname(String id, String nickname) {
        dbGamePlayStorage.updatePlayerNickname(id, nickname);
    }

//    @Override
    public void updateGameName(String id, String name) {
        dbGamePlayStorage.updateGameName(id, name);
    }

//    @Override
    public void updateGameType(String id, boolean isCommandGame) {
        dbGamePlayStorage.updateGameType(id, isCommandGame);
    }


    //------------------------------------
    //operations delete from CRUD
//    @Override
    public boolean deletePlayer(String id) {
        return dbGamePlayStorage.deletePlayer(id);
    }

//    @Override
    public boolean deleteGame(String id) {
        return dbGamePlayStorage.deleteGame(id);
    }


    //------------------------------------
    //relation operations create from CRUD
//    @Override
    public boolean addOnlyPlayerToGame(String playerId, String gameId) {
        return dbGamePlayStorage.addOnlyPlayerToGame(playerId, gameId);
    }

//    @Override
    public void addGameToPlayerInAllDb(String gameId, String playerId) {
        dbGamePlayStorage.addGameToPlayerInAllDb(gameId, playerId);
    }


    //------------------------------------
    //relation operations read from CRUD
//    @Override
    public List<Player> getPlayersByGame(String gameId) {
        return dbGamePlayStorage.getPlayersByGame(gameId);
    }

//    @Override
    public List<Game> getGamesByPlayer(String playerId) {
        return dbGamePlayStorage.getGamesByPlayer(playerId);
    }


    //------------------------------------
    //relation operations delete from CRUD
//    @Override
    public boolean deleteOnlyPlayerFromGame(String playerId, String gameId) {
        return dbGamePlayStorage.deleteOnlyPlayerFromGame(playerId, gameId);
    }

//    @Override
    public boolean deleteGameFromPlayerInAllDb(String gameId, String playerId) {
        return dbGamePlayStorage.deleteGameFromPlayerInAllDb(gameId, playerId);
    }


    //------------------------------------
    //check for duplicate data
//    @Override
    public boolean hasTheSameEmail(String email) {
        return dbGamePlayStorage.hasTheSameEmail(email);
    }

//    @Override
    public boolean hasTheSameNickname(String nickname) {
        return dbGamePlayStorage.hasTheSameNickname(nickname);
    }

//    @Override
    public boolean hasTheSameGameName(String gameName) {
        return dbGamePlayStorage.hasTheSameGameName(gameName);
    }

//    @Override
    public boolean existPlayerId(String playerId) {
        return dbGamePlayStorage.existPlayerId(playerId);
    }

//    @Override
    public boolean existGameId(String gameId) {
        return dbGamePlayStorage.existGameId(gameId);
    }

//    private static class gamePlayContainer {
//        private List<Player> players;
//        private List<Game> games;
//
//        public List<Player> getPlayers() {
//            return players;
//        }
//
//        public void setPlayers(List<Player> players) {
//            this.players = players;
//        }
//
//        public List<Game> getGames() {
//            return games;
//        }
//
//        public void setGames(List<Game> games) {
//            this.games = games;
//        }
//    }
}
