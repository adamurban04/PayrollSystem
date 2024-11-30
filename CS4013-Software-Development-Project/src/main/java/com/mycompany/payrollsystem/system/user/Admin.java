package com.mycompany.payrollsystem.system.user;


import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.ScaleLoader;
import com.mycompany.payrollsystem.system.StaffContainer;
import java.io.FileWriter;
import java.io.IOException;


import java.util.Scanner;

public class Admin {
    private final Scanner in = new Scanner(System.in);



    // Helper method to safely read integers
    private int readInt(String message) {
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }


    // Helper method to read strings
    private String readString(String message) {
        System.out.println(message);
        return in.nextLine().trim();    //trim removes spaces before or after the actual string
    }

    public void addStaff() {

        // Staff Type
        int type = readInt("Enter staff type: (1) full-time (2) part-time:");
        while (type != 1 && type != 2) {
            System.out.println("Invalid staff type. Please enter 1 for full-time or 2 for part-time.");
            type = readInt("Enter staff type: (1) full-time (2) part-time:");
        }

        // Name
        String name = readString("Enter staff name:");

        // Unique ID
        int id;
        while (true) {
            id = readInt("Enter staff ID:");
            if (StaffContainer.getStaffById(id) != null) {
                System.out.println("Staff ID already exists. Please enter a unique ID.");
            } else {
                break;
            }
        }

        // Title
        String title;
        while (true) {
            title = readString("Enter staff title:");
            if (!(ScaleLoader.validTitle(title))){
                System.out.println("Title does not exist. Please enter a valid title.");
            } else {
                break;
            }
        }


        // Scale Point
        int scalePoint = readInt("Enter scale point:");
        int maxScalePoint = ScaleLoader.getMaxScalePoints(title);
        while (scalePoint > maxScalePoint) {
            System.out.println("Maximum scalepoint is: " + maxScalePoint);
            scalePoint = readInt("Enter scale point:");
        }

        // Password
        String password = readString("Set an 8 character password for this employee:");
        while (password.length()!=8 || password.contains(" ")) {
            System.out.println("Password must have exactly 8 characters with no spaces.");
            password = readString("Set a password for this employee:");
        }

        // Full-Time Employee
        if (type == 1) {
            String category;
            while (true) {
                category = readString("Enter category:");
                if (!(ScaleLoader.validCategory(category))){
                    System.out.println("Category does not exist. Please enter a valid category.");
                } else {
                    break;
                }
            }
            FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(name, id, category, title, scalePoint, password);
            if (StaffContainer.add(fullTimeEmployee)) {
                System.out.println("Full-time employee added successfully!");   //otherwise, java does garbage collection automatically
            }

            // Part-Time Employee
        } else {
            PartTimeEmployee partTimeEmployee = new PartTimeEmployee(name, id, title, scalePoint, password);
            if (StaffContainer.add(partTimeEmployee)) {
                System.out.println("Part-time employee added successfully!");
            }
        }
    }

    public void viewStaff() {
        if (StaffContainer.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            StaffContainer.listAllStaff();
            saveAllStaffToCSV();
        }
    }

    private void saveAllStaffToCSV() {
        String fileName = "employees.csv";
        String fullPath = "src/output/" + fileName;
        try (FileWriter writer = new FileWriter(fullPath)) {
            // Write the CSV header
            writer.write("Employee,Name,ID,Category,Title\n");

            for (Staff staff : StaffContainer.getAllStaff()) {
                String category = (staff instanceof FullTimeEmployee) ? ((FullTimeEmployee) staff).getCategory() : "";
                String employee = (staff instanceof FullTimeEmployee) ? "Full-Time" : "Part-Time";

                writer.write(String.format("%s,%s,%d,%s,%s\n",
                        employee,
                        staff.getName(),
                        staff.getId(),
                        category,
                        staff.getTitle()));
            }
            System.out.println("Staff details have been saved to " + fullPath);
        } catch (IOException e) {
            System.out.println("Error saving staff details to CSV: " + e.getMessage());
        }
    }
}
