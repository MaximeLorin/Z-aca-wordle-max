package wordle.game.displayer;

import wordle.game.RoundResult;

public interface Displayer {
    String format(RoundResult result, boolean withLetter);
}
