package com.assignment.service;

import com.assignment.model.Driver;
import com.assignment.model.Formula1Driver;
import com.assignment.model.Race;
import com.assignment.model.Team;
import com.assignment.utility.ApplicationUtility;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * An implementation of UserInterfaceLoader for the purpose of command line UI
 */
public class ConsoleLoaderService implements UserInterfaceLoader {

    private ChampionshipManager formula1ChampionshipManager;

    public ConsoleLoaderService() {
        //injecting 'Formula1ChampionshipManager' instance
        this.formula1ChampionshipManager = new Formula1ChampionshipManager();
    }

    @Override
    public void doLoadUserInterface() {

        boolean iterate = true;
        //start the scanner
        Scanner inputScanner = new Scanner(System.in);

        while (iterate) {
            //display starter user instruction
            displayInitialInstruction();
            //fetching user input via scanner
            String userInput = inputScanner.nextLine();
            // do make decision
            switch (userInput) {
                case "C":
                case "c":
                    //create new driver
                    doProceedCreatingNewDriver(inputScanner);
                    break;
                case "D":
                case "d":
                    //delete driver
                    doProceedDeletingDriver(inputScanner);
                    break;
                case "H":
                case "h":
                    //editing driver team
                    doProceedEditingDriverTeam(inputScanner);
                    break;
                case "S":
                case "s":
                    //display driver statistics
                    doProceedDisplayDriverStatistic(inputScanner);
                    break;
                case "T":
                case "t":
                    //display driver table
                    doDisplayDriverTable();
                    break;
                case "R":
                case "r":
                    //adding a completed race
                    doProceedAddingCompletedRace(inputScanner);
                    break;
                case "E":
                case "e":
                    iterate = false;
                    break;
                default:
                    System.out.println("Please Enter valid argument!");
            }
        }
        //close the scanner
        inputScanner.close();
    }

    //display initial instructions
    private void displayInitialInstruction() {

        System.out.println("###### Welcome to Formula Championship Simulator ######"
                + "\n\n\t(1) To create a new Driver, press 'C'"
                + "\n\t(2) To delete an existing Driver, press 'D'"
                + "\n\t(3) To change an existing Driver Team, press 'H'"
                + "\n\t(4) To display statistics for an existing Driver, press 'S'"
                + "\n\t(5) To display Formula Driver Table for the season, press 'T'"
                + "\n\t(6) To add a race, press 'R'"
                + "\n\t(7) To exit, press 'E'"
                + "\n\n#######################################################");
    }

    //crete a new driver
    private void doProceedCreatingNewDriver(Scanner inputScanner) {

        Driver driver = new Formula1Driver();
        Team team = new Team();

        //setting the driver identification
        driver.setDriverId(ApplicationUtility.doGenerateDriverIdentification());

        System.out.println("Enter Driver Name : ");
        driver.setDriverName(inputScanner.nextLine());

        System.out.println("Enter Driver Location : ");
        driver.setDriverLocation(inputScanner.nextLine());

        System.out.println("Enter assigned Team Name : ");
        team.setTeamName(inputScanner.nextLine());
        driver.setAssignedTeam(team);

        formula1ChampionshipManager.doCreateDriver(driver);

        System.out.println("Driver, " + driver.getDriverId() + " created successfully!");
        System.out.println();
    }

    //deleting a driver
    private void doProceedDeletingDriver(Scanner inputScanner) {

        System.out.println("Please Enter Driver Identification : ");
        String driverId = inputScanner.nextLine();

        formula1ChampionshipManager.doDeleteDriver(driverId);

        System.out.println("Driver, " + driverId + " was deleted successfully!");
        System.out.println();
    }

    //edit driver team
    private void doProceedEditingDriverTeam(Scanner inputScanner) {

        String driverIdentification;
        Team team = new Team();

        System.out.println("Please Enter Driver Identification : ");
        driverIdentification = inputScanner.nextLine();

        System.out.println("Please Enter new Team Name : ");
        team.setTeamName(inputScanner.nextLine());
        formula1ChampionshipManager.doEditDriverTeam(driverIdentification, team);

        System.out.println("New Team, " + team.getTeamName() + " assigned successfully!");
        System.out.println();
    }

    //display driver statistics
    private void doProceedDisplayDriverStatistic(Scanner inputScanner) {

        System.out.println("Please Enter Driver Identification : ");
        String driverIdentification = inputScanner.nextLine();

        Driver driver = formula1ChampionshipManager.doDisplayDriverStatistics(driverIdentification);

        System.out.println("Name : " + driver.getDriverName()
                + "\n\tLocation : " + driver.getDriverLocation()
                + "\n\tNumber of 1st positions : " + driver.getNumberOfFirstPositions()
                + "\n\tNumber of 2nd positions : " + driver.getNumberOfSecondPositions()
                + "\n\tNumber of 3rd positions : " + driver.getNumberOfThirdPositions()
                + "\n\tNumber of points earned: : " + driver.getNumberOfPointsEarned()
                + "\n\tNumber of races participated : " + driver.getNumberOfRacesParticipated()
                + "\n\tTeam assigned : " + driver.getAssignedTeam().getTeamName());
        System.out.println();
    }

    //display driver table
    private void doDisplayDriverTable() {

        List<Driver> drivers = formula1ChampionshipManager.doDisplayDriverTable();

        drivers.forEach(driver -> {
            System.out.println("[id] " + driver.getDriverId() + "  \t|\t  "
                    + "[name] " + driver.getDriverName() + "  \t|\t  "
                    + "[location] " + driver.getDriverLocation() + "  \t|\t  "
                    + "[1st] " + driver.getNumberOfFirstPositions() + "  \t|\t  "
                    + "[2nd] " + driver.getNumberOfSecondPositions() + "  \t|\t  "
                    + "[3rd] " + driver.getNumberOfThirdPositions() + "  \t|\t  "
                    + "[points] " + driver.getNumberOfPointsEarned() + "  \t|\t  "
                    + "[races] " + driver.getNumberOfRacesParticipated() + "  \t|\t  "
                    + "[team] " + driver.getAssignedTeam().getTeamName());
        });

        System.out.println();
    }

    //add a completed race
    private void doProceedAddingCompletedRace(Scanner inputScanner) {

        boolean iterate = true;
        Race race = new Race();
        race.setRaceId(ApplicationUtility.doGenerateRaceIdentification());

        System.out.println("Please enter date race was on (YYYY-MM-DD) : ");
        race.setHeldOn(LocalDate.parse(inputScanner.nextLine()));

        while (iterate) {

            System.out.println("Please enter driver ID : ");
            String driverId = inputScanner.nextLine();

            System.out.println("Please enter winning position : ");
            race.setWinningPositions(driverId, Integer.parseInt(inputScanner.nextLine()));

            System.out.println("Having more drivers (Y/N) : ");
            String input = inputScanner.nextLine();
            if (input.equals("N") || input.equals("n")) {
                iterate = false;
            }
        }

        formula1ChampionshipManager.doInsertRaceCompleted(race);
        System.out.println();
    }
}
