package com.assignment.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstraction of Driver
 */
public abstract class Driver {

    protected String driverId;
    protected String driverName;
    protected String driverLocation;
    protected Team assignedTeam;

    public Driver() {
    }

    public Driver(String driverId, String driverName, String driverLocation,Team assignedTeam) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.assignedTeam = assignedTeam;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public Team getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public abstract Integer getNumberOfFirstPositions();

    public abstract void setNumberOfFirstPositions(Integer numberOfFirstPositions);

    public abstract Integer getNumberOfSecondPositions();

    public abstract void setNumberOfSecondPositions(Integer numberOfSecondPositions);

    public abstract Integer getNumberOfThirdPositions();

    public abstract void setNumberOfThirdPositions(Integer numberOfThirdPositions);

    public abstract Integer getNumberOfPointsEarned();

    public abstract void setNumberOfPointsEarned(Integer numberOfPointsEarned);

    public abstract Integer getNumberOfRacesParticipated();

    public abstract void setNumberOfRacesParticipated(Integer numberOfRacesParticipated);

    @Override
    public String toString() {
        return "Driver" +
                "," + driverId +
                "," + driverName +
                "," + driverLocation +
                "," + assignedTeam;
    }
}
