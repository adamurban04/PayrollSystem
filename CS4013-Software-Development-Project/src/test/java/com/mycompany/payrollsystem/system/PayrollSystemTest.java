package com.mycompany.payrollsystem.system;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PayrollSystemTest {
    private PayrollSystem payrollSystem;

    @BeforeEach
    void setUp() throws Exception {
        ScaleLoader.loadScales("src/database/Salaries.csv");
        payrollSystem = new PayrollSystem();
        StaffContainer.clearAllStaff(); // Clear all staff
        payrollSystem.clearPayClaims(); // Clear pay claims
    }

    @Test
    void testAddPayClaim() {
        PartTimeEmployee employee = new PartTimeEmployee("Alice", 1, "Lab Tutor", 1, "password");
        StaffContainer.add(employee);

        boolean claimAdded = payrollSystem.addPayClaim(employee.getId(), 40);
        assertTrue(claimAdded);

        // Attempt to add another claim in the same month
        boolean secondClaim = payrollSystem.addPayClaim(employee.getId(), 20);
        assertFalse(secondClaim, "Duplicate pay claims in the same month should not be allowed");
    }

    @Test
    void testGeneratePayslipForFullTimeEmployee() {
        FullTimeEmployee employee = new FullTimeEmployee("Bob", 2, "Academic", "Professor", 3, "password");
        StaffContainer.add(employee);

        var payslip = payrollSystem.generatePayslipForEmployee(employee);
        assertNotNull(payslip);
        assertEquals(ScaleLoader.getPay("Academic", "Professor", "3") / 12, payslip.getGrossPay());
    }

    @Test
    void testGeneratePayslipForPartTimeEmployee() {
        PartTimeEmployee employee = new PartTimeEmployee("Charlie", 3, "Exam Invigilator", 1, "password");
        StaffContainer.add(employee);

        payrollSystem.addPayClaim(employee.getId(), 10);
        var payslip = payrollSystem.generatePayslipForEmployee(employee);

        assertNotNull(payslip);
        assertEquals(ScaleLoader.getPay("", "Exam Invigilator", "1") * 10, payslip.getGrossPay());
    }

    @Test
    void testGenerateMonthlyPayslips() {
        // Ensure staff is present
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("Alice", 4, "Academic", "Professor", 3, "password");
        PartTimeEmployee partTimeEmployee = new PartTimeEmployee("Charlie", 5, "LabTutor", 1, "password");
        StaffContainer.add(fullTimeEmployee);
        StaffContainer.add(partTimeEmployee);

        payrollSystem.addPayClaim(partTimeEmployee.getId(), 10);

        ArrayList<Payslip> payslips = payrollSystem.generateMonthlyPayslips();
        assertEquals(2, payslips.size(), "Payslips should be generated for both employees");
    }
}
