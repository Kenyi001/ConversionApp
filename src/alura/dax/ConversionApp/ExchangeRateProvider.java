package alura.dax.ConversionApp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateProvider {

    private static final String API_KEY = "your_api_key_here";

    public static JSONObject getExchangeRates() throws Exception {
        String urlStr = "http://api.exchangeratesapi.io/v1/latest?access_key=" + API_KEY;  // Cambiado https a http
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {  // success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONObject(response.toString());
        } else {
            System.out.println("GET request failed with response code: " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String inputLine;
            StringBuffer errorResponse = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                errorResponse.append(inputLine);
            }
            in.close();
            System.out.println("Error response: " + errorResponse.toString());
            return null;
        }
    }
}

