package com.codeoftheweb.salvo;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 Annotations are a standard part of Java. Annotations are not code, but instructions used by the compiler
 and other tools to help generate code.
 In JPA, an Entity class is equivalent to a row of a database.
*/

@Entity // ~ denotes the whole class for storage in a relational table.
public class Player {

    @Id // ~ to note the primary key and that is generated automatically when needed.
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long ID;
    private String userName;
    private String password;
    private String email;

    // Player has a one-to-many relationship with GamePlayer
    // ergo, Player has a many-to-many relationship with Game
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<Score> scores = new HashSet<>();

    // You must define a default (no-argument) constructor for any entity class. That's what JPA will call to create new instances
    public Player() { };

    public Player(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    /* getters and setters */

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public Set<Score> getScores() {
        return this.scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String toString() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
