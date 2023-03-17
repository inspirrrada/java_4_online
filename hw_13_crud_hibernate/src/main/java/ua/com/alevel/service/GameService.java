package ua.com.alevel.service;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.util.Collection;


public interface GameService extends BaseService<Game> {

    Collection<Game> findGamesByPlayer(Long playerId);
    Collection<GameDto> findGameDto();
    void attachGameToPlayer(Long gameId, Long playerId);
    void deleteGameFromPlayer(Long gameId, Long playerId);
}
