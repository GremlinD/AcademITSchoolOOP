package ru.krivolutsky.work6.main;

import ru.krivolutsky.work6.classes.WorkWithArrayList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList list = WorkWithArrayList.readLinesFromFile(args[0]);
        System.out.println(list.toString());

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(3, 6, 8, 4, 1, 53, 97));
        WorkWithArrayList.deleteEvenNumbers(list2);
        System.out.println(list2.toString());

        list2 = new ArrayList<>(Arrays.asList(3, 6, 2, 7, 4, 3, 8, 2, 6, 5, 6, 4));
        ArrayList<Integer> list3 = WorkWithArrayList.createAListWithoutRepetitions(list2);
        System.out.println(list3);
    }
}
