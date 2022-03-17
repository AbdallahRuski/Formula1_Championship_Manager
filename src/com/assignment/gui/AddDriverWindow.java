package com.assignment.gui;

import com.assignment.model.Formula1Driver;
import com.assignment.model.Team;
import com.assignment.service.ChampionshipManager;
import com.assignment.utility.ApplicationUtility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddDriverWindow extends JFrame {

    private JLabel driverDetailsLabel, driverNameLabel, driverLocationLabel, driverTeamLabel;
    private JTextField driverNameTextFiled, driverLocationTextFiled, driverTeamTextFiled;
    private Font textComponentFont;
    private JButton addDriverButton;

    private final String[] driverTableColumn = {"ID", "NAME", "LOCATION", "1ST(n)", "2ND(n)", "3RD(n)", "POINTS",
            "RACES(n)", "TEAM"};
    private DefaultTableModel defaultTableModel;
    private ChampionshipManager championshipManager;

    public AddDriverWindow(DefaultTableModel defaultTableModel, ChampionshipManager championshipManager) {

        //injecting dependencies
        this.defaultTableModel = defaultTableModel;

        //setting JFrame properties
        this.setTitle("Add New Driver");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 175);
        //initialize components
        initComponent();
        //injecting service bean
        this.championshipManager = championshipManager;
    }

    //initialize all the gui elements
    private void initComponent() {

        //initialize label components
        initLabelComponents();
        //initialize text field components
        initTextFieldComponents();
        //initialize button components
        initButtonComponent();

        //panel containers
        JPanel outerPanel = new JPanel();
        JPanel innerPanel = new JPanel(new GridLayout(4, 2));
        outerPanel.add(driverDetailsLabel);
        innerPanel.add(driverNameLabel);
        innerPanel.add(driverNameTextFiled);
        innerPanel.add(driverLocationLabel);
        innerPanel.add(driverLocationTextFiled);
        innerPanel.add(driverTeamLabel);
        innerPanel.add(driverTeamTextFiled);
        outerPanel.add(innerPanel);
        outerPanel.add(addDriverButton);

        //aligning components on primary container
        this.getContentPane().add(BorderLayout.CENTER, outerPanel);

        //trigger add driver button click event
        addDriverButtonOnClick();
    }

    //initializing label components
    private void initLabelComponents() {
        this.driverDetailsLabel = new JLabel("Please enter driver details ...");
        this.driverNameLabel = new JLabel("driver name : ");
        this.driverLocationLabel = new JLabel("driver location : ");
        this.driverTeamLabel = new JLabel("driver team : ");
    }

    //initializing text field components
    private void initTextFieldComponents() {
        this.textComponentFont = new Font("SansSerif", Font.PLAIN, 15);
        this.driverNameTextFiled = new JTextField(20);
        this.driverNameTextFiled.setFont(textComponentFont);
        this.driverLocationTextFiled = new JTextField(20);
        this.driverLocationTextFiled.setFont(textComponentFont);
        this.driverTeamTextFiled = new JTextField(20);
        this.driverTeamTextFiled.setFont(textComponentFont);
    }

    //initializing button components
    private void initButtonComponent() {
        this.addDriverButton = new JButton("add driver");
    }

    //add a new driver, button click event
    private void addDriverButtonOnClick() {
        addDriverButton.addActionListener(actionEvent -> {
            //calling service bean relevant method to add river
            championshipManager.doCreateDriver(new Formula1Driver(ApplicationUtility.doGenerateDriverIdentification(),
                    driverNameTextFiled.getText(), driverLocationTextFiled.getText(), new Team(driverTeamTextFiled.getText())));
            JOptionPane.showMessageDialog(this, "Added new Driver!");
            //reloading the home window driver table
            this.defaultTableModel.setDataVector(championshipManager.doSortDriversByPosition(), driverTableColumn);
            this.dispose();
        });
    }
}
