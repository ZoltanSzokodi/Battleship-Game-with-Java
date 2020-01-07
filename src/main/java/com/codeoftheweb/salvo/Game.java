package com.codeoftheweb.salvo;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private Date creationDate;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    /* constructor  */

    public Game() { };

    Game(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return this.id;
    }
}
