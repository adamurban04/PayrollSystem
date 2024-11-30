package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.StaffContainer;

import java.time.LocalDate;
import java.util.Scanner;

public class HR {
    private final Scanner in = new Scanner(System.in);

    public void annualPromotion() { //promotion every October for all FullTime Employees
        LocalDate today = LocalDate.now();

        // Ensure it's October
        if (today.getMonthValue() == 10) {  //AFTER TESTING CHANGE TO !=
            System.out.println("Annual promotions can only be conducted in October.");
            return;
        }

        System.out.println("Performing annual promotions for full-time employees...");

        for (Staff staff : StaffContainer.getAllStaff()) {
            if (staff instanceof FullTimeEmployee) {
                FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) staff;
                if (fullTimeEmployee.updateScalePoint()) {
                    System.out.println("Promoted: " + fullTimeEmployee.getName() + " to scale point " + fullTimeEmployee.getScalePoint());
                } else {
                    System.out.println(fullTimeEmployee.getName() + " is already at the top of their scale and cannot be promoted further.");
                }
            }
        }
    }

    public void promoteToNextSalaryScale() {
        System.out.println("Enter the ID of the full-time employee to promote:");
        int id = readInt();
        Staff staff = StaffContainer.getStaffById(id);

        if (staff == null || !(staff instanceof FullTimeEmployee)) {
            System.out.println("Staff not found or not eligible for promotion.");
            return;
        }

        FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) staff;

        System.out.println("Promotion Details:");
        System.out.println("Current Scale Point: " + fullTimeEmployee.getScalePoint());
        System.out.println("Current Salary Scale: " + fullTimeEmployee.getTitle());

        System.out.println("Enter the employee's password for confirmation:");
        String inputPassword = in.nextLine().trim();

        // Authenticate the employee
        if (!fullTimeEmployee.authenticate(inputPassword)) {
            System.out.println("Invalid password. Promotion cannot be authorized.");
            return;
        }

        System.out.println("Enter new salary scale (title) for the employee:");
        String newTitle = in.nextLine().trim();



        // Calculate new scale point based on time spent at the top
        int newScalePoint = calculateNewScalePoint(fullTimeEmployee);

        fullTimeEmployee.promoteToNewTitle(newTitle);
        System.out.println("Successfully promoted " + fullTimeEmployee.getName() + " to title " + newTitle + " at scale point " + newScalePoint);
    }

    private int calculateNewScalePoint(FullTimeEmployee employee) {
        long yearsAtTop = employee.getYearsAtTop();

        // Logic to determine scale point based on years spent at the top
        if (yearsAtTop >= 5) {
            return 5; // Example: If more than 5 years, go to scale point 5
        } else if (yearsAtTop >= 3) {
            return 3; // If 3–4 years, go to scale point 3
        } else {
            return 1; // Otherwise, start at scale point 1
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public void promoteToNextScalePoint() {
        System.out.println("Enter the ID of the part-time employee to promote:");
        int id = Integer.parseInt(in.nextLine().trim());
        Staff staff = StaffContainer.getStaffById(id);

        if (staff instanceof PartTimeEmployee) {
            PartTimeEmployee partTimeEmployee = (PartTimeEmployee) staff;
            if (partTimeEmployee.updateScalePoint()) {
                System.out.println("Successfully promoted " + partTimeEmployee.getName() + " to scale point " + partTimeEmployee.getScalePoint());
            } else {
                System.out.println("Cannot promote " + partTimeEmployee.getName() + " as they are already at the top of their scale.");
            }
        } else {
            System.out.println("Staff not found or not a part-time employee.");
        }
    }
}