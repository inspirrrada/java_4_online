package ua.com.alevel.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.PlayerDao;
import ua.com.alevel.persistance.dto.PlayerDto;
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
    public boolean delete(Player player) {
        boolean successfullyDeleted;
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Player p where p.id = :id ")
                    .setParameter("id", player.getId());
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
    public Optional<Player> findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Player p where p.id = :id")
                    .setParameter("id", id);
            Player player;
            if (query.getResultList().size() > 0) {
                player = (Player) query.getResultList().get(0);
                transaction.commit();
                return Optional.of(player);
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
            Query query = session.createQuery("select g.players from Game g where g.id = :gameId")
                    .setParameter("gameId", gameId);
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
        Collection<Player> players = findAll();
        Collection<PlayerDto> dtoList = new ArrayList<>();
        if (players.isEmpty()) {
            return Collections.emptyList();
        } else {
            for (Player player : players) {
                PlayerDto dto = new PlayerDto(player, player.getGames().size());
                dtoList.add(dto);
            }
            return dtoList;
        }
    }
}
