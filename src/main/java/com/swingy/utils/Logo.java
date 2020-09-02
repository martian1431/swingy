package com.swingy.utils;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Symbols.COPYRIGHT;
import static com.swingy.utils.Symbols.HEART;

//TODO: Refactor
public class Logo {
    public static void displayLogo() {
        System.out.println();
        System.out.println();
        System.out.println(ANSI_WHITE + "                                                                                         " + ANSI_RESET);
        System.out.println(ANSI_WHITE + "                                                                                         " + ANSI_RESET);
        System.out.println(BLUE_BRIGHT  + "      1|10|1    |01|        |11| |10| |10|101    |11|  10|10||1|    |10|     |01| " + ANSI_RESET);
        System.out.println(BLUE_BRIGHT  + "    |01|  |11|  |11|        |00| |11| |11||11|   |01| |11|    |00|  |00|    |11|  " + ANSI_RESET);
        System.out.println(BLUE_BRIGHT  + "    |10|        |01|        |10| |10| |11| |00|  |10| |01|    |00|   |01|  |01|   " + ANSI_RESET);
        System.out.println(BLUE_BRIGHT  + "      |10|       |01| |10| |10|  |01| |01|  |11|1|10| |11|            |011101|    " + ANSI_RESET);
        System.out.println(BLUE_BRIGHT  + "        |10|      |01||11||01|   |10| |00|   |10||11| |01|  |0|1|01|    |11|      " + ANSI_RESET);
        System.out.println(CYAN_BRIGHT  + "    |11|  |01|    |00|   |10|    |01| |01|       |10| |11|    |01|      |01|      " + ANSI_RESET);
        System.out.println(CYAN_BRIGHT  + "      0|01|1      |01|   |00|    |11| |10|       |11|   |01||11|        |10| " + ANSI_WHITE + "v1.0" + ANSI_RESET);
        System.out.println(CYAN_BRIGHT  + "                                                                     " + ANSI_RESET);
//        TODO Extract credits
        System.out.print("                       ");
         System.out.println(CYAN_UNDERLINED + "Copyright " + COPYRIGHT + " 2020  Made with " + HEART + " by pmalope " + ANSI_RESET);
        System.out.println();
//       TODO Instrustions
        System.out.println(gameInfo());

    }

    public static String gameInfo(){
//        TODO goodluck message
//        "\tGood Luck on your Journey... STAY AWAY FROM THANOS at a lower Level";
//        TODO (N.B) use this as soon as the user starts the game
//        "\t * Villains are in Red_-_ Heros are Blue (Refactor)\n"+
        return  CYAN_BOLD_BRIGHT + " Hi, Welcome!!!\n" + ANSI_RESET +
                " This game is a implementation of a minimalistic text-based RPG game in Java using the swingy Framework\n\n" +
                ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "HOW TO PLAY" + ANSI_RESET + ANSI_YELLOW + ":::\n" + ANSI_RESET +
                "  * North: Move Up\n" +
                "  * East: Move Right\n" +
                "  * West: Move Left\n" +
                "  * South: Move Down\n\n" +

                ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "HERE ARE THE RULES" + ANSI_RESET + ANSI_YELLOW + ":::\n" + ANSI_RESET +
                "  * You can have multiple heros of different types\n" +
                "  * Stats are affected by the hero's level and artifacts\n" +
                "  * If you reach the end of the border of the map you win\n" +
                "  * When you move to a position occupied by a villian you have two options; Run or Fight.\n" +
                "  * If you win the fight you will gain experience and an artifact which you can choose to keep or drop.\n" +
                "  * You will level up when you reach the next level experience \n" +
                "  * If you Lose you will die and start over.\n" +
                "  * To Advance to next Level You need to accumulate above 1000xp \n" +
                "  * The Map is based on your level\n";
    }


//    FIXME finish
//    public static String getSubheader(String name) {
//        if (name.toLowerCase().equals("instruction")) {
//            return  Colors.CYAN_BOLD_BRIGHT + "\tHi, Welcome!!!\n\n" + Colors.ANSI_RESET +
//                    "\tSwingy is a minimalistic text-based Role Play Game in Java Swing...(TODO)\n\n" +
//                    Colors.ANSI_YELLOW + "\t:::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + " HOW TO PLAY " + Colors.ANSI_RESET + Colors.ANSI_YELLOW + ":::\n\n" + Colors.ANSI_RESET +
//                    "\t * North: Move Up\n" +
//                    "\t * East: Move Right\n" +
//                    "\t * West: Move Left\n" +
//                    "\t * South: Move Down\n\n";
//        } else if (name.toLowerCase().equals("rules")) {
//            return Colors.ANSI_YELLOW + "\t:::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + " HERE ARE THE RULES " + Colors.ANSI_RESET + Colors.ANSI_YELLOW + ":::\n\n" + Colors.ANSI_RESET +
//                    "\tHere are rules:\n" +
//                    "\t * To win you need to pass the End of the MAP\n" +
//                    "\t * To Advance to next Level You need to accumulate above 1000xp\n" +
//                    "\t * The Map's are based on your level... \n" +
//                    "\t * Fighting a Viilain can boost your XP \n" +
//                    "\t * When Fighting a Villain, if you Lose you will die and start over.\n" +
//                    "\t * If you defeat a villain, You will get more powerful, Your XP,ATT,DEF,HP will Increase\n";
//        } else {
//            return "\tSomething went wrong while retrieving game details...";
//        }
//
//    }

    public static String logoText = "\n\n\n\n\n\n\nSwingy 1.0 We Think Code Developed by pmalope 2020:"
            +  "\n\nA minimalistic text-based Role Play Game in Java Swing.";
}
