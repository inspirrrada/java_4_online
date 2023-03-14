package ua.com.alevel.persistance.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.persistance.dao.GameDao;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class GameDaoImpl implements GameDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Game game) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(game);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Game game) {
       Transaction transaction = null;
       try (Session session = sessionFactory.getCurrentSession()) {
           transaction = session.beginTransaction();
           session.update(game);
           transaction.commit();
       } catch (Exception e) {
           e.printStackTrace();
           if (transaction != null) {
               transaction.rollback();
           }
       }
    }

    @Override
    public void delete(Game game) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(game);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public Optional<Game> findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Game where Game.id = :id");
            Game game = (Game) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(game);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Game> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Game");
            List<Game> games = query.getResultList();
            transaction.commit();
            return games;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }


//    private Game generateGameByResultSet(ResultSet resultSet) throws SQLException {
//        Long id = resultSet.getLong("id");
//        Date created = new Date(resultSet.getTimestamp("created").getTime());
//        String gameName = resultSet.getString("name");
//        boolean commandType = resultSet.getBoolean("command_type");
//        Game game = new Game();
//        game.setId(id);
//        game.setCreated(created);
//        game.setName(gameName);
//        game.setCommandGame(commandType);
//        return game;
//    }
//
//
//
    @Override
    public void attachGameToPlayer(Long gameId, Long playerId) {

    }

    @Override
    public Collection<Game> findGamesByPlayer() {
        return null;
    }

    @Override
    public Collection<GameDto> findGameDto() {
        return null;
    }
//
//
//
//    public void addGameToPlayer(Long gameId, Long playerId) {
//        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(ADD_PLAYER_TO_GAME)) {
//            preparedStatement.setLong(1, gameId);
//            preparedStatement.setLong(2, playerId);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public Collection<Game> getGamesByPlayer(Long playerId) {
//        Set<Game> allGames = new HashSet<>();
//        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(GET_ALL_GAMES_OF_PLAYER + playerId)) {
//            while (resultSet.next()) {
//                allGames.add(generateGameByResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return allGames;
//    }
//
//
//    public Collection<GameDto> getPlayersCountOfAllGames() {
//        List<GameDto> gameDtoList = new ArrayList<>();
//        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(GET_PLAYERS_COUNT_FOR_EVERY_GAME)) {
//            while (resultSet.next()) {
//                gameDtoList.add(generateGameDto(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return gameDtoList;
//    }
//
//    private GameDto generateGameDto(ResultSet resultSet) {
//        try {
//            Long id = resultSet.getLong("id");
//            String gameName = resultSet.getString("name");
//            int playersCount = resultSet.getInt("players_count");
//            Game game = new Game();
//            game.setId(id);
//            game.setName(gameName);
//            return new GameDto(game, playersCount);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
    @Override
    public boolean deleteGameFromPlayer(Long gameId, Long playerId) {
//        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(DELETE_PLAYER_FROM_GAME)) {
//            preparedStatement.setLong(1, gameId);
//            preparedStatement.setLong(2, playerId);
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return false;
    }
//
//
//    public boolean hasRecordsInGeneralTable(Long gameId, Long playerId) {
//        boolean hasRecords = false;
//        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(GET_INFO_FROM_GAMES_PLAYERS_TABLE.replaceFirst("'game_id'", String.valueOf(gameId)).replaceFirst("'player_id'", String.valueOf(playerId)))) {
//            if (resultSet.next()) {
//                hasRecords = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return hasRecords;
//    }

}
