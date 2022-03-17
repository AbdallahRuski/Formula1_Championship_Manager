package com.assignment.model;

import java.util.List;

/**
 * A specialization Formula1 Driver
 */
public final class Formula1Driver extends Driver {

    private Integer numberOfFirstPositions = 0;
    private Integer numberOfSecondPositions = 0;
    private Integer numberOfThirdPositions = 0;
    private Integer numberOfPointsEarned = 0;
    private Integer numberOfRacesParticipated = 0;

    public Formula1Driver() {

    }

    public Formula1Driver(String driverId, String driverName, String driverLocation,Team assignedTeam) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.assignedTeam = assignedTeam;
    }

    public Formula1Driver(String driverId, String driverName, String driverLocation, Team assignedTeam,
                          Integer numberOfFirstPositions, Integer numberOfSecondPositions, Integer numberOfThirdPositions,
                          Integer numberOfPointsEarned, Integer numberOfRacesParticipated) {
        super(driverId, driverName, driverLocation, assignedTeam);
        this.numberOfFirstPositions = numberOfFirstPositions;
        this.numberOfSecondPositions = numberOfSecondPositions;
        this.numberOfThirdPositions = numberOfThirdPositions;
        this.numberOfPointsEarned = numberOfPointsEarned;
        this.numberOfRacesParticipated = numberOfRacesParticipated;
    }

    public Integer getNumberOfFirstPositions() {
        return numberOfFirstPositions;
    }

    public void setNumberOfFirstPositions(Integer numberOfFirstPositions) {
        this.numberOfFirstPositions = numberOfFirstPositions;
    }

    public Integer getNumberOfSecondPositions() {
        return numberOfSecondPositions;
    }

    public void setNumberOfSecondPositions(Integer numberOfSecondPositions) {
        this.numberOfSecondPositions = numberOfSecondPositions;
    }

    public Integer getNumberOfThirdPositions() {
        return numberOfThirdPositions;
    }

    public void setNumberOfThirdPositions(Integer numberOfThirdPositions) {
        this.numberOfThirdPositions = numberOfThirdPositions;
    }

    public Integer getNumberOfPointsEarned() {
        return numberOfPointsEarned;
    }

    public void setNumberOfPointsEarned(Integer numberOfPointsEarned) {
        this.numberOfPointsEarned = numberOfPointsEarned;
    }

    public Integer getNumberOfRacesParticipated() {
        return numberOfRacesParticipated;
    }

    public void setNumberOfRacesParticipated(Integer numberOfRacesParticipated) {
        this.numberOfRacesParticipated = numberOfRacesParticipated;
    }

    @Override
    public String toString() {
        return "Formula1Driver" +
                "," + driverId +
                "," + driverName +
                "," + driverLocation +
                "," + assignedTeam.getTeamName() +
                "," + numberOfFirstPositions +
                "," + numberOfSecondPositions +
                "," + numberOfThirdPositions +
                "," + numberOfPointsEarned +
                "," + numberOfRacesParticipated;
    }
}
