package com.assignment.gui;

import javax.swing.*;
import java.awt.*;

public class GeneratedRaceWindow extends JFrame {

    private JLabel racesInfo;
    private JPanel topJPanel;
    private JTable raceTable;
    private JScrollPane raceTableScrollPane;

    private String[][] generatedRaceOutput;

    public GeneratedRaceWindow(String[][] generatedRaceOutput) {

        //injecting generated race
        this.generatedRaceOutput = generatedRaceOutput;

        //set JFrame properties
        this.setTitle("Generated Races");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 300);

        //initialize components
        initComponent();

    }

    //initializing components
    private void initComponent() {

        //label component
        this.racesInfo = new JLabel("... Generated Race ...");
        this.topJPanel = new JPanel();
        this.topJPanel.add(racesInfo);

        //race table component
        final String[] raceTableColumn = {"RACE ID", "HELD ON", "DRIVER ID", "POSITION"};
        //populate data on race table
        final String[][] raceData = this.generatedRaceOutput;
        //drivers table component
        this.raceTable = new JTable(raceData, raceTableColumn);
        this.raceTable.setBounds(30, 40, 90, 150);
        this.raceTableScrollPane = new JScrollPane(raceTable);

        //aligning components on primary container
        this.getContentPane().add(BorderLayout.PAGE_START, topJPanel);
        this.getContentPane().add(BorderLayout.CENTER, raceTableScrollPane);
    }
}
