package com.codeoftheweb.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

/*
 A Repository class is analogous to a table, i.e., it is a class that manages a collection of instances.
 The interface is a JpaRepository, which is in turn an extension of CrudRepository.
 PlayerRepository is defined to be a JpaRepository that manages instances of Player.
 Spring will create an actual class with code that implements this interface.
 The PlayerRepository class is responsible for persisting instances of Person to some database, and retrieving
 instances.
 With this separation, if another class just needs to work with Person instances, it can do so, without any database. If a web application needs to maintain a database, it does so by interacting with a PersonRepository.
*/

@RepositoryRestResource
// ~ turns PersonRepository into a Rest Repository.
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByUserName(@Param("userName") String userName);
    Player findByEmail(@Param("email") String email);
    Player findByID(Integer ID);
}
