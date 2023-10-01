package alura.dax.ConversionApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterFrame extends JFrame {
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountTextField;
    private JButton convertButton;
    private JLabel resultLabel;

    public CurrencyConverterFrame() {
        setTitle("Conversor de Moneda");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] currencies = {"USD", "EUR", "GBP", "JPY", "KRW", "Bs"};
        fromCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox.setSelectedItem("Bs");  // Selecciona Bs por defecto para la moneda de destino

        amountTextField = new JTextField(10);
        convertButton = new JButton("Convertir");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        JPanel panel = new JPanel();
        panel.add(fromCurrencyComboBox);
        panel.add(new JLabel("a"));
        panel.add(toCurrencyComboBox);
        panel.add(amountTextField);
        panel.add(convertButton);
        panel.add(resultLabel);

        add(panel);
    }

    private void convertCurrency() {
        String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
        String toCurrency = (String) toCurrencyComboBox.getSelectedItem();
        String amountText = amountTextField.getText();
        try {
            double amount = Double.parseDouble(amountText);
            double result = CurrencyConverter.convert(amount, fromCurrency, toCurrency);
            resultLabel.setText(String.format("%.2f %s", result, toCurrency));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad válida.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al convertir la moneda.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverterFrame().setVisible(true);
            }
        });
    }
}
