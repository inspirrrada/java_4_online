package ua.com.alevel.persistance.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column()
    private Integer age;

    @Column()
    private String email;

    @Column()
    private String nickname;

    @ManyToMany(mappedBy = "players", cascade = CascadeType.ALL)
    private Set<Game> games;

    public Player() {
        super();
        games = new HashSet<>();
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "\n{" +
                "\"id\":" + "\"" + getId() + "\"" +
                ", \"created\":" + "\"" + getCreated() + "\"" +
                ", \"age\":" + age +
                ", \"email\":" + "\"" + email + "\"" +
                ", \"nickname\":" + "\"" + nickname + "\"" +
                ", \"games qty\":" + "\"" + games.size() + "\"" +
                "}";
    }
}
