package ua.com.alevel.persistance.entity;

import java.util.HashSet;
import java.util.Set;

public class Game extends BaseEntity {

    private String name;
    private boolean commandGame;
    private Set<Player> players;

    public Game() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCommandGame() {
        return commandGame;
    }

    public void setCommandGame(boolean commandGame) {
        this.commandGame = commandGame;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\"id\":" + "\"" + getId() + "\"" +
                "\"created\":" + "\"" + getCreated() + "\"" +
                ",\"name\":" + "\"" + name + "\"" +
                ",\"commandGame\":" + "\"" + commandGame + "\"" +
                ",\"playerIdList\":" + "\"" + players + "\"" +
                "}";
    }
}
