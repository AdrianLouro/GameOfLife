package model;

public enum Color {
    RESET("\033[0m"),

    BLACK("\033[0;40m"),
    RED("\033[0;41m"),
    GREEN("\033[0;42m"),
    YELLOW("\033[0;43m"),   
    BLUE("\033[0;44m"),
    MAGENTA("\033[0;45m"),
    CYAN("\033[0;46m"),
    WHITE("\033[0;47m");


    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}