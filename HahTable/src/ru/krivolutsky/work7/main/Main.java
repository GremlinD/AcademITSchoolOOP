package ru.krivolutsky.work7.main;

import ru.krivolutsky.work7.classes.MyHashTable;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Object> hashTable2 = new MyHashTable<>();
        hashTable2.add(12);
        hashTable2.add("qwe");
        hashTable2.add("asd");

        MyHashTable<Object> hashTable = new MyHashTable<>();
        hashTable.add(12);
        hashTable.add("qwe");
        hashTable.add((char) 20);
        hashTable.add((byte) 6);
        hashTable.add(12);
        hashTable.add("asd");
        hashTable.add(null);

        System.out.println(hashTable.size());
        System.out.println(hashTable.isEmpty());
        System.out.println(hashTable.contains("qwe"));
        System.out.println(hashTable.contains(1));
        System.out.println(hashTable.contains(null));
        Object[] objects = hashTable.toArray();

        System.out.println(hashTable.remove("qwe"));
        System.out.println(hashTable.remove(1));
        System.out.println(hashTable.remove(null));

        System.out.println(hashTable.containsAll(hashTable2));


        System.out.println();
    }
}
