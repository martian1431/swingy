package com.swingy.controller;

import com.swingy.model.GameModel;
import com.swingy.utils.Globals;
import com.swingy.view.gui.GameInterface;

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
                    e.printStackTrace();
                }
                break;
            case Globals.showCreateScreen:
                gameInterface.showCreateScreen();
                break;
        }
    }

    public static void eventHandler(String input, String value) {

    }
}
