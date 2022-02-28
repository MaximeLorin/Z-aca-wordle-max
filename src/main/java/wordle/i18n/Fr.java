package wordle.i18n;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.*;

public class Fr extends AbstractI18n {

    public Fr() throws FileNotFoundException {
        super(ResourceBundle.getBundle("messages", new Locale("fr", "FR")));
    }

    @Override
    protected void loadDictionnary() throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String path = Objects.requireNonNull(classLoader.getResource("dico_fr.txt")).getPath();
        Scanner scanner = new Scanner(new File(path));
        while(scanner.hasNext()) {
            String line = scanner.next();
            if(line.length() > 3 && !line.contains("-") && line.length()<11) {
                this.dictionary.add(this.removeAccent(line).toUpperCase());
            }
        }
    }

    private String removeAccent(String word) {
        String regex = "[^\\p{ASCII}]";
        return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll(regex, "");
    }
}