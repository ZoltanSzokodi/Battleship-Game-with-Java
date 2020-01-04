package com.codeoftheweb.salvo;

public class Player {

    private String firstName;
    private String lastName;

    // You must define a default (no-argument) constructor for any entity class. That's what JPA will call to create new instances
    public Player () {};

    public Player (String first, String last) {
        firstName = first;
        lastName = last;
    }

    public String getFirstName () {
        return this.firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return this.lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String toString () {
        return this.firstName + " " + this.lastName;
    }
}
