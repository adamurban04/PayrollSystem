package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.system.ScaleLoader;
import com.mycompany.payrollsystem.system.StaffContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HRTest {
    private static HR hr;

    @BeforeAll
    static void setUp() throws Exception {
        hr = new HR();
        ScaleLoader.loadScales("src/database/Salaries.csv");
    }

    @Test
    void testAnnualPromotion() {
        FullTimeEmployee employee = new FullTimeEmployee("Alice", 1, "Academic", "Professor", 1, "password");
        StaffContainer.add(employee);

        hr.annualPromotion();
        assertEquals(2, employee.getScalePoint(), "Annual promotion should increment scale point");
    }

    @Test
    void testPromoteToNextSalaryScale() {
        FullTimeEmployee employee = new FullTimeEmployee("Bob", 2, "Academic", "Professor", 6, "password");
        StaffContainer.add(employee);

        long yearsAtTop = employee.getYearsAtTop();
        assertTrue(yearsAtTop >= 0, "Employee should have a valid number of years at the top scale");

        employee.promoteToNewTitle("Full Professor");
        assertEquals("Full Professor", employee.getTitle(), "Employee should be promoted to a new title");
        assertEquals(1, employee.getScalePoint(), "Scale point should reset after promotion");
    }
}
