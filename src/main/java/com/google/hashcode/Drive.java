package com.google.hashcode;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Andrey on 01.03.2018.
 */
public class Drive {

    Pair<Integer, Integer> src, dst;
    Integer earliestStart;
    Integer latestArrival;

    public Drive(Pair<Integer, Integer> src, Pair<Integer, Integer> dst, Integer earliestStart, Integer latestArrival) {
        this.src = src;
        this.dst = dst;
        this.earliestStart = earliestStart;
        this.latestArrival = latestArrival;
    }

    public Pair<Integer, Integer> getSrc() {
        return src;
    }

    public Pair<Integer, Integer> getDst() {
        return dst;
    }

    public Integer getEarliestStart() {
        return earliestStart;
    }

    public Integer getLatestArrival() {
        return latestArrival;
    }
}
