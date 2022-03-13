package com.zenika.academy.barbajavas.wordle.game;

import com.zenika.academy.barbajavas.wordle.application.GameManager;
import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import com.zenika.academy.barbajavas.wordle.domain.model.GameState;
import com.zenika.academy.barbajavas.wordle.domain.model.ValidationLetter;
import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.DictionaryService;
import com.zenika.academy.barbajavas.wordle.domain.service.GameFinishedException;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import static com.zenika.academy.barbajavas.wordle.domain.model.ValidationLetter.GOOD_POSITION;
import static com.zenika.academy.barbajavas.wordle.domain.model.ValidationLetter.NOT_IN_WORD;
import static com.zenika.academy.barbajavas.wordle.domain.model.ValidationLetter.WRONG_POSITION;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ActiveProfiles("test")
public class GameManagerTest {

    @Autowired
    GameManager gameManager;

    @Autowired
    DictionaryService dictionaryServiceMock;

    @Test
    void testGameSuccessFirstRound() throws BadLengthException, IllegalWordException, GameFinishedException {
        Mockito.when(dictionaryServiceMock.getRandomWord(5)).thenReturn("ROYAL");
        Mockito.when(dictionaryServiceMock.wordExists(anyString())).thenReturn(true);

        Game game = gameManager.startNewGame(5, 5);
        game = gameManager.attempt(game.getTid(), "ROYAL");

        assertEquals(GameState.WIN, game.getGameState());
        assertEquals(1, game.getRounds().size());
        assertTrue(game.getRounds().get(0).isWin());
        assertArrayEquals(
                new ValidationLetter[]{
                        GOOD_POSITION,
                        GOOD_POSITION,
                        GOOD_POSITION,
                        GOOD_POSITION,
                        GOOD_POSITION
                },
                game.getRounds().get(0).validationLetters()
        );
    }

    @Test
    void testGameLoss() throws BadLengthException, IllegalWordException, GameFinishedException {
        Mockito.when(dictionaryServiceMock.getRandomWord(5)).thenReturn("ROYAL");
        Mockito.when(dictionaryServiceMock.wordExists(anyString())).thenReturn(true);

        Game game = gameManager.startNewGame(5, 5);
        String gameTid = game.getTid();
        
        assertThrows(BadLengthException.class, () -> gameManager.attempt(gameTid, "ROYALE"));
        game = gameManager.attempt(game.getTid(), "LOIRE");

        assertEquals(GameState.IN_PROGRESS, game.getGameState());
        assertEquals(1, game.getRounds().size());
        assertFalse(game.getRounds().get(0).isWin());
        assertArrayEquals(
                new ValidationLetter[]{
                        WRONG_POSITION,
                        GOOD_POSITION,
                        NOT_IN_WORD,
                        WRONG_POSITION,
                        NOT_IN_WORD
                },
                game.getRounds().get(0).validationLetters()
        );
        
        game = gameManager.attempt(game.getTid(), "LOIRE");
        game = gameManager.attempt(game.getTid(), "LOIRE");
        game = gameManager.attempt(game.getTid(), "LOIRE");
        game = gameManager.attempt(game.getTid(), "LOIRE");

        assertEquals(GameState.LOSS, game.getGameState());
        assertEquals(5, game.getRounds().size());
        assertFalse(game.getRounds().get(4).isWin());
        
        assertThrows(GameFinishedException.class, () -> gameManager.attempt(gameTid, "LOIRE"));
    }

    @TestConfiguration
    static class GameManagerTestConfiguration {
        @Bean
        @Primary
        public DictionaryService dictionaryService() {
            return Mockito.mock(DictionaryService.class);
        }
    }
}
