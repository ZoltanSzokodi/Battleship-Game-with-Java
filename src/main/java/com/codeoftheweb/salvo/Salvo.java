package com.codeoftheweb.salvo;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Integer turn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name = "location")
    private List<String> location = new ArrayList<>();

    public Salvo() { }

    public Salvo(Integer turn) {
        this.turn = turn;
    }

    public void addLocations(List<String> location) { }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    @JsonIgnore
    public GamePlayer getGamePlayer() {
        return this.gamePlayer;
    }

    public long getId() {
        return id;
    }

    public Integer getTurn() {
        return turn;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Salvo{" +
                "id=" + id +
                ", turn=" + turn +
                ", salvoLocations=" + location +
                ", gamePlayer=" + gamePlayer +
                '}';
    }
}
