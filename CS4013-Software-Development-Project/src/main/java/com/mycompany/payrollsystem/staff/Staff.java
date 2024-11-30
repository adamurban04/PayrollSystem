package com.mycompany.payrollsystem.staff;


import com.mycompany.payrollsystem.system.ScaleLoader;
import com.mycompany.payrollsystem.system.Payslip;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Staff {

    // salary in FullTime, payRate in PartTime
    // category only in FullTime

    private LocalDate startTime;
    protected String name;
    protected int id;
    protected String title;
    protected int scalePoint;
    private final ArrayList<Payslip> payslips = new ArrayList<>();
    private String password;


    public Staff(String name, int id, String title, int scalePoint, String password){
        this.startTime = LocalDate.now();
        this.name = name;
        this.id = id;
        this.title = title;
        this.scalePoint = scalePoint;
        this.password = password;
    }

    public void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }

    public void viewPayslips() {
        if (payslips.isEmpty()) {
            System.out.println("No payslips available.");
            return;
        }

        System.out.println("Payslips:");
        for (Payslip payslip : payslips) {
            System.out.printf("Pay Period: %s | Gross Pay: %.2f | Tax: %.2f | Net Pay: %.2f%n",
                    payslip.getPayPeriod(), payslip.getGrossPay(), payslip.getTax(), payslip.getNetPay());
        }
    }


    public abstract boolean updateScalePoint(); //different in subclasses

    public boolean authenticate(String inputPassword) {
        return password.equals(inputPassword);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getScalePoint() {
        return scalePoint;
    }

    @Override
    public String toString() {
        return String.format("name: %s | id: %d | title: %s | scalePoint: %d", name, id, title, scalePoint);
    }

    public String getPassword() {
        return password;
    }
}