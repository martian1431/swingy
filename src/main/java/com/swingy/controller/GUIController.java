package com.swingy.controller;

import com.swingy.model.GameModel;
import com.swingy.model.character.CharacterFactory;
import com.swingy.model.character.CharacterType;
import com.swingy.utils.Globals;
import com.swingy.view.gui.GameInterface;

import java.util.Random;

import static com.swingy.utils.Globals.hero;

public class GUIController {
    static GameModel model;
    static GameInterface gameInterface;
    static Random random = new Random();


    public GUIController(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
        gameInterface.setEnableStartButton(true);
        gameInterface.clearLoadingLabel();

    }

    public static void eventHandler(String event) {
        switch (event) {
            case Globals.showStartScreen:
                gameInterface.showStartScreen();
                break;
            case Globals.showSelect:
                try {
                    gameInterface.showSelectScreen(GameModel.getInstance().retrieveAllHeroes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Globals.showCreateScreen:
                gameInterface.showCreateScreen();
                break;
            case Globals.showGameView:
                gameInterface.showGameView(hero);
//                System.out.println(hero.getName());
//                gameInterface.showGameView();
        }
    }

    public static void eventHandler(String input, String value) {
        switch (input) {
            case Globals.selectHeroById:
                try {
                    hero = GameModel.getInstance().retrieveHeroData(value);
                    eventHandler(Globals.showGameView);
                } catch (Exception e) {
                    gameInterface.showErrorDialog(e.getMessage());
                }
        }
    }

    public static void eventHandler(String input, String valueOne, String valueTwo) {
        switch (input) {
            case Globals.createNewHero:
                try {
                    if (valueOne.isEmpty()) {
                      gameInterface.showErrorDialog("Input cannot be empty");
                    } else if (valueOne.length() < 2 || valueOne.length() > 20) {
                        gameInterface.showErrorDialog("Name can only 2 or 20 characters long");
                    } else if (!GameModel.getInstance().heroExists(valueOne.trim())) {
                        GameModel.getInstance().insertHero(CharacterFactory.newHero(valueOne.trim(), heroType(valueTwo)));
                        hero = GameModel.getInstance().retrieveHeroData(valueOne.trim());
                        if (hero != null) {
                            eventHandler(Globals.showGameView);
                        }
                        else {
                            gameInterface.showErrorDialog("Hero was not initialized.\n");
                        }
                    }
                    else {
                        gameInterface.showErrorDialog("Hero name already exits.\n");
                    }
                }
                catch (Exception e) {
                    gameInterface.showErrorDialog(e.getMessage());
                }
                break;
        }
    }

    public static CharacterType heroType(String type) {
        CharacterType t = null;
        switch (type) {
            case "Deadpool":
                t = CharacterType.DEADPOOL;
                break;
            case "Thor":
                t = CharacterType.THOR;
                break;
            case "Wolverine":
                t = CharacterType.WOLVERINE;
                break;
        }
        return t;
    }
}
