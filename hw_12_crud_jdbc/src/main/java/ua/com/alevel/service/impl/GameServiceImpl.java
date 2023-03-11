package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistance.dao.GameDao;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.service.GameService;
import ua.com.alevel.utils.ColorUtils;
import java.util.Collection;
import java.util.Optional;

@BeanClass
public class GameServiceImpl implements GameService {

    @InjectBean
    private GameDao gameDao;

    @Override
    public void addGame(Game game) {
        if (!hasTheSameGameName(game.getName()) && isCorrectGameName(game.getName())) {
            gameDao.addGame(game);
        }
    }

    @Override
    public Game getGameById(Long id) {
        Optional<Game> gameOptional = gameDao.getGameById(id);
        if (!gameOptional.isEmpty()) {
            return gameOptional.get();
        } else {
            System.out.println(ColorUtils.RED_TEXT.format("Invalid id!"));
            return null;
        }
    }

    @Override
    public Collection<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public void updateGameName(Long id, String name) {
        if (isCorrectGameName(name) && !hasTheSameGameName(name)) {
            gameDao.updateGameName(id, name);
        }
    }

    @Override
    public void updateGameType(Long id, boolean isCommandGame) {
        gameDao.updateGameType(id, isCommandGame);
    }

    @Override
    public boolean deleteGame(Long id) {
        return gameDao.deleteGame(id);
    }

    @Override
    public void addGameToPlayer(Long gameId, Long playerId) {
        gameDao.addGameToPlayer(gameId, playerId);
    }

    @Override
    public Collection<Game> getGamesByPlayer(Long playerId) {
        return gameDao.getGamesByPlayer(playerId);
    }

    @Override
    public Collection<GameDto> getPlayersCountOfAllGames() {
        return gameDao.getPlayersCountOfAllGames();
    }

    @Override
    public boolean deleteGameFromPlayer(Long gameId, Long playerId) {
        return gameDao.deleteGameFromPlayer(gameId, playerId);
    }

    public boolean hasTheSameGameName(String gameName) {
        boolean hasTheSameGameName = false;
        Collection<Game> allGames = getAllGames();
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
        boolean existGameId = getGameById(gameId) != null;
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

    @Override
    public boolean hasRecordsInGeneralTable(Long gameId, Long playerId) {
        return gameDao.hasRecordsInGeneralTable(gameId, playerId);
    }
}
