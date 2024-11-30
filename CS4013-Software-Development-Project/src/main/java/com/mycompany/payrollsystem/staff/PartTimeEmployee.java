package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.ScaleLoader;



public class PartTimeEmployee extends Staff {
    private double payRate; //hourly rate



    public PartTimeEmployee(String name, int id, String title, int scalePoint, String password) {
        super(name, id, title, scalePoint, password);
        payRate = ScaleLoader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
    }


    public double getPay(double hoursWorked) {    //sets payRate and return "salary"
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        }
        payRate = ScaleLoader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
        return  payRate * hoursWorked; // Pay is based on hours worked
    }


    @Override
    public boolean updateScalePoint() {
        int maxScalePoints = ScaleLoader.getMaxScalePoints(title);
        if (scalePoint < maxScalePoints) {
            scalePoint++;
            payRate = ScaleLoader.getPay("", title, String.valueOf(scalePoint));
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return String.format("Part-Time Employee: name: %s | id: %d | title: %s | scalePoint: %d | payRate: %.2f",
                name, id, title, scalePoint, payRate);
    }
}