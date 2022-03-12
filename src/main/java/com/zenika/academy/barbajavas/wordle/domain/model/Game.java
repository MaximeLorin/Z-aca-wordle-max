package com.zenika.academy.barbajavas.wordle.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final String tid;
    private final String word;
    private final int nbRounds;
    private final List<RoundResult> roundResults;

    public Game(String tid, String word, int nbRounds) {
        this.tid = tid;
        this.word = word;
        this.nbRounds = nbRounds;
        this.roundResults = new ArrayList<>(nbRounds);
    }

    public String getTid() {
        return this.tid;
    }

    public int getAttemptsLeft() {
        return this.nbRounds - this.roundResults.size();
    }

    public GameState getGameState() {
        if (roundResults.isEmpty()) {
            return GameState.IN_PROGRESS;
        } else if (roundResults.get(roundResults.size() - 1).isWin()) {
            return GameState.WIN;
        } else {
            return roundResults.size() < nbRounds ? GameState.IN_PROGRESS : GameState.LOSS;
        }
    }

    public void guess(String userInput) {
        this.roundResults.add(RoundResult.fromGuess(this.word, userInput));
    }

    public int getWordLength() {
        return word.length();
    }

    public List<RoundResult> getRounds() {
        return Collections.unmodifiableList(this.roundResults);
    }
    
    public String getWord() {
        return this.getGameState() == GameState.IN_PROGRESS ? "?" : this.word;
    }
}
