package com.swingy.view.gui;

import com.swingy.controller.GUIController;

import javax.swing.*;
import java.awt.*;

public class GUIView extends JFrame {
    private static final long serialVersionUID = 1L;

    private CardLayout cardLayout;
    private Container container;

    public GUIView() {
        super("Swingy");
        cardLayout = new CardLayout();
        GetStarted getStarted = new GetStarted();
        MainMenu mainMenu = new MainMenu();
        ExistingHeros existingHeros = new ExistingHeros();
        CreateHero createHero = new CreateHero();

        container = getContentPane();
        setLayout(cardLayout);
//        setLayout(cardLayout);

        new GUIController(getStarted);
        new GUIController(mainMenu, existingHeros);

        // adds view to card layout with unique constraints
        add(getStarted, "Get started");
        add(mainMenu, "Main Menu");
        add(createHero,"Create Hero");
        add(existingHeros, "Existing Heros");

//        TODO
        // switch view according to its constraints on click
        getStarted.startButton(e -> cardLayout.show(GUIView.this.getContentPane(), "Main Menu"));
        mainMenu.createHeroButton(e -> cardLayout.show(GUIView.this.getContentPane(), "Create Hero"));
        mainMenu.selectHeroButton(e -> cardLayout.show(GUIView.this.getContentPane(),"Existing Heros"));
        existingHeros.backButton(e -> cardLayout.show(GUIView.this.getContentPane(), "Main Menu"));
//        createHero.backButton(e -> cardLayout.show(GUIView.this.getContentPane(), "Main Menu"));

        // frame width & height
        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;
        // size of our application frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
