package wordle.game.displayer.console.color;

/**
 * Example to use with System.out.print :
 * <br>
 * <br> System.out.print(Color.BLACK_BOLD);
 * <br> System.out.println("Black_Bold");
 * <br> System.out.print(Color.RESET);
 */
enum Color {
    //Color end string, color reset
    RESET("\033[0m"),

    GREEN_BACKGROUND("\033[42m"),
    BLUE_BACKGROUND("\u001B[44m"),
    CYAN_BACKGROUND("\033[46m"),
    BLACK_WRITE("\u001B[30m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}