package com.swingy.utils;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Symbols.COPYRIGHT;
import static com.swingy.utils.Symbols.HEART;
import static com.swingy.utils.Log.log;

//TODO: Rename class
public class Logo {
    public static void displayBanner() {
        log(logo());
        log(welcomeMessage());
        log(instructions());
    }

    private static String  logo() {
        return ANSI_WHITE + "                                                                             \n" + ANSI_RESET +
                ANSI_WHITE + "                                                                                    \n" + ANSI_RESET +
                BLUE_BRIGHT  + "      1|10|1    |01|        |11| |10| |10|101    |11|  10|10||1|    |10|     |01| \n" + ANSI_RESET +
                BLUE_BRIGHT  + "    |01|  |11|  |11|        |00| |11| |11||11|   |01| |11|    |00|  |00|    |11|  \n" + ANSI_RESET +
                BLUE_BRIGHT  + "    |10|        |01|        |10| |10| |11| |00|  |10| |01|    |00|   |01|  |01|   \n" + ANSI_RESET +
                BLUE_BRIGHT  + "      |10|       |01| |10| |10|  |01| |01|  |11|1|10| |11|            |011101|    \n" + ANSI_RESET +
                BLUE_BRIGHT  + "        |10|      |01||11||01|   |10| |00|   |10||11| |01|  |0|1|01|    |11|      \n" + ANSI_RESET +
                CYAN_BRIGHT  + "    |11|  |01|    |00|   |10|    |01| |01|       |10| |11|    |01|      |01|      \n" + ANSI_RESET +
                CYAN_BRIGHT  + "      0|01|1      |01|   |00|    |11| |10|       |11|   |01||11|        |10| " + ANSI_WHITE + "v1.0\n" + ANSI_RESET +
                "\t\t\t\t" + CYAN_UNDERLINED + "Copyright " + COPYRIGHT + " 2020 Wethinkcode_ Developed with " + HEART + " by pmalope\n" + ANSI_RESET;
    }

    private static String welcomeMessage() {
        return CYAN_BOLD_BRIGHT + " Hi, Welcome!!!\n" + ANSI_RESET  +
                "  Implementation of a minimalistic text-based RPG game in Java using the swingy Framework\n\n" +
                ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "HOW TO PLAY" + ANSI_RESET + ANSI_YELLOW + ":::\n" + ANSI_RESET +
                "  * North: Move Up\n" +
                "  * East: Move Right\n" +
                "  * West: Move Left\n" +
                "  * South: Move Down\n";
    }

    private static String instructions() {
        return ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "HERE ARE THE RULES" + ANSI_RESET + ANSI_YELLOW + ":::\n" + ANSI_RESET +
                "  * You can have multiple heros of different types\n" +
                "  * Stats are affected by the hero's level and artifacts\n" +
                "  * If you reach the end of the border of the map you win\n" +
                "  * When you move to a position occupied by a villian you have two options; Run or Fight.\n" +
                "  * If you win the fight you will gain experience and an artifact which you can choose to keep or drop.\n" +
                "  * You will level up when you reach the next level experience\n" +
                "  * If you Lose you will die and start over.\n" +
                "  * To Advance to next Level You need to accumulate above 1000xp \n" +
                "  * The Map is based on your level\n";
    }
}
