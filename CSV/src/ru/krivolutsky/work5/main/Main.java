package ru.krivolutsky.work5.main;

import ru.krivolutsky.work5.classes.scvToHtml;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        scvToHtml csv = new scvToHtml();
        csv.convertScvToHtml(args);
    }
}
