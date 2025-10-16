package service;

import model.Employee;
import model.Position;

import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private final Set<Employee> employees = new HashSet<>();

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Employee> findByCompany(String companyName) {
        return employees.stream()
                .filter(e -> e.getCompanyName().equalsIgnoreCase(companyName))
                .collect(Collectors.toList());
    }

    public List<Employee> getSortedByLastName() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .collect(Collectors.toList());
    }

    public Map<Position, List<String>> groupByPosition() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition,
                        Collectors.mapping(e -> e.getFirstName() + " " + e.getLastName(), Collectors.toList())));
    }

    public Map<Position, Long> countByPosition() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition, Collectors.counting()));
    }

    public double getAverageSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public Optional<Employee> getHighestPaidEmployee() {
        return employees.stream()
                .max(Comparator.comparing(Employee::getSalary));
    }
}
