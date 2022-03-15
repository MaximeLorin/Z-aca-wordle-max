package com.zenika.academy.barbajavas.wordle.configuration;

import com.zenika.academy.barbajavas.wordle.application.GameManager;
import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import com.zenika.academy.barbajavas.wordle.domain.service.displayer.console.color.ConsoleColorDisplayer;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18n;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18nFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.zenika.academy.barbajavas.wordle.configuration.AppConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

import static com.zenika.academy.barbajavas.wordle.domain.model.GameState.IN_PROGRESS;
import static com.zenika.academy.barbajavas.wordle.domain.model.GameState.WIN;

@Configuration
public class CommandLine {
    @Bean
    public CommandLineRunner commandLineRunner(GameManager gameManager, ConsoleColorDisplayer consoleColorDisplayer) {
        return args -> {
            I18n i18n;
            try {
                String language = args[0];
                // Get i18n resources
                i18n = I18nFactory.getI18n(language);
            } catch (Exception e) {
                throw new Exception("Can't initialize app.\nBe sure to add arg with language among 'FR' or 'EN'");
            }
            Scanner scanner = new Scanner(System.in);
            boolean stop = false;
            while (!stop) {
                System.out.println("Démarrer une partie : quelle longueur de mot ?");
                int wordLength = Integer.parseInt(scanner.nextLine());
                System.out.println("Combien de tentatives maximum ?");
                int nbAttempts = Integer.parseInt(scanner.nextLine());

                Game game = gameManager.startNewGame(wordLength, nbAttempts);
                System.out.println(i18n.getMessage("try_to_guess", wordLength));
                while (game.getGameState().equals(IN_PROGRESS)) {
                    System.out.println(consoleColorDisplayer.format(game.getRounds(), true));
                    System.out.println(i18n.getMessage("nb_try_left", game.getAttemptsLeft()));

                    String guess = scanner.nextLine();
                    try {
                        game = gameManager.attempt(game.getTid(), guess);
                    } catch (IllegalWordException e) {
                        System.out.println(i18n.getMessage("word_not_in_dictionary"));
                    }
                    catch (BadLengthException e) {
                        System.out.println(i18n.getMessage("nb_letters_word_try", game.getWordLength()));
                    }
                }
                System.out.println(game.getGameState().equals(WIN) ? i18n.getMessage("victory", game.getRounds().size()) : i18n.getMessage("fail"));
                System.out.println(consoleColorDisplayer.format(game.getRounds(), false));
                System.out.println(i18n.getMessage("word_to_guess_was", game.getWord()));

                System.out.println(i18n.getMessage("would_you_replay"));
                stop = scanner.nextLine().trim().equalsIgnoreCase(i18n.getMessage("no"));
            }
        };
    }
}
