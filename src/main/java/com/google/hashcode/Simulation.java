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
    static int step;

    public Simulation(Integer numVehicles, Integer numSteps, List<Drive> drivesToServe) {

        dispatcher = new DefaultDispatcher(drivesToServe);

        for (int i = 0; i < numVehicles; i++) {
            vehicles.add(new VehicleState(dispatcher));
        }
        this.numSteps = numSteps;
    }

    public void run() {
        for (int step = 0; step < numSteps; step++) {
            vehicles.forEach(VehicleState::move);
        }
    }

    public static Integer getCurrentStep() {
        return step;
    }
}
