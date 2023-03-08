package ua.com.alevel.service.impl;

import ua.com.alevel.persistance.entity.Player;
import ua.com.alevel.service.PlayerService;

import java.util.Collection;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {
    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public Player getPlayerById(String id) {
        return null;
    }

    @Override
    public List<Player> getAllPlayers() {
        return null;
    }

    @Override
    public void updatePlayerAge(String id, int age) {

    }

    @Override
    public void updatePlayerEmail(String id, String email) {

    }

    @Override
    public void updatePlayerNickname(String id, String nickname) {

    }

    @Override
    public boolean deletePlayer(String id) {
        return false;
    }

    @Override
    public Collection<Player> getPlayersByGame(String gameId) {
        return null;
    }
}
