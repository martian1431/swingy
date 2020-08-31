package com.swingy.view.console;

import com.swingy.controller.ConsoleController;
import com.swingy.model.character.CharacterType;
import com.swingy.model.character.Hero;
import com.swingy.utils.Colors;
import com.swingy.utils.Logo;
import com.swingy.utils.database.DatabaseWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.log;

public class ConsoleView {

    /** Display menu choices after launching the game. */
    public static void menuOptions() {
        boolean validInput = false;

        log(Colors.ANSI_YELLOW + ":::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + " SELECT YOUR CHOICE " + Colors.ANSI_RESET + Colors.ANSI_YELLOW + ":::\n" + Colors.ANSI_RESET);
        log(ANSI_CYAN + "  1." + ANSI_RESET + " Create A New Hero.");
        log(ANSI_CYAN + "  2." + ANSI_RESET + " Select A Hero.");
        log(ANSI_CYAN + "  3." + ANSI_RESET + " Switch To GUI view.");
        do {
            if (ConsoleController.menuOptionValidation()) {
                validInput = true;
//                TODO
            } else {
                log(ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-3). Try Again!\n" + ANSI_RESET);
                log("");
            }
        } while (!validInput);
    }

//    TODO
    public static void heroNameOption(CharacterType type) {
        boolean validInput = false;

        log(Colors.ANSI_YELLOW + ":::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + " NAME YOUR HERO " + Colors.ANSI_RESET + Colors.ANSI_YELLOW + ":::\n" + Colors.ANSI_RESET);
        do {
            ConsoleController.createHero(type);
        } while (!validInput);
    }

    private static void menuOptions(String input) {
    }

    /** Display all validtypes of heroes. */
    public static void heroOptions() {
        boolean validInput = false;

        log(Colors.ANSI_YELLOW + ":::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + " SELECT A HERO TYPE " + Colors.ANSI_RESET + Colors.ANSI_YELLOW + ":::\n" + Colors.ANSI_RESET);
        log(ANSI_CYAN + "  1." + ANSI_RESET + " Deadpool");
        log(ANSI_CYAN + "  2." + ANSI_RESET + " Thor");
        log(ANSI_CYAN + "  3." + ANSI_RESET + " Wolverine");
        do {
            if (ConsoleController.heroType()) {
                validInput = true;
            } else {
                log(ANSI_RED + ":::ERROR::: Hero Type Does Not Exist, Try Again!\n" + ANSI_RESET);
            }
        } while (!validInput);
    }

//    TODO refactor
    /** The menu with cool swingy logo :). */
    public static void welcomeBanner() {
        boolean validInput = false;

        Logo.displayLogo();
        log(ANSI_YELLOW + ":::WARNING:::" + "Are ready to go down the Rabit hole? (Y)es or (N)o\n" + ANSI_RESET);
        do {
            int errorNum = ConsoleController.welcomeOption();
            switch (errorNum) {
                case 0:
                    validInput = true;
                    break;
                case 1:
                    log(ANSI_RED + ":::" + "SCARED?::: Go drink some water and try again later" + ANSI_RESET);
                    System.exit(-1);
                case -1:
                    log(ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!\n" + ANSI_RESET);
                    break;
                default:
                    break;
            }
        } while (!validInput);
        menuOptions();
    }

    /** Display Directions or move list. */
    public static void displayMoveList() {
        boolean validInput = false;

        log(ANSI_YELLOW + ":::Move:::" + ANSI_RESET);
        log(ANSI_RED + "1." + ANSI_CYAN + " North" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " East" + ANSI_RESET);
        log(ANSI_RED + "3." + ANSI_CYAN + " South" + ANSI_RESET);
        log(ANSI_RED + "4." + ANSI_CYAN + " West" + ANSI_RESET);
        do {
            if (ConsoleController.directions()) {
                validInput = true;
            } else {
                log(ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-4). Try Again!\n" + ANSI_RESET);
            }
        } while (!validInput);
    }

    /** Display Actions i.e fight or run. */
    public static void displayActions() {
        log(ANSI_YELLOW + "::: Action");
        log(ANSI_RED + "1." + ANSI_CYAN + " Fight" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " Run" + ANSI_RESET);
    }

//    TODO overide
    public static void run() {
        welcomeBanner(); // TODO refactor to promt.WelcomeBanner
    }

    public static void selectedHero(Hero hero, int mapSize) {
        log(Colors.CYAN_BOLD_BRIGHT + " PLAYER STATS" + Colors.ANSI_RESET);
        log( " Level " + Colors.ANSI_YELLOW + ":" + Colors.ANSI_RESET + hero.getLevel() + ANSI_YELLOW + ":" + ANSI_RESET
                + " Hit Points " + Colors.ANSI_YELLOW + ":" + Colors.ANSI_RESET + hero.getHitPoints() + ANSI_YELLOW + ":" + ANSI_RESET
                + " Map size " + Colors.ANSI_YELLOW + ":"
                + Colors.ANSI_RESET + mapSize + ANSI_YELLOW + ":" + ANSI_RESET);
        System.out.println();
    }

    public static void existingHero() {
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

//        String input = scanner.nextLine().trim();
        ConsoleController.selectExistingHero(scanner);
    }

    public static void heroCount(int count) {
        if (count == 1) {
            log(ANSI_YELLOW + count + " Hero Available " + ANSI_RESET);
        } else {
            log(count + " Heroes Available " + ANSI_RESET);
        }
    }

    public static void availableHeros(ResultSet heros) throws SQLException {
        log(BLACK_BACKGROUND_BRIGHT + ":\tHERO NAME\t:" + ANSI_RESET);
        while (heros.next()) {
            System.out.format("\t%s%n", heros.getString("heroName"));
//            TODO use this
//            log(":\t" + heros.getString("heroName") + "\t");
        }
        System.out.println();
        log(Colors.ANSI_YELLOW + ":::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + " SELECT YOUR HERO " + Colors.ANSI_RESET + Colors.ANSI_YELLOW + ":::\n" + Colors.ANSI_RESET);
    }
}
