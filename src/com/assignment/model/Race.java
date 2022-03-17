package com.assignment.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Race {

    private String raceId;
    private LocalDate heldOn;
    private Map<String, Integer> winningPositions = new HashMap<>();

    public Race() {

    }

    public Race(String raceId, LocalDate heldOn) {
        this.raceId = raceId;
        this.heldOn = heldOn;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public LocalDate getHeldOn() {
        return heldOn;
    }

    public void setHeldOn(LocalDate heldOn) {
        this.heldOn = heldOn;
    }

    public Map<String, Integer> getWinningPositions() {
        return winningPositions;
    }

    public void setWinningPositions(String driverId, Integer winningPositions) {
        this.winningPositions.put(driverId, winningPositions);
    }

    @Override
    public String toString() {
        return "Race" +
                ";" + raceId +
                ";" + heldOn +
                ";" + winningPositions;
    }
}
