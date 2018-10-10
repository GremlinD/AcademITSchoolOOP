package ru.krivolutsky.work6.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkWithArrayList {
    public static ArrayList readLinesFromFile(String path) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            ArrayList<String> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            return list;
        }
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> createAListWithoutRepetitioms(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        for (Integer aList : list) {
            if (newList.indexOf(aList) == -1) {
                newList.add(aList);
            }
        }
        return newList;
    }
}
