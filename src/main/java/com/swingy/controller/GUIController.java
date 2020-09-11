package com.swingy.controller;

import com.swingy.view.gui.CreateHero;
import com.swingy.view.gui.ExistingHeros;
import com.swingy.view.gui.GetStarted;
import com.swingy.view.gui.MainMenu;

public class GUIController {

    private GetStarted getStarted;
    private MainMenu mainMenu;
    private ExistingHeros existingHeros;
    private CreateHero createHero;

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
//            TODO use the controller to get the heros from database
//            TODO call a method in the view that will display the heros, pass the hero list
            System.out.println("Get heroes from database");
            this.existingHeros.getHeros();
        });
    }

    public GUIController(CreateHero createHero) {
        this.createHero = createHero;

        this.createHero.createHero(e -> {
            String heroName = this.createHero.getHeroName();
            String heroType = this.createHero.getHeroType();

//            TODO validate input
//            TODO create hero
//            TODO generate grid
            System.out.println("Hero Name -> " + heroName);
            System.out.println("Hero type -> " + heroType);
        });
    }
}
