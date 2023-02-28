package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Game extends BaseEntity {

    private String name;
    private boolean commandGame;
    private Set<String> playerIdList = new HashSet<>();

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

    public Set<String> getPlayerIdList() {
        return playerIdList;
    }

    public void setPlayerIdList(Set<String> playerIdList) {
        this.playerIdList = playerIdList;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\"id\":" + "\"" + getId() + "\"" +
                ",\"name\":" + "\"" + name + "\"" +
                ",\"commandGame\":" + "\"" + commandGame + "\"" +
                ",\"playerIdList\":" + "\"" + playerIdList + "\"" +
                "}";
    }
}
