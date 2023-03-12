package ua.com.alevel.persistance.entity;


import java.util.Set;

public class Player extends BaseEntity {

    private int age;
    private String email;
    private String nickname;
    private Set<Game> games;

    public Player() {
        super();
    }

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

    @Override
    public String toString() {
        return "\n{" +
                "\"id\":" + "\"" + getId() + "\"" +
                ",a\"created\":" + "\"" + getCreated() + "\"" +
                ",\"age\":" + age +
                ",\"email\":" + "\"" + email + "\"" +
                ",\"nickname\":" + "\"" + nickname + "\"" +
                "}";
    }
}
