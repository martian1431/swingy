package com.swingy.utils;

public class Logo {
    public static void displayLogo() {
        System.out.println();
        System.out.println();
        System.out.println(Colors.ANSI_WHITE + "                                                                                  ");
        System.out.println(Colors.ANSI_WHITE + "                                                                                  ");
        System.out.println(Colors.ANSI_BLUE  + "      1|10|1    |01|        |11| |10| |10|101    |11|  10|10||1|    |10|     |01| ");
        System.out.println(Colors.ANSI_BLUE  + "    |01|  |11|  |11|        |00| |11| |11||11|   |01| |11|    |00|  |00|    |11|  ");
        System.out.println(Colors.ANSI_BLUE  + "    |10|        |01|        |10| |10| |11| |00|  |10| |01|    |00|   |01|  |01|   ");
        System.out.println(Colors.ANSI_BLUE  + "      |10|       |01| |10| |10|  |01| |01|  |11|1|10| |11|            |011101|    ");
        System.out.println(Colors.ANSI_BLUE  + "        |10|      |01||11||01|   |10| |00|   |10||11| |01|  |0|1|01|    |11|      ");
        System.out.println(Colors.ANSI_CYAN  + "    |11|  |01|    |00|   |10|    |01| |01|       |10| |11|    |01|      |01|      ");
        System.out.println(Colors.ANSI_CYAN  + "      0|01|1      |01|   |00|    |11| |10|       |11|   |01||11|        |10| " + Colors.ANSI_WHITE + "v1.0");
        System.out.println(Colors.ANSI_WHITE + "                                                                     ");
//        TODO Extract
        System.out.print("                       ");
         System.out.println(Colors.CYAN_UNDERLINED + "Copyright " + Symbols.COPYRIGHT + " 2020  Made with " + Symbols.HEART + " by pmalope ");
    }

    public static String logoText = "\n\n\n\n\n\n\nSwingy 1.0 We Think Code Developed by pmalope 2020:"
            +  "\n\nA minimalistic text-based Role Play Game in Java Swing.";
}
