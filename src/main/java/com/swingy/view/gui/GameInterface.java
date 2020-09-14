package com.swingy.view.gui;

import com.swingy.model.artifact.Artifact;
import com.swingy.model.character.heros.Hero;
import com.swingy.model.character.villian.Villian;

import java.util.List;

public interface GameInterface {
    public void setEnableStartButton(boolean b);
    public void showStartScreen();
    public void showSelectScreen(List<Hero> heroList);
    public void showCreateScreen();
    public void showCreateScreenError(String error);
    public void upDateGameViewHeroStats(Hero hero);
    public void showErrorOnStart(String error);
    public void disableStartSelectScreen();
    public void showGameView(Hero hero);
    public void showCurrentCoords(int[] currentCoords);
    public void setMovementEnabled(boolean b);
    public void setFightRunEnabled(boolean b);
    public void setAttackEnabled(boolean b);
    public void setPickupLeaveEnabled(boolean b);
    public void showGameInfo(String info);
    public void showGameInfo(String info, Villian villian);
    public void showGameInfo(String info, Hero hero, Villian enemy);
    public void showGameInfo(String info, Hero hero, Artifact artifact);
    public void showGameViewError(String error);
    public void endGameMessage(String title, String message);
    public void quitGame();
    public void showErrorDialog(String error);
    public void clearLoadingLabel();
}
