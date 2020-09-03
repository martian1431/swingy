package com.swingy.controller;

import com.swingy.model.character.CharacterType;
import com.swingy.model.character.Hero;
import com.swingy.utils.database.DatabaseWrapper;
import com.swingy.utils.factory.HeroFactory;
import com.swingy.utils.factory.MapFactory;
import com.swingy.view.console.ConsoleView;

import java.util.List;
import java.util.Scanner;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.inputSign;
import static com.swingy.utils.Log.log;
import static com.swingy.utils.StaticGlobal.hero;
import static com.swingy.utils.StaticGlobal.map;

public class ConsoleController {


    public static void selectHeroType() {
        Scanner scanner = getScanner();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        ConsoleView.showHeroNameOption(CharacterType.DEADPOOL);
                        break;
                    case 2:
                        ConsoleView.showHeroNameOption(CharacterType.THOR);
                        break;
                    case 3:
                        ConsoleView.showHeroNameOption(CharacterType.WOLVERINE);
                        break;
                    case 4:
                        ConsoleView.goodbye();
                        break;
                }
                break;
            } else {
//                TODO refactor
                log(ANSI_RED + ":::ERROR::: Hero Type Does Not Exist, Try Again!" + ANSI_RESET);
                inputSign();
            }
        }
        scanner.close();
    }


//    Refactor TODO fix return statement
    public static void createHero(CharacterType type) {
        Scanner scanner = getScanner();

        /**
         * Check if the name has atleast 2 and atmost 10
         * characters.
         */
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            /**
             * Check if the name has atleast 2 and atmost 25
             * characters.
             */
            if (input.length() >= 2 && input.length() < 10) {
                try {
                    // Create the hero and store it in the database.
                    if (!DatabaseWrapper.getInstance().heroExists(input)) {
                        DatabaseWrapper.getInstance().insertHero(HeroFactory.newHero(input.trim(), type));
//                        log(ANSI_CYAN + "Created Hero Named: " + ANSI_YELLOW + input + ANSI_RESET);
                        hero = DatabaseWrapper.getInstance().retrieveHeroData(input.trim());
                        map = MapFactory.generateMap(hero);
                        ConsoleView.selectedHero(hero, map.getSize());
                        directions();
//                        ConsoleView.displayMoveList();

                    } else {
                        //                TODO refactor
                        log(ANSI_RED + " :::ERROR::: " + input + " Hero Exists, Try again!" + ANSI_RESET);
                        inputSign();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
//                catch (SQLException | IOException | ClassNotFoundException exception) {
//                    exception.printStackTrace();
//                }
//                break;
            } else if (input.length() < 3 || input.length() > 10) {
                //                TODO refactor
                log(ANSI_RED + ":::ERROR::: Name Must Have 2 - 10 Characters, Try Again!" + ANSI_RESET);
                inputSign();
            }
        }
        scanner.close();
    }

//   TODO Refactor
    public static void selectExistingHero() {
        Scanner scanner = getScanner();
        try {
            // First check if there are heroes in the database.
            int count = DatabaseWrapper.getInstance().numberOfHeroes();
            if (count > 0) {
                ConsoleView.heroCount(count);
                // Display all the available heroes in the database.
                List<Hero> heros = DatabaseWrapper.getInstance().retrieveAllHeroes();
                ConsoleView.availableHeros(heros);
            } else {
                //                TODO refactor
                log(ANSI_RED + ":::ERROR::: No Heroes Available!" + ANSI_RESET);
                inputSign();
                ConsoleView.showMainOptions();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        catch (IOException | ClassNotFoundException | SQLException exception) {
//            exception.printStackTrace();
//        }
//        TODO refactor
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                // Check if the specified character name exist in the database,
                // If the character name exist in the database, retrieve the data to character object,
                // And lastly generate the map.
                if (DatabaseWrapper.getInstance().heroExists(input.trim())) {
//                    TODO: refactor
                    log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Let's play" + ANSI_RESET + ANSI_YELLOW  + ANSI_RESET);
                    hero = DatabaseWrapper.getInstance().retrieveHeroData(input);
                    map = MapFactory.generateMap(hero);
                    ConsoleView.selectedHero(hero, map.getSize());
//                    ConsoleView.heroStats(hero, map.getSize());
                    directions();
                } else {
                    //                TODO refactor
                    log(ANSI_RED + ":::ERROR::: Hero Does not Exist, Try Again!!" + ANSI_RESET);
                    inputSign();
                }
            } catch (Exception e) {
                //                TODO refactor
                log(ANSI_RED + ":::ERROR::: Something went wrong, please try again" + ANSI_RESET);
                inputSign();
            }
//            catch (ClassNotFoundException | SQLException | IOException exception) {
//                exception.printStackTrace();
//            }
        }
    }

//    TODO refactor
    public static void directions() {
        Scanner scanner = getScanner();

        ConsoleView.displayMoveList();
        while (scanner.hasNextLine()) {
            try {
                String line = scanner.nextLine();
                int direction = Integer.parseInt(line);
                if (direction == 1 || direction == 2 ||
                        direction == 3 || direction == 4) {
                    GameController.moveHero(direction);
                    GameController.goal();
                    ConsoleView.selectedHero(hero, map.getSize());
                    ConsoleView.displayMoveList();
                } else if (direction == 5){
                    ConsoleView.goodbye();
                } else {
                    //                TODO refactor
                    log(ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-4). Try Again!" + ANSI_RESET);
                    inputSign();
                }
            } catch (Exception e) {
                //                TODO refactor
                log(ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-4). Try Again!" + ANSI_RESET);
                inputSign();
            }
        }
        scanner.close();
    }

//    public static void run() {
//        ConsoleView.welcomeBanner();
//    }

    public static int welcomeOption() {
        Scanner scanner = getScanner();
        String input = scanner.nextLine().trim();

        if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
            return 0;
        } else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
            return 1;

        } else {
            return -1;
        }
    }

    public static void startGame() {
        Scanner scanner = getScanner();

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
                ConsoleView.showMainOptions();
            }

            if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
                log(ANSI_RED + ":::" + "SCARED?::: Go drink some water and try again later" + ANSI_RESET);
                System.exit(-1);
            }
            //                TODO refactor
            log(ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!!!" + ANSI_RESET);
            inputSign();
        }
        scanner.close();
    }

    private static Scanner getScanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("$ ");
        return scanner;
    }

    public static void selectMainOption() {
        Scanner scanner = getScanner();

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2")
                    || input.equals("3")) {
                int option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        ConsoleView.showHeroType();
                        break;
                    case 2:
                        ConsoleView.existingHero();
                        break;
                    case 3:
//                        TODO: goodbye message
                        ConsoleView.goodbye();
                        break;
                }
            } else {
                //                TODO refactor
                log(ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-3). Try Again!" + ANSI_RESET);
                inputSign();
            }
        }
        scanner.close();
    }

    public static void retry() {
        Scanner scanner = getScanner();

        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
//                todo
                ConsoleView.showMainOptions();
            } else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")){
//                todo
               ConsoleView.goodbye();
            } else {
                log(ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!!!" + ANSI_RESET);
                inputSign();
            }
        }
    }
}
