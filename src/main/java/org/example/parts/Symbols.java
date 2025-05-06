package org.example.parts;

public class Symbols {
    public static final String TREE = "\uD83C\uDF33";
    public static final String PREDATOR = "\uD83E\uDD81";
    public static final String HERBIVORE = "\uD83D\uDC16";
    public static final String ROCK = "\uD83E\uDDF1";
    public static final String GRASS = "\uD83C\uDF3F";
    public static final String EMPTY_CELL = "\u26AA";
    public static final String DEFAULT = "\u2753";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String HUNGRY_PREDATOR = ANSI_RED_BACKGROUND + PREDATOR + ANSI_RESET;
    public static final String HUNGRY_HERBIVORE = ANSI_RED_BACKGROUND + HERBIVORE + ANSI_RESET;

}

