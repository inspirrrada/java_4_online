package ua.com.alevel;

import java.util.Arrays;

public class PlayerStorage {

    private static Player[] playersArray = new Player[10];
    private static int count = 0;

    private PlayerStorage() { }


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

    //operations read from CRUD
    public static Player getPlayer(String email) {
        Player player = null;

        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] != null) {
                if (playersArray[i].getEmail().equals(email)) {
                    player = playersArray[i];
                    break;
                }
            }
        }
        return player;
    }

    public static Player[] getPlayers() {
        return playersArray;
    }

    //operations update from CRUD
    public static void updatePlayerNickName(String email, String nickname) {
        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] != null) {
                if (playersArray[i].getEmail().equals(email)) {
                    playersArray[i].setNickname(nickname);
                }
            }
        }
    }

    public static void updatePlayerAge(String email, int age) {
        if (age < 18) {
            System.out.println("Age is too young for this game. Your changes of age weren't saved.");
        } else if (age > 100) {
            System.out.println("Age is too great for this game. Your changes of age weren't saved.");
        } else {
            for (int i = 0; i < playersArray.length; i++) {
                if (playersArray[i] != null) {
                    if (playersArray[i].getEmail().equals(email)) {
                        playersArray[i].setAge(age);
                    }
                }
            }
        }
    }

    public static void updatePlayerGuild(String email, String guild) {
        if (!guild.equals("")) {
            for (int i = 0; i < playersArray.length; i++) {
                if (playersArray[i] != null) {
                    if (playersArray[i].getEmail().equals(email)) {
                        playersArray[i].setGuild(guild);
                    }
                }
            }
        } else {
            System.out.println("Please enter some name, we don't accept empty string.");
        }

    }

    public static void updatePlayerLevel(String email, int level) {
        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] != null) {
                if (playersArray[i].getEmail().equals(email)) {
                    playersArray[i].setLevel(level);
                }
            }

        }
    }

    //operation delete from CRUD
    public static boolean deletePlayer(String email) {
        boolean wasDeleted = false;

        for (int i = 0; i < playersArray.length; i++) {
            if (playersArray[i] != null) {
                if (playersArray[i].getEmail().equals(email)) {
                    playersArray[i] = null;
                    wasDeleted = true;
                    break;
                }
            }
        }

        return wasDeleted;
    }


}
