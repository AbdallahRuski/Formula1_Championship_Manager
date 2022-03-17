package com.assignment.gui;

import com.assignment.model.Race;

import javax.swing.*;
import java.awt.*;

public class AddWinningPositionWindow extends JFrame {

    private JLabel infoLabel, driverIdLabel, driverPositionLabel;
    private JTextField driverIdTextFiled, driverWinningPositionTextFiled;
    private Font textComponentFont;
    private JButton addWinningPositionButton;

    private Race race;

    public AddWinningPositionWindow(Race race) {
        //setting JFrame properties
        this.setTitle("Add Driver Position");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(650, 140);
        //initialize components
        initComponent();
        //injecting data model
        this.race = race;
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
        JPanel innerPanel = new JPanel(new GridLayout(2, 2));
        outerPanel.add(infoLabel);
        innerPanel.add(driverIdLabel);
        innerPanel.add(driverIdTextFiled);
        innerPanel.add(driverPositionLabel);
        innerPanel.add(driverWinningPositionTextFiled);
        outerPanel.add(innerPanel);
        outerPanel.add(addWinningPositionButton);

        //aligning components on primary container
        this.getContentPane().add(BorderLayout.CENTER, outerPanel);

        //trigger add record button click event
        addWinningPositionButtonClick();
    }

    //initializing label components
    private void initLabelComponents() {
        this.infoLabel = new JLabel("Please enter required details ...");
        this.driverIdLabel = new JLabel("driver identification : ");
        this.driverPositionLabel = new JLabel("driver winning position : ");
    }

    //initializing text field components
    private void initTextFieldComponents() {
        this.textComponentFont = new Font("SansSerif", Font.PLAIN, 15);
        this.driverIdTextFiled = new JTextField(20);
        this.driverIdTextFiled.setFont(textComponentFont);
        this.driverWinningPositionTextFiled = new JTextField(20);
        this.driverWinningPositionTextFiled.setFont(textComponentFont);
    }

    //initializing button components
    private void initButtonComponent() {
        this.addWinningPositionButton = new JButton("add record");
    }

    //add record, button click event
    private void addWinningPositionButtonClick() {
        addWinningPositionButton.addActionListener(actionEvent -> {
            //adding driver ID & winning positions
            race.setWinningPositions(driverIdTextFiled.getText(), Integer.parseInt(
                    driverWinningPositionTextFiled.getText()));
            JOptionPane.showMessageDialog(this, "Record added!");
            this.dispose();
        });
    }
}
