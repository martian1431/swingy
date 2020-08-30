package com.swingy.view.console;

import com.swingy.controller.ConsoleController;
import com.swingy.model.character.Hero;
import com.swingy.utils.Colors;
import com.swingy.utils.Logo;

import java.util.Scanner;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.log;

public class ConsoleView {

    /** Display menu choices after launching the game. */
    public static void menuOptions() {
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        do {
            log(Colors.ANSI_YELLOW + "\t:::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + " SELECT YOUR CHOICE " + Colors.ANSI_RESET + Colors.ANSI_YELLOW + ":::\n" + Colors.ANSI_RESET);
            log(ANSI_CYAN + "\t1." + ANSI_RESET + " Create A New Hero.");
            log(ANSI_CYAN + "\t2." + ANSI_RESET + " Select A Hero.");
            log(ANSI_CYAN + "\t3." + ANSI_RESET + " Switch To GUI view.");

//            TODO move to Console Controller
            String input = scanner.nextLine().trim();

            if (ConsoleController.menuOptionValidation(input)) {
                System.out.println("test");
                validInput = true;
            } else {
                log(ANSI_RED + "\t::: ERROR ::: Invalid input, please choose between (1-3)" + ANSI_RESET);
                log("");
            }


//            TODO delete
//            if (input.equals("1") || input.equals("2")
//                    || input.equals("3")) {
//                validInput = true;
//                int option = Integer.parseInt(input);
//                ConsoleController.chooseHeroType();
//                switch (option) {
//                    case 1:
//                        ConsoleController.heroType();
//                        break;
//                    case 2:
//                        ConsoleController.selectExistingHero();
//                        break;
//                    case 3:
////                        GameWindow.run();
//                        break;
//                    default:
////                        TODO handle exection
//                        break;
//                }
//            } else {
//                log(ANSI_RED + "\t::: ERROR ::: Invalid input, please choose between (1-3)" + ANSI_RESET);
//                log("");
//            }
        } while (!validInput);
    }

//    public static void createHeroOptions() {
//        boolean validInput = false;
//        Scanner scanner = new Scanner(System.in);
//
//        do {
//            log(ANSI_YELLOW + "::: SELECT A HERO TYPE" + ANSI_RESET);
//
//        } while (!validInput);
//    }

    private static void menuOptions(String input) {
    }

    /** Display all validtypes of heroes. */
    public static void heroOptions() {
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        log(ANSI_YELLOW + "::: SELECT A HERO TYPE" + ANSI_RESET);
        log(ANSI_CYAN + "\t1." + ANSI_RESET + " Deadpool");
        log(ANSI_CYAN + "\t2." + ANSI_RESET + " Thor");
        log(ANSI_CYAN + "\t3." + ANSI_RESET + " Wolverine");
        do {

            String input = scanner.nextLine().trim();
            if (ConsoleController.heroType(input)) {
                System.out.println("test");
                validInput = true;
            } else {
                log(ANSI_RED + "\t::: ERROR ::: Hero Type Does Not Exist, Try Again!" + ANSI_RESET);
            }

//            TODO delete
//            if (input.equals("1") || input.equals("2") || input.equals("3")) {
//                int opt = Integer.parseInt(input);
//                switch (opt) {
//                    case 1:
//                        createHero(CharacterType.DEADPOOL);
//                        break;
//                    case 2:
//                        createHero(CharacterType.THOR);
//                        break;
//                    case 3:
//                        createHero(CharacterType.WOLVERINE);
//                        break;
//                }
//                break;
//            } else {
//                log(ANSI_RED + ">>> Hero Type Does Not Exist, Try Again!" + ANSI_RESET);
//            }
        } while (!validInput);
    }

//    TODO overide
    /** The menu with cool swingy logo :). */
    public static void welcomeBanner() {
        String play = "";
        Scanner scanner = new Scanner(System.in);

        Logo.displayLogo();
        log(ANSI_YELLOW + "\t::: " + "Are ready to go down the Rabit hole? (Y)es or (N)o" + " :::" + ANSI_RESET);
//        TODO use do while
        while (scanner.hasNextLine()) {
            System.out.print("  ");
            play = scanner.nextLine().trim();

            if (play.toLowerCase().equals("y") || play.toLowerCase().equals("yes")) {
                break;
            } else if (play.toLowerCase().equals("n") || play.toLowerCase().equals("no")) {
//                TODO add sad emojis/symbol
                log(ANSI_RED + "\t::: " + "You scared? Go drink some water and try again later" + " ::::" + ANSI_RESET);
                System.exit(-1);
            } else {
                log(ANSI_RED + "\t::: ERROR ::: Expected input (Y)es or (N)o" + ANSI_RESET);
            }

        }
        menuOptions();
//        TODO delete
//        if (DISPLAY_LOGO == true) {
//            Logo.displayLogo();
//        }
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

//    TODO overide
    public static void run() {
        welcomeBanner(); // TODO refactor to promt.WelcomeBanner

//        TODO to be moved
//        Scanner scanner = new Scanner(System.in);
//
//        while(scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            if (line.equals("1") || line.equals("2")
//                    || line.equals("3")) {
//                Integer choice = Integer.parseInt(line);
//                switch (choice) {
//                    case 1:
//                        ConsoleController.chooseHeroType();
//                        break;
//                    case 2:
//                        ConsoleController.selectExistingHero();
//                        break;
//                    case 3:
////                        GameWindow.run();
//                        break;
//                }
//            } else {
//                log(ANSI_RED + " >>> Incorrect Choice, Try Again!");
//            }
//        }
    }

    public static void displayHeroStats(Hero hero) {
        System.out.println(hero.getType());

    }

    public static void existingHero() {
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

//        String input = scanner.nextLine().trim();
        ConsoleController.selectExistingHero(scanner);
    }
}
