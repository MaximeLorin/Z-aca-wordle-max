package com.zenika.academy.barbajavas.wordle.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.zenika.academy.barbajavas.wordle.domain.model.ValidationLetter.GOOD_POSITION;
import static com.zenika.academy.barbajavas.wordle.domain.model.ValidationLetter.NOT_IN_WORD;
import static com.zenika.academy.barbajavas.wordle.domain.model.ValidationLetter.WRONG_POSITION;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoundResultTest {

    @Test
    public void win() {
        String word = "CRANE";
        String guess = "CRANE";

        RoundResult result = RoundResult.fromGuess(word, guess);

        assertTrue(result.isWin());
        assertEquals(Set.of(), result.invalidLetters());
        assertArrayEquals("CRANE".toCharArray(), result.letters());
        assertArrayEquals(
                new ValidationLetter[]{
                        GOOD_POSITION,
                        GOOD_POSITION,
                        GOOD_POSITION,
                        GOOD_POSITION,
                        GOOD_POSITION
                }, 
                result.validationLetters()
        );
    }

    @Test
    public void doubleLettersOneCorrect() {
        String word = "CRANE";
        String guess = "CRIER";

        RoundResult result = RoundResult.fromGuess(word, guess);

        assertFalse(result.isWin());
        assertEquals(Set.of('I'), result.invalidLetters());
        assertArrayEquals("CRIER".toCharArray(), result.letters());
        assertArrayEquals(
                new ValidationLetter[]{
                        GOOD_POSITION,
                        GOOD_POSITION,
                        NOT_IN_WORD,
                        WRONG_POSITION,
                        NOT_IN_WORD
                },
                result.validationLetters()
        );
    }

    @Test
    public void doubleLettersOtherOrder() {
        String word = "FUTON";
        String guess = "TOTAL";

        RoundResult result = RoundResult.fromGuess(word, guess);

        assertFalse(result.isWin());
        assertEquals(Set.of('A', 'L'), result.invalidLetters());
        assertArrayEquals("TOTAL".toCharArray(), result.letters());
        assertArrayEquals(
                new ValidationLetter[]{
                        NOT_IN_WORD,
                        WRONG_POSITION,
                        GOOD_POSITION,
                        NOT_IN_WORD,
                        NOT_IN_WORD
                },
                result.validationLetters()
        );
    }

    @Test
    public void doubleLettersBothWrong() {
        String word = "CRANE";
        String guess = "OCCIR";

        RoundResult result = RoundResult.fromGuess(word, guess);

        assertFalse(result.isWin());
        assertArrayEquals("OCCIR".toCharArray(), result.letters());
        assertEquals(Set.of('I', 'O'), result.invalidLetters());
        assertArrayEquals(
                new ValidationLetter[]{
                        NOT_IN_WORD,
                        WRONG_POSITION,
                        NOT_IN_WORD,
                        NOT_IN_WORD,
                        WRONG_POSITION
                },
                result.validationLetters()
        );
    }
}