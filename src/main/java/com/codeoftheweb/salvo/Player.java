package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 Annotations are a standard part of Java. Annotations are not code, but instructions used by the compiler
 and other tools to help generate code.
 In JPA, an Entity class is equivalent to a row of a database.
*/

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private String firstName;
    private String lastName;
    private String userName;

    // You must define a default (no-argument) constructor for any entity class. That's what JPA will call to create new instances
    public Player() {};

    Player(String first, String last, String user) {
        firstName = first;
        lastName = last;
        userName = user;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String first) {
        this.firstName = first;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String last) {
        this.lastName = last;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String user) {
        this.userName = user;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
