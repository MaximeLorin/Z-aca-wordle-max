package wordle.game;

import java.util.Arrays;

public class RoundResult {

    private final char[] letters;
    private final ValidationLetter[] validationLetters;

    public RoundResult(char[] letters, ValidationLetter[] validationLetters) {
        this.letters = letters;
        this.validationLetters = validationLetters;
    }

    public char[] getLetters() {
        return letters;
    }

    public ValidationLetter[] getValidationLetters() {
        return validationLetters;
    }

    public boolean isWin() {
        return Arrays.stream(this.validationLetters)
                .allMatch((v) -> v==ValidationLetter.GOOD_POSITION);
    }
}
