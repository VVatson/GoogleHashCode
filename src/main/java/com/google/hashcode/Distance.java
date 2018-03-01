package com.google.hashcode;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Andrey on 01.03.2018.
 */
public class Distance {
    public static Integer calculate(Pair<Integer, Integer> startingPoint, Pair<Integer, Integer> curDst) {
        return Math.abs(startingPoint.getLeft() - curDst.getLeft()) +
                Math.abs(startingPoint.getRight() - curDst.getRight());
    }
}
