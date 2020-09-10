package com.swingy.controller;

import com.swingy.view.gui.ExistingHeros;
import com.swingy.view.gui.GetStarted;
import com.swingy.view.gui.MainMenu;

public class GUIController {

    private GetStarted getStarted;
    private MainMenu mainMenu;
    private ExistingHeros existingHeros;

    public GUIController(GetStarted getStarted) {
        this.getStarted = getStarted;

        this.getStarted.startButton(e -> {
            System.out.println("Someone clicke me for no reason");
        });
    }

    public GUIController(MainMenu mainMenu, ExistingHeros existingHeros) {
        this.mainMenu = mainMenu;
        this.existingHeros = existingHeros;

        this.mainMenu.selectHeroButton(e -> {
            System.out.println("Get heroes from database");
            this.existingHeros.getHeros();
        });
    }
}
