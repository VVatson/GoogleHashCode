package com.google.hashcode.data;

import com.google.hashcode.objects.Drive;

import java.util.List;

/**
 * Input data.
 */
public class Input {
    private final int numberRows;
    private final int numberColumns;
    private final int numberVehicles;
    private final int numberRides;
    private final int bonus;
    private final int numberSimulationSteps;
    private final List<Drive> rides;

    public int getNumberVehicles() {
        return numberVehicles;
    }

    public int getNumberSimulationSteps() {
        return numberSimulationSteps;
    }

    public List<Drive> getRides() {
        return rides;
    }

    public Input(final int numberRows, final int numberColumns,
                 final int numberVehicles, final int numberRides,
                 final int bonus, final int numberSimulationSteps,
                 final List<Drive> rides) {
        this.numberRows = numberRows;
        this.numberColumns = numberColumns;
        this.numberVehicles = numberVehicles;
        this.numberRides = numberRides;
        this.bonus = bonus;
        this.numberSimulationSteps = numberSimulationSteps;
        this.rides = rides;
    }
}
