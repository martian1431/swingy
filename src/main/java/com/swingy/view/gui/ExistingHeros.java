package com.swingy.view.gui;

import com.swingy.model.GameModel;
import com.swingy.model.character.heros.Hero;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ExistingHeros extends JPanel {

    private JTable heroTable;
//    private String[] heroTableColumn = {"Id", "Hero Name", "Hero Class", "Attack", "Defense", "Experience", "Hit Points"};

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


    // FIXME gets heros from database and loads to table
//    TODO change method name to displayHeros or something along those lines
    public void getHeros() {
        Object[] tableColumn = new Object[7];
        tableColumn[0] = "ID";
        tableColumn[1] = "Hero Name";
        tableColumn[2] = "Hero Type";
        tableColumn[3] = "Attack";
        tableColumn[4] = "Defense";
        tableColumn[5] = "Experience";
        tableColumn[6] = "Hit Points";

        DefaultTableModel defaultTableModel = (DefaultTableModel) heroTable.getModel();
        defaultTableModel.setColumnIdentifiers(tableColumn);
        try {
            List<Hero> heroes = GameModel.getInstance().retrieveAllHeroes();
            for (int i = 0; i < heroes.size(); i++) {
                Object[] rowData = new Object[7];
                rowData[0] = heroes.get(i).getId();
                rowData[1] = heroes.get(i).getName();
                rowData[2] = heroes.get(i).getType();
                rowData[3] = heroes.get(i).getAttack();
                rowData[4] = heroes.get(i).getDefense();
                rowData[5] = heroes.get(i).getExperience();
                rowData[6] = heroes.get(i).getHitPoints();
//                TODO configure column width
//                TableColumn tableColumn1 = heroTable.getColumn(i);
//                tableColumn1.setPreferredWidth(20);
                defaultTableModel.addRow(rowData);
            }
        } catch (Exception e) {
//            TODO handle exception
            System.out.println(e.getMessage());
        } finally {
            heroTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String selectedHero = (String) heroTable.getValueAt(heroTable.getSelectedRow(), 1);
                    System.out.println("Selected hero -> " + selectedHero);
                }
            });
        }
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
