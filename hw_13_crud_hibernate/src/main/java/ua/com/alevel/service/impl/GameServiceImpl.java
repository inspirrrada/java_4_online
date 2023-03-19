package ua.com.alevel.service.impl;

import ua.com.alevel.dao.GameDao;
import ua.com.alevel.dao.PlayerDao;
import ua.com.alevel.dao.impl.GameDaoImpl;
import ua.com.alevel.dao.impl.PlayerDaoImpl;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;
import ua.com.alevel.service.GameService;
import ua.com.alevel.utils.ColorUtils;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class GameServiceImpl implements GameService {

    private GameDao gameDao = new GameDaoImpl();
    private PlayerDao playerDao = new PlayerDaoImpl();

    @Override
    public void create(Game game) {
        if (!hasTheSameGameName(game.getName()) && isCorrectGameName(game.getName())) {
            gameDao.create(game);
        }
    }

    @Override
    public void update(Game game) {
        if (isCorrectGameName(game.getName()) && !hasTheSameGameName(game.getName())) {
            gameDao.update(game);
        }
    }

    @Override
    public boolean delete(Game game) {
        return gameDao.delete(game);
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> gameOptional = gameDao.findById(id);
        if (gameOptional.isPresent()) {
            return gameDao.findById(id).get();
        }
        return null;
    }

    @Override
    public Collection<Game> findAll() {
        return gameDao.findAll();
    }

    @Override
    public Collection<Game> findGamesByPlayer(Long playerId) {
        return gameDao.findGamesByPlayer(playerId);
    }

    @Override
    public Collection<GameDto> findGameDto() {
        return gameDao.findGameDto();
    }

    @Override
    public void attachGameToPlayer(Long gameId, Long playerId) {
        Game game = gameDao.findById(gameId).get();
        Player player = playerDao.findById(playerId).get();
        Set<Player> players = game.getPlayers();
        players.add(player);
        gameDao.update(game);
    }

    @Override
    public boolean alreadyAttached(Long gameId, Long playerId) {
        Game game = gameDao.findById(gameId).get();
        Player player = playerDao.findById(playerId).get();
        Set<Player> players = game.getPlayers();
        for (Player player1 : players) {
            if (player1.getId().equals(player.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteGameFromPlayer(Long gameId, Long playerId) {
        Game game = gameDao.findById(gameId).get();
        Player player = playerDao.findById(playerId).get();
        Set<Player> playerSet = game.getPlayers();
        for (Player player1 : playerSet) {
            if (player1.getId().equals(player.getId())) {
                playerSet.remove(player1);
                gameDao.update(game);
            }
        }
    }

    public boolean hasTheSameGameName(String gameName) {
        boolean hasTheSameGameName = false;
        Collection<Game> allGames = findAll();
        if (allGames != null) {
            for (Game game : allGames) {
                if (game.getName().equalsIgnoreCase(gameName)) {
                    hasTheSameGameName = true;
                    break;
                }
            }
        }
        return hasTheSameGameName;
    }

    public boolean isCorrectGameName(String gameName) {
        boolean correctGameName = true;
        //name of game can't have only digits
        if (gameName.matches("\\d+")) {
            System.out.println(ColorUtils.RED_TEXT.format("Name of game can't contain only digits!"));
            correctGameName = false;
        }
        return correctGameName;
    }

    public boolean existGameId(Long gameId) {
        boolean existGameId = findById(gameId) != null;
        if (!existGameId) {
            System.out.println(ColorUtils.RED_TEXT.format("We can't find game with such id!"));
        }
        return existGameId;
    }
}
