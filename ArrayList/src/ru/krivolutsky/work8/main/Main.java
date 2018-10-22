package ru.krivolutsky.work8.main;

import ru.krivolutsky.work8.classes.MyArrayList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Object> arrayList = new MyArrayList<>();
        System.out.println(arrayList.isEmpty());
        System.out.println();
        arrayList.add(12);
        arrayList.add("prevSecond");
        arrayList.add((char) 20);
        arrayList.add(12);
        arrayList.add("asd");
        arrayList.add(1, "second");
        arrayList.print();
        System.out.println();
        System.out.println(arrayList.lastIndexOf(12));
        System.out.println();
        System.out.println(arrayList.indexOf(12));
        System.out.println();
        arrayList.remove(2);
        arrayList.print();
        System.out.println();
        arrayList.set(2, "new");
        arrayList.print();
        System.out.println();
        System.out.println(arrayList.get(2));
        System.out.println();

        List<Object> objects = new ArrayList<>();
        objects.add(12);
        objects.add("second");
        objects.add(2);
        arrayList.retainAll(objects);
        arrayList.print();
        System.out.println();
        arrayList.add(3);
        arrayList.removeAll(objects);
        arrayList.print();
        System.out.println();
        arrayList.addAll(objects);
        arrayList.print();
        System.out.println();
        arrayList.addAll(2, objects);
        arrayList.print();
        System.out.println();
        System.out.println(arrayList.containsAll(objects));
        objects.add(135);
        System.out.println(arrayList.containsAll(objects));
        System.out.println();
        arrayList.remove((Integer)12);
        arrayList.print();
        System.out.println();
        Object[] objects1 = new Object[10];
        objects1 = arrayList.toArray();
        for (Object o : objects1) {
            System.out.println(o);
        }


    }
}
