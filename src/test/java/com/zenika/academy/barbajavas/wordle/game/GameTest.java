package com.zenika.academy.barbajavas.wordle.game;

import com.zenika.academy.barbajavas.wordle.application.GameManager;
import com.zenika.academy.barbajavas.wordle.domain.service.displayer.console.color.ConsoleColorDisplayer;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18n;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class GameTest {

    @Autowired
    GameManager gameManager;

    @Autowired
    I18n i18n;

    @Autowired
    ConsoleColorDisplayer consoleColorDisplayer;

    @Test
    void testGameSuccessFirstRound() {

    }

    @Test
    void testGameSuccessFirstRoundButFirstTryNotInDictionary() {
//        List<String> wordsToTry = new ArrayList<>();
//        wordsToTry.add("PLOP");
//        wordsToTry.add("TEST");
//        I18nMock mockI18n = new I18nMock("TEST", List.of(new String[]{"TEST"}));
//
//        Game game = new Game(mockI18n, wordsToTry.iterator(), new DisplayerMock());
//        List<RoundResult> result = game.play();
//
//        assertEquals(1, result.size());
//        assertTrue( result.get(0).isWin());
//
//        assertTrue(Arrays.stream(result.get(0).getValidationLetters()).allMatch(v -> v==ValidationLetter.GOOD_POSITION));
//
//        char[] lettersTry = new char[] {'T','E','S','T'};
//        assertArrayEquals(lettersTry, result.get(0).getLetters());
    }

    @Test
    void testGameSuccessFirstRoundButFirstWordNotSameLength() {
//        List<String> wordsToTry = new ArrayList<>();
//        wordsToTry.add("PAF");
//        wordsToTry.add("TEST");
//        I18nMock mockI18n = new I18nMock("TEST", List.of(new String[]{"TEST", "PAF"}));
//
//        Game game = new Game(mockI18n, wordsToTry.iterator(), new DisplayerMock());
//        List<RoundResult> result = game.play();
//
//        assertEquals(1, result.size());
//        assertTrue( result.get(0).isWin());
//
//        assertTrue(Arrays.stream(result.get(0).getValidationLetters()).allMatch(v -> v==ValidationLetter.GOOD_POSITION));
//
//        char[] lettersTry = new char[] {'T','E','S','T'};
//        assertArrayEquals(lettersTry, result.get(0).getLetters());
    }

    @Test
    void testGameSuccessFail6Rounds() {
//        List<String> wordsToTry = new ArrayList<>();
//        wordsToTry.add("PLOP");
//        wordsToTry.add("PLOP");
//        wordsToTry.add("PLOP");
//        wordsToTry.add("PLOP");
//        wordsToTry.add("PLOP");
//        wordsToTry.add("PLOP");
//        I18nMock mockI18n = new I18nMock("TEST", List.of(new String[]{"TEST", "PLOP"}));
//
//        Game game = new Game(mockI18n, wordsToTry.iterator(), new DisplayerMock());
//        List<RoundResult> result = game.play();
//
//        assertEquals(6, result.size());
//        assertTrue( result.stream().noneMatch(RoundResult::isWin));
    }
}
