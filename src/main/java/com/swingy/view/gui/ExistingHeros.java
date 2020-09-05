package com.swingy.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ExistingHeros extends JPanel {

    private JTable heroTable;
    private String[] heroTableColumn = {"Id", "Hero Name", "Hero Class", "Attack", "Defense", "Experience", "Hit Points"};

    private JButton backButton;

    public ExistingHeros() {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // toolbar for buttons
        JToolBar toolBar = new JToolBar();
        heroTable = new JTable();
        JScrollPane heroTableScroll = new JScrollPane(heroTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        backButton = new JButton("Go Back");
        add(toolBar);
        toolBar.add(backButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(heroTableScroll);
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
