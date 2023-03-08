package ua.com.alevel.persistance.dto;

import ua.com.alevel.persistance.entity.Game;

public record GameDto(Game game, int playerCount) {
}
