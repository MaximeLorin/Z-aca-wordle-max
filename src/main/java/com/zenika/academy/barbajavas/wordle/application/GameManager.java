package com.zenika.academy.barbajavas.wordle.application;

import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import com.zenika.academy.barbajavas.wordle.domain.model.GameState;
import com.zenika.academy.barbajavas.wordle.domain.repository.GameRepository;
import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.DictionaryService;
import com.zenika.academy.barbajavas.wordle.domain.service.GameFinishedException;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GameManager {
    
    private final DictionaryService dictionaryService;
    private final GameRepository gameRepository;

    @Autowired
    public GameManager(DictionaryService dictionaryService, GameRepository gameRepository) {
        this.dictionaryService = dictionaryService;
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(int wordLength, int nbAttempts) {
        Game game = new Game(UUID.randomUUID().toString(), dictionaryService.getRandomWord(wordLength), nbAttempts);
        gameRepository.save(game);
        return game;
    }
    
    public Game attempt(String gameTid, String word) throws IllegalWordException, BadLengthException, GameFinishedException {
        Game game = gameRepository.findByTid(gameTid)
                .orElseThrow(() -> new IllegalArgumentException("This game does not exist"));
        
        if(!dictionaryService.wordExists(word)) {
            throw new IllegalWordException();
        }
        if(word.length() != game.getWordLength()) {
            throw new BadLengthException();
        }
        if(game.getGameState() != GameState.IN_PROGRESS) {
            throw new GameFinishedException();
        }
        
        game.guess(word);
        
        gameRepository.save(game);
        
        return game;
    }
    
    public Optional<Game> getGame(String gameTid) {
        return gameRepository.findByTid(gameTid);
    }
}
