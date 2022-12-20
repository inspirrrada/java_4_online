package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Player extends BaseEntity {

    private int age;
    private String email;
    private String nickname;
    private Set<String> gameIdList = new HashSet<>();

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
            System.out.println("Invalid value!");
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
            System.out.println("Nickname can't contain only digits!");
            correctNickname = false;
        } else {
            this.nickname = nickname;
            correctNickname = true;
        }

        return correctNickname;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + getId() +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gameIdList=" + gameIdList +
                '}';
    }
}
