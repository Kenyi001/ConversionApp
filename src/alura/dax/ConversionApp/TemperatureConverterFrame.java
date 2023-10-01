package alura.dax.ConversionApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterFrame extends JFrame {

    private JComboBox<String> fromTemperatureComboBox;
    private JComboBox<String> toTemperatureComboBox;
    private JTextField amountTextField;
    private JButton convertButton;
    private JLabel resultLabel;

    public TemperatureConverterFrame() {
        setTitle("Conversor de Temperatura");
        setSize(400, 110);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] temperatureUnits = {"Celsius", "Fahrenheit", "Kelvin"};
        fromTemperatureComboBox = new JComboBox<>(temperatureUnits);
        toTemperatureComboBox = new JComboBox<>(temperatureUnits);

        amountTextField = new JTextField(10);
        convertButton = new JButton("Convertir");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        JPanel panel = new JPanel();  // 0 filas significa que se añadirán tantas filas como sea necesario
        panel.add(fromTemperatureComboBox);
        panel.add(new JLabel("a"));
        panel.add(toTemperatureComboBox);
        panel.add(amountTextField);
        panel.add(convertButton);
        panel.add(resultLabel);

        add(panel);
    }

    private void convertTemperature() {
        String fromUnit = (String) fromTemperatureComboBox.getSelectedItem();
        String toUnit = (String) toTemperatureComboBox.getSelectedItem();
        String amountText = amountTextField.getText();
        try {
            double amount = Double.parseDouble(amountText);
            double result = TemperatureConverter.convert(amount, fromUnit, toUnit);  // Llama al método static convert
            resultLabel.setText(String.format("%.2f %s", result, toUnit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad válida.");
        }
    }
}
