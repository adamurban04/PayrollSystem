package com.mycompany.payrollsystem.stresstest;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.system.StaffContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeLoader {

    public static void loadEmployeesFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                parseEmployee(line);
            }
        }
    }

    private static void parseEmployee(String csvLine) {
        String[] parts = csvLine.split(",");

        String name = parts[0];
        int id = Integer.parseInt(parts[1].trim());
        String title = parts[2];
        int tier = Integer.parseInt(parts[3].trim());
        String password = parts[4];
        String category = parts[5];

        if (category.equals("null")) {
            PartTimeEmployee employee = new PartTimeEmployee(name, id, title, tier, password);
            StaffContainer.add(employee);
        } else {
            FullTimeEmployee employee = new FullTimeEmployee(name, id, category, title, tier, password);
            StaffContainer.add(employee);
        }
    }
}
