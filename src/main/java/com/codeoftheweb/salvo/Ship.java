package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long ID;

    private String shipType;
    private boolean sunk = false;

    // one gamePlayer can have many ships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayerID")
    private GamePlayer gamePlayer;

    // we have a one-to-many relationship between ships and locations
    // Locations are just strings, "h2" and "b3". In Java JPA, you can create a one-to-many relationship from an entity, such as Ship, to a basic data type, such as a location string, using the @ElementCollection annotation
    @ElementCollection
    @Column(name = "location")
    private List<String> location = new ArrayList<>();

    public Ship() { }

    public Ship(String shipType) {
        this.shipType = shipType;
    }

    // A ship has a gamePlayer, return that player
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    @JsonIgnore
    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "ID=" + ID +
                ", shipType='" + shipType + '\'' +
                ", gamePlayer=" + this.gamePlayer +
                ", location=" + location +
                '}';
    }
}
