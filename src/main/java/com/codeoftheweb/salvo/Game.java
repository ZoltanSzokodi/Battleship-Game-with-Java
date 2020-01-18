package com.codeoftheweb.salvo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long ID;
    private long gameCreated =  new Date().getTime();
    private boolean finished = false;

    // Game has a one-to-many relationship with GamePlayer
    // ergo, Game has a many-to-many relationship with Player
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    Set<Score> scores = new HashSet<>();

    /* constructor  */
    public Game() { };

    /* getters and setters */

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }

    @JsonIgnore
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    @JsonIgnore
    public Set<Score> getScores() {
        return scores;
    }

    public long getGameCreated() {
        return this.gameCreated;
    }

    public void setGameCreated(long gameTime) {
        this.gameCreated = gameTime;
    }

    public long getID() {
        return this.ID;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    boolean isFull() {
        return this.gamePlayers.size() > 1;
    }

    @Override
    public String toString() {
        return ID + " " + gameCreated;
    }
}
