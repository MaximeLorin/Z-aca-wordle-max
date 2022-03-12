package com.zenika.academy.barbajavas.wordle.domain.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public record RoundResult(char[] letters, ValidationLetter[] validationLetters) {
    public boolean isWin() {
        return Arrays.stream(this.validationLetters)
                .allMatch((v) -> v == ValidationLetter.GOOD_POSITION);
    }

    public static RoundResult fromGuess(String wordToGuess, String userInput) {
        char[] userInputLetters = userInput.toUpperCase(Locale.ROOT).toCharArray();
        char[] wordToGuessLetters = wordToGuess.toUpperCase(Locale.ROOT).toCharArray();
        final Map<Character, Integer> wordHistogram = computeHistogram(wordToGuess);

        ValidationLetter[] validationLetters = new ValidationLetter[wordToGuess.length()];
        for (int i = 0; i < userInputLetters.length; i++) {
            final char curLetter = userInputLetters[i];
            final String curLetterString = String.valueOf(curLetter);
            if (curLetter == wordToGuessLetters[i]) {
                validationLetters[i] = ValidationLetter.GOOD_POSITION;
                wordHistogram.computeIfPresent(curLetter, (k, v) -> v-1);
            } else if (wordToGuess.contains(curLetterString) && wordHistogram.get(curLetter) > 0) {
                validationLetters[i] = ValidationLetter.WRONG_POSITION;
                wordHistogram.computeIfPresent(curLetter, (k, v) -> v-1);
            } else {
                validationLetters[i] = ValidationLetter.NOT_IN_WORD;
            }
        }

        return new RoundResult(userInputLetters, validationLetters);
    }
    
    private static Map<Character, Integer> computeHistogram(String word) {
        Map<Character, Integer> result = new HashMap<>();
        for (char c : word.toCharArray()) {
            result.merge(c, 1, Integer::sum);
        }
        return result;
    }
}
