package com.google.hashcode.objects;

import com.google.hashcode.Dispatcher;
import com.google.hashcode.Distance;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.google.hashcode.objects.VehicleState.*;

/**
 * Self-driving vehicle object.
 */
public class Vehicle {

    private static final Pair<Integer, Integer> STARTING_POINT = Pair.of(0, 0);

    private Integer distToCurDst;
    private List<Drive> completedDrives = new ArrayList<>();
    private Drive curDrive;
    private Drive nextDrive;
    private Dispatcher dispatcher;
    private VehicleState state;

    public Vehicle(Dispatcher dispatcher) {
        state = INITIAL_STATE;
        this.dispatcher = dispatcher;
        nextDrive = dispatcher.getNewRide(this);
        if (nextDrive == null) {
            state = DISABLED;
        }
    }

    public List<Drive> getCompletedDrives() {
        return completedDrives;
    }

    public void setState(final VehicleState state) {
        this.state = state;
    }

    public void move() {
        switch(state) {

            case DISABLED:
                break;

            case INITIAL_STATE:
                Pair<Integer, Integer> nextDriveSrc = nextDrive.getSrc();
                if (STARTING_POINT.equals(nextDriveSrc)) {
                    curDrive = nextDrive;
                    setState(GO_TO_DESTINATION);
                    distToCurDst = Distance.calculate(curDrive);
                } else {
                    setState(GO_TO_SOURCE);
                    distToCurDst = Distance.calculate(STARTING_POINT, nextDriveSrc);
                }
                break;

            case GO_TO_SOURCE:
                distToCurDst--;
                if (distToCurDst == 0) {
                    curDrive = nextDrive;
                    setState(GO_TO_DESTINATION);
                    distToCurDst = Distance.calculate(curDrive);
                }
                break;

            case GO_TO_DESTINATION:
                distToCurDst--;
                if (distToCurDst == 0) {
                    completedDrives.add(curDrive);
                    nextDrive = dispatcher.getNewRide(this);
                    if (nextDrive == null) {
                        setState(DISABLED);
                        break;
                    }
                    if (curDrive.getDst().equals(nextDrive.getSrc())) {
                        curDrive = nextDrive;
                        setState(GO_TO_DESTINATION);
                        distToCurDst = Distance.calculate(curDrive);
                    } else {
                        setState(GO_TO_SOURCE);
                        distToCurDst = Distance.calculate(curDrive.getDst(), nextDrive.getSrc());
                    }
                }
                break;

            default:
                throw new RuntimeException("UNKNOWN STATE!");
        }
    }

    public Pair<Integer, Integer> getCurrentPos() {
        switch(state) {
            case INITIAL_STATE:
                return STARTING_POINT;
            case GO_TO_DESTINATION:
                return curDrive.getDst();
            default:
                throw new RuntimeException("The vehicle position is unknown in the current state!");
        }
    }
}
