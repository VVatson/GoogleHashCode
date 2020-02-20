package com.google.hashcode;

import com.google.hashcode.objects.Book;
import com.google.hashcode.objects.Drive;
import com.google.hashcode.objects.Library;
import com.google.hashcode.objects.Vehicle;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Dispatcher implementation.
 * To distribute rides to vehicles.
 */
public class DefaultDispatcher implements Dispatcher {

    private final List<Library> libraries;
    private final Set<Book> alreadyShippedBooks;
    private final Simulation simulation;

    public DefaultDispatcher(List<Library> libraries, final Simulation simulation) {
        this.libraries = libraries;
        this.simulation = simulation;
        this.alreadyShippedBooks = new HashSet<>();
    }

    @Override
    public Drive getNewRide(final Vehicle vehicle) {
        Pair<Integer, Integer> curPos = vehicle.getCurrentPos();
        List<Drive> feasibleDrives = drivesToServe.stream()
                .filter(drive -> simulation.getCurrentStep() +
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

    class LibraryComparator implements Comparator<Library>{

        private Library library;

        public LibraryComparator(Library library) {
            this.library = library;
        }

        @Override
        public int compare(Library library1, Library library2) {
            return Integer.compare(
                    Math.max(
                            library1.getEarliestStart() - simulation.getCurrentStep(),
                            Distance.calculate(vehicle.getCurrentPos(), library2.getSrc())
                    ),
                    Math.max(
                            library2.getEarliestStart() - simulation.getCurrentStep(),
                            Distance.calculate(vehicle.getCurrentPos(), library2.getSrc())
                    )
            );
        }
    }
}
