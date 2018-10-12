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
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> createListWithoutRepetitions(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        for (Integer i : list) {
            if (!newList.contains(i)) {
                newList.add(i);
            }
        }
        return newList;
    }
}
