package com.zenika.academy.barbajavas.wordle.domain.repository;

import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class GameRepository {
    private Map<String, Game> games = new HashMap<>();
    
    public void save(Game game) {
        this.games.put(game.getTid(), game);
    }
    
    public Optional<Game> findByTid(String tid) {
        return Optional.ofNullable(games.get(tid));
    }
}
