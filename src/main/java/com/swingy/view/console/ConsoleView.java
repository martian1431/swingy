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
import static com.swingy.utils.Log.inputSign;
import static com.swingy.utils.Log.log;

public class ConsoleView {

    /** Display menu choices after launching the game. */
    public static void menuOptions() {
        log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Select your choice "
                + ANSI_RESET + ANSI_YELLOW + ":::" + Colors.ANSI_RESET);
        log(ANSI_CYAN + "  1." + ANSI_RESET + " Create A New Hero.");
        log(ANSI_CYAN + "  2." + ANSI_RESET + " Select A Hero.");
        log(ANSI_CYAN + "  3." + ANSI_RESET + " Switch To GUI view.");
        inputSign();
        ConsoleController.menuOption();
    }

//    TODO
    public static void heroNameOption(CharacterType type) {
        log(Colors.ANSI_YELLOW + ":::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + "Name your hero" + Colors.ANSI_RESET + Colors.ANSI_YELLOW + Colors.ANSI_RESET);
        inputSign();
        ConsoleController.createHero(type);
    }

    private static void menuOptions(String input) {
    }

    /** Display all validtypes of heroes. */
    public static void heroOptions() {
//        boolean validInput = false;

        log(Colors.ANSI_YELLOW + ":::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + "Select hero type" + Colors.ANSI_RESET + Colors.ANSI_YELLOW  + Colors.ANSI_RESET);
        log(ANSI_CYAN + "  1." + ANSI_RESET + " Deadpool");
        log(ANSI_CYAN + "  2." + ANSI_RESET + " Thor");
        log(ANSI_CYAN + "  3." + ANSI_RESET + " Wolverine");
        inputSign();
        ConsoleController.heroType();
    }

//    TODO refactor
    /** The menu with cool swingy logo :). */
    public static void welcomeBanner() {
        Logo.displayLogo();
        log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Are ready to go down the Rabit hole? (Y)es or (N)o" + ANSI_RESET);
        inputSign();
        ConsoleController.startGame();
    }

//    TODO debug
    /** Display Directions or move list. */
    public static void displayMoveList() {
        log(CYAN_BOLD_BRIGHT + ">> Move <<" + ANSI_RESET);
        log(ANSI_RED + "1." + ANSI_CYAN + " North" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " East" + ANSI_RESET);
        log(ANSI_RED + "3." + ANSI_CYAN + " South" + ANSI_RESET);
        log(ANSI_RED + "4." + ANSI_CYAN + " West" + ANSI_RESET);
        inputSign();
    }

    /** Display Actions i.e fight or run. */
    public static void displayActions() {
        log(CYAN_BOLD_BRIGHT + ">> Action <<");
        log(ANSI_RED + "1." + ANSI_CYAN + " Fight" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " Run" + ANSI_RESET);
        inputSign();
    }

//    TODO overide
    public static void run() {
        welcomeBanner(); // TODO refactor to promt.WelcomeBanner

    }

    public static void selectedHero(Hero hero, int mapSize) {
        log(CYAN_BOLD_BRIGHT + " PLAYER STATS" + ANSI_RESET);
        log( " Level " + ANSI_YELLOW + ":" + ANSI_RESET + hero.getLevel() + ANSI_YELLOW + ":" + ANSI_RESET
                + " Hit Points " + ANSI_YELLOW + ":" + ANSI_RESET + hero.getHitPoints() + ANSI_YELLOW + ":" + ANSI_RESET
                + " Map size " + ANSI_YELLOW + ":" + ANSI_RESET + mapSize + ANSI_YELLOW + ":" + ANSI_RESET);
    }

    public static void existingHero() {
        ConsoleController.selectExistingHero();
    }

    public static void heroCount(int count) {
        if (count == 1)
            log(ANSI_YELLOW + count + " Hero Available " + ANSI_RESET);
        log(count + " Heroes Available " + ANSI_RESET);
        inputSign();
    }

    public static void availableHeros(ResultSet heros) throws SQLException {
        log(BLACK_BACKGROUND_BRIGHT + ":\tHERO NAME\t:" + ANSI_RESET);
        while (heros.next()) {
            System.out.format("\t%s%n", heros.getString("heroName"));
//            TODO use this
//            log(":\t" + heros.getString("heroName") + "\t");
        }
        log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Select your hero" + ANSI_RESET + ANSI_YELLOW + ANSI_RESET);
        inputSign();
    }
}
