package com.mycompany.payrollsystem.system;

public class Payslip{

    private String name;
    private int id;
    private double grossPay;
    private double tax;
    private double netPay;
    private String payPeriod;


    public Payslip(String name, int id, double grossPay, double tax, double netPay, String payPeriod) {

    this.name = name;
    this.id = id;
    this.grossPay = grossPay;
    this.tax = tax;
    this.netPay = netPay;
    this.payPeriod = payPeriod;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getTax() {
        return tax;
    }

    public double getNetPay() {
        return netPay;
    }

    public String getPayPeriod() {
        return payPeriod;
    }
}