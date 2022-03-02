package wordle.game;

import wordle.game.displayer.Displayer;
import wordle.i18n.I18n;

import java.util.*;

public class Game {

    public static int gameNumber;
    private final I18n i18n;
    private final Iterator<String> scanner;
    private final Displayer displayer;
    private final String wordToGuess;
    private final List<RoundResult> roundResults = new ArrayList<>();

    private static final int NB_MAX_ROUND = 6;

    public Game(I18n i18n, Iterator<String> scanner, Displayer displayer) {
        this.i18n = i18n;
        this.scanner = scanner;
        this.displayer = displayer;
        this.wordToGuess = i18n.getRandomWord();
    }

    public List<RoundResult> play() {
        while (this.roundResults.size() < NB_MAX_ROUND && !this.isWin()) {
            int nbTryLeft = NB_MAX_ROUND - this.roundResults.size();
            System.out.println(i18n.getMessage("nb_try_left", nbTryLeft));
            if (!this.roundResults.isEmpty()) {
                displayInvalidLetters();
            }
            Round round = new Round(this.roundResults.size() + 1, this.wordToGuess, this.i18n, this.scanner);
            RoundResult roundResult = round.play();
            System.out.println(this.displayer.format(roundResult, true));
            this.roundResults.add(roundResult);
        }

        // Display Result Game
        this.displayGameResult();

        return this.roundResults;
    }

    private void displayInvalidLetters() {
        Set<Character> letterUsed = new HashSet<>();
        for (RoundResult r : this.roundResults) {
            for (int i = 0; i < r.getLetters().length; i++) {
                if (r.getValidationLetters()[i] == ValidationLetter.NOT_IN_WORD) {
                    letterUsed.add(r.getLetters()[i]);
                }
            }
        }
        System.out.print(i18n.getMessage("invalid_letters_tried") + " : |");
        for (Character c : letterUsed) {
            System.out.print(" " + c + " |");
        }
        System.out.println();
    }

    private void displayGameResult() {
        System.out.println(i18n.getMessage("word_to_guess_was", this.wordToGuess));
        if (this.isWin()) {
            System.out.println(i18n.getMessage("victory", this.roundResults.size()));
        } else {
            System.out.println(i18n.getMessage("fail"));
        }
        for (RoundResult r : this.roundResults) {
            System.out.println(this.displayer.format(r, false));
        }
    }

    private boolean isWin() {
        return this.roundResults.stream().anyMatch(RoundResult::isWin);
    }
}
