package ua.com.alevel.service;

import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Player;

import java.util.Collection;


public interface PlayerService extends BaseService<Player> {

    Collection<Player> findPlayersByGame(Long gameId);
    Collection<PlayerDto> findPlayerDto();
}
