package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.ScaleLoader;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

public class FullTimeEmployee extends Staff {

    private String category;    //only FullTimeEmployee has category (Director/Academic/...)
    private double salary;      //only FullTimeEmployee has annual salary
    private LocalDateTime topScaleStartTime; //Checks time that employee has been at the top scalePoint

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint, String password) {
        super(name, id, title, scalePoint, password);
        this.category = category;
        this.salary = getSalary();
        checkTopScale();
    }

    public void checkTopScale(){
        int maxScalePoints = ScaleLoader.getMaxScalePoints(title);
        if (scalePoint == maxScalePoints){
            if (topScaleStartTime == null) {
                topScaleStartTime = LocalDateTime.now();
            }
        }
        else {
            topScaleStartTime = null;
        }
    }

    public boolean updateScalePoint() {
        int maxScalePoints = ScaleLoader.getMaxScalePoints(title);
        if (scalePoint < maxScalePoints) {
            checkTopScale();
            scalePoint++;
            salary = getSalary();
            return true;
        } else {
            return false; // Already at top
        }
    }


    public long getYearsAtTop() {
        if (topScaleStartTime == null) return 0;
        return ChronoUnit.YEARS.between(topScaleStartTime, LocalDateTime.now());
    }

    public void promoteToNewTitle(String newTitle) {
        title = newTitle;
        int yearsAtTop = (int) getYearsAtTop();
        topScaleStartTime = null; // Reset top scale timer
        int maxScalePoints = ScaleLoader.getMaxScalePoints(newTitle);


        scalePoint = Math.min(yearsAtTop, ScaleLoader.getMaxScalePoints(newTitle));   //new scalepoint will be years at top (limit is the maximum scale point)
        scalePoint = scalePoint == 0 ? 1 : scalePoint; //years could be 0, so make skillpoint 1

        salary = getSalary();
        checkTopScale();


    }

    public double getSalary() {
        return ScaleLoader.getPay(category, title, String.valueOf(scalePoint));
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("Full-Time Employee: name: %s | id: %d | category: %s | title: %s | scalePoint: %d | salary: %.2f",
                name, id, category, title, scalePoint, salary);
    }
}