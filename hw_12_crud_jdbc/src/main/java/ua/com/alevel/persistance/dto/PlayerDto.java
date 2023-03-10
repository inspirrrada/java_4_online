package ua.com.alevel.persistance.dto;

import ua.com.alevel.persistance.entity.Player;

public record PlayerDto(Player player, int gamesCount) {
}
