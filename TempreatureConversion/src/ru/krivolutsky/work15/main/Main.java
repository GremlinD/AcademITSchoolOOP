package ru.krivolutsky.work15.main;

import ru.krivolutsky.work15.classes.TemperatureConversion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Перевод температур");
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setSize(220, 130);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            mainFrame.add(panel);
            JComboBox comboBox = new JComboBox();
            comboBox.setSize(400, 20);
            comboBox.addItem("Кельвин");
            comboBox.addItem("Цельсий");
            comboBox.addItem("Фаренгейт");
            panel.add(comboBox);

            JComboBox comboBox2 = new JComboBox();
            comboBox2.setSize(50, 20);
            comboBox2.addItem("Кельвин");
            comboBox2.addItem("Цельсий");
            comboBox2.addItem("Фаренгейт");
            panel.add(comboBox2);

            JTextField textField = new JTextField();
            textField.setColumns(8);
            panel.add(textField);

            JTextField textField2 = new JTextField();
            textField2.setColumns(8);
            textField2.setEnabled(false);
            panel.add(textField2);

            JButton button = new JButton("Ответ");
            button.addActionListener(e -> {
                int scale = comboBox.getSelectedIndex();
                int scale2 = comboBox2.getSelectedIndex();
                String temperature = textField.getText();
                if (isNumeric(temperature)) {
                    switch (scale) {
                        case 0:
                            switch (scale2) {
                                case 0:
                                    textField2.setText(temperature);
                                    break;
                                case 1:
                                    textField2.setText(Double.toString(TemperatureConversion.kelvinToCelsius(Double.parseDouble(temperature))));
                                    break;
                                case 2:
                                    textField2.setText(Double.toString(TemperatureConversion.kelvinToFahrenheit(Double.parseDouble(temperature))));
                                    break;
                            }
                            break;
                        case 1:
                            switch (scale2) {
                                case 0:
                                    textField2.setText(Double.toString(TemperatureConversion.celsiusToKelvin(Double.parseDouble(temperature))));
                                    break;
                                case 1:
                                    textField2.setText(temperature);
                                    break;
                                case 2:
                                    textField2.setText(Double.toString(TemperatureConversion.celsiusToFahrenheit(Double.parseDouble(temperature))));
                                    break;
                            }
                            break;
                        case 2:
                            switch (scale2) {
                                case 0:
                                    textField2.setText(Double.toString(TemperatureConversion.fahrenheitToKelvin(Double.parseDouble(temperature))));
                                    break;
                                case 1:
                                    textField2.setText(Double.toString(TemperatureConversion.fahrenheitToCelsius(Double.parseDouble(temperature))));
                                    break;
                                case 2:
                                    textField2.setText(temperature);
                                    break;
                            }
                            break;
                    }
                }
            });
            panel.add(button);
            mainFrame.setVisible(true);
        });
    }
}