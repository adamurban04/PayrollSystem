package com.mycompany.payrollsystem.stresstest;

import com.mycompany.payrollsystem.system.ScaleLoader;
import com.mycompany.payrollsystem.system.PayrollSystem;
import com.mycompany.payrollsystem.system.StaffContainer;

import java.io.IOException;

public class TestRunner {

    public static void main(String[] args) throws IOException {
        PayrollSystem payrollSystem = new PayrollSystem();
        System.out.println("==== Begin Test Run ====");

        System.out.println("---- Loading Pay Rates ----");
        ScaleLoader.loadScales("src/database/Salaries.csv");

        System.out.println("---- Loading Employees ----");
        EmployeeLoader.loadEmployeesFromFile("src/database/SampleEmployees.csv");

        System.out.println("---- Submitting Pay Claims ----");
        payrollSystem.addPayClaim(101, 15); // FullTime Employee (SHOULD NOT BE ALLOWED)
        payrollSystem.addPayClaim(306, 20); // PartTime Employee (SHOULD BE ALLOWED (Michael Connor))

        System.out.println("---- Generating and Printing Payslips ----");
        payrollSystem.generateMonthlyPayslips();

        System.out.println("---- Final Cleanup ----");
        StaffContainer.clearAllStaff();

        System.out.println("==== End of Test Run ====");
    }
}
