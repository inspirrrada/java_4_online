package ua.com.alevel.persistance.dao.impl;

import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistance.config.JdbcService;
import ua.com.alevel.persistance.dao.PlayerDao;
import ua.com.alevel.persistance.entity.Player;

import java.util.Collection;
import java.util.Optional;

public class PlayerDaoImpl implements PlayerDao {

    @InjectBean
    private JdbcService jdbcService;

    private static final String CREATE_PLAYER = "insert into players values (default, ?, ?, ?, ?)";
    private static final String GET_ALL_PLAYERS = "select * from players";
    private static final String GET_PLAYER_BY_ID = "select * from players where id = ";
    private static final String UPDATE_PLAYER_AGE = "update players set age = ? where id = ?";
    private static final String UPDATE_PLAYER_EMAIL = "update players set email = ? where id = ?";
    private static final String UPDATE_PLAYER_NICKNAME = "update players set nickname = ? where id = ?";
    private static final String DELETE_PLAYER = "delete from players where id = ?";
    private static final String GET_GAMES_COUNT_FOR_EVERY_PLAYER = "select players.id, players.nickname, count(game_id) as 'games_count' from players " +
            "left join games_players as general_table on players.id = general_table.player_id group by players.id";
    private static final String GET_ALL_GAMES_OF_PLAYER = "select id, name, command_type from games " +
            " left join games_players as 'general_table' on games.id = general_table.game_id " +
            " where general_table.player_id = ";

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public Optional<Player> getPlayerById(String id) {
        return Optional.empty();
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return null;
    }

    @Override
    public void updatePlayerAge(String id, int age) {

    }

    @Override
    public void updatePlayerEmail(String id, String email) {

    }

    @Override
    public void updatePlayerNickname(String id, String nickname) {

    }

    @Override
    public boolean deletePlayer(String id) {
        return false;
    }

    @Override
    public Collection<Player> getPlayersByGame(String gameId) {
        return null;
    }
}
