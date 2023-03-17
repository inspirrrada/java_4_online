package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.dao.GameDao;
import ua.com.alevel.dao.PlayerDao;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.service.GameService;
import ua.com.alevel.utils.ColorUtils;

import java.util.Collection;
import java.util.Optional;

public class GameServiceImpl implements GameService {

    private GameDao gameDao;
    private PlayerDao playerDao;

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
    public void delete(Game game) {
        gameDao.delete(game);
    }

    @Override
    public Game findById(Long id) {
        return gameDao.findById(id).get();
    }

    @Override
    public Collection<Game> findAll() {
        return gameDao.findAll();
    }

    @Override
    public Collection<Game> findGamesByPlayer(Long playerId) {
        return null;
    }

    @Override
    public Collection<GameDto> findGameDto() {
        return null;
    }

    @Override
    public void attachGameToPlayer(Long gameId, Long playerId) {

    }

    @Override
    public boolean deleteGameFromPlayer(Long gameId, Long playerId) {
        return false;
//        return gameDao.deleteGameFromPlayer(gameId, playerId);
    }



//    public void addGameToPlayer(Long gameId, Long playerId) {
//        gameDao.addGameToPlayer(gameId, playerId);
//    }
//
//    public Collection<Game> getGamesByPlayer(Long playerId) {
//        return gameDao.getGamesByPlayer(playerId);
//    }
//
//    public Collection<GameDto> getPlayersCountOfAllGames() {
//        return gameDao.getPlayersCountOfAllGames();
//    }



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

    public boolean existGameId(Long gameId) {
        boolean existGameId = findById(gameId) != null;
        if (!existGameId) {
            System.out.println(ColorUtils.RED_TEXT.format("We can't find game with such id!"));
        }
        return existGameId;
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

//    public boolean hasRecordsInGeneralTable(Long gameId, Long playerId) {
//        return gameDao.hasRecordsInGeneralTable(gameId, playerId);
//    }


}
