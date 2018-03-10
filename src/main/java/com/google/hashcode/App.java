package com.google.hashcode;

import com.google.hashcode.data.InputData;
import com.google.hashcode.data.Output;

public class App {
    private final static String [] FILES = {
            "a_example.in",
            "b_should_be_easy.in",
            "c_no_hurry.in",
            "d_metropolis.in",
            "e_high_bonus.in"
    };

    public static void main(String[] args) {
        for (String file : FILES) {
            InputData inputData = FileHandler.read(file);

            Simulation simulation = new Simulation(inputData);
            Output output = simulation.run();

            FileHandler.write(file.replace(".in", ".out"), output);
        }
    }
}