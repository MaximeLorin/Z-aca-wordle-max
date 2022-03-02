package wordle;

import wordle.game.Game;
import wordle.game.RoundResult;
import wordle.game.displayer.console.color.ConsoleColorDisplayer;
import wordle.i18n.I18n;
import wordle.i18n.I18nFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Launch params
        I18n i18n;
        try {
            String language = args[0];
            // Get i18n resources
            i18n = I18nFactory.getI18n(language);
        } catch (Exception e) {
            throw new Exception("Can't initialize app.\nBe sure to add arg with language among 'FR' or 'EN'");
        }
        Scanner scanner = new Scanner(System.in);
        ConsoleColorDisplayer consoleColorDisplayer = new ConsoleColorDisplayer();

        // Game
        boolean stop = false;
        while (!stop) {
            Game game = new Game(i18n, scanner, consoleColorDisplayer);
            game.play();

            System.out.println(i18n.getMessage("would_you_replay"));
            stop = scanner.next().equalsIgnoreCase(i18n.getMessage("no"));
        }
    }
}
