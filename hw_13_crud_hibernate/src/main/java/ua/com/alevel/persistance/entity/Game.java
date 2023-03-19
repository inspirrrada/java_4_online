package ua.com.alevel.persistance.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "command_type")
    private boolean commandGame;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "games_players",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )

    private Set<Player> players;

    public Game() {
        super();
        players = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCommandGame() {
        return commandGame;
    }

    public void setCommandGame(boolean commandGame) {
        this.commandGame = commandGame;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
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
                ", \"name\":" + "\"" + name + "\"" +
                ", \"commandGame\":" + "\"" + commandGame + "\"" +
                ", \"players qty\":" + "\"" + players.size() + "\"" +
                "}";
    }
}
