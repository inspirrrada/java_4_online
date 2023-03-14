package ua.com.alevel.persistance.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.persistance.dao.PlayerDao;
import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class PlayerDaoImpl implements PlayerDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Player player) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(player);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Player player) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(player);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Player player) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(player);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Player> findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Player where Player.id = :id");
            Player player = (Player) query.getResultList().get(0);
            transaction.commit();
            return Optional.of(player);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Player> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Player ");
            List<Player> players = query.getResultList();
            transaction.commit();
            return players;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


//    private Player generatePlayerByResultSet(ResultSet resultSet) throws SQLException {
//        Long id = resultSet.getLong("id");
//        Date created = new Date(resultSet.getTimestamp("created").getTime());
//        int age = resultSet.getInt("age");
//        String email = resultSet.getString("email");
//        String nickname = resultSet.getString("nickname");
//        Player player = new Player();
//        player.setId(id);
//        player.setCreated(created);
//        player.setAge(age);
//        player.setEmail(email);
//        player.setNickname(nickname);
//        return player;
//    }
//
    @Override
    public Collection<Player> findPlayersByGame() {
        return null;
    }

    @Override
    public Collection<PlayerDto> findPlayerDto() {
        return null;
    }
//
//
//
//    public Collection<Player> getPlayersByGame(Long gameId) {
//        Set<Player> allPlayers = new HashSet<>();
//        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(GET_ALL_PLAYERS_OF_GAME + gameId)) {
//            while (resultSet.next()) {
//                allPlayers.add(generatePlayerByResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return allPlayers;
//    }
//
//
//    public Collection<PlayerDto> getGamesCountOfAllPlayers() {
//        List<PlayerDto> playerDtoList = new ArrayList<>();
//        try (ResultSet resultSet = jdbcService.getStatement().executeQuery(GET_GAMES_COUNT_FOR_EVERY_PLAYER)) {
//            while (resultSet.next()) {
//                playerDtoList.add(generatePlayerDto(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return playerDtoList;
//    }
//
//    private PlayerDto generatePlayerDto(ResultSet resultSet) {
//        try {
//            Long id = resultSet.getLong("id");
//            String nickname = resultSet.getString("nickname");
//            int gamesCount = resultSet.getInt("games_count");
//            Player player = new Player();
//            player.setId(id);
//            player.setNickname(nickname);
//            return new PlayerDto(player, gamesCount);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
