package ru.krivolutsky.work5.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    public void readCsv() throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new FileInputStream("csv.txt"),"windows-1251"); PrintWriter writer = new PrintWriter("html.txt")){
            writer.println("table");
            while (scanner.hasNextLine()) {
                writer.println("tr");
                String line = scanner.nextLine();
                writer.println("td");
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ',' || line.charAt(i)!='"') {
                        writer.print(line.charAt(i));
                        continue;
                    }
                    if (line.charAt(i) == '"' && line.charAt(i - 1) == ',') {
                        writer.println();
                        writer.println("/td");
                        writer.println("td");
                        i++;
                        while (line.charAt(i) != '"'){
                            writer.print(line.charAt(i));
                            i++;
                        }
                        i+=2;
                        continue;
                    }
                    if (line.charAt(i) == ',') {
                        i++;
                        writer.println();
                        writer.println("td");
                        writer.println("/td");
                    }
                }
                writer.println("/td");
            }
            writer.print("/table");
        }
    }
}
