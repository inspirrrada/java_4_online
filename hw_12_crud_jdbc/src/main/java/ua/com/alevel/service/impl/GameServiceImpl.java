package ua.com.alevel.service.impl;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.service.GameService;

import java.util.Collection;
import java.util.List;

public class GameServiceImpl implements GameService {

    @Override
    public void addGame(Game game) {

    }

    @Override
    public Game getGameByIdOrNull(String id) {
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public void updateGameName(String id, String name) {

    }

    @Override
    public void updateGameType(String id, boolean isCommandGame) {

    }

    @Override
    public boolean deleteGame(String id) {
        return false;
    }

    @Override
    public void addGameToPlayerInAllDb(String gameId, String playerId) {

    }

    @Override
    public Collection<Game> getGamesByPlayer(String playerId) {
        return null;
    }

    @Override
    public Collection<GameDto> getPlayersCountByGame() {
        return null;
    }

    @Override
    public boolean deleteGameFromPlayerInAllDb(String gameId, String playerId) {
        return false;
    }
}
