package com.swingy;

import com.swingy.view.console.ConsoleView;
import com.swingy.view.gui.GameWindow;

public class Swingy {
    public static void main(String[] args) {
        try {
            String view = args[0];
            DatabaseHandler.getInstance().setupDatabase("Swingy.db");
            if (view.toLowerCase().equals("console")) {
                ConsoleView.run();
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
