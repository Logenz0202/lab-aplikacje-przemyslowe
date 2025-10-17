package org.example;

import model.ImportSummary;
import model.Employee;
import model.CompanyStatistics;
import service.EmployeeService;
import service.ImportService;
import service.ApiService;
import exception.ApiException;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        ImportService importService = new ImportService(employeeService);
        ApiService apiService = new ApiService();

        // import z pliku CSV
        String csvPath = "employees.csv";
        ImportSummary summary = importService.importFromCsv(csvPath);
        System.out.println("Podsumowanie importu z CSV: " + summary);

        // pobieranie pracowników z API
        try {
            List<Employee> apiEmployees = apiService.fetchEmployeesFromApi();
            for (Employee e : apiEmployees) {
                employeeService.addEmployee(e);
            }
            System.out.println("Zaimportowano z API: " + apiEmployees.size() + " pracowników");
        } catch (ApiException e) {
            System.out.println("Błąd API: " + e.getMessage());
        }

        // pracownicy z pensją poniżej bazowej
        List<Employee> underpaid = employeeService.validateSalaryConsistency();
        System.out.println("Pracownicy z pensją poniżej bazowej:");
        for (Employee e : underpaid) {
            System.out.println(e);
        }

        // statystyki firm
        Map<String, CompanyStatistics> stats = employeeService.getCompanyStatistics();
        System.out.println("Statystyki firm:");
        for (var entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
