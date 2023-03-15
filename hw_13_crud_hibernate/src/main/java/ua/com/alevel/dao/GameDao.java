package ua.com.alevel.dao;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.util.Collection;

public interface GameDao extends BaseDao<Game> {

    Collection<Game> findGamesByPlayer(Long playerId);
    Collection<GameDto> findGameDto();
}
