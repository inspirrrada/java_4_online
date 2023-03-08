package ua.com.alevel.persistance.dao.impl;

import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistance.config.JdbcService;
import ua.com.alevel.persistance.dao.GameDao;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class GameDaoImpl implements GameDao {

    @InjectBean
    private JdbcService jdbcService;
    Connection connection = this.jdbcService.getConnection();

    private static final String CREATE_GAME = "insert into games values (default, ?, ?, ?)";
    private static final String GET_ALL_GAMES = "select * from games";
    private static final String GET_GAME_BY_ID = "select * from games where id = ";
    private static final String UPDATE_GAME_NAME = "update games set name = ? where id = ?";
    private static final String UPDATE_GAME_TYPE = "update games set command_type = ? where id = ?";
    private static final String DELETE_GAME = "delete from games where id = ?";
    private static final String GET_PLAYERS_COUNT_FOR_EVERY_GAME = "select games.id, games.name, count(player_id) as 'players_count' from games " +
            "left join games_players as general_table on games.id = general_table.game_id group by games.id";
    private static final String GET_ALL_PLAYERS_OF_GAME = "select id, nickname, age from players " +
            " left join games_players as 'general_table' on players.id = general_table.player_id " +
            " where general_table.game_id = ";

    private static final String ADD_PLAYER_TO_GAME = "";
    private static final String DELETE_PLAYER_FROM_GAME = "";

    @Override
    public void addGame(Game game) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_GAME)) {
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(game.getCreated().getTime()));
            preparedStatement.setString(2, game.getName());
            preparedStatement.setBoolean(3, game.isCommandGame());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Game> getGameById(String id) {
        return Optional.empty();
    }

    @Override
    public Collection<Game> getAllGames() {
        return null;
    }

    @Override
    public void updateGameName(String id, String name) {

    }

    @Override
    public void updateGameType(String id, boolean isCommandGame) {

    }

    @Override
    public boolean deleteGame(String id) {
        return false;
    }

    @Override
    public void addGameToPlayerInAllDb(String gameId, String playerId) {

    }

    @Override
    public Collection<Game> getGamesByPlayer(String playerId) {
        return null;
    }

    @Override
    public Collection<GameDto> getPlayersCountByGame() {
        return null;
    }

    @Override
    public boolean deleteGameFromPlayerInAllDb(String gameId, String playerId) {
        return false;
    }
}
