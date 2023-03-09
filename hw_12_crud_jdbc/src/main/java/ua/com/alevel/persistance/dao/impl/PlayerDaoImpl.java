package ua.com.alevel.persistance.dao.impl;

import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistance.config.JdbcService;
import ua.com.alevel.persistance.dao.PlayerDao;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class PlayerDaoImpl implements PlayerDao {

    @InjectBean
    private JdbcService jdbcService;
    private Statement statement;
    Connection connection = this.jdbcService.getConnection();

    private static final String CREATE_PLAYER = "insert into players values (default, ?, ?, ?, ?)";
    private static final String GET_ALL_PLAYERS = "select * from players";
    private static final String GET_PLAYER_BY_ID = "select * from players where id = ";
    private static final String UPDATE_PLAYER_AGE = "update players set age = ? where id = ?";
    private static final String UPDATE_PLAYER_EMAIL = "update players set email = ? where id = ?";
    private static final String UPDATE_PLAYER_NICKNAME = "update players set nickname = ? where id = ?";
    private static final String DELETE_PLAYER = "delete from players where id = ?";
    private static final String GET_GAMES_COUNT_FOR_EVERY_PLAYER = "select players.id, players.nickname, count(game_id) as 'games_count' from players " +
            "left join games_players as general_table on players.id = general_table.player_id group by players.id";
    private static final String GET_ALL_PLAYERS_OF_GAME = "select id, nickname, age from players " +
            " left join games_players as 'general_table' on players.id = general_table.player_id " +
            " where general_table.game_id = ";


    @Override
    public void addPlayer(Player player) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PLAYER)) {
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(player.getCreated().getTime()));
            preparedStatement.setInt(2, player.getAge());
            preparedStatement.setString(3, player.getEmail());
            preparedStatement.setString(4, player.getNickname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Player> getPlayerById(Long id) {
        try(ResultSet resultSet = statement.executeQuery(GET_PLAYER_BY_ID)) {
            while(resultSet.next()) {
                return Optional.of(generatePlayerByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Player> getAllPlayers() {
        List<Player> allPLayers = new ArrayList<>();
        try(ResultSet resultSet = statement.executeQuery(GET_ALL_PLAYERS)) {
            while (resultSet.next()) {
                allPLayers.add(generatePlayerByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPLayers;
    }

    private Player generatePlayerByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        java.util.Date created = new Date(resultSet.getTimestamp("created").getTime());
        int age = resultSet.getInt("age");
        String email = resultSet.getString("email");
        String nickname = resultSet.getString("nickname");
        Player player = new Player();
        player.setId(id);
        player.setCreated(created);
        player.setAge(age);
        player.setEmail(email);
        player.setNickname(nickname);
        return player;
    }

    @Override
    public void updatePlayerAge(Long id, int age) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAYER_AGE)) {
            preparedStatement.setInt(1, age);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlayerEmail(Long id, String email) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAYER_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlayerNickname(Long id, String nickname) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAYER_NICKNAME)) {
            preparedStatement.setString(1, nickname);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deletePlayer(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLAYER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<Player> getPlayersByGame(Long gameId) {
        Set<Player> allPlayers = new HashSet<>();
        try(ResultSet resultSet = statement.executeQuery(GET_ALL_PLAYERS_OF_GAME)) {
            while (resultSet.next()) {
                allPlayers.add(generatePlayerByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlayers;
    }
}
