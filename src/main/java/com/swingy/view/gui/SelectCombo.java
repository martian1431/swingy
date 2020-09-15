package com.swingy.view.gui;

import com.swingy.controller.GUIController;
import com.swingy.model.character.heros.Hero;
import com.swingy.utils.Globals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectCombo extends JPanel implements ActionListener{
    JLabel screenTitle;
    JLabel heroStatsLabel;
    JButton selectHeroButton;
    int heroID;

    private List<Hero> heroList;

    public SelectCombo(List<Hero> heroes) {
        super(new BorderLayout());

        System.out.println("heros " + heroes);
        this.heroList = heroes;
        String[] heroTitles = new String[this.heroList.size()];
        final String[] heroNames = new String[this.heroList.size()];
        StringBuilder heroString = new StringBuilder();

        for (int i = 0; i < this.heroList.size(); i++) {
            heroString.append(this.heroList.get(i).getName());
            heroNames[i] = this.heroList.get(i).getName();
            heroTitles[i] = heroString.toString();
            heroString.delete(0, heroString.length());
        }

        JComboBox selectHeroMenu = new JComboBox(heroTitles);
        selectHeroMenu.setSelectedIndex(0);
        heroID = selectHeroMenu.getSelectedIndex();
        selectHeroMenu.addActionListener(this);

        screenTitle = new JLabel();
        selectHeroButton = new JButton();
        heroStatsLabel = new JLabel();
        JPanel subPanelCenter = new JPanel();
        JPanel menuPanel = new JPanel();
        subPanelCenter.setLayout(new BoxLayout(subPanelCenter, BoxLayout.Y_AXIS));

        screenTitle.setText("Choose your hero!");
        screenTitle.setPreferredSize(new Dimension(600, 50));
        selectHeroButton.setText("Select hero");
        selectHeroButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        screenTitle.setHorizontalAlignment(JLabel.CENTER);
        displayHeroStats(heroID);
//    heroStatsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        heroStatsLabel.setPreferredSize(new Dimension(100, 150));
        heroStatsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        menuPanel.add(selectHeroMenu);

        subPanelCenter.add(menuPanel);
        subPanelCenter.add(heroStatsLabel);
        subPanelCenter.add(selectHeroButton);

        add(screenTitle, BorderLayout.PAGE_START);
        add(subPanelCenter, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        selectHeroButton.addActionListener(e -> {
            GUIController.eventHandler(Globals.selectHeroById, heroNames[heroID]);
        });
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        heroID = cb.getSelectedIndex();
        displayHeroStats(heroID);
    }

    protected void displayHeroStats(int index) {
        int totalHP = heroList.get(index).getHitPoints();
        String heroStatsString =  ""
                + " Level " + heroList.get(index).getLevel()
                + " "
                + "\nArmor: " + " (" + heroList.get(index).getDefense() + " Defense)"
                + "\nWeapon: " + " (" + heroList.get(index).getAttack() + " Attack)"
                + "\nHelm: " + " (" + heroList.get(index).getHitPoints() + " hp)"
                + "\nExperience: " + heroList.get(index).getExperience();

        heroStatsLabel.setText("<html>" + heroStatsString.replaceAll("<","&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\n", "<br/>")
                + "</html>");
    }
}
