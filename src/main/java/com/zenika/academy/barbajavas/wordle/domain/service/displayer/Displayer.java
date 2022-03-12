package com.zenika.academy.barbajavas.wordle.domain.service.displayer;

import com.zenika.academy.barbajavas.wordle.domain.model.RoundResult;

import java.util.List;
import java.util.stream.Collectors;

public interface Displayer {
    default String format(List<RoundResult> results, boolean withLetter) {
        return results.stream()
                .map(r -> this.format(r, withLetter))
                .collect(Collectors.joining(System.lineSeparator()));
    }
    
    String format(RoundResult result, boolean withLetter);
}
