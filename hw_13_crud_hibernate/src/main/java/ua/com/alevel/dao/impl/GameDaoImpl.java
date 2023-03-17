package ua.com.alevel.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.GameDao;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;

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

    @Override
    public Collection<Game> findGamesByPlayer(Long playerId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Player left join Player.games where Player.id = " + playerId);
            Collection<Game> gameList = query.getResultList();
            transaction.commit();
            return gameList;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<GameDto> findGameDto() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(
                    "select new ua.com.alevel.persistance.dto.GameDto(Game, Game.id) from Game " +
                    "left join Game.players group by Game.id");
            Collection<GameDto> dtoList = query.getResultList();
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
