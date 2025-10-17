package service;

import model.Employee;
import model.Position;
import model.ImportSummary;
import exception.InvalidDataException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportService {
    private final EmployeeService employeeService;

    public ImportService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ImportSummary importFromCsv(String path) {
        List<String> errors = new ArrayList<>();
        int importedCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNum = 0;
            boolean isFirst = true;
            while ((line = br.readLine()) != null) {
                lineNum++;
                if (isFirst) { isFirst = false; continue; } // pominięcie nagłówka
                if (line.trim().isEmpty()) continue;        // pominięcie pustych linii
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    errors.add("Linia " + lineNum + ": nieprawidłowa liczba kolumn");
                    continue;
                }
                try {
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    String email = parts[2].trim();
                    String company = parts[3].trim();
                    String posStr = parts[4].trim();
                    String salaryStr = parts[5].trim();
                    Position position;
                    try {                                                       // walidacja stanowiska
                        position = Position.valueOf(posStr);
                    } catch (IllegalArgumentException e) {
                        throw new InvalidDataException("Nieznane stanowisko: " + posStr);
                    }
                    double salary;
                    try {                                                       // walidacja wynagrodzenia
                        salary = Double.parseDouble(salaryStr);
                    } catch (NumberFormatException e) {
                        throw new InvalidDataException("Nieprawidłowa pensja: " + salaryStr);
                        
                    }
                    if (salary <= 0) {
                        throw new InvalidDataException("Pensja musi być dodatnia");
                    }
                    Employee emp = new Employee(firstName, lastName, email, company, position, salary);
                    employeeService.addEmployee(emp);
                    importedCount++;
                } catch (InvalidDataException e) {                              // błędy z numerami linii
                    errors.add("Linia " + lineNum + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            errors.add("Błąd odczytu pliku: " + e.getMessage());
        }
        return new ImportSummary(importedCount, errors);
    }
}
