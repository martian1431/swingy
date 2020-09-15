package pmalope.controller;

import pmalope.model.GameModel;
import pmalope.model.character.CharacterFactory;
import pmalope.model.character.CharacterType;
import pmalope.utils.Globals;
import pmalope.view.gui.GameInterface;

import java.util.Random;

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
                    gameInterface.showErrorDialog("Please create a hero...\n");
                }
                break;
            case Globals.showCreateScreen:
                gameInterface.showCreateScreen();
                break;
            case Globals.showGameView:
                gameInterface.showGameView(Globals.hero);
        }
    }

    public static void eventHandler(String input, String value) {
        System.out.println("test123");
        switch (input) {
            case Globals.selectHeroById:
                try {
                    Globals.hero = GameModel.getInstance().retrieveHeroData(value);
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
                        Globals.hero = GameModel.getInstance().retrieveHeroData(valueOne.trim());
                        if (Globals.hero != null) {
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
