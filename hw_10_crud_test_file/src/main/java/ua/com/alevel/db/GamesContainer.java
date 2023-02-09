package ua.com.alevel.db;

import ua.com.alevel.entity.Game;

import java.util.List;

public class GamesContainer {

    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "{\"games\":" + games +
                '}';
    }
}
