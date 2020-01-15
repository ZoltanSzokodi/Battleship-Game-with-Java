package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Double scoreValue;
    private long gameFinished;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                "id=" + id +
                ", scoreValue=" + scoreValue +
                ", finishDate=" + gameFinished +
                ", player=" + player +
                ", game=" + game +
                '}';
    }
}
