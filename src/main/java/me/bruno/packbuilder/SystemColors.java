package me.bruno.packbuilder;

public class SystemColors {
    // Reset
    public static final SystemColors RESET = new SystemColors("\033[0m");  // Text Reset

    // Regular Colors
    public static final SystemColors BLACK = new SystemColors("\033[0;30m");   // BLACK
    public static final SystemColors RED = new SystemColors("\033[0;31m");     // RED
    public static final SystemColors GREEN = new SystemColors("\033[0;32m");   // GREEN
    public static final SystemColors YELLOW = new SystemColors("\033[0;33m");  // YELLOW
    public static final SystemColors BLUE = new SystemColors("\033[0;34m");    // BLUE
    public static final SystemColors PURPLE = new SystemColors("\033[0;35m");  // PURPLE
    public static final SystemColors CYAN = new SystemColors("\033[0;36m");    // CYAN
    public static final SystemColors WHITE = new SystemColors("\033[0;37m");   // WHITE

    // Bold
    public static final SystemColors BLACK_BOLD = new SystemColors("\033[1;30m");  // BLACK
    public static final SystemColors RED_BOLD = new SystemColors("\033[1;31m");    // RED
    public static final SystemColors GREEN_BOLD = new SystemColors("\033[1;32m");  // GREEN
    public static final SystemColors YELLOW_BOLD = new SystemColors("\033[1;33m"); // YELLOW
    public static final SystemColors BLUE_BOLD = new SystemColors("\033[1;34m");   // BLUE
    public static final SystemColors PURPLE_BOLD = new SystemColors("\033[1;35m"); // PURPLE
    public static final SystemColors CYAN_BOLD = new SystemColors("\033[1;36m");   // CYAN
    public static final SystemColors WHITE_BOLD = new SystemColors("\033[1;37m");  // WHITE

    // Underline
    public static final SystemColors BLACK_UNDERLINED = new SystemColors("\033[4;30m");  // BLACK
    public static final SystemColors RED_UNDERLINED = new SystemColors("\033[4;31m");    // RED
    public static final SystemColors GREEN_UNDERLINED = new SystemColors("\033[4;32m");  // GREEN
    public static final SystemColors YELLOW_UNDERLINED = new SystemColors("\033[4;33m"); // YELLOW
    public static final SystemColors BLUE_UNDERLINED = new SystemColors("\033[4;34m");   // BLUE
    public static final SystemColors PURPLE_UNDERLINED = new SystemColors("\033[4;35m"); // PURPLE
    public static final SystemColors CYAN_UNDERLINED = new SystemColors("\033[4;36m");   // CYAN
    public static final SystemColors WHITE_UNDERLINED = new SystemColors("\033[4;37m");  // WHITE

    // Background
    public static final SystemColors BLACK_BACKGROUND = new SystemColors("\033[40m");  // BLACK
    public static final SystemColors RED_BACKGROUND = new SystemColors("\033[41m");    // RED
    public static final SystemColors GREEN_BACKGROUND = new SystemColors("\033[42m");  // GREEN
    public static final SystemColors YELLOW_BACKGROUND = new SystemColors("\033[43m"); // YELLOW
    public static final SystemColors BLUE_BACKGROUND = new SystemColors("\033[44m");   // BLUE
    public static final SystemColors PURPLE_BACKGROUND = new SystemColors("\033[45m"); // PURPLE
    public static final SystemColors CYAN_BACKGROUND = new SystemColors("\033[46m");   // CYAN
    public static final SystemColors WHITE_BACKGROUND = new SystemColors("\033[47m");  // WHITE

    // High Intensity
    public static final SystemColors BLACK_BRIGHT = new SystemColors("\033[0;90m");  // BLACK
    public static final SystemColors RED_BRIGHT = new SystemColors("\033[0;91m");    // RED
    public static final SystemColors GREEN_BRIGHT = new SystemColors("\033[0;92m");  // GREEN
    public static final SystemColors YELLOW_BRIGHT = new SystemColors("\033[0;93m"); // YELLOW
    public static final SystemColors BLUE_BRIGHT = new SystemColors("\033[0;94m");   // BLUE
    public static final SystemColors PURPLE_BRIGHT = new SystemColors("\033[0;95m"); // PURPLE
    public static final SystemColors CYAN_BRIGHT = new SystemColors("\033[0;96m");   // CYAN
    public static final SystemColors WHITE_BRIGHT = new SystemColors("\033[0;97m");  // WHITE

    // Bold High Intensity
    public static final SystemColors BLACK_BOLD_BRIGHT = new SystemColors("\033[1;90m"); // BLACK
    public static final SystemColors RED_BOLD_BRIGHT = new SystemColors("\033[1;91m");   // RED
    public static final SystemColors GREEN_BOLD_BRIGHT = new SystemColors("\033[1;92m"); // GREEN
    public static final SystemColors YELLOW_BOLD_BRIGHT = new SystemColors("\033[1;93m");// YELLOW
    public static final SystemColors BLUE_BOLD_BRIGHT = new SystemColors("\033[1;94m");  // BLUE
    public static final SystemColors PURPLE_BOLD_BRIGHT = new SystemColors("\033[1;95m");// PURPLE
    public static final SystemColors CYAN_BOLD_BRIGHT = new SystemColors("\033[1;96m");  // CYAN
    public static final SystemColors WHITE_BOLD_BRIGHT = new SystemColors("\033[1;97m"); // WHITE

    // High Intensity backgrounds
    public static final SystemColors BLACK_BACKGROUND_BRIGHT = new SystemColors("\033[0;100m");// BLACK
    public static final SystemColors RED_BACKGROUND_BRIGHT = new SystemColors("\033[0;101m");// RED
    public static final SystemColors GREEN_BACKGROUND_BRIGHT = new SystemColors("\033[0;102m");// GREEN
    public static final SystemColors YELLOW_BACKGROUND_BRIGHT = new SystemColors("\033[0;103m");// YELLOW
    public static final SystemColors BLUE_BACKGROUND_BRIGHT = new SystemColors("\033[0;104m");// BLUE
    public static final SystemColors PURPLE_BACKGROUND_BRIGHT = new SystemColors("\033[0;105m"); // PURPLE
    public static final SystemColors CYAN_BACKGROUND_BRIGHT = new SystemColors("\033[0;106m");  // CYAN
    public static final SystemColors WHITE_BACKGROUND_BRIGHT = new SystemColors("\033[0;107m");   // WHITE

    private String thing;

    public SystemColors(String color) {
        thing = color;
    }
    @Override
    public String toString() {
        return thing;
    }
}
