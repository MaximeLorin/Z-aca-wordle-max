package com.zenika.academy.barbajavas.wordle.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import com.zenika.academy.barbajavas.wordle.domain.repository.GameRepository;
import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.DictionaryService;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class GameManager {
    
    private final DictionaryService dictionaryService;
    protected final GameRepository gameRepository;

    @Autowired
    public GameManager(DictionaryService dictionaryService, GameRepository gameRepository) {
        this.dictionaryService = dictionaryService;
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(int wordLength, int nbAttempts) throws JsonProcessingException {
        Game game = new Game(UUID.randomUUID().toString(), dictionaryService.getRandomWord(wordLength), nbAttempts);
        gameRepository.save(game);
        return game;
    }
    
    public Game attempt(String gameTid, String word) throws IllegalWordException, BadLengthException, JsonProcessingException {
        Game game = gameRepository.findByTid(gameTid)
                .orElseThrow(() -> new IllegalArgumentException("This game does not exist"));
        
        if(!dictionaryService.wordExists(word)) {
            throw new IllegalWordException();
        }
        if(word.length() != game.getWordLength()) {
            throw new BadLengthException();
        }
        
        game.guess(word);
        
        gameRepository.save(game);
        
        return game;
    }
}
