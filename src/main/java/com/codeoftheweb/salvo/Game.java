package com.codeoftheweb.salvo;

import java.time.LocalDateTime;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    // private long creationDate = new Date().getTime();

    // -------------------------------------------------------
    private LocalDateTime gameTime =  LocalDateTime.now();
    // -------------------------------------------------------

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    /* constructor  */
    public Game() { };

    /* getters and setters */

    // ------------------------------------------------------
    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }
    // ------------------------------------------------------

    public LocalDateTime getGameTime() {
        return this.gameTime;
    }

    public void setGameTime(LocalDateTime gameTime) {
        this.gameTime = gameTime;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public long getId() {
        return this.id;
    }

    /*
    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
    */
}
