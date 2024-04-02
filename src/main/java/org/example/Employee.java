package org.example;

import java.util.*;
import java.time.*;
import java.time.format.*;



class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private double yearlySalary;

    public Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, double yearlySalary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
    }

    @Override
    public String toString() {
        //Json template kept giving an error
        //  (use --enable-preview to enable string templates)
        return "{ \"employeeId\": " + employeeId + ", \"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName +
                "\", \"employmentDate\": \"" + employmentDate + "\", \"yearlySalary\": " + yearlySalary + " }";
    }

    public String toJsom(String plan) {
        return "{ \"employeeId\": " + employeeId + ", \"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName +
                "\", \"employmentDate\": \"" + employmentDate + "\", \"yearlySalary\": " + yearlySalary +  plan +" }";
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }
}

