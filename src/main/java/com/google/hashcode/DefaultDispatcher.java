package com.google.hashcode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andrey on 01.03.2018.
 */
public class DefaultDispatcher implements Dispatcher {

    List<Drive> drivesToServe;


    public DefaultDispatcher(List<Drive> drivesToServe) {
        this.drivesToServe = drivesToServe;
    }

    @Override
    public Drive getNewRide(VehicleState vehicleState) {

        List<Drive> futureDrivers = drivesToServe.stream().filter(drive -> drive.getEarliestStart().compareTo(Simulation.getCurrentStep()) >= 0).collect(Collectors.toList());
        List<Drive> feasibleDrives = futureDrivers.stream().filter(drive -> Simulation.getCurrentStep() + Distance.calculate(vehicleState.getCurrentPost(), drive.getSrc()) + Distance.calculate(drive.getSrc(), drive.getDst()) < drive.getLatestArrival()).collect(Collectors.toList());

    }
}
