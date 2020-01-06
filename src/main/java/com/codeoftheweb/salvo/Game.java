package com.codeoftheweb.salvo;

import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private Date creationDate;

    /* constructor  */

    public Game() { };

    Game(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }
}
