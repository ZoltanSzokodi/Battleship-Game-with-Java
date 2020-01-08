package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    // private long creationDate = new Date().getTime();

    // -------------------------------------------------------
    private LocalDateTime gameTime =  LocalDateTime.now();
    // -------------------------------------------------------

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    /* constructor */

    public GamePlayer() { };

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    /* getters and setters */

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LocalDateTime getGameTime() {
        return this.gameTime;
    }

    public long getId() {
        return id;
    }
}
