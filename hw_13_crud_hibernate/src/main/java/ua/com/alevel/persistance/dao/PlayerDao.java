package ua.com.alevel.persistance.dao;

import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Player;

import java.util.Collection;
import java.util.Optional;

public interface PlayerDao extends BaseDao<Player> {

//    void updatePlayerAge(Long id, int age);
//    void updatePlayerEmail(Long id, String email);
//    void updatePlayerNickname(Long id, String nickname);
    Collection<Player> findPlayersByGame();
    Collection<PlayerDto> findPlayerDto();
}
