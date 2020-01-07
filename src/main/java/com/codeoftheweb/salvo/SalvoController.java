package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    /* ~ makes sure that all the URLs this controller looks for will have to start with /api. This will prevent any
    accidental overlap with the REST repository URLs, because you've made them start with /rest. */
    @RequestMapping("/games")
    public List<Object> getAll() {
        List<Object> games = new ArrayList<>();

        gameRepository.findAll().forEach(game -> {
            Map<String, Object> gameMap = new HashMap<>();
            gameMap.put("game_id", game.getId());
            games.add(gameMap);
        });
        return games;
    }
}
