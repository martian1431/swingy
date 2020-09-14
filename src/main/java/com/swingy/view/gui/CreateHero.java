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

//    TODO testing something
    private JPanel subPanelCenter;
    private JPanel subPanelOne;
    private JPanel subPanelTwo;
    private JLabel userTextLabel;
    private JTextField userTextInputField;
    private JLabel menuLabel;
    private JComboBox<String> heroClassesMenu;
    private JLabel titlelabel;
    private JLabel errorLabel;

    public CreateHero() {
        super(new BorderLayout());

//      TODO new
        subPanelCenter = new JPanel();
        subPanelCenter.setLayout(new BoxLayout(subPanelCenter, BoxLayout.Y_AXIS));

        subPanelOne = new JPanel();
        subPanelTwo = new JPanel();

        titlelabel = new JLabel("Top");
        errorLabel = new JLabel("End");
        userTextLabel = new JLabel("Hero name");
        userTextInputField = new JTextField(20);
        menuLabel = new JLabel("Hero class");
        heroClassesMenu = new JComboBox<>();
        heroClassesMenu.addItem("Test1");
        heroClassesMenu.addItem("Test2");
        heroClassesMenu.addItem("Test3");

        subPanelOne.add(userTextLabel);
        subPanelOne.add(userTextInputField);
        subPanelTwo.add(menuLabel);
        subPanelTwo.add(heroClassesMenu);

//        subPanelCenter.add(subPanelOne);
        subPanelCenter.add(subPanelTwo);


        add(subPanelOne, BorderLayout.PAGE_START);
        add(subPanelCenter, BorderLayout.CENTER);
        add(errorLabel, BorderLayout.PAGE_END);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // TODO old
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
//    public void backButton(ActionListener actionListener) {
//        backButton.addActionListener(actionListener);
//    }

    public void createHero(ActionListener actionListener) {
        submit.addActionListener(actionListener);
    }
}
