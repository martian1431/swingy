package com.swingy.view.gui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;

public class GetStarted extends JPanel {

    private JButton startButton;

    public GetStarted() {

        startButton = new JButton("Get Started");
        startButton.setPreferredSize(new Dimension(278, 40));
        Insets buttonInset = new Insets(20,0,0,0);

        // uses Grid Bag Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        add(startButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = buttonInset;

    }

    public void startButton(ActionListener actionListener) { startButton.addActionListener(actionListener);}
}
