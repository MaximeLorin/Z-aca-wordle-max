package com.zenika.academy.barbajavas.wordle.domain.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public record RoundResult(char[] letters, ValidationLetter[] validationLetters) {
    public boolean isWin() {
        return Arrays.stream(this.validationLetters)
                .allMatch((v) -> v == ValidationLetter.GOOD_POSITION);
    }
    
    public Set<Character> invalidLetters() {
        Set<Character> invalidLetters = new HashSet<>();
        for (int i = 0; i < validationLetters.length; i++) {
            if(validationLetters[i] == ValidationLetter.NOT_IN_WORD) {
                invalidLetters.add(letters[i]);
            }
        }
        for (int i = 0; i < validationLetters.length; i++) {
            if(validationLetters[i] != ValidationLetter.NOT_IN_WORD) {
                invalidLetters.remove(letters[i]);
            }
        }
        return invalidLetters;
    }

    public static RoundResult fromGuess(String rawWordToGuess, String rawUserInput) {
        String wordToGuess = rawWordToGuess.toUpperCase(Locale.ROOT);
        String userInput = rawUserInput.toUpperCase(Locale.ROOT);
        char[] userInputLetters = userInput.toCharArray();
        char[] wordToGuessLetters = wordToGuess.toCharArray();
        final Map<Character, Integer> wordHistogram = computeHistogram(wordToGuess);

        ValidationLetter[] validationLetters = new ValidationLetter[wordToGuess.length()];
        // First pass for good letters
        for (int i = 0; i < userInputLetters.length; i++) {
            final char curLetter = userInputLetters[i];
            if (curLetter == wordToGuessLetters[i]) {
                validationLetters[i] = ValidationLetter.GOOD_POSITION;
                wordHistogram.computeIfPresent(curLetter, (k, v) -> v-1);
            }
        }
        
        // Second pass for wrong positions and bad letters
        for (int i = 0; i < userInputLetters.length; i++) {
            final char curLetter = userInputLetters[i];
            final String curLetterString = String.valueOf(curLetter);
            if (curLetter == wordToGuessLetters[i]) {
                // Do nothing, already set during first pass
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
