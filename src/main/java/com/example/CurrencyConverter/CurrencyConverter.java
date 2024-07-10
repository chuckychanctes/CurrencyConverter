package com.example.CurrencyConverter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/9e93094a6e0646dd9062b363/latest/USD";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("######################################");
                System.out.println("Bienvenido al Convertidor 3000");
                System.out.println("######################################");
                System.out.println("1. Dólar Estadounidense (USD)");
                System.out.println("2. Peso Argentino (ARS)");
                System.out.println("3. Real Brasileño (BRL)");
                System.out.println("4. Peso Colombiano (COP)");
                System.out.println("5. Peso Mexicano (MXN)");
                System.out.println("6. Peso Uruguayo (UYU)");
                System.out.println("7. Euro (EUR)");
                System.out.println("8. Yen Japonés (JPY)");
                System.out.println("9. Salir");
                System.out.println("######################################");
                System.out.print("Seleccione la moneda de origen: ");
                int fromOption = scanner.nextInt();

                if (fromOption == 9) {
                    exit = true;
                    continue;
                }

                System.out.println("######################################");
                System.out.println("1. Dólar Estadounidense (USD)");
                System.out.println("2. Peso Argentino (ARS)");
                System.out.println("3. Real Brasileño (BRL)");
                System.out.println("4. Peso Colombiano (COP)");
                System.out.println("5. Peso Mexicano (MXN)");
                System.out.println("6. Peso Uruguayo (UYU)");
                System.out.println("7. Euro (EUR)");
                System.out.println("8. Yen Japonés (JPY)");
                System.out.println("9. Salir");
                System.out.println("######################################");
                System.out.print("Seleccione la moneda de destino: ");
                int toOption = scanner.nextInt();

                if (toOption == 9) {
                    exit = true;
                    continue;
                }
                System.out.println("********************************");
                System.out.print("Ingrese la cantidad a convertir: ");

                double amount = scanner.nextDouble();

                String fromCurrency = getCurrencyCode(fromOption);
                String toCurrency = getCurrencyCode(toOption);

                if (fromCurrency == null || toCurrency == null) {
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    continue;
                }

                double fromRate = conversionRates.get(fromCurrency).getAsDouble();
                double toRate = conversionRates.get(toCurrency).getAsDouble();

                double convertedAmount = (amount / fromRate) * toRate;
                System.out.println("********************************");
                System.out.printf("Monto convertido: %.2f %s\n", convertedAmount, toCurrency);

            }
            System.out.println("########################################");
            System.out.println("| Gracias por usar el Convertidor 3000 |");
            System.out.println("########################################");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrencyCode(int option) {
        switch (option) {
            case 1:
                return "USD";
            case 2:
                return "ARS";
            case 3:
                return "BRL";
            case 4:
                return "COP";
            case 5:
                return "MXN";
            case 6:
                return "UYU";
            case 7:
                return "EUR";
            case 8:
                return "JPY";
            default:
                return null;
        }
    }
}
