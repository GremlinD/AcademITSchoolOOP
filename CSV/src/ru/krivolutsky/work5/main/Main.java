package ru.krivolutsky.work5.main;

import ru.krivolutsky.work5.classes.Csv;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Csv csv = new Csv();
        csv.readCsv();
    }

}
