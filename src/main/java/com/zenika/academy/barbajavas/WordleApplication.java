package com.zenika.academy.barbajavas;

import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import com.zenika.academy.barbajavas.wordle.domain.repository.GameRepository;
import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.DictionaryService;
import com.zenika.academy.barbajavas.wordle.application.GameManager;
import com.zenika.academy.barbajavas.wordle.domain.service.I18nDictionaryService;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import com.zenika.academy.barbajavas.wordle.domain.service.displayer.console.color.ConsoleColorDisplayer;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18n;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18nFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

import static com.zenika.academy.barbajavas.wordle.domain.model.GameState.IN_PROGRESS;
import static com.zenika.academy.barbajavas.wordle.domain.model.GameState.WIN;

@SpringBootApplication
public class WordleApplication {
    public static void main(String[] args) throws Exception {

        SpringApplication.run(WordleApplication.class, args);

    }
}
