import model.*;
import service.Service;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        service.addEmployee(new Employee("Jan", "Kowalski", "jan.k@techcorp.com", "TechCorp", Position.PREZES, 25000));
        service.addEmployee(new Employee("Jakub", "Nawrocki", "jakub.n@techcorp.com", "TechCorp", Position.WICEPREZES, 18500));
        service.addEmployee(new Employee("Anna", "Nowak", "anna.n@techcorp.com", "TechCorp", Position.MANAGER, 12500));
        service.addEmployee(new Employee("Piotr", "Wiśniewski", "piotr.w@softdev.com", "SoftDev", Position.PROGRAMISTA, 9000));
        service.addEmployee(new Employee("Ewa", "Mazur", "ewa.m@techcorp.com", "TechCorp", Position.STAZYSTA, 3200));
        service.addEmployee(new Employee("Marek", "Zieliński", "marek.z@softdev.com", "SoftDev", Position.MANAGER, 11000));
        service.addEmployee(new Employee("Katarzyna", "Wójcik", "katarzyna.w@innoware.com", "InnoWare", Position.PROGRAMISTA, 9500));
        service.addEmployee(new Employee("Tomasz", "Lewandowski", "tomasz.l@innoware.com", "InnoWare", Position.STAZYSTA, 3500));
        service.addEmployee(new Employee("Agnieszka", "Dąbrowska", "agnieszka.d@softdev.com", "SoftDev", Position.MANAGER, 12000));
        service.addEmployee(new Employee("Paweł", "Kaczmarek", "pawel.k@innoware.com", "InnoWare", Position.PROGRAMISTA, 9800));
        service.addEmployee(new Employee("Magdalena", "Sikora", "magdalena.s@innoware.com", "InnoWare", Position.STAZYSTA, 3400));
        service.addEmployee(new Employee("Robert", "Jankowski", "robert.j@softdev.com", "SoftDev", Position.PROGRAMISTA, 9100));
        service.addEmployee(new Employee("Joanna", "Baran", "joanna.b@innoware.com", "InnoWare", Position.MANAGER, 11800));
        service.addEmployee(new Employee("Grzegorz", "Witkowski", "grzegorz.w@innoware.com", "InnoWare", Position.STAZYSTA, 3300));
        service.addEmployee(new Employee("Michał", "Kamiński", "michal.k@softdev.com", "SoftDev", Position.STAZYSTA, 3600));
        service.addEmployee(new Employee("Karolina", "Szymańska", "karolina.s@innoware.com", "InnoWare", Position.PROGRAMISTA, 9700));
        service.addEmployee(new Employee("Łukasz", "Woźniak", "lukasz.w@softdev.com", "SoftDev", Position.STAZYSTA, 3450));

        System.out.println("=== Wszyscy pracownicy ===");
        service.getAllEmployees().forEach(System.out::println);

        System.out.println("\n=== Pracownicy TechCorp ===");
        service.findByCompany("TechCorp").forEach(System.out::println);

        System.out.println("\n=== Posortowani alfabetycznie ===");
        service.getSortedByLastName().forEach(System.out::println);

        System.out.println("\n=== Grupowanie wg stanowiska ===");
        service.groupByPosition().forEach((pos, list) -> {
            System.out.println(pos + ": " + list);
        });

        System.out.println("\n=== Liczba pracowników wg stanowiska ===");
        service.countByPosition().forEach((pos, count) ->
                System.out.println(pos + " -> " + count));

        System.out.printf("\nŚrednie wynagrodzenie: %.2f zł\n", service.getAverageSalary());

        service.getHighestPaidEmployee().ifPresent(e ->
                System.out.println("Najlepiej opłacany pracownik: " + e));
    }
}
