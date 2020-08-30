package com.swingy.utils;

public class Logo {
    public static void displayLogo() {
        System.out.println();
        System.out.println();
        System.out.println(Colors.ANSI_WHITE  + "                                                                     ");
        System.out.println(Colors.ANSI_WHITE  + "                                                                     ");
        System.out.println(Colors.ANSI_PURPLE + "       |*|   |*|        |*| |*| |*|      |*|   |*||*|   |*|      |*| ");
        System.out.println(Colors.ANSI_PURPLE + "    |*|  |*| |*|        |*| |*| |*||*|   |*| |*|    |*|  |*|    |*|  ");
        System.out.println(Colors.ANSI_PURPLE + "    |*|      |*|        |*| |*| |*| |*|  |*| |*|    |*|   |*|  |*|   ");
        System.out.println(Colors.ANSI_YELLOW + "      |*|     |*| |*|  |*|  |*| |*|  |*| |*| |*|           |*||*|    ");
        System.out.println(Colors.ANSI_BLUE   + "        |*|    |*|||*||*|   |*| |*|   |*||*| |*|  |*||*|     |*|     ");
        System.out.println(Colors.ANSI_BLUE   + "    |*|  |*|    |*|  |*|    |*| |*|      |*| |*|    |*|      |*|     ");
        System.out.println(Colors.ANSI_BLUE   + "      |*|       |*|  |*|    |*| |*|      |*|   |*||*|        |*| " + Colors.ANSI_WHITE + "v1.0");
        System.out.println(Colors.ANSI_WHITE  + "                                                                     ");
        System.out.println(Colors.ANSI_WHITE  + "                Copyright " + Symbols.COPYRIGHT + " 2020  Made with " + Symbols.HEART + " by pmalope ");
        System.out.println(Colors.ANSI_WHITE  + "                                                                     ");
//        S();W();
    }

    public static void S() {
        System.out.println(Colors.ANSI_RED + "     |*|   ");
        System.out.println(Colors.ANSI_RED + "  |*|  |*| ");
        System.out.println(Colors.ANSI_RED + "  |*|      ");
        System.out.println(Colors.ANSI_RED + "    |*|    ");
        System.out.println(Colors.ANSI_RED + "      |*|  ");
        System.out.println(Colors.ANSI_RED + "  |*|  |*| ");
        System.out.println(Colors.ANSI_RED + "    |*|    ");
    }

    public static void W() {
        System.out.print(Colors.ANSI_YELLOW + "  |*|        |*| ");
        System.out.print(Colors.ANSI_YELLOW + "  |*|        |*| ");
        System.out.print(Colors.ANSI_YELLOW + "  |*|        |*| ");
        System.out.print(Colors.ANSI_YELLOW + "   |*| |*|  |*|  ");
        System.out.print(Colors.ANSI_YELLOW + "    |*|||*||*|   ");
        System.out.print(Colors.ANSI_YELLOW + "     |*|  |*|    ");
        System.out.print(Colors.ANSI_YELLOW + "     |*|  |*|    ");
    }
    public static String logoText = "\n\n\n\n\n\n\nSwingy 1.0 We Think Code Developed by pmalope 2020:"
            +  "\n\nA minimalistic text-based Role Play Game in Java Swing.";
}
