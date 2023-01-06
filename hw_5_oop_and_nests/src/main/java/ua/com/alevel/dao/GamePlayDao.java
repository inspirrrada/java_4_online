package ua.com.alevel.dao;


import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface GamePlayDao {

    //all methods for connection with db

    //------------------------------------
    //operations create from CRUD
    void addPlayer(Player player);

    void addGame(Game game);


    //------------------------------------
    //operations read from CRUD
    Player getPlayerByIdOrNull(String id);

    List<Player> getAllPlayers();

    Game getGameByIdOrNull(String id);

    List<Game> getAllGames();


    //------------------------------------
    //operations update from CRUD
    void updatePlayer(Player playerNew);

    void updateGame(Game gameNew);


    //------------------------------------
    //operations delete from CRUD
    boolean deletePlayer(String id);
    boolean deleteGame(String id);


    //------------------------------------
    //relation operations create from CRUD
    boolean addOnlyPlayerToGame(String playerId, String gameId);

    void addGameToPlayerInAllDb(String gameId, String playerId);


    //------------------------------------
    //relation operations read from CRUD
    List<Player> getPlayersByGame(String gameId);

    List<Game> getGamesByPlayer(String playerId);


    //------------------------------------
    //relation operations delete from CRUD
    boolean deleteOnlyPlayerFromGame(String playerId, String gameId);

    boolean deleteGameFromPlayerInAllDb(String gameId, String playerId);


    //------------------------------------
    //check data
    boolean hasTheSameEmail(String email);

    boolean hasTheSameNickname(String nickname);

    boolean hasTheSameGameName(String gameName);

    boolean existPlayerId(String playerId);

    boolean existGameId(String gameId);

}
