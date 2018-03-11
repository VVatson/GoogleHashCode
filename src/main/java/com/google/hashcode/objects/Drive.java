package com.google.hashcode.objects;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Drive object..
 */
public class Drive {
    private final Pair<Integer, Integer> src, dst;
    private final Integer earliestStart;
    private final Integer latestArrival;
    private final Integer numberDrive;

    public Integer getNumberDrive() {
        return numberDrive;
    }

    public Drive(final Pair<Integer, Integer> src, final Pair<Integer, Integer> dst,
                 final Integer earliestStart, final Integer latestArrival, final Integer numberDrive) {
        this.src = src;
        this.dst = dst;
        this.earliestStart = earliestStart;
        this.latestArrival = latestArrival;
        this.numberDrive = numberDrive;
    }

    public Pair<Integer, Integer> getSrc() {
        return src;
    }

    public Pair<Integer, Integer> getDst() {
        return dst;
    }

    public Integer getLatestArrival() {
        return latestArrival;
    }

    public Integer getEarliestStart() {
        return earliestStart;
    }
}
