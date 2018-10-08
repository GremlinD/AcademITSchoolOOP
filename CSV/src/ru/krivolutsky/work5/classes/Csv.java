package ru.krivolutsky.work5.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    public void readCsvParseHtml() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("csv.txt"), "windows-1251"); PrintWriter writer = new PrintWriter("html.txt")) {
            writer.println("<table>");


            int quotationMarkCount = 0;
            while (scanner.hasNextLine()) {
                int comma2 = 0;
                if (quotationMarkCount % 2 == 0) {
                    writer.println("<tr>");
                }
                String line = scanner.nextLine();
                int comma1;
                while (comma2 < line.length()) {
                    if (quotationMarkCount % 2 == 0) {
                        writer.print("<td>");
                    } else if (line.charAt(comma2) != ',') {
                        writer.print("<br/>");
                    }
                    comma1 = comma2;
                    comma2 = line.indexOf(',', comma1 + 1);

                    String line2;
                    if (comma2 == -1) {
                        line2 = line.substring(comma1);
                        comma2 = line.length();
                    } else {
                        line2 = line.substring(comma1, comma2);
                    }

                    for (int i = 0; i < line2.length(); i++) {
                        if (line2.charAt(i) != '"' && line2.charAt(i) != ',') {
                            switch (line2.charAt(i)) {
                                case '<':
                                    writer.print("&lt");
                                    break;
                                case '>':
                                    writer.print("&gt");
                                    break;
                                case '&':
                                    writer.print("&amp");
                                    break;
                                default:
                                    writer.print(line2.charAt(i));
                            }
                        }
                        if (line2.charAt(i) == '"') {
                            if (quotationMarkCount % 2 == 0 && quotationMarkCount != 0) {
                                writer.print(line2.charAt(i));
                            }
                            quotationMarkCount++;
                        }
                        if (line2.charAt(i) == ',') {
                            if (quotationMarkCount % 2 != 0 && quotationMarkCount != 0) {
                                writer.print(line2.charAt(i));
                            }
                        }
                    }
                    if (quotationMarkCount % 2 == 0) {
                        writer.println("</td>");
                        quotationMarkCount = 0;
                    }
                }
                if (quotationMarkCount % 2 == 0) {
                    writer.println("</tr>");
                    quotationMarkCount = 0;
                }
            }
            writer.print("</table>");
        }
    }
}
