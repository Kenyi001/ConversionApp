package alura.dax.ConversionApp;

import javax.swing.*;

public class UserInterface {
    public static void showMainMenu() {
        String[] options = {"Conversor de Moneda", "Conversor de Temperatura"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción:",
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0:
                showConversionMenu();  // Llama al menú de conversión de moneda
                break;
            case 1:
                showTemperatureMenu();  // Llama al menú de conversión de temperatura (aún no definido)
                break;
            default:
                // El usuario cerró el diálogo o hizo clic en el botón de cierre
                break;
        }
    }
    public static void showTemperatureMenu() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverterFrame().setVisible(true);
            }
        });
    }
    public static void showConversionMenu() {
        while (true) {  // Bucle que se ejecuta indefinidamente
            String[] currencies = {"USD", "EUR", "GBP", "JPY", "KRW", "BOB"};
            JComboBox<String> fromCurrencyComboBox = new JComboBox<>(currencies);
            JComboBox<String> toCurrencyComboBox = new JComboBox<>(currencies);
            toCurrencyComboBox.setSelectedItem("BOB");

            JPanel panel = new JPanel();
            panel.add(new JLabel("De:"));
            panel.add(fromCurrencyComboBox);
            panel.add(new JLabel("A:"));
            panel.add(toCurrencyComboBox);
            panel.add(new JLabel("Cantidad:"));
            JTextField amountTextField = new JTextField(10);
            panel.add(amountTextField);

            int choice = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Conversor de Moneda",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (choice == JOptionPane.OK_OPTION) {
                String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
                String toCurrency = (String) toCurrencyComboBox.getSelectedItem();
                String amountStr = amountTextField.getText();
                try {
                    double amount = Double.parseDouble(amountStr);
                    performConversion(fromCurrency, toCurrency, amount);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                }
            } else {
                break;  // Si el usuario selecciona "Cancelar", sale del bucle y termina el programa
            }
        }
    }

    public static void performConversion(String fromCurrency, String toCurrency, double amount) {
        try {
            double result = CurrencyConverter.convert(amount, fromCurrency, toCurrency);
            String resultCurrency = toCurrency;
            String formattedResult = String.format("%.2f", result);  // Redondea el resultado a dos decimales
            JOptionPane.showMessageDialog(
                    null,
                    amount + " " + fromCurrency + " es igual a " + formattedResult + " " + resultCurrency,
                    "Resultado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al realizar la conversión.");
        }
    }
}
