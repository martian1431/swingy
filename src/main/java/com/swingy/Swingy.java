package com.swingy;

import com.swingy.model.GameModel;
import com.swingy.view.console.ConsoleView;
import com.swingy.view.gui.GUIView;
import com.swingy.view.gui.GameWindow;

import javax.swing.*;

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
//                SwingUtilities.invokeLater(GUIView::new);
//                new GameWindow();
                GameWindow.run();
            } else {
                log(ANSI_RED + ":::ERROR::: Invalid argument" + ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log(ANSI_RED + ":::ERROR::: Invalid argument" + ANSI_RESET);
        }
    }
}
