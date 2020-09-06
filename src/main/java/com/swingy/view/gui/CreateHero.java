package com.swingy.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CreateHero extends JPanel implements ItemListener {

    private JButton backButton;
    private JComboBox jComboBox;
    private JLabel jLabel;

    public CreateHero() {
//        setLayout(new GridBagLayout());

        String s1[] = { "Jalpaiguri", "Mumbai", "Noida", "Kolkata", "New Delhi" };

        JToolBar toolBar = new JToolBar();
//        jLabel = new JLabel("type your hero name");
        jComboBox = new JComboBox(s1);

        backButton = new JButton("Go Back");
        add(toolBar);
        toolBar.add(backButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
//        jComboBox.addItemListener(this);

        add(jComboBox);
//        add(jLabel);
    }

    public void itemStateChanged(ItemEvent e)
    {
        // if the state combobox is changed
        if (e.getSource() == jComboBox) {
            System.out.println("Okay");
//            l1.setText(jComboBox.getSelectedItem() + " selected");
        }
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
