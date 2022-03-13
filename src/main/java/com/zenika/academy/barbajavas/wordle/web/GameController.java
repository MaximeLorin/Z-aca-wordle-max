package com.zenika.academy.barbajavas.wordle.web;

import com.zenika.academy.barbajavas.wordle.application.GameManager;
import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.GameFinishedException;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {
    
    private final GameManager gameManager;

    @Autowired
    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @PostMapping
    ResponseEntity<Game> createGame(@RequestParam Integer wordLength, @RequestParam Integer maxAttempts) {
        if(wordLength < 3 || maxAttempts < 2) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(gameManager.startNewGame(wordLength, maxAttempts));
    }
    
    @PostMapping("/{gameTid}")
    ResponseEntity<Game> guess(@PathVariable String gameTid, @RequestBody GuessRequestDto guessRequestDto) {
        try {
            return ResponseEntity.ok(gameManager.attempt(gameTid, guessRequestDto.guess()));
        } catch (IllegalWordException | BadLengthException | GameFinishedException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{gameTid}")
    ResponseEntity<Game> getGame(@PathVariable String gameTid) {
        return gameManager.getGame(gameTid)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    } 
}
