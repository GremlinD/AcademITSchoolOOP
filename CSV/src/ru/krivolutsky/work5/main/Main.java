package ru.krivolutsky.work5.main;

import ru.krivolutsky.work5.classes.CsvToHtml;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CsvToHtml csv = new CsvToHtml();
        csv.convertScvToHtml(args[0], args[1]);
    }
}
