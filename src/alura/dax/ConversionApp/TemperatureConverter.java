package alura.dax.ConversionApp;

public class TemperatureConverter {

    public static double convert(double amount, String fromUnit, String toUnit) {
        double result = amount;

        // Convertir la cantidad a Celsius primero (si es necesario)
        if (fromUnit.equals("Fahrenheit")) {
            result = (amount - 32) * 5/9;
        } else if (fromUnit.equals("Kelvin")) {
            result = amount - 273.15;
        }

        // Convertir de Celsius a la unidad objetivo
        if (toUnit.equals("Fahrenheit")) {
            result = (result * 9/5) + 32;
        } else if (toUnit.equals("Kelvin")) {
            result = result + 273.15;
        }

        return result;
    }
}
