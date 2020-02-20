package com.google.hashcode;

import com.google.hashcode.data.Input;
import com.google.hashcode.data.Output;
import com.google.hashcode.objects.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulation object is designed to move each vehicle at each step.
 */
public class Process {

    private List<Vehicle> vehicles = new ArrayList<>();
    private Integer numSteps;
    private Dispatcher dispatcher;
    private int step;

    public Process(final Input input) {
        dispatcher = new DefaultDispatcher(input.getRides(), this);

        for (int i = 0; i < input.getNumberVehicles(); i++) {
            vehicles.add(new Vehicle(dispatcher));
        }
        this.numSteps = input.getNumberSimulationSteps();
    }

    public Output run() {
        for (step = 0; step < numSteps; step++) {
            vehicles.forEach(Vehicle::move);
        }
        return Output.fromVehicles(this.vehicles);
    }

    public Integer getCurrentStep() {
        return step;
    }
}
