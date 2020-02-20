package com.google.hashcode;

import com.google.hashcode.data.Input;
import com.google.hashcode.data.Output;

/**
 * Main class.
 * Processes all files.
 */
public class App {
    private final static String [] FILES = {
            "a_example.txt",
            "b_read_on.txt",
            "c_incunabula.txt",
            "d_tough_choices.txt",
            "e_so_many_books.txt",
            "f_libraries_of_the_world.txt"
    };

    public static void main(String[] args) {
        for (String file : FILES) {
            Input input = FileHandler.read(file);

            Process process = new Process(input);
            Output output = process.run();

            FileHandler.write(file, output);
        }
    }
}