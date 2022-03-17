package com.assignment.service;

import com.assignment.model.Driver;
import com.assignment.model.Race;
import com.assignment.model.Team;

import java.util.List;
import java.util.Map;

/**
 * An abstraction of Championship Manager
 */
public interface ChampionshipManager {

    //create a new driver - for both UIs
    void doCreateDriver(Driver driver);

    //delete a driver - for both UIs
    void doDeleteDriver(String driverId);

    //edit driver team - for the console based UI
    void doEditDriverTeam(String driverId, Team team);

    //display driver statistics - for the console based UI
    Driver doDisplayDriverStatistics(String driverId);

    //display driver table - for the console based UI
    List<Driver> doDisplayDriverTable();

    //insert race completed - for both UIs
    void doInsertRaceCompleted(Race race);

    //fetch statistics of drivers in descending order based on points - for GUI
    String[][] doGetDriversStatByPointsOnDesc();

    //search driver position for all the races participated - for GUI
    String[][] doSearchDriverPositionOnRace(String driverId);

    //sort drivers by points earned - for GUI
    String[][] doSortDriversByPoints();

    //sort drivers the largest number of first positions - for GUI
    String[][] doSortDriversByPosition();

    //fetching races completed
    Map<String, Race> doGetRacesCompleted();

    //for generating a random race
    Map<String, String[][]> doGenerateRandomRace();

    //for intelligent race generation
    Map<String, String[][]> doGenerateIntelligentRace();
}
