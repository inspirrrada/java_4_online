package ua.com.alevel.db;

import ua.com.alevel.entity.Player;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayersContainer {
    private List<Player> players;

    private String generatePlayerId() {
        String id = UUID.randomUUID().toString();
//1
        return id;
    }

//    public void addPlayer(Player player) {
//        player.setId(generatePlayerId());
//        try {
//            players = readFile();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        players.add(player);
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter("players.json");
//            fileWriter.write(players.toString());
//            fileWriter.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }



    public void writeToFile(String object) {
        try {
            FileWriter fileWriter = new FileWriter("players.json", true);
            fileWriter.write(object);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    @Override
    public String toString() {
        return "{\"players\":" + players +
                '}';
    }
}
