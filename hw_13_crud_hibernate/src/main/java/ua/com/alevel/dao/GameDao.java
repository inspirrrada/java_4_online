package ua.com.alevel.dao;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.util.Collection;

public interface GameDao extends BaseDao<Game> {

    void attachGameToPlayer(Long gameId, Long playerId);
    Collection<Game> findGamesByPlayer(Long playerId);
    Collection<GameDto> findGameDto();
    boolean deleteGameFromPlayer(Long gameId, Long playerId);
}
