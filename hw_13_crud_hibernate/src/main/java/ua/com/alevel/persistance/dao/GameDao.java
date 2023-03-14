package ua.com.alevel.persistance.dao;

import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.entity.Game;

import java.util.Collection;
import java.util.Optional;

public interface GameDao extends BaseDao<Game> {

//    void updateGameName(Long id, String name);
//    void updateGameType(Long id, boolean isCommandGame);
    void attachGameToPlayer(Long gameId, Long playerId);
    Collection<Game> findGamesByPlayer();
    Collection<GameDto> findGameDto();
    boolean deleteGameFromPlayer(Long gameId, Long playerId);
}
