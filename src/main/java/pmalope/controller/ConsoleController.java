package pmalope.controller;

import pmalope.model.character.CharacterType;
import pmalope.model.character.heros.Hero;
import pmalope.model.GameModel;
import pmalope.model.character.CharacterFactory;
import pmalope.utils.GridFactory;
import pmalope.view.console.ConsoleView;
import org.jetbrains.annotations.NotNull;
import pmalope.utils.Colors;
import pmalope.utils.Globals;
import pmalope.utils.Log;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    public static void selectHeroType() {
        Scanner scanner = getScanner();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
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
                Log.log(Colors.ANSI_RED + ":::ERROR::: Hero Type Does Not Exist, Try Again!" + Colors.ANSI_RESET);
                Log.inputSign();
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
                        Globals.hero = GameModel.getInstance().retrieveHeroData(input.trim());
                        Globals.grid = GridFactory.generateMap(Globals.hero);
                        ConsoleView.showSelectedHero(Globals.hero, Globals.grid.getSize());
                        startMission();
                    } else {
                        //                TODO refactor
                        Log.log(Colors.ANSI_RED + " :::ERROR::: " + input + " Hero Exists, Try again!" + Colors.ANSI_RESET);
                        Log.inputSign();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
//                break;
            } else if (input.length() < 3 || input.length() > 20) {
                //                TODO refactor
                Log.log(Colors.ANSI_RED + ":::ERROR::: Name Must Have 2 - 20 Characters, Try Again!" + Colors.ANSI_RESET);
                Log.inputSign();
            }
        }
        scanner.close();
    }

//   TODO Refactor
    public static void selectExistingHero() {
        try {
            int count = GameModel.getInstance().numberOfHeroes();
            if (count > 0) {
                ConsoleView.heroCount(count);
                List<Hero> heros = GameModel.getInstance().retrieveAllHeroes();
                ConsoleView.showAvailableHeros(heros);
            } else {
                //                TODO refactor
                Log.log(Colors.ANSI_RED + ":::ERROR::: No Heroes Available!" + Colors.ANSI_RESET);
                Log.inputSign();
                ConsoleView.showStartScreen();
            }
        } catch (SQLException exception) {
            Log.log(Colors.ANSI_RED + ":::ERROR::: No Heroes Available!" + Colors.ANSI_RESET);
            Log.inputSign();
        }
        selectedHero();
    }

    private static void selectedHero() {
        Scanner scanner = getScanner();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                if (GameModel.getInstance().heroExists(input.trim())) {
                    Log.log(Colors.ANSI_YELLOW + ":::" + Colors.ANSI_RESET + Colors.CYAN_BOLD_BRIGHT + "Let's play" + Colors.ANSI_RESET + Colors.ANSI_YELLOW  + Colors.ANSI_RESET);
                    Globals.hero = GameModel.getInstance().retrieveHeroData(input);
                    Globals.grid = GridFactory.generateMap(Globals.hero);
                    ConsoleView.showSelectedHero(Globals.hero, Globals.grid.getSize());

                    startMission();
                } else {
                    Log.log(Colors.ANSI_RED + ":::ERROR::: Hero Does not Exist, Try Again!!" + Colors.ANSI_RESET);
                    Log.inputSign();
                }
            } catch (SQLException exception) {
                Log.log(Colors.ANSI_RED + ":::ERROR::: Something went wrong, please try again" + Colors.ANSI_RESET);
                Log.inputSign();
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
                    GameModel.moveHero(direction);
                    GameModel.goal();
                    ConsoleView.showSelectedHero(Globals.hero, Globals.grid.getSize());
                    ConsoleView.showDirectionOptions();
                } else if (direction == 5){
                    ConsoleView.goodbye();
                } else {
                    //                TODO refactor
                    Log.log(Colors.ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-4). Try Again!" + Colors.ANSI_RESET);
                    Log.inputSign();
                }
            } catch (Exception e) {
                //                TODO refactor
                Log.log(Colors.ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-4). Try Again!" + Colors.ANSI_RESET);
                Log.inputSign();
            }
        }
        scanner.close();
    }

    public static void startGame() {
        Scanner scanner = getScanner();

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                ConsoleView.showStartScreen();
            } else if (input.equals("n") || input.equals("no")) {
                Log.log(Colors.ANSI_RED + ":::" + "SCARED?::: Go drink some water and try again later" + Colors.ANSI_RESET);
                System.exit(-1);
            } else {
            //               TODO refactor
                Log.log(Colors.ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!!!" + Colors.ANSI_RESET);
                Log.inputSign();
            }

        }
        scanner.close();
    }

    @NotNull
    public static Scanner getScanner() {
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
                        ConsoleView.showCreateScreen();
                        break;
                    case 2:
                        ConsoleView.showSelectScreen();
                        break;
                    case 3:
                        ConsoleView.goodbye();
                        break;
                }
            } else {
                Log.log(Colors.ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-3). Try Again!" + Colors.ANSI_RESET);
                Log.inputSign();
            }
        }
        scanner.close();
    }

    public static void retry() {
        Scanner scanner = getScanner();

        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
                ConsoleView.showStartScreen();
            } else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")){
               ConsoleView.goodbye();
            } else {
                Log.log(Colors.ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!!!" + Colors.ANSI_RESET);
                Log.inputSign();
            }
        }
    }
}