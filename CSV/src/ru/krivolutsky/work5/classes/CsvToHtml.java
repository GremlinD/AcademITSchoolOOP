package ru.krivolutsky.work5.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CsvToHtml {
    public void convertScvToHtml(String pathCsv, String pathHtml) {
        try (Scanner scanner = new Scanner(new FileInputStream(pathCsv), "windows-1251"); PrintWriter writer = new PrintWriter(pathHtml, StandardCharsets.UTF_8)) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException();
            }
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println(" <head>");
            writer.println("  <meta charset=\"utf-8\" />");
            writer.println("  <title>HTML Document</title>");
            writer.println(" </head>");
            writer.println(" <body>");
            writer.println("  <table>");
            int quotationMarkCount = 0;
            while (scanner.hasNextLine()) {
                int comma2 = 0;
                if (quotationMarkCount % 2 == 0) {
                    writer.println("   <tr>");
                }
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    writer.println("    <td></td>");
                    writer.println("   </tr>");
                    continue;
                }
                int comma1;
                while (comma2 < line.length()) {
                    if (quotationMarkCount % 2 == 0) {
                        writer.print("    <td>");
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
                                if (comma1 == 0 || i == line.length() - 1) {
                                    writer.println("</td>");
                                    writer.print("    <td>");
                                    continue;
                                }
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
                    writer.println("   </tr>");
                    quotationMarkCount = 0;
                }
            }
            writer.println("  </table>");
            writer.println(" </body>");
            writer.println("</html>");
        } catch (IllegalArgumentException i) {
            System.out.println("Использование пустого файла невозможно.");
        } catch (FileNotFoundException f) {
            System.out.println("Не найден файл с именем: " + pathCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}