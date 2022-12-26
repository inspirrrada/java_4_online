package ua.com.alevel.entity;

import ua.com.alevel.db.DbStorage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game extends BaseEntity {

    private String name;
    private boolean commandGame;
    private String[] playerIdList = new String[10];
    private int playerIdCount = 0;

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

    public String[] getPlayerIdList() {
        return playerIdList;
    }

    public void setPlayerIdList(String[] playerIdList) {
        this.playerIdList = playerIdList;
    }

    public int getPlayerIdCount() {
        return playerIdCount;
    }

    public void setPlayerIdCount(int playerIdCount) {
        this.playerIdCount = playerIdCount;
    }

    @Override
    public String toString() {
        String playerIdListValues = "";
        int count = 0;

        for (int i = 0; i < playerIdList.length; i++) {
            if (playerIdList[i] != null) {
                if (playerIdListValues.equals("")) {
                    playerIdListValues = playerIdListValues + "[" + playerIdList[i];
                } else {
                    playerIdListValues = playerIdListValues + ", " + playerIdList[i];
                }

                count++;
            }
        }

        if (count == 0) {
            playerIdListValues = "[]";
        } else {
            playerIdListValues = playerIdListValues + "]";
        }

        return "Game{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", commandGame=" + commandGame +
                ", playerIdList=" + playerIdListValues +
                '}';
    }
}
