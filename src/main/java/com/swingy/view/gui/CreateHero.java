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

        // FIXME toolbar for buttons
//        JToolBar toolBar = new JToolBar();
//        JScrollPane heroTableScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        backButton = new JButton("Go Back");
//        add(toolBar);
//        toolBar.add(backButton);
//        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
//        add(heroTableScroll);
        JLabel heroType = new JLabel("Select Hero Type");
        JLabel heroTName = new JLabel("Hero Name");
        JTextField inputTextField = new JTextField();
        JButton submit = new JButton("Create");
        submit.setPreferredSize(new Dimension(20, 20));
        JComboBox<String> heroList = new JComboBox<String>();
        JPanel jPanel = new JPanel();
        heroList.addItem("Deadpool   - Attack | 0 - Defense | 0 - Hit Point | 0");
        heroList.addItem("Thor           - Attack | 0 - Defense | 0 - Hit Point | 0");
        heroList.addItem("Wolverine - Attack | 0 - Defense | 0 - Hit Point | 0");

        jPanel.add(heroType);
        jPanel.add(heroList);
        jPanel.add(heroTName);
        jPanel.add(inputTextField);
        jPanel.add(submit);
        jPanel.setLayout(new GridLayout(5,1));
        jPanel.setPreferredSize(new Dimension(400,100));
        add(jPanel);
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
