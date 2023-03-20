package ua.com.alevel.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.GameDao;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
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
    public boolean delete(Game game) {
        boolean successfullyDeleted;
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            //при звичайному session.delete() видаляються по кругу всі дані з БД, тому тут використано query
            Query query = session.createQuery("delete from Game g where g.id = :id ")
                    .setParameter("id", game.getId());
            query.executeUpdate();
            transaction.commit();
            successfullyDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            successfullyDeleted = false;
        }
        return successfullyDeleted;
    }

    @Override
    public Optional<Game> findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Game g where g.id = :id")
                    .setParameter("id", id);
            Game game;
            if (query.getResultList().size() > 0) {
                game = (Game) query.getResultList().get(0);
                transaction.commit();
                return Optional.of(game);
            } else {
                transaction.commit();
                return Optional.empty();
            }
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
            Query query = session.createQuery("select p.games from Player p where p.id = :playerId")
                    .setParameter("playerId", playerId);
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
        Collection<Game> games = findAll();
        Collection<GameDto> dtoList = new ArrayList<>();
        if (games.isEmpty()) {
            return Collections.emptyList();
        } else {
            for (Game game : games) {
                GameDto dto = new GameDto(game, game.getPlayers().size());
                dtoList.add(dto);
            }
            return dtoList;
        }
    }
}
