package ru.krivolutsky.work9.main;

import ru.krivolutsky.work9.classes.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Object> linkedList = new SinglyLinkedList<>();
        linkedList.setToStart(5);
        linkedList.setByIndex(1, "eqw");
        linkedList.setByIndex(2, 4);
        linkedList.setByIndex(3, (byte) 3);
        linkedList.setToStart("sad");
        linkedList.print();

        System.out.printf("Размер: %d%n", linkedList.getSize());

        System.out.print("Первый элемент: ");
        System.out.println(linkedList.getHeadItem());

        System.out.print("Элемент с индексом 2: ");
        System.out.println(linkedList.getByIndex(2));

        Object o = linkedList.changeByIndex(2, "dsa");
        System.out.print("Измененный элемент: ");
        System.out.println(o);
        linkedList.print();

        o = linkedList.deleteByIndex(2);
        System.out.print("Удаленный элемент: ");
        System.out.println(o);
        linkedList.print();

        System.out.println(linkedList.deleteByValue(5));
        linkedList.print();

        o = linkedList.deleteStartItem();
        System.out.print("Удаленный элемент: ");
        System.out.println(o);
        linkedList.print();

        linkedList.setToStart("qwe");
        linkedList.turn();
        linkedList.print();

        SinglyLinkedList<Object> linkedList1 = linkedList.copy();
        linkedList1.print();
    }
}