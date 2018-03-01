package com.google.hashcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andrey on 01.03.2018.
 */
public class OutputData {
    private final int s;
    private final List<Object> slices;

    public OutputData(final int s, final List<Object> slices) {
        this.s = s;
        this.slices = slices;
    }

    public OutputData(final int s, final Object... slices) {
        this.s = s;
        this.slices = Arrays.asList(slices);
    }

    public int getS() {
        return s;
    }

    public List<Object> getSlices() {
        return slices;
    }

    @Override
    public String toString() {
        String slicesStr = slices.stream().map(Object::toString).collect(Collectors.joining("\n"));
        return s + "\n" + slicesStr;
    }
}
