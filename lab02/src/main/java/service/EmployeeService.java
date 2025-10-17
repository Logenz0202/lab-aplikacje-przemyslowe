package service;

import model.Employee;
//import model.Position;
import model.CompanyStatistics;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getAllEmployees() {
        return Collections.unmodifiableList(employees);
    }

    // pracownicy z pensją niższą niż bazowa stawka stanowiska
    public List<Employee> validateSalaryConsistency() {
        return employees.stream()
                .filter(e -> e.getSalary() < e.getPosition().getBaseSalary())
                .collect(Collectors.toList());
    }

    // map: nazwa firmy -> statystyki
    public Map<String, CompanyStatistics> getCompanyStatistics() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getCompanyName,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            int count = list.size();
                            double avg = list.stream().mapToDouble(Employee::getSalary).average().orElse(0);
                            Employee top = list.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
                            String topName = (top != null) ? top.getFirstName() + " " + top.getLastName() : "";
                            return new CompanyStatistics(count, avg, topName);
                        })
                ));
    }
}
