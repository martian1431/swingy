package com.swingy.controller;

import com.swingy.view.gui.GetStarted;

public class GUIController {

    private GetStarted getStarted;


    public GUIController(GetStarted getStarted) {
        this.getStarted = getStarted;

        this.getStarted.startButton(e -> {
            System.out.println("Someone clicke me for no reason");
        });
    }
}
