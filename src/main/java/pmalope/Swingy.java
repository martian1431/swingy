package com.swingy;

import com.swingy.controller.GUIController;
import com.swingy.model.GameModel;
import com.swingy.utils.Globals;
import com.swingy.view.console.ConsoleView;
import com.swingy.view.gui.GUIView;
import com.swingy.view.gui.GameView;

import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.log;

public class Swingy {
    public static void main(String[] args) {
        try {
            String view = args[0];
            GameModel.getInstance().setupDatabase();
            if (view.toLowerCase().equals("console")) {
                ConsoleView.run();
            } else if (view.toLowerCase().equals("gui")) {
                Globals.CONSOLE_MODE = false;
                GUIView guiView = new GUIView();
                GUIController guiController = new GUIController(guiView);
//                GameView.run();
            } else {
                log(ANSI_RED + ":::ERROR::: Invalid argument" + ANSI_RESET);
            }
        } catch (Exception e) {
            log(ANSI_RED + ":::ERROR::: Invalid argument" + ANSI_RESET);
        }
    }
}
