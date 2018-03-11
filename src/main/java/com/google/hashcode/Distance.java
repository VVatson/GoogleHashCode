package com.google.hashcode;

import com.google.hashcode.objects.Drive;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Distance.
 * Helps to calculate the destination.
 */
public class Distance {
    public static Integer calculate(Pair<Integer, Integer> startingPoint, Pair<Integer, Integer> curDst) {
        return Math.abs(startingPoint.getLeft() - curDst.getLeft()) +
                Math.abs(startingPoint.getRight() - curDst.getRight());
    }

    public static Integer calculate(Drive drive) {
        return Math.abs(drive.getSrc().getLeft() - drive.getDst().getLeft()) +
                Math.abs(drive.getSrc().getRight() - drive.getDst().getRight());
    }
}
