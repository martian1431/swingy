package com.swingy.controller;

import com.swingy.model.hero.HeroEnum;
import com.swingy.utils.database.DatabaseWrapper;
import com.swingy.utils.factory.HeroFactory;
import com.swingy.utils.factory.MapFactory;
import com.swingy.view.console.ConsoleView;

import java.awt.*;
import java.util.Scanner;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.log;
import static com.swingy.utils.StaticGlobal.CONSOLE_MODE;
import static com.swingy.utils.StaticGlobal.hero;
import static com.swingy.utils.StaticGlobal.map;

public class ConsoleController {

    public static void chooseHeroType() {
        Scanner scanner = new Scanner(System.in);

        log(ANSI_YELLOW + "::: SELECT A HERO TYPE" + ANSI_RESET);
        ConsoleView.displayHeroTypes();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("1") || line.equals("2") || line.equals("3")) {
                Integer choice = Integer.parseInt(line);
                switch (choice) {
                    case 1:
                        createHero(HeroEnum.DEADPOOL);
                        break;
                    case 2:
                        createHero(HeroEnum.THOR);
                        break;
                    case 3:
                        createHero(HeroEnum.WOLVERINE);
                        break;
                }
                break;
            } else {
                log(ANSI_RED + ">>> Hero Type Does Not Exist, Try Again!" + ANSI_RESET);
            }
        }
        ConsoleView.displayMenuChoices();
    }

    public static void createHero(HeroEnum type) {
        Scanner scanner = new Scanner(System.in);

        log(ANSI_YELLOW + "::: Enter The Name Of The Hero");
        while (scanner.hasNextLine()) {
            String heroName = scanner.nextLine();

            /**
             * Check if the name has atleast 2 and atmost 25
             * characters.
             */
            if (heroName.length() >= 2 && heroName.length() < 26) {
                try {
                    // Create the hero and store it in the database.
                    if (!DatabaseWrapper.getInstance().heroExists(heroName)) {
                        DatabaseWrapper.getInstance().insertHero(HeroFactory.newHero(heroName.trim(), type));
                        log(ANSI_CYAN + "Created Hero Named: " + ANSI_YELLOW + heroName);
//                        TODO refactor
                        hero = DatabaseWrapper.getInstance().retrieveHeroData(heroName.trim());
                        ConsoleView.displayHeroStats(hero);
                        map = MapFactory.generateMap(hero);
                        if (CONSOLE_MODE == true) {
                            directions();
                        }
                    } else {
                        log(ANSI_RED + " >>> " + heroName + " Hero Exists, Try again!" + ANSI_RESET);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
//                catch (SQLException | IOException | ClassNotFoundException exception) {
//                    exception.printStackTrace();
//                }
                break;
            } else {
                log(ANSI_RED + ">>> Name Must Have 2 - 25 Characters, Try Again:" + ANSI_RESET);
            }
        }
    }

    public static void selectExistingHero() {
        Scanner scanner = new Scanner(System.in);

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
                ConsoleView.displayMenuChoices();
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
                // Check if the specified hero name exist in the database,
                // If the hero name exist in the database, retrieve the data to hero object,
                // And lastly generate the map.
                System.out.println(DatabaseWrapper.getInstance().heroExists(line));
                if (DatabaseWrapper.getInstance().heroExists(line)) {
                    hero = DatabaseWrapper.getInstance().retrieveHeroData(line.trim());
                    map = MapFactory.generateMap(hero);
                    if (CONSOLE_MODE == true) {
                        directions();
                    }
                } else {
                    log(ANSI_RED + ">>> Hero Does not Exist, Try Again!!" + ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//            catch (ClassNotFoundException | SQLException | IOException exception) {
//                exception.printStackTrace();
//            }
        }
    }

    public static void directions() {
        Scanner scanner = new Scanner(System.in);

        ConsoleView.displayMoveList();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Integer direction = Integer.parseInt(line);
            if (direction == 1 || direction == 2 ||
                    direction == 3 || direction == 4) {
                GameController.moveHero(direction);
                GameController.goal();
            } else {
                log(ANSI_RED + ">>> Incorrect Choice, Try Again!" + ANSI_RESET);
            }
            ConsoleView.displayMoveList();
        }
        scanner.close();
    }
}
