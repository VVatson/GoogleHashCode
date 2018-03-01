package com.google.hashcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrey on 01.03.2018.
 */
public class Simulation {

    Set<VehicleState> vehicles = new HashSet<>();
    Integer numSteps;
    Dispatcher dispatcher;

    public Simulation(Integer numVehicles, Integer numSteps, List<Drive> drivesToServe) {
        for (int i = 0; i < numVehicles; i++) {
            vehicles.add(new VehicleState(dispatcher));
        }
        this.numSteps = numSteps;
    }

    public void run() {
        for (int i = 0; i < numSteps; i++) {
            vehicles.forEach(VehicleState::move);
        }
    }
}
