package ua.com.alevel.persistance.dao.impl;

import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistance.config.JdbcService;
import ua.com.alevel.persistance.dao.GameDao;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class GameDaoImpl implements GameDao {

    @InjectBean
    private JdbcService jdbcService;
    private Connection connection = this.jdbcService.getConnection();
    private Statement statement;

    private static final String CREATE_GAME = "insert into games values (default, ?, ?, ?)";
    private static final String GET_ALL_GAMES = "select * from games";
    private static final String GET_GAME_BY_ID = "select * from games where id = ";
    private static final String UPDATE_GAME_NAME = "update games set name = ? where id = ?";
    private static final String UPDATE_GAME_TYPE = "update games set command_type = ? where id = ?";
    private static final String DELETE_GAME = "delete from games where id = ?";
    private static final String GET_PLAYERS_COUNT_FOR_EVERY_GAME = "select games.id, games.name, count(player_id) as 'players_count' from games " +
            "left join games_players as general_table on games.id = general_table.game_id group by games.id";

    private static final String GET_ALL_GAMES_OF_PLAYER = "select id, name, command_type from games " +
            " left join games_players as 'general_table' on games.id = general_table.game_id " +
            " where general_table.player_id = ";

    private static final String ADD_PLAYER_TO_GAME = "insert into games_players values (?, ?)";
    private static final String DELETE_PLAYER_FROM_GAME = "delete from games_players values (?, ?)";

    @Override
    public void addGame(Game game) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_GAME)) {
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(game.getCreated().getTime()));
            preparedStatement.setString(2, game.getName());
            preparedStatement.setBoolean(3, game.isCommandGame());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Game> getGameById(Long id) {
        try(ResultSet resultSet = statement.executeQuery(GET_GAME_BY_ID + id)) {
            while(resultSet.next()) {
                return Optional.of(generateGameByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Game> getAllGames() {
        List<Game> allGames = new ArrayList<>();
        try(ResultSet resultSet = statement.executeQuery(GET_ALL_GAMES)) {
            while (resultSet.next()) {
                allGames.add(generateGameByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGames;
    }

    private Game generateGameByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Date created = new Date(resultSet.getTimestamp("created").getTime());
        String gameName = resultSet.getString("name");
        boolean commandType = resultSet.getBoolean("command_type");
        Game game = new Game();
        game.setId(id);
        game.setCreated(created);
        game.setName(gameName);
        game.setCommandGame(commandType);
        return game;
    }

    @Override
    public void updateGameName(Long id, String name) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GAME_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGameType(Long id, boolean isCommandGame) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GAME_TYPE)) {
            preparedStatement.setBoolean(1, isCommandGame);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteGame(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GAME)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addGameToPlayer(Long gameId, Long playerId) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_PLAYER_TO_GAME)) {
            preparedStatement.setLong(1, gameId);
            preparedStatement.setLong(2, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Game> getGamesByPlayer(Long playerId) {
        Set<Game> allGames = new HashSet<>();
        try(ResultSet resultSet = statement.executeQuery(GET_ALL_GAMES_OF_PLAYER)) {
            while (resultSet.next()) {
                allGames.add(generateGameByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGames;
    }

    @Override
    public Collection<GameDto> getPlayersCountByGame() {
        List<GameDto> gameDtoList = new ArrayList<>();
        try(ResultSet resultSet = jdbcService.getStatement().executeQuery(GET_PLAYERS_COUNT_FOR_EVERY_GAME)) {
            while(resultSet.next()) {
                gameDtoList.add(generateGameDto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameDtoList;
    }

    private GameDto generateGameDto(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("id");
            Timestamp created = resultSet.getTimestamp("created");
            String gameName = resultSet.getString("name");
            boolean commandGame = resultSet.getBoolean("command_type");
            int playersCount = resultSet.getInt("games_count");
            Game game = new Game();
            game.setId(id);
            game.setCreated(created);
            game.setName(gameName);
            game.setCommandGame(commandGame);
            return new GameDto(game, playersCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteGameFromPlayerInAllDb(Long gameId, Long playerId) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLAYER_FROM_GAME)) {
            preparedStatement.setLong(1, gameId);
            preparedStatement.setLong(2, playerId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
