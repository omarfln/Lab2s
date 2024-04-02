package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PensionManagementApp {

    public static void main(String[] args) {
        List<Employee> employees = initializeEmployees();
        List<PensionPlan> pensionPlans = initializePensionPlans();

        System.out.println("Employees with pension data: ");
        printEmployeesWithPensionPlanData(employees, pensionPlans);
        System.out.println("\nEmployees eligible for pension:");
        printMonthlyUpcomingEnrolleesReport(employees, pensionPlans);
    }

    // Initialize sample employee data
    private static List<Employee> initializeEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50));
        employees.add(new Employee(2, "Bernard", "Shaw", LocalDate.of(2011, 4, 3), 197750.00));
        employees.add(new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75));
        employees.add(new Employee(4, "Wesley", "Schneider", LocalDate.of(2019, 5, 2), 74500.00));
        return employees;
    }

    // Initialize sample pension plan data
    private static List<PensionPlan> initializePensionPlans() {
        List<PensionPlan> pensionPlans = new ArrayList<>();
        pensionPlans.add(new PensionPlan(1, LocalDate.of(2023, 1, 17), 100.00, 1));
        pensionPlans.add(new PensionPlan(3, LocalDate.of(2019, 11, 4), 1555.50, 3));
        return pensionPlans;
    }

    // 1: Print list of employees with pension plan data in JSON format
    private static void printEmployeesWithPensionPlanData(List<Employee> employees, List<PensionPlan> pensionPlans) {
        Collections.sort(employees, Comparator.comparing(Employee::getLastName)
                .thenComparing(Employee::getYearlySalary).reversed());
        for (Employee employee : employees) {
            PensionPlan plan = pensionPlans.stream()
                    .filter(p -> p.getEmployeeId() == employee.getEmployeeId())
                    .findFirst()
                    .orElse(null);

            String pensionPlanData = plan != null ? ", \"pensionPlan\": " + plan.toString() : "";
            System.out.println(employee.toJsom(pensionPlanData));
        }
    }

    // 2: Print monthly upcoming enrollees report in JSON format
    private static void printMonthlyUpcomingEnrolleesReport(List<Employee> employees, List<PensionPlan> pensionPlans) {
        List<Employee> upcomingEnrollees = employees.stream()
                .filter(e -> !isEnrolledInPensionPlan(e, pensionPlans) && isEligibleForEnrollment(e))
                .collect(Collectors.toList());

        Collections.sort(upcomingEnrollees, Comparator.comparing(Employee::getEmploymentDate));
        for (Employee employee : upcomingEnrollees) {
            System.out.println(employee.toString());
        }
    }

    private static boolean isEligibleForEnrollment(Employee employee) {
        LocalDate currentDate = LocalDate.now();
        LocalDate employmentDatePlusFiveYears = employee.getEmploymentDate().plusYears(5);
        return !employmentDatePlusFiveYears.isAfter(currentDate) &&
                employmentDatePlusFiveYears.isBefore(currentDate.withDayOfMonth(currentDate.lengthOfMonth()));
    }

    private static boolean isEnrolledInPensionPlan(Employee employee, List<PensionPlan> pensionPlans) {
        return pensionPlans.stream().anyMatch(p -> p.getEmployeeId() == employee.getEmployeeId());
    }
}