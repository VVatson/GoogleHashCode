package com.google.hashcode;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 01.03.2018.
 */
public class VehicleState {

    Pair<Integer, Integer> curDst;
    Integer distToCurDst;
    List<Drive> drivesToServe = new ArrayList<>();
    List<Drive> completedDrives = new ArrayList<>();

    void addDrive(Drive drive) {
    }

    void move() {
        
    }
}
