package com.assignment.gui;

import com.assignment.service.ChampionshipManager;

import javax.swing.*;
import java.awt.*;

/**
 * Window for loading search results
 */
public class SearchResultWindow extends JFrame {

    private JLabel driverPositionInfo;
    private JPanel topJPanel;
    private JTable raceTable;
    private JScrollPane raceTableScrollPane;

    private ChampionshipManager championshipManager;
    private String driverId;

    public SearchResultWindow(ChampionshipManager championshipManager, String driverId) {

        //injecting dependencies
        this.championshipManager = championshipManager;
        this.driverId = driverId;

        //set JFrame properties
        this.setTitle("Driver Positions");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 200);

        //initialize components
        initComponent();
    }

    //initializing components
    private void initComponent() {

        //top panel container
        this.topJPanel = new JPanel();

        //label component
        this.driverPositionInfo = new JLabel("... " + driverId + " winning positions in all races ...");
        this.topJPanel.add(this.driverPositionInfo);

        //race table component
        final String[] raceTableColumn = {"RACE ID", "HELD ON", "POSITION"};
        //populate search results
        final String[][] raceData = championshipManager.doSearchDriverPositionOnRace(driverId);
        //drivers table component
        this.raceTable = new JTable(raceData, raceTableColumn);
        this.raceTable.setBounds(30, 40, 90, 150);
        this.raceTableScrollPane = new JScrollPane(raceTable);

        //aligning components on primary container
        this.getContentPane().add(BorderLayout.PAGE_START, topJPanel);
        this.getContentPane().add(BorderLayout.CENTER, raceTableScrollPane);
    }
}
