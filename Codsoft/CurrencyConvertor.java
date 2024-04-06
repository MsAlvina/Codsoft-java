import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.org.*;

public class CurrencyConverter {

    private static final String API_BASE_URL = "https://api.exchangeratesapi.io/latest";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the base currency (e.g., USD): ");
            String baseCurrency = reader.readLine().toUpperCase();

            System.out.println("Enter the target currency (e.g., EUR): ");
            String targetCurrency = reader.readLine().toUpperCase();

            System.out.println("Enter the amount to convert: ");
            double amount = Double.parseDouble(reader.readLine());

            double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);

            if (convertedAmount != -1) {
                System.out.printf("%.2f %s is equivalent to %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
            } else {
                System.out.println("Error fetching exchange rates. Please try again later.");
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        URL url = new URL(API_BASE_URL + "?base=" + baseCurrency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONObject rates = jsonResponse.getJSONObject("rates");
        return rates.optDouble(targetCurrency, -1);
    }

    private static double convertCurrency(double amount, String baseCurrency, String targetCurrency) {
        try {
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            if (exchangeRate != -1) {
                return amount * exchangeRate;
            }
        } catch (IOException e) {
            System.err.println("Error fetching exchange rate: " + e.getMessage());
        }
        return -1;
    }
}
