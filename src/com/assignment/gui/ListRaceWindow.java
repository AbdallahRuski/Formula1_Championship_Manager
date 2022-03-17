package com.assignment.gui;

import com.assignment.service.ChampionshipManager;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class ListRaceWindow extends JFrame {

    private JLabel racesInfo;
    private JPanel topJPanel;
    private JTable raceTable;
    private JScrollPane raceTableScrollPane;

    private ChampionshipManager championshipManager;

    public ListRaceWindow(ChampionshipManager championshipManager) {

        //injecting service bean
        this.championshipManager = championshipManager;

        //set JFrame properties
        this.setTitle("Races");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 300);

        //initialize components
        initComponent();

    }

    //initializing components
    private void initComponent() {

        //label component
        this.racesInfo = new JLabel("... races held on ...");
        this.topJPanel = new JPanel();
        this.topJPanel.add(racesInfo);

        //race table component
        final String[] raceTableColumn = {"RACE ID", "HELD ON"};
        //populate data on race table
        final String[][] raceData = populateRaceTable();
        //drivers table component
        this.raceTable = new JTable(raceData, raceTableColumn);
        this.raceTable.setBounds(30, 40, 90, 150);
        this.raceTableScrollPane = new JScrollPane(raceTable);

        //aligning components on primary container
        this.getContentPane().add(BorderLayout.PAGE_START, topJPanel);
        this.getContentPane().add(BorderLayout.CENTER, raceTableScrollPane);
    }

    //assemble data to populate on race table
    private String[][] populateRaceTable() {

        String[][] raceArray = new String[championshipManager.doGetRacesCompleted().size()][2];
        AtomicInteger count = new AtomicInteger(0);

        //setting values on raceDate, 2D array
        championshipManager.doGetRacesCompleted().forEach((key, value) -> {
            raceArray[count.intValue()][0] = value.getRaceId();
            raceArray[count.intValue()][1] = value.getHeldOn().toString();
            count.getAndIncrement();
        });

        //for sorting asc order based on date
        Arrays.sort(raceArray, Comparator.comparing(index -> index[1]));

        return raceArray;
    }
}
