package ua.com.alevel.entity;

import ua.com.alevel.db.DbStorage;

import java.util.HashSet;
import java.util.Set;

public class Game extends BaseEntity {

    private String name;
    private boolean commandGame;
    private Set<String> playerIdList = new HashSet<>();

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        boolean correctGameName;

        //name of game can't have only digits
        if (name.matches("\\d+")) {
            System.out.println(DbStorage.getRedText().format("Name of game can't contain only digits!"));
            correctGameName = false;
        } else {
            this.name = name;
            correctGameName = true;
        }

        return correctGameName;
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


    @Override
    public String toString() {
        return "Game{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", commandGame=" + commandGame +
                ", playerIdList=" + playerIdList +
                '}';
    }
}
