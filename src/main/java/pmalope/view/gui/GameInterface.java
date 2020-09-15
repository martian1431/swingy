package pmalope.view.gui;

import pmalope.model.character.heros.Hero;

import java.util.List;

public interface GameInterface {
    public void setEnableStartButton(boolean b);
    public void showStartScreen();
    public void showSelectScreen(List<Hero> heroList);
    public void showCreateScreen();
    public void showGameView(Hero hero);
    public void showErrorDialog(String error);
    public void clearLoadingLabel();
}
