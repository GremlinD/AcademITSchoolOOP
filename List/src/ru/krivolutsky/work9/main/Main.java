package ru.krivolutsky.work9.main;

import ru.krivolutsky.work9.classes.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Object> linkedList = new SinglyLinkedList<>();
        linkedList.addFirst(5);
        try {
            linkedList.add(1, "eqw");
            linkedList.add(2, 4);
            linkedList.add(-1, (byte) 3);

            linkedList.addFirst("sad");
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

            o = linkedList.deleteFirstItem();
            System.out.print("Удаленный элемент: ");
            System.out.println(o);
            linkedList.print();

            linkedList.addFirst("qwe");
            linkedList.turn();
            linkedList.print();

            SinglyLinkedList<Object> linkedList1 = linkedList.copy();
            linkedList1.print();
        }
        catch (NullPointerException | IndexOutOfBoundsException exception) {
            System.out.println(exception.toString());
        }
    }
}