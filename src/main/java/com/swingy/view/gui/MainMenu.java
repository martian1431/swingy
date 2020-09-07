package com.swingy.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private final JButton createHeroButton;
    private final JButton selectHeroButton;
    private final JButton consoleViewButton;

    public MainMenu() {

//        TODO create hero button
        createHeroButton = new JButton("Create Hero");
        createHeroButton.setPreferredSize(new Dimension(278, 40));
        Insets createButtonInset = new Insets(20,0,0,0);


        //        TODO select hero button
        selectHeroButton = new JButton("Select Existing Hero");
        selectHeroButton.setPreferredSize(new Dimension(278, 40));
        Insets selectButtonInset = new Insets(20,0,0,0);

//        TODO switch views button
        consoleViewButton = new JButton("Switch to console");
        consoleViewButton.setPreferredSize(new Dimension(278, 40));
        Insets consoleViewButtonInset = new Insets(20,0,0,0);


        // uses Grid Bag Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        add(createHeroButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = createButtonInset;

        add(selectHeroButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = selectButtonInset;

        add(consoleViewButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = consoleViewButtonInset;
    }

    public void createHeroButton(ActionListener actionListener) { createHeroButton.addActionListener(actionListener);}

    public void selectHeroButton(ActionListener actionListener) { selectHeroButton.addActionListener(actionListener);}

    public void consoleViewButton(ActionListener actionListener) { consoleViewButton.addActionListener(actionListener);}
}
