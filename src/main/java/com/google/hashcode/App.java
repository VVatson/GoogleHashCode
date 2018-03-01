package com.google.hashcode;

import com.google.hashcode.data.InputData;

public class App {
    public static void main(String[] args) {
        InputData inputData = FileHandler.read("e_high_bonus.in");
        Simulation simulation = new Simulation(inputData.getNumberVehicles(), inputData.getNumberSimulationSteps(),
                inputData.getRides());
        simulation.run();
    }
}