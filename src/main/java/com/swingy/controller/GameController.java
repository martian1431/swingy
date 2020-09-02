package com.swingy.controller;

import com.swingy.model.artifact.Armor;
import com.swingy.model.artifact.Helm;
import com.swingy.model.artifact.Weapon;
import com.swingy.model.character.Villian;
import com.swingy.model.character.CharacterType;
import com.swingy.utils.database.DatabaseWrapper;
import com.swingy.utils.factory.HeroFactory;
import com.swingy.utils.factory.MapFactory;
import com.swingy.view.console.ConsoleView;

import java.util.Random;
import java.util.Scanner;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.inputSign;
import static com.swingy.utils.Log.log;
import static com.swingy.utils.StaticGlobal.*;

public class GameController {
    private static int[] previousPosition = new int[2];

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
        if (map.getMap()[hero.getXCoordinate()][hero.getYCoordinate()] == 'X') {
            int random = new Random().nextInt(3);
            if (random == 2) {
                villian = (Villian) HeroFactory.newEnemy(hero, CharacterType.MAGNETO);
            } else {
                villian = (Villian) HeroFactory.newEnemy(hero, CharacterType.ULTRON);
            }
            if (CONSOLE_MODE) {
                action();
            }
        }
    }

    /**
     * When a character moves to a position occupied by
     * an enemy, the character has two options.
     *
     * 1. Fight: {@code fight()}, which engages him in
     * a battle with the enemy.
     *
     * 2. Run: {@code run()},  which gives him a 50% chance
     * of returning to the position. If the odds arent on his side,
     * he must fight the eneny.
     */
    public static void action() {
        Scanner scanner = new Scanner(System.in);

        log(ANSI_YELLOW + "::: You Are Facing: " + villian.getName() + ANSI_RESET);
        ConsoleView.displayActions();
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
                ConsoleView.displayActions();
            }
        }

    }

    /**
     * Engages the Hero in a battle with
     * the enemy. If the character ran and the odds
     * were against him, the enemy will attack first
     * otherwise the character will attack first.
     */
    public static void fight() {
        if (!HERO_RAN) {
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
                DatabaseWrapper.getInstance().updateHero(hero);
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

    /**
     * Gives the character a 50% chance of returning to
     * the previous position. If the odds arent on
     * their side, the character must fight the Enemy.
     */
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

    /**
     * If character wins a battle, he gains:
     * 1. Experience points, based on the enemy power.
     * 2. An artifact, which he can keep or leave.
     * But winning a battle does guarantee that an arifact
     * will be dropped.
     */
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

    /**
     * Equip the character with the chosen artifact.
     * The character wont be equiped if the Experience
     * is chosen instead of an Artifact.
     *
     * @param artifactType The artifact type.
     */
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

    /**
     * The character wins if he reaches one of
     * the borders of the map.
     */
    public static void goal() {
        if (hero.getXCoordinate() == map.getSize() - 1 ||
                hero.getYCoordinate() == map.getSize() - 1 ||
                hero.getXCoordinate() == 0 ||
                hero.getYCoordinate() == 0) {
            log(GREEN_BRIGHT + "::: Congratutations, You Reached Your Goal!" + ANSI_RESET);
            map = MapFactory.generateMap(hero);
            GOAL_REACHED = true;
        } else {
            GOAL_REACHED = false;
        }
    }
}
