package com.swingy;

import com.swingy.controller.ConsoleController;
import com.swingy.utils.database.DatabaseWrapper;
import com.swingy.view.gui.GameWindow;

public class Swingy {
    public static void main(String[] args) {
        try {
            String view = args[0];
            DatabaseWrapper.getInstance().setupDatabase();
            if (view.toLowerCase().equals("console")) {
                ConsoleController.run();
            } else if (view.toLowerCase().equals("gui")) {
                GameWindow.run();
            } else {
                System.err.println("Please choose a view to run...");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
//            FIXME
            System.out.println("Invalid argument... ");
        }
    }
}
