package com.mycompany.payrollsystem.system;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffContainerTest {

    @BeforeEach
    void resetStaffContainer() {
        StaffContainer.clearAllStaff(); // Clear staff data before each test
    }

    @Test
    void testAddAndRetrieveStaff() {
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("Alice", 1, "Academic", "Professor", 1, "password");
        PartTimeEmployee partTimeEmployee = new PartTimeEmployee("Bob", 2, "Lab Tutor", 1, "password");

        StaffContainer.add(fullTimeEmployee);
        StaffContainer.add(partTimeEmployee);

        assertEquals(fullTimeEmployee, StaffContainer.getStaffById(1));
        assertEquals(partTimeEmployee, StaffContainer.getStaffById(2));
    }

    @Test
    void testDuplicateStaffId() {
        FullTimeEmployee fullTimeEmployee1 = new FullTimeEmployee("Alice", 1, "Academic", "Professor", 1, "password");
        FullTimeEmployee fullTimeEmployee2 = new FullTimeEmployee("Bob", 1, "Academic", "Professor", 1, "password");

        StaffContainer.add(fullTimeEmployee1);
        boolean added = StaffContainer.add(fullTimeEmployee2);

        assertFalse(added, "Duplicate ID should not be added");
    }
}
