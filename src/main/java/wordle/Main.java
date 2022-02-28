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
        String language = args[0];

        // Get i18n resources
        I18n i18n = I18nFactory.getI18n(language);
        Scanner scanner = new Scanner(System.in);
        ConsoleColorDisplayer consoleColorDisplayer = new ConsoleColorDisplayer();

        // Game
        boolean stop = false;
        while (!stop) {
            Game game = new Game(i18n, scanner, consoleColorDisplayer);
            game.play();

            System.out.println("Voulez-vous relancer une partie (y/n) ?");
            stop = scanner.next().equalsIgnoreCase("N");
        }
    }
}
