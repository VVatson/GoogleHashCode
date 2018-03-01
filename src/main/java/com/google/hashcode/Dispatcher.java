package com.google.hashcode;

import com.google.hashcode.data.InputData;
import com.google.hashcode.data.OutputData;

public class Dispatcher {
    final InputData inputData;

    public Dispatcher(InputData inputData) {
        this.inputData = inputData;
    }

    public OutputData calculate() {
        return new OutputData();
    }
}
