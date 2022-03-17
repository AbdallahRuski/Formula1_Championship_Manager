package com.assignment.model;

public class Team {

    private String teamName;
    private Driver participatingDriver;

    public Team() {

    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, Driver participatingDriver) {
        this.teamName = teamName;
        this.participatingDriver = participatingDriver;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Driver getParticipatingDriver() {
        return participatingDriver;
    }

    public void setParticipatingDriver(Driver participatingDriver) {
        this.participatingDriver = participatingDriver;
    }

    @Override
    public String toString() {
        return "Team" +
                "," + teamName +
                "," + participatingDriver;
    }
}
