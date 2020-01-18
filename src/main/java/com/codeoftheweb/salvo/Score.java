package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long ID;

    private Double scoreValue;
    private long gameFinished;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playerID")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameID")
    private Game game;

    public Score() { }

    public Score(Player player, Game game, Double scoreValue) {
        this.player = player;
        this.game = game;
        this.scoreValue = scoreValue;
        this.gameFinished =  new Date().getTime();;
    }

    public Double getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Double scoreValue) {
        this.scoreValue = scoreValue;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(long gameFinished) {
        this.gameFinished = gameFinished;
    }

    @Override
    public String toString() {
        return "Score{" +
                "ID=" + ID +
                ", scoreValue=" + scoreValue +
                ", gameFinished=" + gameFinished +
                ", player=" + player +
                ", game=" + game +
                '}';
    }
}
