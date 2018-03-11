package com.google.hashcode.data;

import com.google.hashcode.objects.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Output data that contains a schedule of the rides.
 */
public class Output {

    private List<List<Integer>> schedule;

    public Output(final List<List<Integer>> schedule) {
        this.schedule = schedule;
    }

    public static Output fromVehicles(List<Vehicle> vehicles) {
        List<List<Integer>> schedule = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            schedule.add(vehicle.getCompletedDrives().stream()
                    .sequential()
                    .map(drive -> drive.getNumberDrive())
                    .collect(Collectors.toList()));
        }
        return new Output(schedule);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (List<Integer> rides : schedule) {
            if (rides != null && !rides.isEmpty())
            sb.append(rides.size());
            sb.append(" ");

            String ridesGapSeparated = rides.stream()
                    .sequential()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            sb.append(ridesGapSeparated);
            sb.append("\n");

        }
        return sb.toString();
    }
}

