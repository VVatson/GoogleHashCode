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
    Pair<Integer, Integer> curPos;
    Integer distToCurDst;
    List<Drive> completedDrives = new ArrayList<>();
    private Drive curDrive;
    private Dispatcher dispatcher;
    private Drive nextDrive;

    public VehicleState(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        distToCurDst = 1;
        curPos = curDst = STARTING_POINT;
        nextDrive = dispatcher.getNewRide(this);
    }

    public static Pair<Integer, Integer> getStartingPoint() {
        return STARTING_POINT;
    }

    public Pair<Integer, Integer> getCurDst() {
        return curDst;
    }

    public Integer getDistToCurDst() {
        return distToCurDst;
    }

    public List<Drive> getCompletedDrives() {
        return completedDrives;
    }

    public Drive getCurDrive() {
        return curDrive;
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
                this.curPos = curPos;
                nextDrive = dispatcher.getNewRide(this);
                curDst = nextDrive.getSrc();
                distToCurDst = Distance.calculate(curPos, curDst);
            }
        }
    }

    public Pair<Integer, Integer> getCurrentPos() {
        return this.curPos;
    }
}
