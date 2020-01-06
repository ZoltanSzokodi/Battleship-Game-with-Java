package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;

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
    private String userName;

    // You must define a default (no-argument) constructor for any entity class. That's what JPA will call to create new instances
    public Player() {};

    Player(String userName) {
        this.userName = userName;
    }

    /* getters and setters */

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
