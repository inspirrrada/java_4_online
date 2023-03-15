package ua.com.alevel.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.PlayerDao;
import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

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

    @Override
    public Collection<Player> findPlayersByGame(Long gameId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Game left join Game.players where Game.id = " + gameId);
            Collection<Player> playerList = query.getResultList();
            transaction.commit();
            return playerList;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<PlayerDto> findPlayerDto() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(
                    "select new ua.com.alevel.persistance.dto.PlayerDto(Player, count (Player .id)) from Player " +
                            "left join Player.games group by Player.id");
            Collection<PlayerDto> dtoList = query.getResultList();
            transaction.commit();
            return dtoList;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }



}
