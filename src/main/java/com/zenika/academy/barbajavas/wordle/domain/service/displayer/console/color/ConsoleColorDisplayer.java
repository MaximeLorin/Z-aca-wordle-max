package com.zenika.academy.barbajavas.wordle.domain.service.displayer.console.color;

import com.zenika.academy.barbajavas.wordle.domain.model.RoundResult;
import com.zenika.academy.barbajavas.wordle.domain.model.ValidationLetter;
import com.zenika.academy.barbajavas.wordle.domain.service.displayer.Displayer;

public class ConsoleColorDisplayer implements Displayer {

    @Override
    public String format(RoundResult result, boolean withLetter) {
        StringBuilder validationConsoleColorText = new StringBuilder();

        for (int i = 0; i < result.letters().length; i++) {
            ValidationLetter validationLetter = result.validationLetters()[i];
            String color = Color.CYAN_BACKGROUND + "" + Color.BLACK_WRITE;
            if (validationLetter == ValidationLetter.GOOD_POSITION) {
                color = Color.GREEN_BACKGROUND + "" + Color.BLACK_WRITE;
            } else if (validationLetter == ValidationLetter.WRONG_POSITION) {
                color = Color.BLUE_BACKGROUND + "" + Color.BLACK_WRITE;
            }
            if (i == 0) {
                validationConsoleColorText.append("|");
            }
            validationConsoleColorText.append(color);
            if (withLetter) {
                validationConsoleColorText.append(" ").append(result.letters()[i]).append(" ");
            } else {
                validationConsoleColorText.append("   ");
            }
            validationConsoleColorText.append(Color.RESET).append("|");
        }

        return validationConsoleColorText.toString();
    }
}
