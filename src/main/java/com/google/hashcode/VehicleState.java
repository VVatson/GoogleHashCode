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
    List<Drive> drivesToServe = new ArrayList<>();
    List<Drive> completedDrives = new ArrayList<>();
    private Drive curDrive;

    public VehicleState() {

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

    public List<Drive> getDrivesToServe() {
        return drivesToServe;
    }

    public List<Drive> getCompletedDrives() {
        return completedDrives;
    }

    public Drive getCurDrive() {
        return curDrive;
    }

    void addDrive(Drive drive) {
        if (drivesToServe.isEmpty()) {
            curDst = drive.getSrc();
            distToCurDst = Distance.calculate(STARTING_POINT, curDst);
        }
        drivesToServe.add(drive);
    }

    void move() {
        distToCurDst--;

        if (distToCurDst == 0) {
            Pair<Integer, Integer> curPos = curDst;

            if (curDrive == null) {
                curDrive = drivesToServe.remove(0);
                curDst = curDrive.getDst();
                distToCurDst = Distance.calculate(curPos, curDst);
            } else {
                completedDrives.add(curDrive);
                curDst = drivesToServe.get(0).getSrc();
                distToCurDst = Distance.calculate(curPos, curDst);
            }
        }
    }
}
