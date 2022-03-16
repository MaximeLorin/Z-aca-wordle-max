package com.zenika.academy.barbajavas.wordle.domain.controller;

import com.zenika.academy.barbajavas.wordle.application.GameManager;
import com.zenika.academy.barbajavas.wordle.domain.model.Game;

import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GameController{
    GameManager gameManager;

    public GameController(GameManager gameManager) {
        this.gameManager=gameManager;
    }

    @GetMapping("/games")
    public String test(){
        return "POEI Validée !!!";
    }

    @PostMapping("/games")
    public Game game(@RequestParam(value = "wordLength", defaultValue = "0") int wordLength,@RequestParam(value = "maxAttempt", defaultValue = "0") int maxAttempt){
        return gameManager.startNewGame(wordLength, maxAttempt);
    }

    @PostMapping("/games/{gameTid}")
    public Game tryGame(@PathVariable(value="gameTid")String gameTid, @RequestBody Map<String, String> body) throws BadLengthException, IllegalWordException {
        return gameManager.attempt(gameTid,body.get("guess"));
    }

    @GetMapping("/games/{gameTid}")
    public Game getGame(@PathVariable(value="gameTid")String gameTid){
        return gameManager.gameByTid(gameTid);
    }
}
