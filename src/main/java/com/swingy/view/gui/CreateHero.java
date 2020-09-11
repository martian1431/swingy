package com.swingy.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CreateHero extends JPanel implements ItemListener {

    private JButton backButton;
    private JButton submit;
    private JComboBox<String> heroType;
    private JLabel heroTypeLabel;
    private JLabel heroName;
    private JTextField inputTextField;

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
//        heroTypeLabel = new JLabel("Select Hero Type");
//        heroName = new JLabel("Hero Name");
//        inputTextField = new JTextField(25);
//        submit = new JButton("Create");
//        submit.setPreferredSize(new Dimension(20, 20));
//        heroType = new JComboBox<String>();
//        JPanel jPanel = new JPanel();
//        heroType.addItem("Deadpool");
//        heroType.addItem("Thor");
//        heroType.addItem("Wolverine");
//
//        jPanel.add(heroTypeLabel);
//        jPanel.add(heroType);
//        jPanel.add(heroName);
//        jPanel.add(inputTextField);
//        jPanel.add(submit);
//        jPanel.setLayout(new GridLayout(5,1));
//        jPanel.setPreferredSize(new Dimension(400,100));
//        add(jPanel);
    }

    public String getHeroName() {
        return inputTextField.getText();
    }

    public String getHeroType() {
        System.out.println(heroType.getSelectedItem());
        return (String) heroType.getSelectedItem();
    }

//    TODO delete
    public void itemStateChanged(ItemEvent e)
    {
        // if the state combobox is changed
        if (e.getSource() == heroType) {
            System.out.println("Okay");
//            l1.setText(jComboBox.getSelectedItem() + " selected");
        }
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public void createHero(ActionListener actionListener) {
        submit.addActionListener(actionListener);
    }
}
