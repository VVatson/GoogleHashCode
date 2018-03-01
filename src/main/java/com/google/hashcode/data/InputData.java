package com.google.hashcode.data;

import com.google.hashcode.Drive;

import java.util.Set;

/**
 * Created by Andrey on 01.03.2018.
 */
public class InputData {
    private final int numberRows;
    private final int numberColumns;
    private final int numberVehicles;
    private final int numberRides;
    private final int bonus;
    private final int numberSimulationSteps;
    private final Set<Drive> rides;

    public int getNumberRows() {
        return numberRows;
    }

    public int getNumberColumns() {
        return numberColumns;
    }

    public int getNumberVehicles() {
        return numberVehicles;
    }

    public int getNumberRides() {
        return numberRides;
    }

    public int getBonus() {
        return bonus;
    }

    public int getNumberSimulationSteps() {
        return numberSimulationSteps;
    }

    public Set<Drive> getRides() {
        return rides;
    }

    public InputData(int numberRows, int numberColumns,
                     int numberVehicles, int numberRides,
                     int bonus, int numberSimulationSteps,
                     Set<Drive> rides) {
        this.numberRows = numberRows;
        this.numberColumns = numberColumns;
        this.numberVehicles = numberVehicles;
        this.numberRides = numberRides;
        this.bonus = bonus;
        this.numberSimulationSteps = numberSimulationSteps;
        this.rides = rides;
    }
}
