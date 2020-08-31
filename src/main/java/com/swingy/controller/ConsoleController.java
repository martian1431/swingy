package com.swingy.controller;

import com.swingy.model.character.CharacterType;
import com.swingy.utils.Colors;
import com.swingy.utils.database.DatabaseWrapper;
import com.swingy.utils.factory.HeroFactory;
import com.swingy.utils.factory.MapFactory;
import com.swingy.view.console.ConsoleView;

import java.util.Scanner;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.log;
import static com.swingy.utils.StaticGlobal.CONSOLE_MODE;
import static com.swingy.utils.StaticGlobal.hero;
import static com.swingy.utils.StaticGlobal.map;

public class ConsoleController {


    public static boolean heroType() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("1") || input.equals("2") || input.equals("3")) {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    ConsoleView.heroNameOption(CharacterType.DEADPOOL);
                    break;
                case 2:
                    ConsoleView.heroNameOption(CharacterType.THOR);
                    break;
                case 3:
                    ConsoleView.heroNameOption(CharacterType.WOLVERINE);
                    break;
                default:
                    return false;
            }
            return true;
        }
//        ConsoleView.menuOptions();
        return false;
    }


//    Refactor TODO fix return statement
    public static void createHero(CharacterType type) {
        Scanner scanner = new Scanner(System.in);
        String heroName = scanner.nextLine();

        /**
         * Check if the name has atleast 2 and atmost 25
         * characters.
         */
        if (heroName.length() >= 2 && heroName.length() < 26) {
            try {
                // Create the character and store it in the database.
                if (!DatabaseWrapper.getInstance().heroExists(heroName)) {
                    DatabaseWrapper.getInstance().insertHero(HeroFactory.newHero(heroName.trim(), type));
                    log(ANSI_CYAN + "Created Hero Named: " + ANSI_YELLOW + heroName);
//                        TODO refactor
                    hero = DatabaseWrapper.getInstance().retrieveHeroData(heroName.trim());
//                    ConsoleView.displayHeroStats(hero);
                    map = MapFactory.generateMap(hero);
                    if (CONSOLE_MODE == true) {
//                        directions();
                        ConsoleView.displayMoveList();
                    }
                } else {
                    log(ANSI_RED + ":::ERROR:::" + heroName + " Hero Exists, Try again!" + ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//                catch (SQLException | IOException | ClassNotFoundException exception) {
//                    exception.printStackTrace();
//                }
        } else {
            log(ANSI_RED + "\":::ERROR::: Name Must Have 2 - 25 Characters, Try Again:" + ANSI_RESET);
        }
    }

//   TODO Refactor
    public static void selectExistingHero(Scanner scanner) {
        try {
            // First check if there are heroes in the database.
            if (DatabaseWrapper.getInstance().numberOfHeroes() > 0) {
                if (DatabaseWrapper.getInstance().numberOfHeroes() == 1) {
                    log(ANSI_YELLOW + DatabaseWrapper.getInstance().numberOfHeroes()
                            + " Hero Available, Choose: " + ANSI_RESET);
                } else {
                    log(ANSI_YELLOW + DatabaseWrapper.getInstance().numberOfHeroes()
                            + " Heroes Available: " + ANSI_RESET);
                }
                // Display all the available heroes in the database.
                DatabaseWrapper.getInstance().retrieveAllHeroes();
            } else {
                log(ANSI_RED + ">>> No Heroes Available!" + ANSI_RESET);
                ConsoleView.menuOptions();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        catch (IOException | ClassNotFoundException | SQLException exception) {
//            exception.printStackTrace();
//        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            try {
                // Check if the specified character name exist in the database,
                // If the character name exist in the database, retrieve the data to character object,
                // And lastly generate the map.
                System.out.println(DatabaseWrapper.getInstance().heroExists(line));
                if (DatabaseWrapper.getInstance().heroExists(line)) {
                    hero = DatabaseWrapper.getInstance().retrieveHeroData(line.trim());
                    map = MapFactory.generateMap(hero);
                    if (CONSOLE_MODE == true) {
//                        directions();
                        ConsoleView.displayMoveList();
                    }
                } else {
                    log(ANSI_RED + ":::ERROR::: Hero Does not Exist, Try Again!!" + ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//            catch (ClassNotFoundException | SQLException | IOException exception) {
//                exception.printStackTrace();
//            }
        }
    }


//    TODO refactor
    public static boolean directions() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

//        ConsoleView.displayMoveList();
//        while (scanner.hasNextLine()) {
        try {
            int direction = Integer.parseInt(input);
            if (direction == 1 || direction == 2 ||
                    direction == 3 || direction == 4) {
                GameController.moveHero(direction);
                GameController.goal();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


//    TODO change name
    public static boolean menuOptionValidation() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("1") || input.equals("2")
                || input.equals("3")) {
            int option = Integer.parseInt(input);
            switch (option) {
                case 1:
                    ConsoleView.heroOptions();
                    break;
                case 2:
                    ConsoleView.existingHero();
                    break;
                case 3:
//                  GameWindow.run();
                    break;
                default:
                    return false;
            }
            return true;
        }
        return false;
    }

    public static void run() {
        ConsoleView.welcomeBanner();
    }

    public static int welcomeOption() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
            return 0;
        } else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
            return 1;

        } else {
            return -1;
        }
    }
}
