package com.google.hashcode;

import com.google.hashcode.data.Input;
import com.google.hashcode.data.Output;
import com.google.hashcode.objects.Library;
import com.google.hashcode.objects.LibraryProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulation object is designed to move each vehicle at each step.
 */
public class Simulation {

    private List<LibraryProcess> libraryProcesses = new ArrayList<>();
    private Integer daysNum;
    private Dispatcher dispatcher;
    private int step;

    public Simulation(final Input input) {
        dispatcher = new DefaultDispatcher(input.libraries, this);

        for (Library library : input.libraries) {
            libraryProcesses.add(new LibraryProcess(library, dispatcher));
        }
        this.daysNum = input.daysNum;
    }

    public Output run() {
        for (step = 0; step < daysNum; step++) {

            libraryProcesses.forEach(LibraryProcess::process);
        }
        return Output.fromVehicles(this.vehicles);
    }

    public Integer getCurrentStep() {
        return step;
    }
}
