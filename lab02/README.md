# lab02 — Instrukcja uruchamiania (PL)

To repo zawiera prostą aplikację Java (Maven) do importu i analizy pracowników.

## Wymagania
- Java 11 (JDK) zainstalowana i dostępna w PATH
- Maven 3.x
- Połączenie z internetem przy pierwszym buildzie (pobranie zależności)

## Szybkie kroki — PowerShell (Windows)

1. Otwórz PowerShell w katalogu projektu (tam, gdzie jest `pom.xml`).

2. Zbuduj projekt:

```pwsh
mvn clean package
```

3. Uruchom aplikację bez tworzenia JAR (Maven wykona `org.example.Main`):

```pwsh
mvn exec:java -Dexec.mainClass="org.example.Main"
```

4. Lub uruchom powstały JAR (po `mvn package`). Sprawdź dokładną nazwę w katalogu `target` i zastąp poniższą przykładową nazwę jeśli trzeba:

```pwsh
# sprawdź pliki w target
ls target\
# uruchom jar (przykład nazwy pliku - zastąp własną)
java -jar target\lab02-1.0-SNAPSHOT.jar
```

## Plik CSV z pracownikami

Program podczas startu próbuje wczytać plik `employees.csv` z katalogu, z którego uruchamiasz program. Jeśli plik nie istnieje — aplikacja i tak kontynuuje, ale nie załaduje dodatkowych rekordów z CSV.

Oczekiwany format CSV (pierwszy wiersz to nagłówek):

firstName,lastName,email,company,position,salary

Przykładowy plik `employees.csv`:

```
firstName,lastName,email,company,position,salary
Jan,Kowalski,jan.kowalski@example.com,Acme,PROGRAMISTA,6000
Anna,Nowak,anna.nowak@example.com,Globex,PROGRAMISTA,7000
```

Zasady walidacji:
- `position` musi odpowiadać jednej z wartości enum `Position` (np. `PROGRAMISTA`).
- `salary` musi być liczbą dodatnią.

Jeśli nie chcesz pobierać dodatkowych danych z internetu, utwórz `employees.csv` z samym nagłówkiem lub z żądanymi wierszami.

## Co robi program

- Wczytuje (opcjonalnie) pracowników z `employees.csv`.
- Pobiera przykładowe dane z publicznego API (https://jsonplaceholder.typicode.com/users) i mapuje je na obiekty `Employee`.
- Wypisuje listę pracowników z pensją poniżej bazowej stawki stanowiska.
- Oblicza i wypisuje statystyki dla firm (liczba pracowników, średnia pensja, najlepszy zarobek).

## Debug i typowe problemy

- Błąd kompilacji: upewnij się, że masz zainstalowane JDK 11 i używasz go w konsoli (sprawdź `java -version`).
- Problem z pobieraniem zależności: sprawdź połączenie internetowe i ustawienia zapory/proxy.
- Błąd przy odczycie CSV: komunikat z numerem linii będzie wypisany w podsumowaniu importu.

## Uruchomienie w IDE

Możesz zaimportować projekt jako projekt Maven w IntelliJ IDEA lub VS Code (z rozszerzeniami Java). Ustaw klasę startową na `org.example.Main`.

## Opcjonalne ułatwienia (mogę dodać)

- Dodać przykładowy `employees.csv` do repo.
- Dodać skrypt PowerShell `run.ps1` który zbuduje i uruchomi aplikację.
- Zaktualizować README o dokładną nazwę JAR po `mvn package` (mogę to zrobić po uruchomieniu builda).

---
Jeśli chcesz, abym dodał przykładowy `employees.csv` lub uruchomił `mvn package` teraz i zaktualizował README o dokładną nazwę JAR — daj znać, a wykonam to.

