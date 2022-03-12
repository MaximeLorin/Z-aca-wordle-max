package com.zenika.academy.barbajavas.wordle.game.mock;

import com.zenika.academy.barbajavas.wordle.domain.model.RoundResult;
import com.zenika.academy.barbajavas.wordle.domain.service.displayer.Displayer;

public class DisplayerMock implements Displayer {

    @Override
    public String format(RoundResult result, boolean withLetter) {
        return "";
    }
}
