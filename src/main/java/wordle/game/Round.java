package wordle.game;

import wordle.i18n.I18n;

import java.util.Iterator;

public class Round {

    private final int roundNumber;
    private final I18n i18n;
    private final Iterator<String> scanner;
    private final String wordToGuess;

    public Round(int roundNumber, String wordToGuess, I18n i18n, Iterator<String> scanner) {
        this.roundNumber = roundNumber;
        this.wordToGuess = wordToGuess;
        this.i18n = i18n;
        this.scanner = scanner;
    }

    public RoundResult play() {
        String tryGuess;
        boolean valid;
        do {
            System.out.println("Essayez de deviner le mot de " + this.wordToGuess.length() + " lettres");
            tryGuess = this.scanner.next().toUpperCase();

            valid =true;
            if(tryGuess.length() != this.wordToGuess.length()) {
                System.out.println("Votre mot fait " + tryGuess.length() + " lettres");
                valid = false;
            }
            else if(!i18n.wordExists(tryGuess)) {
                System.out.println("Ce mot n'est pas présent dans le dictionnaire");
                valid = false;
            }
        } while (!valid);

        char[] lettersTryToGuess = tryGuess.toCharArray();
        String lettersAlreadyGuess = this.wordToGuess;

        ValidationLetter[] validationLetters = new ValidationLetter[lettersTryToGuess.length];
        for (int i = 0; i < lettersTryToGuess.length; i++) {
            if (lettersTryToGuess[i] == this.wordToGuess.toCharArray()[i]) {
                validationLetters[i] = ValidationLetter.GOOD_POSITION;
                lettersAlreadyGuess = lettersAlreadyGuess.replaceFirst(String.valueOf(lettersTryToGuess[i]), "");
            }
        }

        for (int i = 0; i < lettersTryToGuess.length; i++) {
            if (lettersTryToGuess[i] == this.wordToGuess.toCharArray()[i]) {
                validationLetters[i] = ValidationLetter.GOOD_POSITION;
            } else if (lettersAlreadyGuess.contains(String.valueOf(lettersTryToGuess[i]))) {
                validationLetters[i] = ValidationLetter.WRONG_POSITION;
                lettersAlreadyGuess = lettersAlreadyGuess.replaceFirst(String.valueOf(lettersTryToGuess[i]), "");
            } else {
                validationLetters[i] = ValidationLetter.NOT_IN_WORD;
            }
        }
        return new RoundResult(lettersTryToGuess, validationLetters);
    }


}