package com.codeoftheweb.salvo;

import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private Date gameStartDate;

    public Game() { };

    Game(Date startDate) {
        gameStartDate = startDate;
    }

    public Date getGameStartDate() {
        return this.gameStartDate;
    }

    public void setGameStartDate(Date startDate) {
        this.gameStartDate = startDate;
    }
}
