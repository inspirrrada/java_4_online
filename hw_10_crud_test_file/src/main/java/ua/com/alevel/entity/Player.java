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
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<String> getGameIdList() {
        return gameIdList;
    }

    public void setGameIdList(Set<String> gameIdList) {
        this.gameIdList = gameIdList;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\"id\":" + "\"" + getId() + "\"" +
                ", \"age\":" + age +
                ", \"email\":" + "\"" + email + "\"" +
                ", \"nickname\":" + "\"" + nickname + "\"" +
                ", \"gameIdList\":" + gameIdList +
                "}";
    }
}
