package com.swingy.view.gui;

import com.swingy.model.character.heros.Hero;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;

public class GameView extends JPanel {
    private JButton leaveItemButton;
    private JButton moveNorthButton;
    private JButton moveSouthButton;
    private JButton moveEastButton;
    private JButton moveWestButton;
    private JButton fightButton;
    private JButton runButton;
//    private JButton attackButton;
    private JButton pickUpButton;
    private JLabel gameInfoLabel;
    private JLabel heroStatsLabel;
    private JLabel gameViewTitleLabel;
    private JLabel errorLabel;
    private JLabel currentCoordsLabel;

    GameView(Hero hero) {
        gameInfoLabel = new JLabel();
        heroStatsLabel = new JLabel();
        moveNorthButton = new JButton();
        moveSouthButton = new JButton();
        moveEastButton = new JButton();
        moveWestButton = new JButton();
        fightButton = new JButton();
        runButton = new JButton();
//        attackButton = new JButton();
        gameViewTitleLabel = new JLabel();
        errorLabel = new JLabel();
        currentCoordsLabel = new JLabel();
        pickUpButton = new JButton();
        leaveItemButton = new JButton();

        gameInfoLabel.setBackground(new Color(255, 255, 255));
        gameInfoLabel.setPreferredSize(new Dimension(200, 500));
        gameInfoLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        heroStatsLabel.setBackground(new Color(255, 255, 255));
        setHeroStatsLabel(hero);
        heroStatsLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        moveNorthButton.setText("N");
        moveSouthButton.setText("S");
        moveEastButton.setText("E");
        moveWestButton.setText("W");
        fightButton.setText("Fight");
        runButton.setText("Run");
//        attackButton.setText("Attack!");
        gameViewTitleLabel.setText("The battle dome!");
        currentCoordsLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        pickUpButton.setText("Pickup item");
        leaveItemButton.setText("Leave item");
    }

    public void setHeroStatsLabel(Hero hero) {
        String heroStatsString ="<html>" + hero.getName()
                + " lvl " + hero.getLevel()
                + " " + hero.getType()
                + "<br/>Axe: " + hero.getWeapon().getType() + " (" + hero.getAttack() + " attack)"
                + "<br/>Armor: " + hero.getArmor().getType() + " (" + hero.getDefense() + " defense)"
                + "<br/>Helm: " + hero.getHelm().getType() + " (" + hero.getHelm().getHitPoints() + " hp bonus)"
                + "<br/>HP: " + hero.getHitPoints()
                + "<br/>XP: " + hero.getExperience()
                + "<html>";
        heroStatsLabel.setText(heroStatsString);
    }

//    public static void run() {
//        new GUIView();
//    }
}
