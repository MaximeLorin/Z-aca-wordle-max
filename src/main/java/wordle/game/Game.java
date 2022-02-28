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
    private List<RoundResult> roundResults = new ArrayList<>();

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
            System.out.println("Il vous reste " + nbTryLeft + " essai(s)");
            if(!this.roundResults.isEmpty()) {
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
        for(RoundResult r : this.roundResults) {
            for(int i=0; i < r.getLetters().length; i++) {
                if(r.getValidationLetters()[i] == ValidationLetter.NOT_IN_WORD) {
                    letterUsed.add(r.getLetters()[i]);
                }
            }
        }
        System.out.print("Lettres invalides : |");
        for(Character c : letterUsed){
            System.out.print(" "+c+" |");
        }
        System.out.println();
    }

    private void displayGameResult() {
        System.out.println("Le mot à deviner était : " + this.wordToGuess);
        if (this.isWin()) {
            System.out.println("Bien joué vous avez trouvé en " + this.roundResults.size() + " essai(s) !!");
        } else {
            System.out.println("Dommage, la prochaine fois vous y arriverez !!");
        }
        for (RoundResult r : this.roundResults) {
            System.out.println(this.displayer.format(r, false));
        }
    }

    private boolean isWin() {
        return this.roundResults.stream().anyMatch(RoundResult::isWin);
    }
}
