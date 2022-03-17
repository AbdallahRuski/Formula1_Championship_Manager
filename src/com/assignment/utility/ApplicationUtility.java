package com.assignment.utility;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationUtility {

    //ID constant prefixes
    private final static String DRIVER_ID_PREFIX = "drv_";
    private final static String RACE_ID_PREFIX = "rc_";

    //for storing number of points for a particular winning position
    private final static Map<Integer, Integer> pointsAndPositionHolder = Map.of(1, 25, 2, 18, 3, 15,
            4, 12, 5, 10, 6, 8, 7, 6, 8, 4, 9, 2, 10, 1);

     //For fetching number of points taken for the winning position
    public static Integer doGetPointsByPosition(Integer position) {
        return pointsAndPositionHolder.get(position);
    }

    //generate diver ID
    public static String doGenerateDriverIdentification() {
        return DRIVER_ID_PREFIX + LocalTime.now().toString().substring(10, 15);
    }

    //generate race ID
    public static String doGenerateRaceIdentification() {
        return RACE_ID_PREFIX + LocalTime.now().toString().substring(10, 15);
    }

    //writing to a file, single operation
    public static void doWriteFile(File file, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.newLine();
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //writing to a file, bulk operation
    public static void doWriteFile(File file, List<Object> drivers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            drivers.forEach(index -> {
                try {
                    writer.newLine();
                    writer.write(index.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for removing all the records form file
    public static void doCleanFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read the file & fetch data
    public static List<String> doReadFile(File file) {
        List<String> stream = new ArrayList<>();
        String data;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((data = reader.readLine()) != null) {
                stream.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }
}
