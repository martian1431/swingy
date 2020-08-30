package com.swingy.view.console;

import com.swingy.controller.ConsoleController;
import com.swingy.utils.Logo;

import java.util.Scanner;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.log;
import static com.swingy.utils.StaticGlobal.DISPLAY_LOGO;

public class ConsoleView {

    /** Display menu choices after launching the game. */
    public static void displayMenuChoices() {
        log(ANSI_RED + "1." + ANSI_CYAN + " Create A New Hero." + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " Select A Hero." + ANSI_RESET);
        log(ANSI_RED + "3." + ANSI_CYAN + " Switch To GUI view." + ANSI_RESET);
    }

    /** Display all validtypes of heroes. */
    public static void displayHeroTypes() {

        log(ANSI_RED + "1." + ANSI_CYAN + " Deadpool" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " Thor" + ANSI_RESET);
        log(ANSI_RED + "3." + ANSI_CYAN + " Wolverine" + ANSI_RESET);
    }

    /** The menu with cool swingy logo :). */
    public static void menu() {
        if (DISPLAY_LOGO == true) {
            Logo.displayLogo();
        }
        log("");
        log(ANSI_YELLOW + "::: SELECT YOUR CHOICE");
        displayMenuChoices();
        DISPLAY_LOGO = false;
    }

    /** Display Directions or move list. */
    public static void displayMoveList() {
        log(ANSI_YELLOW + "::: Move");
        log(ANSI_RED + "1." + ANSI_CYAN + " North" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " East" + ANSI_RESET);
        log(ANSI_RED + "3." + ANSI_CYAN + " South" + ANSI_RESET);
        log(ANSI_RED + "4." + ANSI_CYAN + " West" + ANSI_RESET);
    }

    /** Display Actions i.e fight or run. */
    public static void displayActions() {
        log(ANSI_YELLOW + "::: Action");
        log(ANSI_RED + "1." + ANSI_CYAN + " Fight" + ANSI_RESET);
        log(ANSI_RED + "2." + ANSI_CYAN + " Run" + ANSI_RESET);
    }

    public static void run() {
        menu();
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("1") || line.equals("2")
                    || line.equals("3")) {
                Integer choice = Integer.parseInt(line);
                switch (choice) {
                    case 1:
                        ConsoleController.chooseHeroType();
                        break;
                    case 2:
                        ConsoleController.selectExistingHero();
                        break;
                    case 3:
//                        GameWindow.run();
                        break;
                }
            } else {
                log(ANSI_RED + " >>> Incorrect Choice, Try Again!");
            }
        }
    }
}
