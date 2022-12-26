package ua.com.alevel.entity;

import ua.com.alevel.db.DbStorage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Player extends BaseEntity {

    private int age;
    private String email;
    private String nickname;
    private String[] gameIdList = new String[10];
    private int gameIdCount = 0;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
//        if (age < 11) {
//            System.out.println("Grow up first and come later");
//        } else {
//            this.age = age;
//        }
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        boolean correctEmail;

        if (email.matches("^(.+)@(.+)$")) {
            this.email = email;
            correctEmail = true;
        } else {
            System.out.println(DbStorage.getRedText().format("Invalid value!"));
            correctEmail = false;
        }

        return correctEmail;

    }

    public String getNickname() {
        return nickname;
    }

    public boolean setNickname(String nickname) {
        boolean correctNickname;
        //nickname can't have only digits
       if (nickname.matches("\\d+")) {
            System.out.println(DbStorage.getRedText().format("Nickname can't contain only digits!"));
            correctNickname = false;
        } else {
            this.nickname = nickname;
            correctNickname = true;
        }

        return correctNickname;
    }

    public String[] getGameIdList() {
        return gameIdList;
    }

    public void setGameIdList(String[] gameIdList) {
        this.gameIdList = gameIdList;
    }

    public int getGameIdCount() {
        return gameIdCount;
    }

    public void setGameIdCount(int gameIdCount) {
        this.gameIdCount = gameIdCount;
    }

    @Override
    public String toString() {
        String gameIdListValues = "";
        int count = 0;

        for (int i = 0; i < gameIdList.length; i++) {
            if (gameIdList[i] != null) {
                if (gameIdListValues.equals("")) {
                    gameIdListValues = gameIdListValues + "[" + gameIdList[i];
                } else {
                    gameIdListValues = gameIdListValues + ", " + gameIdList[i];
                }

                count++;
            }
        }

        if (count == 0) {
            gameIdListValues = "[]";
        } else {
            gameIdListValues = gameIdListValues + "]";
        }

        return "Player{" +
                "id=" + getId() +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gameIdList=" + gameIdListValues +
                '}';
    }
}
