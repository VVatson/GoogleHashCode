package com.google.hashcode.data;

import com.google.hashcode.Drive;
import com.google.hashcode.VehicleState;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andrey on 01.03.2018.
 */
public class Output {
    private List<VehicleState> states;

    public Output(final List<VehicleState> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Integer i = 0;
        for (VehicleState state : states) {
            i++;
            List<Drive> completedDrives = state.getCompletedDrives();

            if (completedDrives != null && completedDrives.size() != 0) {
                sb.append(i);
                for (Drive drive : completedDrives) {
                    sb.append(" ");
                    sb.append(drive.getNumberDrive());
                }
                sb.append(completedDrives.size());
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}

