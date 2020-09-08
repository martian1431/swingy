package com.swingy.controller;

import com.swingy.model.character.CharacterType;
import com.swingy.model.character.heros.Hero;
import com.swingy.model.GameModel;
import com.swingy.model.character.CharacterFactory;
import com.swingy.utils.GridFactory;
import com.swingy.view.console.ConsoleView;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static com.swingy.model.GameModel.goal;
import static com.swingy.model.GameModel.moveHero;
import static com.swingy.model.character.CharacterType.*;
import static com.swingy.utils.Colors.*;
import static com.swingy.utils.GridFactory.generateMap;
import static com.swingy.utils.Log.inputSign;
import static com.swingy.utils.Log.log;
import static com.swingy.utils.Globals.*;
import static com.swingy.view.console.ConsoleView.*;

public class ConsoleController {

    public static void selectHeroType() {
        Scanner scanner = getScanner();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        showHeroNameOption(DEADPOOL);
                        break;
                    case 2:
                        showHeroNameOption(THOR);
                        break;
                    case 3:
                        showHeroNameOption(WOLVERINE);
                        break;
                    case 4:
                        goodbye();
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


    public static void createHero(CharacterType type) {
        Scanner scanner = getScanner();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            if (input.length() >= 2 && input.length() < 20) {
                try {
                    // Create the hero and store it in the database.
                    if (!GameModel.getInstance().heroExists(input)) {
                        GameModel.getInstance().insertHero(CharacterFactory.newHero(input.trim(), type));
                        hero = GameModel.getInstance().retrieveHeroData(input.trim());
                        grid = generateMap(hero);
                        showSelectedHero(hero, grid.getSize());
                        startMission();
//                        ConsoleView.displayMoveList();

                    } else {
                        //                TODO refactor
                        log(ANSI_RED + " :::ERROR::: " + input + " Hero Exists, Try again!" + ANSI_RESET);
                        inputSign();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
//                break;
            } else if (input.length() < 3 || input.length() > 20) {
                //                TODO refactor
                log(ANSI_RED + ":::ERROR::: Name Must Have 2 - 20 Characters, Try Again!" + ANSI_RESET);
                inputSign();
            }
        }
        scanner.close();
    }

//   TODO Refactor
    public static void selectExistingHero() {
        try {
            int count = GameModel.getInstance().numberOfHeroes();
            if (count > 0) {
                heroCount(count);
                List<Hero> heros = GameModel.getInstance().retrieveAllHeroes();
                ConsoleView.showAvailableHeros(heros);
            } else {
                //                TODO refactor
                log(ANSI_RED + ":::ERROR::: No Heroes Available!" + ANSI_RESET);
                inputSign();
                showMainOptions();
            }
        } catch (SQLException exception) {
            log(ANSI_RED + ":::ERROR::: No Heroes Available!" + ANSI_RESET);
            inputSign();
        }
        selectedHero();
    }

    private static void selectedHero() {
        Scanner scanner = getScanner();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                if (GameModel.getInstance().heroExists(input.trim())) {
                    log(ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "Let's play" + ANSI_RESET + ANSI_YELLOW  + ANSI_RESET);
                    hero = GameModel.getInstance().retrieveHeroData(input);
                    grid = generateMap(hero);
                    showSelectedHero(hero, grid.getSize());

                    startMission();
                } else {
                    log(ANSI_RED + ":::ERROR::: Hero Does not Exist, Try Again!!" + ANSI_RESET);
                    inputSign();
                }
            } catch (SQLException exception) {
                log(ANSI_RED + ":::ERROR::: Something went wrong, please try again" + ANSI_RESET);
                inputSign();
            }
        }
    }

    //    TODO refactor
    public static void startMission() {
        Scanner scanner = getScanner();

        ConsoleView.showDirectionOptions();
        while (scanner.hasNextLine()) {
            try {
                String line = scanner.nextLine().trim();
                int direction = Integer.parseInt(line);
                if (direction == 1 || direction == 2 ||
                        direction == 3 || direction == 4) {
                    moveHero(direction);
                    goal();
                    showSelectedHero(hero, grid.getSize());
                    showDirectionOptions();
                } else if (direction == 5){
                    goodbye();
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

    public static void startGame() {
        Scanner scanner = getScanner();

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                showMainOptions();
            } else if (input.equals("n") || input.equals("no")) {
                log(ANSI_RED + ":::" + "SCARED?::: Go drink some water and try again later" + ANSI_RESET);
                System.exit(-1);
            } else {
            //               TODO refactor
                log(ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!!!" + ANSI_RESET);
                inputSign();
            }

        }
        scanner.close();
    }

    @NotNull
    private static Scanner getScanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("$ ");
        return scanner;
    }

    public static void selectMainOption() {
        Scanner scanner = getScanner();

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals("1") || input.equals("2")
                    || input.trim().equals("3")) {
                int option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        showHeroType();
                        break;
                    case 2:
                        showExistingHero();
                        break;
                    case 3:
                        goodbye();
                        break;
                }
            } else {
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
                showMainOptions();
            } else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")){
               goodbye();
            } else {
                log(ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!!!" + ANSI_RESET);
                inputSign();
            }
        }
    }
}