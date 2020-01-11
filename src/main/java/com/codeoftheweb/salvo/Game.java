package com.codeoftheweb.salvo;

import java.util.Date;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private long gameCreated =  new Date().getTime();

    // Game has a one-to-many relationship with GamePlayer
    // ergo, Game has a many-to-many relationship with Player
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    /* constructor  */
    public Game() { };

    /* getters and setters */

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }

    public long getGameCreated() {
        return this.gameCreated;
    }

    public void setGameCreated(long gameTime) {
        this.gameCreated = gameTime;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public long getId() {
        return this.id;
    }
}
