package com.google.hashcode;

import com.google.hashcode.data.Input;
import com.google.hashcode.data.Output;
import com.google.hashcode.objects.Library;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulation object is designed to move each vehicle at each step.
 */
public class Simulation {

    private List<Library> libraries = new ArrayList<>();
    private Integer daysNum;
    private Dispatcher dispatcher;
    private int step;

    public Simulation(final Input input) {
        dispatcher = new DefaultDispatcher(input.libraries, this);

        for (int i = 0; i < input.libraryNum; i++) {
            libraries.add(new Library(dispatcher));
        }
        this.daysNum = input.daysNum;
    }

    public Output run() {
        for (step = 0; step < daysNum; step++) {

            libraries.forEach(Library::chooseBooksToShip);
        }
        return Output.fromVehicles(this.vehicles);
    }

    public Integer getCurrentStep() {
        return step;
    }
}
