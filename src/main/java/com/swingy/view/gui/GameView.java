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
        moveNorthButton.setEnabled(false);
        moveSouthButton.setText("S");
        moveSouthButton.setEnabled(false);
        moveEastButton.setText("E");
        moveEastButton.setEnabled(false);
        moveWestButton.setText("W");
        moveWestButton.setEnabled(false);
        fightButton.setText("Fight");
        fightButton.setEnabled(false);
        runButton.setText("Run");
        runButton.setEnabled(false);
//        attackButton.setText("Attack!");
        gameViewTitleLabel.setText("The battle area");
        currentCoordsLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        pickUpButton.setText("Pickup item");
        pickUpButton.setEnabled(false);
        leaveItemButton.setText("Leave item");
        leaveItemButton.setEnabled(false);

        GroupLayout jPanel2Layout = new GroupLayout(this);
        this.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(gameViewTitleLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(heroStatsLabel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                        .addGap(50, 50, 50)
                                                                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(moveSouthButton)
                                                                                .addComponent(moveNorthButton)))
                                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                                        .addComponent(moveWestButton)
                                                                        .addGap(27, 27, 27)
                                                                        .addComponent(moveEastButton)))
                                                        .addComponent(fightButton))
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(runButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(currentCoordsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
//                                                .addComponent(attackButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(pickUpButton)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(leaveItemButton)))
                                        .addComponent(gameInfoLabel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(gameViewTitleLabel)
                                .addGap(54, 54, 54)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(heroStatsLabel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                                .addGap(35, 35, 35)
                                                .addComponent(moveNorthButton)
                                                .addGap(3, 3, 3)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(moveEastButton)
                                                        .addComponent(moveWestButton)
                                                        .addComponent(currentCoordsLabel))
                                                .addGap(4, 4, 4)
                                                .addComponent(moveSouthButton))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(306, 306, 306)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(fightButton)
                                                        .addComponent(runButton))
                                                .addGap(18, 18, 18)
                                                .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(gameInfoLabel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
//                                                .addComponent(attackButton)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(pickUpButton)
                                                        .addComponent(leaveItemButton))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public void setHeroStatsLabel(Hero hero) {
        String heroStatsString ="<html>"
                + "<br/>Name: " + hero.getName()
                + " <br/>Type: " + hero.getType()
                + "<br/>Level: " + hero.getLevel()
                + "<br/>Armor: " + " (" + hero.getDefense() + " defense)"
                + "<br/>Weapon: " + " (" + hero.getAttack() + " attack)"
                + "<br/>Helm: " + " (" + hero.getHelm().getHitPoints() + " HP)"
                + "<br/>XP: " + hero.getExperience()
                + "<html>";
        heroStatsLabel.setText(heroStatsString);
    }

    public void setGameInfoLabel(String info) {
        gameInfoLabel.setText(info);
    }

    public void setGameViewErrorLabel(String error) {
        errorLabel.setText(error);
    }
}
