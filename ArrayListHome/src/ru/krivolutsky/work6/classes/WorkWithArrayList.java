package ru.krivolutsky.work6.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkWithArrayList {
    public static ArrayList readLinesFromFile(String path) throws FileNotFoundException {
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("Файл с таким именем не найден");
        }
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            ArrayList<String> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            return list;
        }
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        if (list.size() < 1){
            throw new IllegalArgumentException("Список пуст, операция невозможна.");
        }
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> createAListWithoutRepetitions(ArrayList<Integer> list) {
        if (list.size() < 1){
            throw new IllegalArgumentException("Список пуст, операция невозможна.");
        }
        ArrayList<Integer> newList = new ArrayList<>();
        for (Integer aList : list) {
            if (newList.indexOf(aList) == -1) {
                newList.add(aList);
            }
        }
        return newList;
    }
}
