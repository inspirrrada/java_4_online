package ua.com.alevel.persistance.dto;

import ua.com.alevel.persistance.entity.Player;

public record PlayerDto(Player player, long gamesCount) {

    @Override
    public String toString() {
        return
                "player=" + player.getNickname() +
                        ", gamesCount=" + gamesCount;
    }
}
