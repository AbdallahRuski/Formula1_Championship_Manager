package com.assignment.gui;

import com.assignment.model.Race;
import com.assignment.service.ChampionshipManager;
import com.assignment.utility.ApplicationUtility;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddRaceWindow extends JFrame {

    private JLabel raceDetailsJField, raceDateJField;
    private JTextField raceDateTextFiled;
    private Font textComponentFont;
    private JButton addWinningPositionsButton, addRaceButton;

    private ChampionshipManager championshipManager;
    private Race race;

    public AddRaceWindow(ChampionshipManager championshipManager) {
        //setting JFrame properties
        this.setTitle("Add Race");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(650, 120);
        //initialize components
        initComponent();
        //injecting service bean
        this.championshipManager = championshipManager;
        this.race = new Race();
    }

    //initialize components
    private void initComponent() {
        //label component
        this.raceDetailsJField = new JLabel("please enter required details ...");
        this.raceDateJField = new JLabel("race was on : ");
        //text component font
        this.textComponentFont = new Font("SansSerif", Font.PLAIN, 15);
        //text component
        this.raceDateTextFiled = new JTextField(20);
        this.raceDateTextFiled.setFont(textComponentFont);
        //button component
        this.addWinningPositionsButton = new JButton("winning position");
        this.addRaceButton = new JButton("add race");

        //panel containers
        JPanel outerPanel = new JPanel();
        JPanel innerPanel = new JPanel(new GridLayout(1,2));
        outerPanel.add(raceDetailsJField);
        innerPanel.add(raceDateJField);
        innerPanel.add(raceDateTextFiled);
        outerPanel.add(innerPanel);
        outerPanel.add(addWinningPositionsButton);
        outerPanel.add(addRaceButton);

        //aligning components on primary container
        this.getContentPane().add(BorderLayout.CENTER,  outerPanel);

        //trigger add winning positions button click
        addWinningPositionsButtonOnClick();
        //trigger add race button click event
        addRaceButtonOnClick();
    }

    //add winning positions, button click event
    private void addWinningPositionsButtonOnClick() {
        addWinningPositionsButton.addActionListener(actionEvent -> {
            AddWinningPositionWindow addWinningPositionWindow = new AddWinningPositionWindow(race);
            addWinningPositionWindow.setVisible(true);
        });
    }

    //add a race, button click event
    private void addRaceButtonOnClick() {
        addRaceButton.addActionListener(actionEvent -> {
            race.setRaceId(ApplicationUtility.doGenerateRaceIdentification());
            race.setHeldOn(LocalDate.parse(raceDateTextFiled.getText()));
            championshipManager.doInsertRaceCompleted(race);
            JOptionPane.showMessageDialog(this, "Race Added!");
            this.dispose();
        });
    }
}
