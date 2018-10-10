package ru.krivolutsky.work5.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class scvToHtml {
    public void convertScvToHtml(String[] paths) throws FileNotFoundException {
        if (paths.length < 2) {
            throw new IllegalArgumentException("В аргуменах программы отсутствует один или более путей к файлам.");
        }
        if (!new File(paths[0]).exists()) {
            throw new FileNotFoundException("Не найден файл с именем: " + paths[0]);
        }

        try (Scanner scanner = new Scanner(new FileInputStream(paths[0]), "windows-1251"); PrintWriter writer = new PrintWriter(paths[1])) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Использование пустого файла невозможно.");
            }
            writer.println("<table>");
            int quotationMarkCount = 0;
            while (scanner.hasNextLine()) {
                int comma2 = 0;
                if (quotationMarkCount % 2 == 0) {
                    writer.println(" <tr>");
                }
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    writer.println("  <td></td>");
                    writer.println(" </tr>");
                    continue;
                }
                int comma1;
                while (comma2 < line.length()) {
                    if (quotationMarkCount % 2 == 0) {
                        writer.print("  <td>");
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
                        switch (line2.charAt(i)) {
                            case '<':
                                writer.print("&lt;");
                                break;
                            case '>':
                                writer.print("&gt;");
                                break;
                            case '&':
                                writer.print("&amp;");
                                break;
                            case '"':
                                if (quotationMarkCount % 2 == 0 && quotationMarkCount != 0) {
                                    writer.print(line2.charAt(i));
                                }
                                quotationMarkCount++;
                                break;
                            case ',':
                                if (quotationMarkCount % 2 != 0 && quotationMarkCount != 0) {
                                    writer.print(line2.charAt(i));
                                }
                                break;
                            default:
                                writer.print(line2.charAt(i));
                        }
                    }
                    if (quotationMarkCount % 2 == 0) {
                        writer.println("</td>");
                        quotationMarkCount = 0;
                    }
                }
                if (quotationMarkCount % 2 == 0) {
                    writer.println(" </tr>");
                    quotationMarkCount = 0;
                }
            }
            writer.print("</table>");
        }
    }
}
