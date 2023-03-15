package ua.com.alevel.persistance.dto;

import ua.com.alevel.persistance.entity.Game;

public record GameDto(Game game, long playerCount) {

    @Override
    public String toString() {
        return
                "game=" + game.getName() +
                        ", playersCount=" + playerCount;
    }
}
