package ru.krivolutsky.work5.main;

import ru.krivolutsky.work5.classes.CsvToHtml;

public class Main {
    public static void main(String[] args) {
        CsvToHtml csv = new CsvToHtml();
        if (args.length > 1) {
            csv.convertCsvToHtml(args[0], args[1]);
        } else {
            System.out.println("Введите в аргументы программы название Csv файла и название конечного html файла");
        }
    }
}
