package com.swingy.view.console;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.swingy.controller.ConsoleController;
import com.swingy.model.character.CharacterType;
import com.swingy.model.character.heros.Hero;
import com.swingy.utils.Colors;

import java.util.Arrays;
import java.util.List;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.inputSign;
import static com.swingy.utils.Log.log;
import static com.swingy.utils.Messages.showWelcomeMessage;

public class ConsoleView {

    public static void showMainOptions() {
        log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Select your choice "
                + ANSI_RESET + ANSI_YELLOW + ":::" + Colors.ANSI_RESET);
        log(ANSI_RED + "  1." + ANSI_RESET + " Create A New Hero");
        log(ANSI_RED + "  2." + ANSI_RESET + " Select A Hero");
//        log(ANSI_RED + "  3." + ANSI_RESET + " Switch To GUI view.");
        log(ANSI_RED + "  3." + ANSI_RESET + " Quit");
        ConsoleController.selectMainOption();
    }

//    TODO find a better name
    public static void showHeroNameOption(CharacterType type) {
        log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Name your hero" + ANSI_RESET + ANSI_YELLOW + ANSI_RESET);
        ConsoleController.createHero(type);
    }

    public static void showHeroType() {
        log(Colors.ANSI_YELLOW + ":::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + "Select hero type" + Colors.ANSI_RESET + Colors.ANSI_YELLOW  + Colors.ANSI_RESET);
        log(ANSI_RED + "  1." + ANSI_RESET + ANSI_YELLOW + " Deadpool " + ANSI_RESET + "Attack[7] Defense[2] Hit Points[26]");
        log(ANSI_RED + "  2." + ANSI_RESET + ANSI_YELLOW + " Thor " + ANSI_RESET + "Attack[9] Defense[3] Hit Points[51]");
        log(ANSI_RED + "  3." + ANSI_RESET + ANSI_YELLOW + " Wolverine " + ANSI_RESET + "Attack[10] Defense[3] Hit Points[75]");
        log(ANSI_RED + "  4." + ANSI_RESET + " Quit");
        ConsoleController.selectHeroType();
    }


//    TODO debug
    public static void showDirectionOptions() {
        log(CYAN_BOLD_BRIGHT + ">> Move <<" + ANSI_RESET);
        log(ANSI_RED + "1." + ANSI_CYAN + " North" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " East" + ANSI_RESET);
        log(ANSI_RED + "3." + ANSI_CYAN + " West" + ANSI_RESET);
        log(ANSI_RED + "4." + ANSI_CYAN + " South" + ANSI_RESET);
        log(ANSI_RED + "5." + ANSI_CYAN + " Quit" + ANSI_RESET);
        inputSign();
    }

    public static void showActionOption() {
        log(CYAN_BOLD_BRIGHT + ">> Action <<");
        log(ANSI_RED + "1." + ANSI_CYAN + " Fight" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " Run" + ANSI_RESET);
        log(ANSI_RED + "3." + ANSI_RESET + " Quit");
//        ConsoleController.testMethod();
    }

    public static void run() {
        showWelcomeMessage();
        log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Are ready to go down the Rabit hole? (Y)es or (N)o" + ANSI_RESET);
        ConsoleController.startGame();
    }

    public static void showSelectedHero(Hero hero, int mapSize) {
        log( "Stats: " + "Name" + ANSI_YELLOW + "[" + ANSI_RESET + hero.getName() + ANSI_YELLOW + "] " + ANSI_RESET
                + "Type" + ANSI_YELLOW + "[" + ANSI_RESET + hero.getType() + ANSI_YELLOW + "] "
                + ANSI_RESET + " Level" + ANSI_YELLOW + "[" + ANSI_RESET + hero.getLevel() + ANSI_YELLOW + "] " + ANSI_RESET
                + " Hit Points" + ANSI_YELLOW + "[" + ANSI_RESET + hero.getHitPoints() + ANSI_YELLOW + "] " +
                ANSI_RESET+ " Experience" + ANSI_YELLOW + "[" + ANSI_RESET + hero.getExperience() + ANSI_YELLOW + "] " + ANSI_RESET
                + " Map size" + ANSI_YELLOW + "[" + ANSI_RESET + mapSize + ANSI_YELLOW + "] " + ANSI_RESET);
    }

    public static void showExistingHero() {
        ConsoleController.selectExistingHero();
    }

    public static void heroCount(int count) {
        if (count == 1)
            log(ANSI_YELLOW + count + " Hero Available " + ANSI_RESET);
        log(count + " Heroes Available " + ANSI_RESET);
    }

    /*
    * https://github.com/freva/ascii-table.git
    * */
//    TODO: use the collection feature to have the ability to format the table
//    TODO: change parameter type to String[][]
//    TODO: select hero by id
    public static void showAvailableHeros(List<Hero> heros) {
        log(AsciiTable.getTable(heros, Arrays.asList(
                new Column().header("Hero ID").with(hero -> Integer.toString(hero.getId())),
                new Column().header("Hero Class").with(Hero::getType),
                new Column().header("Hero Name").with(Hero::getName),
                new Column().header("Attack").with(hero -> Integer.toString(hero.getAttack())),
                new Column().header("Defense").with(hero -> Integer.toString(hero.getDefense())),
                new Column().header("Experience").with(hero -> Integer.toString(hero.getExperience())),
                new Column().header("Hit points").with(hero -> Integer.toString(hero.getHitPoints())),
                new Column().header("Level").with(hero -> Integer.toString(hero.getLevel())))));
        log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Select your hero by name" + ANSI_RESET + ANSI_YELLOW + ANSI_RESET);
    }

    public static void goodbye() {
        log(ANSI_YELLOW + " :::Thank you for playing:::" + ANSI_RESET);
        System.exit(1);
    }

    public static void gameOver() {
        log(ANSI_RED + ":::You Lost, do you want to try again? (Y)es or (N)o" + ANSI_RESET);
        ConsoleController.retry();
    }

//    TODO overload this methods
    public static void heroStats(Hero hero, int size) {
        int len = 7;
        int index = 0;
        String[][] data = new String[1][7];
        String[] headers = {"Hero Name", "Hero Class", "Attack", "Defense", "Experience", "Hit points", "Level"};
        for (int i = 0; i < len; i++) {
            data[index][i] = hero.getName();
            data[index][i] = hero.getType();
            data[index][i] = String.valueOf(hero.getAttack());
            data[index][i] = String.valueOf(hero.getDefense());
            data[index][i] = String.valueOf(hero.getExperience());
            data[index][i] = String.valueOf(hero.getHitPoints());
            data[index][i] = String.valueOf(hero.getLevel());
            log(AsciiTable.getTable(headers, data));
        }
    }
}
