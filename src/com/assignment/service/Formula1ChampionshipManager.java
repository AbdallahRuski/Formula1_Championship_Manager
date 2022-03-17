package com.assignment.service;

import com.assignment.model.Driver;
import com.assignment.model.Formula1Driver;
import com.assignment.model.Race;
import com.assignment.model.Team;
import com.assignment.utility.ApplicationUtility;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * An implementation of championship manager
 */
public class Formula1ChampionshipManager implements ChampionshipManager {

    //for storing participating drivers
    private Map<String, Driver> participatingDrivers = new HashMap<>();

    //for storing completed races
    private Map<String, Race> racesCompleted = new HashMap<>();

    //for persisting data
    private File file;

    public Formula1ChampionshipManager() {
        //load file
        this.file = new File("./resource/backup.txt");
        //load data to memory from file
        restoreDataFromFile();
    }

    @Override
    public void doCreateDriver(Driver driver) {
        //store data on memory
        participatingDrivers.put(driver.getDriverId(), driver);
        //store data in file
        ApplicationUtility.doWriteFile(file, driver.toString());
    }

    @Override
    public void doDeleteDriver(String driverId) {
        //remove from memory
        participatingDrivers.remove(driverId);
        //clean file
        ApplicationUtility.doCleanFile(file);
        //update file as a bulk
        ApplicationUtility.doWriteFile(file, new ArrayList<>(participatingDrivers.values()));
    }

    @Override
    public void doEditDriverTeam(String driverId, Team team) {
        //edit in memory
        Driver driver = participatingDrivers.get(driverId);
        driver.setAssignedTeam(team);
        //clean file
        ApplicationUtility.doCleanFile(file);
        //update file as a bulk
        ApplicationUtility.doWriteFile(file, new ArrayList<>(participatingDrivers.values()));
    }

    @Override
    public Driver doDisplayDriverStatistics(String driverId) {
        return participatingDrivers.get(driverId);
    }

    @Override
    public List<Driver> doDisplayDriverTable() {

        List<Driver> driverList = new ArrayList<>(participatingDrivers.values());

        //sorting records according to points & number of first position attributes in drivers
        driverList.sort(Comparator.comparing(Driver::getNumberOfPointsEarned).thenComparing(
                Driver::getNumberOfFirstPositions).reversed());

        return driverList;
    }

    @Override
    public void doInsertRaceCompleted(Race race) {

        //add races
        racesCompleted.put(race.getRaceId(), race);

        //calculate & store driver statistics
        racesCompleted.forEach((raceId, raceCompleted) -> {
            raceCompleted.getWinningPositions().forEach((driverId, position) -> {
                //fetch relevant driver
                Driver driver = participatingDrivers.get(driverId);
                //increment number of winning positions for first place
                if (position == 1) {
                    driver.setNumberOfFirstPositions(driver.getNumberOfFirstPositions() + 1);
                }
                //increment number of winning positions for second place
                if (position == 2) {
                    driver.setNumberOfSecondPositions(driver.getNumberOfSecondPositions() + 1);
                }
                //increment number of winning positions for third place
                if (position == 3) {
                    driver.setNumberOfThirdPositions(driver.getNumberOfThirdPositions() + 1);
                }
                //set number of points earned & number of races participated
                driver.setNumberOfPointsEarned(driver.getNumberOfPointsEarned() + ApplicationUtility.doGetPointsByPosition(position));
                driver.setNumberOfRacesParticipated(driver.getNumberOfRacesParticipated() + 1);
            });
        });

        //clean file
        ApplicationUtility.doCleanFile(file);
        //update file as a bulk, drivers
        ApplicationUtility.doWriteFile(file, new ArrayList<>(participatingDrivers.values()));
        //update file as a bulk, races
        ApplicationUtility.doWriteFile(file, new ArrayList<>(racesCompleted.values()));
    }

    @Override
    public String[][] doGetDriversStatByPointsOnDesc() {

        String[][] driversArray = new String[participatingDrivers.size()][9];
        List<Driver> driverList = new ArrayList<>(participatingDrivers.values());

        //sorting records based on points earned, descending order
        driverList.sort(Comparator.comparing(Driver::getNumberOfPointsEarned).reversed());

        //populating driver array
        AtomicInteger count = new AtomicInteger(0);
        driverList.forEach(driver -> {
            driversArray[count.intValue()][0] = driver.getDriverId();
            driversArray[count.intValue()][1] = driver.getDriverName();
            driversArray[count.intValue()][2] = driver.getDriverLocation();
            driversArray[count.intValue()][3] = Integer.toString(driver.getNumberOfFirstPositions());
            driversArray[count.intValue()][4] = Integer.toString(driver.getNumberOfSecondPositions());
            driversArray[count.intValue()][5] = Integer.toString(driver.getNumberOfThirdPositions());
            driversArray[count.intValue()][6] = Integer.toString(driver.getNumberOfPointsEarned());
            driversArray[count.intValue()][7] = Integer.toString(driver.getNumberOfRacesParticipated());
            driversArray[count.intValue()][8] = driver.getAssignedTeam().getTeamName();
            count.getAndIncrement();
        });

        return driversArray;
    }

    @Override
    public String[][] doSearchDriverPositionOnRace(String driverId) {

        String[][] searchResult = new String[racesCompleted.size()][3];
        List<Race> races = new ArrayList<>(racesCompleted.values());

        //find winning position for driver
        AtomicInteger count = new AtomicInteger(0);
        races.forEach(race -> {
            if (race.getWinningPositions().containsKey(driverId)) {
                searchResult[count.intValue()][0] = race.getRaceId();
                searchResult[count.intValue()][1] = race.getHeldOn().toString();
                searchResult[count.intValue()][2] = Integer.toString(race.getWinningPositions().get(driverId));
                count.getAndIncrement();
            }
        });

        return searchResult;
    }

    @Override
    public String[][] doSortDriversByPoints() {

        String[][] driversArray = new String[participatingDrivers.size()][9];
        List<Driver> driverList = new ArrayList<>(participatingDrivers.values());

        //sorting records based on points earned, ascending order
        driverList.sort(Comparator.comparing(Driver::getNumberOfPointsEarned));

        //populating driver array
        AtomicInteger count = new AtomicInteger(0);
        driverList.forEach(driver -> {
            driversArray[count.intValue()][0] = driver.getDriverId();
            driversArray[count.intValue()][1] = driver.getDriverName();
            driversArray[count.intValue()][2] = driver.getDriverLocation();
            driversArray[count.intValue()][3] = Integer.toString(driver.getNumberOfFirstPositions());
            driversArray[count.intValue()][4] = Integer.toString(driver.getNumberOfSecondPositions());
            driversArray[count.intValue()][5] = Integer.toString(driver.getNumberOfThirdPositions());
            driversArray[count.intValue()][6] = Integer.toString(driver.getNumberOfPointsEarned());
            driversArray[count.intValue()][7] = Integer.toString(driver.getNumberOfRacesParticipated());
            driversArray[count.intValue()][8] = driver.getAssignedTeam().getTeamName();
            count.getAndIncrement();
        });

        return driversArray;
    }

    @Override
    public String[][] doSortDriversByPosition() {

        String[][] driversArray = new String[participatingDrivers.size()][9];
        List<Driver> driverList = new ArrayList<>(participatingDrivers.values());

        //sorting records based on largest number of first positions
        driverList.sort(Comparator.comparing(Driver::getNumberOfFirstPositions).reversed());

        //populating driver array
        AtomicInteger count = new AtomicInteger(0);
        driverList.forEach(driver -> {
            driversArray[count.intValue()][0] = driver.getDriverId();
            driversArray[count.intValue()][1] = driver.getDriverName();
            driversArray[count.intValue()][2] = driver.getDriverLocation();
            driversArray[count.intValue()][3] = Integer.toString(driver.getNumberOfFirstPositions());
            driversArray[count.intValue()][4] = Integer.toString(driver.getNumberOfSecondPositions());
            driversArray[count.intValue()][5] = Integer.toString(driver.getNumberOfThirdPositions());
            driversArray[count.intValue()][6] = Integer.toString(driver.getNumberOfPointsEarned());
            driversArray[count.intValue()][7] = Integer.toString(driver.getNumberOfRacesParticipated());
            driversArray[count.intValue()][8] = driver.getAssignedTeam().getTeamName();
            count.getAndIncrement();
        });

        return driversArray;
    }

    @Override
    public Map<String, Race> doGetRacesCompleted() {
        return this.racesCompleted;
    }

    @Override
    public Map<String, String[][]> doGenerateRandomRace() {

        Map<String, String[][]> output = new HashMap<>();
        String[][] driversArray = new String[participatingDrivers.size()][9];
        String[][] raceArray = new String[participatingDrivers.size()][4];

        //random race
        Race randomRace = new Race(ApplicationUtility.doGenerateRaceIdentification(), LocalDate.now());

        //generate random winning positions
        this.participatingDrivers.forEach((id, driver) -> {
            Random random = new Random();
            int winningPosition = random.nextInt(participatingDrivers.size());
            randomRace.setWinningPositions(id, winningPosition == 0 ? 1 : winningPosition);
        });

        //store newly created race in system
        doInsertRaceCompleted(randomRace);

        List<Driver> driverList = new ArrayList<>(participatingDrivers.values());
        //sorting records based on points earned, descending order
        driverList.sort(Comparator.comparing(Driver::getNumberOfPointsEarned).reversed());

        //assemble drivers output
        AtomicInteger count = new AtomicInteger(0);
        driverList.forEach(driver -> {
            driversArray[count.intValue()][0] = driver.getDriverId();
            driversArray[count.intValue()][1] = driver.getDriverName();
            driversArray[count.intValue()][2] = driver.getDriverLocation();
            driversArray[count.intValue()][3] = Integer.toString(driver.getNumberOfFirstPositions());
            driversArray[count.intValue()][4] = Integer.toString(driver.getNumberOfSecondPositions());
            driversArray[count.intValue()][5] = Integer.toString(driver.getNumberOfThirdPositions());
            driversArray[count.intValue()][6] = Integer.toString(driver.getNumberOfPointsEarned());
            driversArray[count.intValue()][7] = Integer.toString(driver.getNumberOfRacesParticipated());
            driversArray[count.intValue()][8] = driver.getAssignedTeam().getTeamName();
            count.getAndIncrement();
        });

        //assemble races output
        count.set(0);
        randomRace.getWinningPositions().forEach((driverId, position) -> {
            raceArray[count.intValue()][0] = randomRace.getRaceId();
            raceArray[count.intValue()][1] = randomRace.getHeldOn().toString();
            raceArray[count.intValue()][2] = driverId;
            raceArray[count.intValue()][3] = Integer.toString(position);
            count.getAndIncrement();
        });

        //populating the output map
        output.put("DRIVERS_OUTPUT", driversArray);
        output.put("RACE_OUTPUT",raceArray);

        return output;
    }

    @Override
    public Map<String, String[][]> doGenerateIntelligentRace() {

        Map<String, String[][]> output = new HashMap<>();
        String[][] driversArray = new String[participatingDrivers.size()][9];
        String[][] raceArray = new String[participatingDrivers.size()][4];

        //random race
        Race randomRace = new Race(ApplicationUtility.doGenerateRaceIdentification(), LocalDate.now());

        //generate random starter positions
        Map<String, Integer> randomStarterPositions = new HashMap<>();
        this.participatingDrivers.forEach((id, driver) -> {
            Random random = new Random();
            int randomStarterPosition = random.nextInt(participatingDrivers.size());
            randomStarterPositions.put(id, randomStarterPosition == 0 ? 1 : randomStarterPosition);
        });

        //assigning winning positions by starter positions
        randomStarterPositions.forEach((driverId, starterPosition) -> {
            Random random = new Random();
            if (starterPosition == 1) {
                randomRace.setWinningPositions(driverId, 1);
            } else {
                int winningPosition = random.nextInt(participatingDrivers.size());
                randomRace.setWinningPositions(driverId, winningPosition == 0 ? 1 : winningPosition);
            }
        });

        //store newly created race in system
        doInsertRaceCompleted(randomRace);

        List<Driver> driverList = new ArrayList<>(participatingDrivers.values());
        //sorting records based on points earned, descending order
        driverList.sort(Comparator.comparing(Driver::getNumberOfPointsEarned).reversed());

        //assemble drivers output
        AtomicInteger count = new AtomicInteger(0);
        driverList.forEach(driver -> {
            driversArray[count.intValue()][0] = driver.getDriverId();
            driversArray[count.intValue()][1] = driver.getDriverName();
            driversArray[count.intValue()][2] = driver.getDriverLocation();
            driversArray[count.intValue()][3] = Integer.toString(driver.getNumberOfFirstPositions());
            driversArray[count.intValue()][4] = Integer.toString(driver.getNumberOfSecondPositions());
            driversArray[count.intValue()][5] = Integer.toString(driver.getNumberOfThirdPositions());
            driversArray[count.intValue()][6] = Integer.toString(driver.getNumberOfPointsEarned());
            driversArray[count.intValue()][7] = Integer.toString(driver.getNumberOfRacesParticipated());
            driversArray[count.intValue()][8] = driver.getAssignedTeam().getTeamName();
            count.getAndIncrement();
        });

        //assemble races output
        count.set(0);
        randomRace.getWinningPositions().forEach((driverId, position) -> {
            raceArray[count.intValue()][0] = randomRace.getRaceId();
            raceArray[count.intValue()][1] = randomRace.getHeldOn().toString();
            raceArray[count.intValue()][2] = driverId;
            raceArray[count.intValue()][3] = Integer.toString(position);
            count.getAndIncrement();
        });

        //populating the output map
        output.put("DRIVERS_OUTPUT", driversArray);
        output.put("RACE_OUTPUT",raceArray);

        return output;
    }

    //read the file & populate content
    private void restoreDataFromFile() {

        //read file & loading records to memory
        ApplicationUtility.doReadFile(this.file).forEach(index -> {
            //loading the driver related records to memory
            if (index.startsWith("Formula1Driver")) {
                Team team = new Team(index.split(",")[4]);
                Driver driver = new Formula1Driver(index.split(",")[1], index.split(",")[2],
                        index.split(",")[3], team, Integer.parseInt(index.split(",")[5]),
                        Integer.parseInt(index.split(",")[6]), Integer.parseInt(index.split(",")[7]),
                        Integer.parseInt(index.split(",")[8]), Integer.parseInt(index.split(",")[9]));
                participatingDrivers.put(driver.getDriverId(), driver);
            }
            //loading the race related records to the memory
            else if (index.startsWith("Race")) {
                Race race = new Race(index.split(";")[1], LocalDate.parse(index.split(";")[2]));
                String[] winningsArr = index.split(";")[3].split(",");
                for (String record : winningsArr) {
                    if (record.startsWith("{")) {
                        race.setWinningPositions(record.substring(1).split("=")[0],
                                Integer.parseInt(record.substring(1).split("=")[1]));
                    } else if (record.endsWith("}")) {
                        final String substring = record.trim().substring(0, (record.length() - 2));
                        race.setWinningPositions(substring.split("=")[0],
                                Integer.parseInt(substring.split("=")[1]));
                    } else {
                        race.setWinningPositions(record.trim().split("=")[0],
                                Integer.parseInt(record.trim().split("=")[1]));
                    }
                }
                racesCompleted.put(race.getRaceId(), race);
            }
        });
    }
}
