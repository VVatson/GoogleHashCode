package com.google.hashcode;

import com.google.hashcode.objects.Drive;
import com.google.hashcode.objects.Vehicle;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Dispatcher implementation.
 * To distribute rides to vehicles.
 */
public class DefaultDispatcher implements Dispatcher {

    private final List<Drive> drivesToServe;
    private final Process process;

    public DefaultDispatcher(final List<Drive> drivesToServe, final Process process) {
        this.drivesToServe = drivesToServe;
        this.process = process;
    }

    @Override
    public Drive getNewRide(final Vehicle vehicle) {
        Pair<Integer, Integer> curPos = vehicle.getCurrentPos();
        List<Drive> feasibleDrives = drivesToServe.stream()
                .filter(drive -> process.getCurrentStep() +
                        Distance.calculate(curPos, drive.getSrc()) +
                        Distance.calculate(drive.getSrc(), drive.getDst()) < drive.getLatestArrival())
                .collect(Collectors.toList());
        Drive chosen = chooseDrive(vehicle, feasibleDrives);
        this.drivesToServe.remove(chosen);
        return chosen;
    }

    private Drive chooseDrive(final Vehicle vehicle, final List<Drive> drives) {
        Integer expensiveDrive = Integer.MIN_VALUE;
        Drive optimalDrive = null;

        // get 10 nearest rides
        TreeSet<Drive> sortedDrives = new TreeSet(new DriveComparator(vehicle));
        sortedDrives.addAll(drives);
        Integer size = Math.min(10, sortedDrives.size());
        List<Drive> nearestDrives = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            nearestDrives.add(sortedDrives.pollFirst());
        }

        // get a more expensive ride from 10 rides
        for (Drive drive : nearestDrives) {
            Integer cost = Distance.calculate(drive);
            if (cost > expensiveDrive) {
                expensiveDrive = cost;
                optimalDrive = drive;
            }
        }
        return optimalDrive;
    }

    class DriveComparator implements Comparator<Drive>{

        private Vehicle vehicle;

        public DriveComparator(final Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        @Override
        public int compare(final Drive drive1, final Drive drive2) {
            return Integer.compare(
                    Math.max(
                            drive1.getEarliestStart() - process.getCurrentStep(),
                            Distance.calculate(vehicle.getCurrentPos(), drive1.getSrc())
                    ),
                    Math.max(
                            drive2.getEarliestStart() - process.getCurrentStep(),
                            Distance.calculate(vehicle.getCurrentPos(), drive2.getSrc())
                    )
            );
        }
    }
}
