package ru.krivolutsky.work15.main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Перевод температур");
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setSize(206, 130);

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

            JTextField textField = new JTextField("qweqwe");
            textField.setColumns(8);
            panel.add(textField);

            JTextField textField2 = new JTextField();
            textField2.setColumns(8);
            panel.add(textField2);

            JButton button = new JButton("Ответ");
            panel.add(button);

            mainFrame.setVisible(true);
        });
    }
}
