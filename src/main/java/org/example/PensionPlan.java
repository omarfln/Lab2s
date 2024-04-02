package org.example;

import java.time.LocalDate;

class PensionPlan {
    private long employeeId;
    private long planReferenceNumber;
    private LocalDate enrollmentDate;
    private double monthlyContribution;

    public PensionPlan(long planReferenceNumber, LocalDate enrollmentDate, double monthlyContribution, long employeeId) {
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "{ \"planReferenceNumber\": " + planReferenceNumber + ", \"enrollmentDate\": \"" + enrollmentDate +
                "\", \"monthlyContribution\": " + monthlyContribution + " }";
    }

    public long getEmployeeId() {
        return employeeId;
    }

}
