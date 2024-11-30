package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.Staff;

public class Employee {
    private final Staff staff;

    public Employee(Staff staff) {
        this.staff = staff;
    }

    public void viewDetails() {
        System.out.println("Your Details:");
        System.out.println(staff);
    }

    public void viewPayslips() {
        staff.viewPayslips();
    }

    public Staff getStaff() {
        return staff;
    }
}
