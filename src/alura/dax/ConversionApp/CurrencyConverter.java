package alura.dax.ConversionApp;

import org.json.JSONObject;

public class CurrencyConverter {
    public static double convert(double amount, String fromCurrency, String toCurrency) throws Exception {
        JSONObject rates = ExchangeRateProvider.getExchangeRates();
        if (rates != null) {
            double fromRate = rates.getJSONObject("rates").getDouble(fromCurrency);
            double toRate = rates.getJSONObject("rates").getDouble(toCurrency);
            return (amount / fromRate) * toRate;
        } else {
            throw new Exception("Failed to retrieve exchange rates");
        }
    }

    // Similar methods for other currencies...
}

