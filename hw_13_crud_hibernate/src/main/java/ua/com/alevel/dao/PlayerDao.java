package ua.com.alevel.dao;

import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Player;
import java.util.Collection;

public interface PlayerDao extends BaseDao<Player> {

    Collection<Player> findPlayersByGame(Long gameId);
    Collection<PlayerDto> findPlayerDto();
}
