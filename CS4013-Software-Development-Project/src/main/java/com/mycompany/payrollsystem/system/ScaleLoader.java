package com.mycompany.payrollsystem.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ScaleLoader {
    // Data structure:
    //      Key: category, role, tier
    //      Value: pay
    private static final HashMap<String, Double> payGrades = new HashMap<>();   //for generating pay
    private static final HashMap<String, Integer> titleScalePoints = new HashMap<>();   //for max scalepoint
    private static final ArrayList<String> titles = new ArrayList<>();  //title validation
    private static final ArrayList<String> categories = new ArrayList<>();  //title validation

    public static void loadScales(String csvFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String category = data[0];
                String title = data[1];
                String tier = data[2];
                double pay = Double.parseDouble(data[3]);
                String key = generateKey(category, title, tier);
                payGrades.put(key, pay);

                titles.add(title);  //will contain duplicates but will work
                categories.add(category);   //same case

                int currentTier = Integer.parseInt(tier);
                titleScalePoints.put(title, Math.max(titleScalePoints.getOrDefault(title, 0), currentTier));
            }
        }
    }

    public static int getMaxScalePoints(String title) {
        return titleScalePoints.getOrDefault(title, 0);
    }

    public static double getPay(String category, String title, String tier) {   // returns value
        String key = generateKey(category, title, tier);
        return payGrades.getOrDefault(key, -1.0);   //-1 for testing purposes
    }

    public static boolean validTitle(String title) {
        return titles.contains(title);
    }

    public static boolean validCategory(String category) {
        return categories.contains(category);
    }

    private static String generateKey(String category, String role, String tier) { // returns key
        return category + "-" + role + "-" + tier;
    }
}
