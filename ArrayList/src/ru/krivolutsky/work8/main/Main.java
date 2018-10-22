package ru.krivolutsky.work8.main;

import ru.krivolutsky.work8.classes.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Object> arrayList = new MyArrayList<>(10);

        arrayList.add(12);
        arrayList.add(45);
        arrayList.add("21231");
        arrayList.add('q');
        arrayList.add((char) 23);
        arrayList.add((byte) 2);
        arrayList.add("qwe");

    }
}
