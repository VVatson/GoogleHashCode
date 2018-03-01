package com.google.hashcode;

import com.google.hashcode.data.Output;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 01.03.2018.
 */
public class Simulation {

    List<VehicleState> vehicles = new ArrayList<>();
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
        for (step = 0; step < numSteps; step++) {
            try {
                vehicles.forEach(VehicleState::move);
            } catch (Exception e) {
                break;
            }

        }
        String string = new Output(this.vehicles).toString();
        System.out.println(string);
    }

    public static Integer getCurrentStep() {
        return step;
    }
}
