package com.zenika.academy.barbajavas.wordle.application;

import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import com.zenika.academy.barbajavas.wordle.domain.repository.GameRepository;
import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.DictionaryService;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import com.zenika.academy.barbajavas.wordle.domain.service.online.GetScrabbleDictionary;

import java.util.UUID;

public class GameManager {
    
    private final DictionaryService dictionaryService;
    private final GameRepository gameRepository;
    private final GetScrabbleDictionary getScrabbleDictionary;

    public GameManager(DictionaryService dictionaryService, GameRepository gameRepository,GetScrabbleDictionary getScrabbleDictionary) {
        this.dictionaryService = dictionaryService;
        this.gameRepository = gameRepository;
        this.getScrabbleDictionary=getScrabbleDictionary;
    }

    public Game startNewGame(int wordLength, int nbAttempts) {
        Game game = new Game(UUID.randomUUID().toString(), dictionaryService.getRandomWord(wordLength), nbAttempts);
        gameRepository.save(game);
        return game;
    }
    
    public Game attempt(String gameTid, String word) throws IllegalWordException, BadLengthException {
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
