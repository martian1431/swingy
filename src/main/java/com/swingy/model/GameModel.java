package com.swingy.model;

import com.swingy.controller.ConsoleController;
import com.swingy.model.artifact.Armor;
import com.swingy.model.artifact.Helm;
import com.swingy.model.artifact.Weapon;
import com.swingy.model.character.heros.Hero;
import com.swingy.model.character.CharacterType;
import com.swingy.model.character.villian.Villian;
import com.swingy.utils.database.Database;
import com.swingy.model.character.CharacterFactory;
import com.swingy.utils.GridFactory;
import com.swingy.view.console.ConsoleView;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Globals.*;
import static com.swingy.utils.Globals.GOAL_REACHED;
import static com.swingy.utils.Log.inputSign;
import static com.swingy.utils.Log.log;
import static com.swingy.view.console.ConsoleView.goodbye;
import static com.swingy.view.console.ConsoleView.showMainOptions;


//TODO convert to wrapper
public class GameModel {
    private static GameModel instance;
    private static Connection conn = Database.getConnection();
    private static int[] previousPosition = new int[2];

    private GameModel() {}

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public void setupDatabase() {
        createTable();
    }

    private void createTable() {
        String sql = String.format("CREATE TABLE IF NOT EXISTS heroes (\nheroID INTEGER PRIMARY KEY," +
                "\nheroName TEXT NOT NULL UNIQUE ,\nheroClass TEXT NOT NULL ,\nheroLevel INTEGER NOT NULL ," +
                "\nheroExperience INTEGER NOT NULL ,\nheroHP INTEGER NOT NULL ,\nheroAttack INTEGER NOT NULL ," +
                "\nheroDefense INTEGER NOT NULL \n );");

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("table added");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nError: cannot create table");
            System.exit(-1);
        }
    }

    public boolean heroExists(String name) throws SQLException {
        boolean exists = false;
        String sql = "SELECT * FROM heroes WHERE heroName='" + name + "'";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        if (resultSet.next()) {
            exists = true;
        }
        return exists;
    }

    public void insertHero(Hero newHero) throws SQLException {
        String sql = "INSERT INTO heroes (" +
                "heroName, heroClass, " +
                "heroLevel, heroExperience, " +
                "heroHP, heroAttack, heroDefense) " +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newHero.getName());
        pstmt.setString(2, newHero.getType());
        pstmt.setInt(3, newHero.getLevel());
        pstmt.setInt(4, newHero.getExperience());
        pstmt.setInt(5, newHero.getHitPoints());
        pstmt.setInt(6, newHero.getAttack());
        pstmt.setInt(7, newHero.getDefense());
        pstmt.execute();
    }

    public int numberOfHeroes() throws SQLException {
        int rowCount = 0;
        String sql = "SELECT * FROM heroes";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next())
            rowCount++;
        return rowCount;
    }

    public List<Hero> retrieveAllHeroes() throws SQLException {
        String sql = "SELECT * FROM heroes";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        return (parseResult(resultSet));
    }

//    TODO use prepared statement
    public Hero retrieveHeroData(String name) throws SQLException {
        String sql = String.format("SELECT * FROM heroes WHERE heroName='%s'", name);
        Statement stmt = conn.createStatement();
        ResultSet resultSet =  stmt.executeQuery(sql);
        return parseResult(resultSet).get(0);
    }

//    TODO handle resultset validation
    @NotNull
    private List<Hero> parseResult(ResultSet rs) throws SQLException {
//        conn  = Database.getConnection();
        List<Hero> list = new ArrayList<>();
        CharacterType type = null;
        while (rs.next()) {
            String heroType = rs.getString("heroClass").toUpperCase();
            switch (heroType) {
                case "DEADPOOL":
                    type = CharacterType.DEADPOOL;
                    break;
                case "THOR":
                    type = CharacterType.THOR;
                    break;
                case "WOLVERINE":
                    type = CharacterType.WOLVERINE;
                    break;
            }
            assert type != null;
            Hero hero = CharacterFactory.newHero(rs.getString("heroName"), type);
            hero.setId(rs.getInt("heroID"));
            hero.setLevel(rs.getInt("heroLevel"));
            hero.setHitPoints(rs.getInt("heroHP"));
            hero.setExperience(rs.getInt("heroExperience"));
            hero.setAttack(rs.getInt("heroAttack"));
            hero.setDefense(rs.getInt("heroDefense"));
            list.add(hero);
        }
        return list;
    }

//    TODO use prepared statement
    public void updateHero(@NotNull Hero hero) throws SQLException {
        String sql = String.format("UPDATE heroes " +
                "SET heroAttack='%s', " +
                "heroDefense='%s', " +
                "heroExperience='%s', " +
                "heroHP='%s', " +
                "heroLevel='%s' WHERE heroName='%s'", hero.getAttack(), hero.getDefense(), hero.getExperience(), hero.getHitPoints(), hero.getLevel(), hero.getName());
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public List<Hero> retrieveDatabase() {
        return new ArrayList<Hero>();
    }


    //    TODO Static methods
    public static void moveHero(int direction) {
        switch (direction) {
            case 1:
                hero.setPosition(-1, 0);
                previousPosition[0] = -1;
                previousPosition[1] = 0;
                break;
            case 2:
                hero.setPosition(0, 1);
                previousPosition[0] = -1;
                previousPosition[1] = 0;
                break;
            case 3:
                hero.setPosition(0, -1);
                previousPosition[0] = -1;
                previousPosition[1] = 0;
                break;
            case 4:
                hero.setPosition(1, 0);
                previousPosition[0] = -1;
                previousPosition[1] = 0;
                break;
        }
        if (grid.getMap()[hero.getXCoordinate()][hero.getYCoordinate()] == 'X') {
            int random = new Random().nextInt(3);
            if (random == 2) {
                villian = (Villian) CharacterFactory.newEnemy(hero, CharacterType.MAGNETO);
            } else {
                villian = (Villian) CharacterFactory.newEnemy(hero, CharacterType.ULTRON);
            }
            if (CONSOLE_MODE) {
                action();
            }
        }
    }




//    TODO move to base controller
    public static void action() {
        Scanner scanner = new Scanner(System.in);

        log(ANSI_YELLOW + "::: You Are Facing: " + villian.getName() + ANSI_RESET);
        ConsoleView.showActionOption();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            int choice = Integer.parseInt(input);

            if (choice == 1 || choice == 2) {
                switch (choice) {
                    case 1:
                        fight();
                        return;
                    case 2:
                        run();
                        return;
                    default:
                        break;
                }
            } else {
                //                TODO refactor
                log(ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-2). Try Again!\n" + ANSI_RESET);
                inputSign();
//                log("Try again!");
                ConsoleView.showActionOption();
            }
        }

    }


//    TODO move to model base controller
    public static void fight() {
        if (!HERO_RAN) {
//            TODO refactor to model class
            while (hero.getHitPoints() > 0 && villian.getHitPoints() > 0) {
                hero.attack(villian);
                if (villian.getHitPoints() > 0) {
                    villian.attack(hero);
                }
            }
        } else {
            while (hero.getHitPoints() > 0 && villian.getHitPoints() > 0) {
                villian.attack(hero);
                if (hero.getHitPoints() > 0) {
                    hero.attack(villian);
                }
            }
        }
        if (hero.getHitPoints() <= 0) {
            if (CONSOLE_MODE) {
                ConsoleView.gameOver();
//                ConsoleView.run();
            }
        } else {
            try {
                GameModel.getInstance().updateHero(hero);
                hero.setPosition(0, 0);
                battleGains();
                log(ANSI_GREEN + ":::" + "Congratulations, You Won The Battle!" + ANSI_RESET);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//            catch (ClassNotFoundException | SQLException | IOException e) {
//                e.printStackTrace();
//            }
        }
    }

//    TODO move to base controller
    public static void run() {
        int chance = new Random().nextInt(2);

        if (chance == 1) {
            log(ANSI_YELLOW + ":::Hahaha, You Can't Run My Friend, We Gonna Fight!" + ANSI_RESET);
            HERO_RAN = true;
            fight();
        } else {
            HERO_RAN = false;
            log(ANSI_RED + ":::Coward! You Ran Away!" + ANSI_RESET);
            hero.setPosition(previousPosition[0] * -1, previousPosition[1] * -1);
        }
    }


//    TODO move to model
    private static void battleGains() {
        int drop = new Random().nextInt(2);
        boolean artifactIsDropped = drop == 1;

        if (artifactIsDropped) {
            ARTIFACT_DROPPED = true;
            try {
                log(ANSI_YELLOW + "::: Artifact is Dropped!");
                String[] artifacts = {"ARMOR", "HELM", "WEAPON", "EXPERIENCE"};
                String artifactType = artifacts[new Random().nextInt(4)];
                int variety = hero.getLevel() + 1;

                if ("ARMOR".equals(artifactType)) {
                    artifact = new Armor("Dropped Armor", variety);
                    int gainedDefense = (((Armor) artifact).getDefense() - hero.getArmor().getDefense());
                    log(ANSI_YELLOW + "::: If You Keep This Artifact Your Defense Increases by " + gainedDefense + ".");
                } else if ("HELM".equals(artifactType)) {
                    artifact = new Helm("Dropped Helmet", variety);
                    int gainedHitPoints = (((Helm) artifact).getHitPoints() - hero.getHelm().getHitPoints());
                    log(ANSI_YELLOW + "::: If You Keep This Artifact Your Hit Point(s) Increase by " + gainedHitPoints + ".");
                } else if ("WEAPON".equals(artifactType)) {
                    artifact = new Weapon("Dropped Weapon", variety);
                    int gainedAttack = (((Weapon) artifact).getAttack() - hero.getWeapon().getAttack());
                    log(ANSI_YELLOW + "::: If You Keep This Artifact Your Attack Increases by " + gainedAttack + ".");
                } else if ("EXPERIENCE".equals(artifactType)) {
                    hero.setHitPoints(hero.getHitPoints() + variety);
                    log(ANSI_YELLOW  + "::: Healed Up, Current Health: " + hero.getHitPoints());
                    return;
                }
                // Equip the character.
                equip(artifactType);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (!artifactIsDropped) {
            log( ANSI_RED + ":::Sorry, No Artifact Dropped!");
        }
    }


//    TODO move to model
    private static void equip(String artifactType) {
        if (CONSOLE_MODE) {
            Scanner scanner = new Scanner(System.in);
            log(ANSI_YELLOW + "::: Do You Wanna Keep The Artifact?\n1. YES!\n2. NO!" + ANSI_RESET);
            inputSign();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equals("1") || input.equals("2")) {
                    int choice = Integer.parseInt(input.trim());
                    if (choice == 1) {
                        hero.equipHero(artifact, artifact.getType());
                        log("::: " + hero.getName() + " Is Equipped With " + artifactType);
                        break;
                    } else if (choice == 2) {
                        break;
                    }
                } else {
                    //                TODO refactor
                    log(ANSI_RED + ":::ERROR::: Incorrect Choice, Try Again!" + ANSI_RESET);
                    inputSign();
                }
            }
        }
    }


//    TODO move to model
    public static void goal() {
        if (hero.getXCoordinate() == grid.getSize() - 1 ||
                hero.getYCoordinate() == grid.getSize() - 1 ||
                hero.getXCoordinate() == 0 ||
                hero.getYCoordinate() == 0) {
//
//            TODO factor
//            ConsoleView.nextMission();
            log(ANSI_GREEN + "::: Congratutations, You Reached Your Goal! Do you want to continue? (Y)es (N)o" + ANSI_RESET);
            Scanner scanner = ConsoleController.getScanner();
            while(scanner.hasNextLine()) {
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("y") || input.equals("yes")) {
                    grid = GridFactory.generateMap(hero);
                    break;
                } else if (input.equals("n") || input.equals("no")) {
                    goodbye();
                    System.exit(-1);
                } else {
                    //               TODO refactor
                    log(ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!!!" + ANSI_RESET);
                    inputSign();
                }
            }
            GOAL_REACHED = true;
        } else {
            GOAL_REACHED = false;
        }
    }
}
