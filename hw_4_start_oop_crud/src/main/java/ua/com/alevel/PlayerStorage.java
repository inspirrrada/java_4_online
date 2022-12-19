package ua.com.alevel;

import java.util.Arrays;

public class PlayerStorage {

    private static Player[] playersArray = new Player[10];
    private static int count = 0;

    private PlayerStorage() { }

    public static Player[] getPlayers() {
        return playersArray;
    }

    //operation create from CRUD
    public static void addPlayer(Player player) {
        if (count == playersArray.length) {
            int newLength = playersArray.length * 2;
            Player[] playersArrayNew = Arrays.copyOf(playersArray, newLength);
            playersArray = playersArrayNew;
        }

        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] == null) {
                playersArray[i] = player;
                count++;
                break;
            }
        }
    }

    //operation read from CRUD
    public static Player getPlayer(String email) {
        Player player = null;

        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i].getEmail().equals(email)) {
                player = playersArray[i];
                break;
            }
        }

        return player;
    }

    //operations update from CRUD
//    public static void updatePlayer(String email, String nickname, int age, String guild, int level) {
//        for (int i = 0; i < playersArray.length; i++) {
//            if (playersArray[i].getEmail().equals(email)) {
//                playersArray[i].setNickname(nickname);
//                playersArray[i].setAge(age);
//                playersArray[i].setGuild(guild);
//                playersArray[i].setLevel(level);
//            }
//        }
//    }

    public static void updatePlayerNickName(String email, String nickname) {
        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i].getEmail().equals(email)) {
                playersArray[i].setNickname(nickname);
            }
        }
    }

    public static void updatePlayerAge(String email, int age) {
        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i].getEmail().equals(email)) {
                playersArray[i].setAge(age);
            }
        }
    }

    public static void updatePlayerGuild(String email, String guild, int level) {
        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i].getEmail().equals(email)) {
                playersArray[i].setGuild(guild);
                playersArray[i].setLevel(level);
            }
        }
    }

    public static void updatePlayerLevel(String email, int level) {
        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i].getEmail().equals(email)) {
                playersArray[i].setLevel(level);
            }
        }
    }

    //operation delete from CRUD
    public static boolean deletePlayer(String email) {
        boolean wasDeleted = false;

        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i].getEmail().equals(email)) {
                playersArray[i] = null;
                wasDeleted = true;
                break;
            }
        }

        return wasDeleted;
    }


}
