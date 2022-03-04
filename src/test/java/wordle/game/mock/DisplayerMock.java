package wordle.game.mock;

import wordle.game.RoundResult;
import wordle.game.displayer.Displayer;

public class DisplayerMock implements Displayer {

    @Override
    public String format(RoundResult result, boolean withLetter) {
        return "";
    }
}
