package com.google.hashcode;

import com.google.hashcode.data.InputData;

public class App {
    public static void main(String[] args) {
        InputData inputData = FileHandler.read("a_example.in");
        Dispatcher dispatcher = new Dispatcher(inputData);
        System.out.println("test");
        // write the code here
    }
}