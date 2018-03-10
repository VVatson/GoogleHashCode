package com.google.hashcode;

import com.google.hashcode.data.InputData;
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

    public Simulation(InputData inputData) {
        dispatcher = new DefaultDispatcher(inputData.getRides());

        for (int i = 0; i < inputData.getNumberVehicles(); i++) {
            vehicles.add(new VehicleState(dispatcher));
        }
        this.numSteps = inputData.getNumberSimulationSteps();
    }

    public Output run() {
        for (step = 0; step < numSteps; step++) {
            try {
                vehicles.forEach(VehicleState::move);
            } catch (Exception e) {
                break;
            }

        }
        return Output.fromVehicles(this.vehicles);
    }

    public static Integer getCurrentStep() {
        return step;
    }
}
