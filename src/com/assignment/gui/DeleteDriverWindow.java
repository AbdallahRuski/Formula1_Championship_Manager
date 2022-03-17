package com.assignment.gui;

import com.assignment.service.ChampionshipManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeleteDriverWindow extends JFrame {

    private JLabel driverDeleteJField, driverIdJField;
    private JTextField driverIdTextFiled;
    private Font textComponentFont;
    private JButton deleteDriverButton;

    private final String[] driverTableColumn = {"ID", "NAME", "LOCATION", "1ST(n)", "2ND(n)", "3RD(n)", "POINTS",
            "RACES(n)", "TEAM"};
    private DefaultTableModel defaultTableModel;
    private ChampionshipManager championshipManager;

    public DeleteDriverWindow(DefaultTableModel defaultTableModel, ChampionshipManager championshipManager) {
        //injecting dependencies
        this.defaultTableModel = defaultTableModel;
        //setting JFrame properties
        this.setTitle("Delete Driver");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(650, 120);
        //initialize components
        initComponent();
        //injecting service bean
        this.championshipManager = championshipManager;
    }

    //initialize components
    private void initComponent() {
        //label component
        this.driverDeleteJField = new JLabel("please enter driver ID ...");
        this.driverIdJField = new JLabel("driver Identification : ");
        //text component font
        this.textComponentFont = new Font("SansSerif", Font.PLAIN, 15);
        //text component
        this.driverIdTextFiled = new JTextField(20);
        this.driverIdTextFiled.setFont(textComponentFont);
        //button component
        this.deleteDriverButton = new JButton("delete driver");

        //panel containers
        JPanel outerPanel = new JPanel();
        JPanel innerPanel = new JPanel(new GridLayout(1, 2));
        outerPanel.add(driverDeleteJField);
        innerPanel.add(driverIdJField);
        innerPanel.add(driverIdTextFiled);
        outerPanel.add(innerPanel);
        outerPanel.add(deleteDriverButton);

        //aligning components on primary container
        this.getContentPane().add(BorderLayout.CENTER, outerPanel);

        //trigger delete driver click event
        deleteDriverButtonOnClick();
    }

    //delete a driver, button click event
    private void deleteDriverButtonOnClick() {
        deleteDriverButton.addActionListener(actionEvent -> {
            //calling service bean relevant method to delete river
            championshipManager.doDeleteDriver(driverIdTextFiled.getText());
            JOptionPane.showMessageDialog(this, "Deleted Driver!");
            //reloading the home window driver table
            this.defaultTableModel.setDataVector(championshipManager.doSortDriversByPosition(), driverTableColumn);
            this.dispose();
        });
    }
}
