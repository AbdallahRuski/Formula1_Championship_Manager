package com.assignment.gui;

import com.assignment.service.ChampionshipManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class HomeWindow extends JFrame {

    private JPanel topJPanel, bottomJPanel;
    private JButton sortByPointsButton, sortByPositionButton, searchButton, addDriverButton,
            deleteDriverButton, addRaceButton, displayRacesButton, randomRaceButton, intelligentRaceButton;
    private JTextField searchTextFiled;
    private Font searchTextFiledFont;
    private DefaultTableModel defaultTableModel;
    private JTable driverTable;
    private JScrollPane driverTableScrollPane;
    private final String[] driverTableColumn = {"ID", "NAME", "LOCATION", "1ST(n)", "2ND(n)", "3RD(n)", "POINTS",
            "RACES(n)", "TEAM"};

    private ChampionshipManager championshipManager;

    public HomeWindow(ChampionshipManager championshipManager) {

        //injecting service bean
        this.championshipManager = championshipManager;

        //set JFrame properties
        this.setTitle("Formula Championship Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(900, 400);

        //initialize components
        initComponent();
    }

    //initialize all the gui elements
    private void initComponent() {

        //initialize search component
        initSearchComponent();
        //initialize button components
        initButtonComponents();

        //top panel container
        topJPanel = new JPanel();
        topJPanel.add(searchButton);
        topJPanel.add(searchTextFiled);
        topJPanel.add(sortByPointsButton);
        topJPanel.add(sortByPositionButton);

        //bottom panel container
        bottomJPanel = new JPanel();
        bottomJPanel.add(addDriverButton);
        bottomJPanel.add(deleteDriverButton);
        bottomJPanel.add(addRaceButton);
        bottomJPanel.add(displayRacesButton);
        bottomJPanel.add(randomRaceButton);
        bottomJPanel.add(intelligentRaceButton);

        //initialize driver table component
        initDriverTableComponent();

        //aligning components on primary container
        this.getContentPane().add(BorderLayout.PAGE_START, topJPanel);
        this.getContentPane().add(BorderLayout.CENTER, driverTableScrollPane);
        this.getContentPane().add(BorderLayout.PAGE_END, bottomJPanel);

        //search races onClick event
        searchOnClick();
        //sort driver by points onClick event
        sortByPointsOnClick();
        //sort driver by position onClick event
        sortByPositionOnClick();
        //add drivers onClick event
        addDriverOnClick();
        //delete drivers onClick event
        deleteDriverOnClick();
        //add race onClick event
        addRaceOnClick();
        //display races onClick event
        displayRacesOnClick();
        //generate random race onClick event
        randomRaceOnClick();
        //generate intelligent race onClick event
        intelligentRaceOnClick();
    }

    //for initializing search component
    private void initSearchComponent() {

        //search button
        this.searchButton = new JButton("search");

        //search text filed
        this.searchTextFiled = new JTextField(20);
        this.searchTextFiledFont = new Font("SansSerif", Font.PLAIN, 15);
        this.searchTextFiled.setFont(searchTextFiledFont);
    }

    //for initializing button components
    private void initButtonComponents() {

        //'sort by points' button component
        this.sortByPointsButton = new JButton("sort-points");
        //'sort by position' button component
        this.sortByPositionButton = new JButton("sort-position");
        //'add drivers' button component
        this.addDriverButton = new JButton("add driver");
        //'delete drivers' button component
        this.deleteDriverButton = new JButton("delete driver");
        //'add race' button component
        this.addRaceButton = new JButton("add race");
        //'display race' button component
        this.displayRacesButton = new JButton("display races");
        //'random race generator' button component
        this.randomRaceButton = new JButton("random race");
        //'intelligent race generator' button component
        this.intelligentRaceButton = new JButton("intelligent race");
    }

    //for initializing drivers table component
    private void initDriverTableComponent() {

        //populating driver table
        final String[][] driverTableData = championshipManager.doGetDriversStatByPointsOnDesc();

        //drivers table component
        this.defaultTableModel = new DefaultTableModel(driverTableData, driverTableColumn);
        this.driverTable = new JTable(defaultTableModel);
        this.driverTable.setBounds(30, 40, 90, 150);
        this.driverTableScrollPane = new JScrollPane(driverTable);
    }

    //handle search button click event
    private void searchOnClick() {
        this.searchButton.addActionListener(actionEvent -> {
            SearchResultWindow searchResultWindow = new SearchResultWindow(championshipManager,
                    searchTextFiled.getText());
            searchResultWindow.setVisible(true);
        });
    }

    //handle sort by points, button click event
    private void sortByPointsOnClick() {
        this.sortByPointsButton.addActionListener(actionEvent -> {
            //sorted drivers based on points, ascending & apply on table
            this.defaultTableModel.setDataVector(championshipManager.doSortDriversByPoints(), driverTableColumn);
        });
    }

    //handle sort by position, button click event
    private void sortByPositionOnClick() {
        this.sortByPositionButton.addActionListener(actionEvent -> {
            //sorted drivers based on largest number of first positions & apply on table
            this.defaultTableModel.setDataVector(championshipManager.doSortDriversByPosition(), driverTableColumn);
        });
    }

    //handle add driver, button click
    private void addDriverOnClick() {
        this.addDriverButton.addActionListener(actionEvent -> {
            AddDriverWindow addDriverWindow = new AddDriverWindow(defaultTableModel, championshipManager);
            addDriverWindow.setVisible(true);
        });
    }

    //handle delete driver, button click
    private void deleteDriverOnClick() {
        this.deleteDriverButton.addActionListener(actionEvent -> {
            DeleteDriverWindow deleteDriverWindow = new DeleteDriverWindow(defaultTableModel, championshipManager);
            deleteDriverWindow.setVisible(true);
        });
    }

    //handle add race, button click
    private void addRaceOnClick() {
        this.addRaceButton.addActionListener(actionEvent -> {
            AddRaceWindow addRaceWindow = new AddRaceWindow(championshipManager);
            addRaceWindow.setVisible(true);
        });
    }

    //handle display races button click event
    private void displayRacesOnClick() {
        this.displayRacesButton.addActionListener(actionEvent -> {
            ListRaceWindow listRaceWindow = new ListRaceWindow(championshipManager);
            listRaceWindow.setVisible(true);
        });
    }

    //handle random race generation button click event
    private void randomRaceOnClick() {
        this.randomRaceButton.addActionListener(actionEvent -> {
            Map<String, String[][]> randomOutput = championshipManager.doGenerateRandomRace();
            //populate drivers on home window
            this.defaultTableModel.setDataVector(randomOutput.get("DRIVERS_OUTPUT"), driverTableColumn);
            //load generated race window
            GeneratedRaceWindow generatedRaceWindow = new GeneratedRaceWindow(randomOutput.get("RACE_OUTPUT"));
            generatedRaceWindow.setVisible(true);
        });
    }

    //handle intelligent race generation button click event
    private void intelligentRaceOnClick() {
        this.intelligentRaceButton.addActionListener(actionEvent -> {
            Map<String, String[][]> intelligentOutput = championshipManager.doGenerateIntelligentRace();
            //populate drivers on home window
            this.defaultTableModel.setDataVector(intelligentOutput.get("DRIVERS_OUTPUT"), driverTableColumn);
            //load generated race window
            GeneratedRaceWindow generatedRaceWindow = new GeneratedRaceWindow(intelligentOutput.get("RACE_OUTPUT"));
            generatedRaceWindow.setVisible(true);
        });
    }
}
