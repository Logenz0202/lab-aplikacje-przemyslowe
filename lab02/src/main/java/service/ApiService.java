package service;

import com.google.gson.*;
import model.Employee;
import model.Position;
import exception.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {
    public List<Employee> fetchEmployeesFromApi() throws ApiException {
        String url = "https://jsonplaceholder.typicode.com/users";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ApiException("Błąd HTTP: " + response.statusCode());
            }
            String body = response.body();
            JsonArray arr = JsonParser.parseString(body).getAsJsonArray();
            List<Employee> result = new ArrayList<>();
            for (JsonElement el : arr) {
                JsonObject obj = el.getAsJsonObject();
                String fullName = obj.get("name").getAsString();
                String[] names = fullName.split(" ", 2);
                String firstName = names.length > 0 ? names[0] : "";
                String lastName = names.length > 1 ? names[1] : "";
                String email = obj.get("email").getAsString();
                String company = obj.getAsJsonObject("company").get("name").getAsString();
                Position position = Position.PROGRAMISTA;
                double salary = position.getBaseSalary();
                Employee emp = new Employee(firstName, lastName, email, company, position, salary);
                result.add(emp);
            }
            return result;
        } catch (IOException | InterruptedException | IllegalStateException | NullPointerException | JsonParseException e) {
            throw new ApiException("Błąd pobierania lub parsowania danych: " + e.getMessage());
        }
    }
}
