package ru.krivolutsky.work5.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    public void readCsv() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("csv.txt"), "windows-1251"); PrintWriter writer = new PrintWriter("html.txt")) {
            writer.println("table");
            boolean isQuotationMark = false;
            int i = 0;
            while (scanner.hasNextLine()) {
                writer.println("tr");
                writer.println("td");
                String line = scanner.nextLine();
                while (line.charAt(i) != '"' && line.charAt(i) != ',') {
                    writer.print(line.charAt(i));
                    i++;
                }
                if (isQuotationMark = false && line.charAt(i) == '"') {
                    isQuotationMark = true;
                    i++;
                    continue;
                }

            }
        }
    }
}
