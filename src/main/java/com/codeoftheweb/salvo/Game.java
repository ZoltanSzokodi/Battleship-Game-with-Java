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
    private long creationDate = new Date().getTime();

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    /* constructor  */
    Game() { };

    long getCreationDate() {
        return this.creationDate;
    }

    /*
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    */

    long getId() {
        return this.id;
    }
}
