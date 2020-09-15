package pmalope.controller;

import pmalope.model.GameModel;
import pmalope.model.character.CharacterFactory;
import pmalope.model.character.CharacterType;
import pmalope.utils.Globals;
import pmalope.view.ViewInterface;

public class GUIController {
    static ViewInterface viewInterface;


    public GUIController(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        viewInterface.setEnableStartButton(true);
        viewInterface.clearLoadingLabel();

    }

    public static void eventHandler(String event) {
        switch (event) {
            case Globals.showStartScreen:
                viewInterface.showStartScreen();
                break;
            case Globals.showSelect:
                try {
                    viewInterface.showSelectScreen(GameModel.getInstance().retrieveAllHeroes());
                } catch (Exception e) {
                    viewInterface.showErrorDialog("Please create a hero...\n");
                }
                break;
            case Globals.showCreateScreen:
                viewInterface.showCreateScreen();
                break;
            case Globals.showGameView:
                viewInterface.showGameView(Globals.hero);
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
                    viewInterface.showErrorDialog(e.getMessage());
                }
        }
    }

    public static void eventHandler(String input, String valueOne, String valueTwo) {
        switch (input) {
            case Globals.createNewHero:
                try {
                    if (valueOne.isEmpty()) {
                      viewInterface.showErrorDialog("Input cannot be empty");
                    } else if (valueOne.length() < 2 || valueOne.length() > 20) {
                        viewInterface.showErrorDialog("Name can only 2 or 20 characters long");
                    } else if (!GameModel.getInstance().heroExists(valueOne.trim())) {
                        GameModel.getInstance().insertHero(CharacterFactory.newHero(valueOne.trim(), heroType(valueTwo)));
                        Globals.hero = GameModel.getInstance().retrieveHeroData(valueOne.trim());
                        if (Globals.hero != null) {
                            eventHandler(Globals.showGameView);
                        }
                        else {
                            viewInterface.showErrorDialog("Hero was not initialized.\n");
                        }
                    }
                    else {
                        viewInterface.showErrorDialog("Hero name already exits.\n");
                    }
                }
                catch (Exception e) {
                    viewInterface.showErrorDialog(e.getMessage());
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
