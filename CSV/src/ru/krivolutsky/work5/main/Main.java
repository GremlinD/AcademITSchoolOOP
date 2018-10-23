package ru.krivolutsky.work5.main;

import ru.krivolutsky.work5.classes.CsvToHtml;

public class Main {
    public static void main(String[] args) {
        CsvToHtml csv = new CsvToHtml();
        try {
            csv.convertScvToHtml(args[0], args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не указаны пути к файлам.");
        }
    }
}
