package com.swingy.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CreateHero extends JPanel implements ItemListener {

    private JComboBox jComboBox;
    private JLabel jLabel;

    public CreateHero() {
//        setLayout(new GridBagLayout());

        String s1[] = { "Jalpaiguri", "Mumbai", "Noida", "Kolkata", "New Delhi" };

        jLabel = new JLabel("type your hero name");
        jComboBox = new JComboBox(s1);


        jComboBox.addItemListener(this);

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
}
