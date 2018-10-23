package ru.krivolutsky.work7.main;

import ru.krivolutsky.work7.classes.MyHashTable;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Object> hashTable = new MyHashTable<>();
        hashTable.add(12);
        hashTable.add("qwe");
        hashTable.add((char) 20);
        hashTable.add((byte) 6);
        hashTable.add(12);
        hashTable.add("asd");

        System.out.println(hashTable.contains((char) 20));
        System.out.println(hashTable.contains(1));

        hashTable.remove("qwe");
        hashTable.remove(12);

        System.out.println();
    }
}
