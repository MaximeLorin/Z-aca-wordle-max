package wordle.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import wordle.game.mock.DisplayerMock;
import wordle.game.mock.I18nMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void testGameSuccessFirstRound() {
        List<String> wordsToTry = new ArrayList<>();
        wordsToTry.add("TEST");
        I18nMock mockI18n = new I18nMock("TEST", List.of(new String[]{"TEST"}));

        Game game = new Game(mockI18n, wordsToTry.iterator(), new DisplayerMock());
        List<RoundResult> result = game.play();

        assertEquals(1, result.size());
        assertTrue( result.get(0).isWin());

        assertTrue(Arrays.stream(result.get(0).getValidationLetters()).allMatch(v -> v==ValidationLetter.GOOD_POSITION));

        char[] lettersTry = new char[] {'T','E','S','T'};
        assertArrayEquals(lettersTry, result.get(0).getLetters());
    }

    @Test
    void testGameSuccessFirstRoundButFirstTryNotInDictionary() {
        List<String> wordsToTry = new ArrayList<>();
        wordsToTry.add("PLOP");
        wordsToTry.add("TEST");
        I18nMock mockI18n = new I18nMock("TEST", List.of(new String[]{"TEST"}));

        Game game = new Game(mockI18n, wordsToTry.iterator(), new DisplayerMock());
        List<RoundResult> result = game.play();

        assertEquals(1, result.size());
        assertTrue( result.get(0).isWin());

        assertTrue(Arrays.stream(result.get(0).getValidationLetters()).allMatch(v -> v==ValidationLetter.GOOD_POSITION));

        char[] lettersTry = new char[] {'T','E','S','T'};
        assertArrayEquals(lettersTry, result.get(0).getLetters());
    }

    @Test
    void testGameSuccessFirstRoundButFirstWordNotSameLength() {
        List<String> wordsToTry = new ArrayList<>();
        wordsToTry.add("PAF");
        wordsToTry.add("TEST");
        I18nMock mockI18n = new I18nMock("TEST", List.of(new String[]{"TEST", "PAF"}));

        Game game = new Game(mockI18n, wordsToTry.iterator(), new DisplayerMock());
        List<RoundResult> result = game.play();

        assertEquals(1, result.size());
        assertTrue( result.get(0).isWin());

        assertTrue(Arrays.stream(result.get(0).getValidationLetters()).allMatch(v -> v==ValidationLetter.GOOD_POSITION));

        char[] lettersTry = new char[] {'T','E','S','T'};
        assertArrayEquals(lettersTry, result.get(0).getLetters());
    }

    @Test
    void testGameSuccessFail6Rounds() {
        List<String> wordsToTry = new ArrayList<>();
        wordsToTry.add("PLOP");
        wordsToTry.add("PLOP");
        wordsToTry.add("PLOP");
        wordsToTry.add("PLOP");
        wordsToTry.add("PLOP");
        wordsToTry.add("PLOP");
        I18nMock mockI18n = new I18nMock("TEST", List.of(new String[]{"TEST", "PLOP"}));

        Game game = new Game(mockI18n, wordsToTry.iterator(), new DisplayerMock());
        List<RoundResult> result = game.play();

        assertEquals(6, result.size());
        assertTrue( result.stream().noneMatch(RoundResult::isWin));
    }
}
