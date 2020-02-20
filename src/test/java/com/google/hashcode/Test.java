package com.google.hashcode;

import com.google.hashcode.data.Input;

public class Test {

    @org.junit.jupiter.api.Test
    public void test() {
        System.out.println("test");
    }

    @org.junit.jupiter.api.Test
    public void testInput() {
        String file = "a_example.txt";
        Input input = FileHandler.read(file);

        System.out.println(input);
    }
}
