package com.google.hashcode;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 01.03.2018.
 */
public class VehicleState {

    private static final Pair<Integer, Integer> STARTING_POINT = Pair.of(0, 0);

    Pair<Integer, Integer> curDst;
    Integer distToCurDst;
    List<Drive> completedDrives = new ArrayList<>();
    private Drive curDrive;
    private Dispatcher dispatcher;
    private Drive nextDrive;

    public VehicleState(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        distToCurDst = 1;
        curDst = STARTING_POINT;
        nextDrive = dispatcher.getNewRide(this);
    }

    void move() {
        distToCurDst--;

        if (distToCurDst == 0) {
            Pair<Integer, Integer> curPos = curDst;

            if (curDrive == null) {
                curDrive = nextDrive;
                curDst = curDrive.getDst();
                distToCurDst = Distance.calculate(curPos, curDst);
            } else {
                completedDrives.add(curDrive);
                curDrive = null;
                nextDrive = dispatcher.getNewRide(this);
                curDst = nextDrive.getSrc();
                distToCurDst = Distance.calculate(curPos, curDst);
            }
        }
    }
}
