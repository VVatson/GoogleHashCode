package com.google.hashcode;

import com.google.hashcode.data.InputData;
import com.google.hashcode.data.Output;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 01.03.2018.
 */
public class FileHandler {
    public static final String INPUT_DIR = "src/main/java/com/google/hashcode/input/";
    public static final String OUTPUT_DIR = "src/main/java/com/google/hashcode/output/";

    public static InputData read(final String fileName) {
        BufferedReader br = null;
        FileReader fr = null;

        final int numberRows;
        final int numberColumns;
        final int numberVehicles;
        final int numberRides;
        final int bonus;
        final int numberSimulationSteps;
        final List<Drive> rides = new ArrayList<>();

        try {
            fr = new FileReader(INPUT_DIR + fileName);
            br = new BufferedReader(fr);

            String sCurrentLine = br.readLine();
            String[] split = sCurrentLine.split(" ");
            numberRows = Integer.parseInt(split[0]);
            numberColumns = Integer.parseInt(split[1]);
            numberVehicles = Integer.parseInt(split[2]);
            numberRides = Integer.parseInt(split[3]);
            bonus = Integer.parseInt(split[4]);
            numberSimulationSteps = Integer.parseInt(split[5]);

            for (int numberDrive = 0; numberDrive < numberRides; numberDrive++) {
                sCurrentLine = br.readLine();
                String[] splitRide = sCurrentLine.split(" ");
                int xStart = Integer.parseInt(splitRide[0]);
                int yStart = Integer.parseInt(splitRide[1]);
                int xFinish = Integer.parseInt(splitRide[2]);
                int yFinish = Integer.parseInt(splitRide[3]);
                int earlyLimitStart = Integer.parseInt(splitRide[4]);
                int latestArrival = Integer.parseInt(splitRide[5]);
                rides.add(new Drive(
                        Pair.of(xStart, yStart),
                        Pair.of(xFinish, yFinish),
                        earlyLimitStart, latestArrival, numberDrive)
                );
            }

            return new InputData(
                    numberRows, numberColumns,
                    numberVehicles, numberRides,
                    bonus, numberSimulationSteps,
                    rides);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void write(final String fileName, final String output) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(OUTPUT_DIR + fileName, false);
            bw = new BufferedWriter(fw);

            bw.append(output);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}