package ru.krivolutsky.work9.classes;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getHeadItem() {
        if (count == 0) {
            throw new NullPointerException("Список пуст");
        }
        return head.getData();
    }

    private ListItem<T> reachItem(int index) {
        ListItem<T> p = head;
        for (int i = 0; i < index; i++) {
            p = p.getNext();
        }
        return p;
    }

    public T getByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        ListItem<T> p = this.reachItem(index);
        return p.getData();
    }

    public T changeByIndex(int index, T item) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        ListItem<T> p = this.reachItem(index);
        T t = p.getData();
        p.setData(item);
        return t;
    }

    public T deleteByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        T t;
        if (index == 0) {
            count--;
            t = head.getData();
            head = head.getNext();
            return t;
        }
        ListItem<T> prev = this.reachItem(index - 1);
        ListItem<T> p = prev.getNext();
        t = p.getData();
        prev.setNext(p.getNext());
        count--;
        return t;
    }

    public void addFirst(T item) {
        head = new ListItem<>(item, head);
        count++;
    }

    public void add(int index, T item) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        if (index == 0) {
            head = new ListItem<>(item, head);
            count++;
            return;
        }
        ListItem<T> prev = this.reachItem(index - 1);
        ListItem<T> p = prev.getNext();
        ListItem<T> li = new ListItem<>(item, p);
        prev.setNext(li);
        count++;
    }

    public boolean deleteByValue(T item) {
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(p.getData(), item)) {
                if (prev == null) {
                    head = p.getNext();
                } else {
                    prev.setNext(p.getNext());
                }
                count--;
                return true;
            }
        }
        return false;
    }

    public T deleteFirstItem() {
        if (count == 0) {
            throw new NullPointerException("Список пуст.");
        }
        T t = head.getData();
        head = head.getNext();
        count--;
        return t;
    }

    public void turn() {
        if (head == null) {
            return;
        }
        ListItem<T> current = head;
        ListItem<T> next;
        ListItem<T> prev = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> list = new SinglyLinkedList<>();
        list.count = this.count;
        if (this.head == null) {
            return list;
        }
        list.head = new ListItem<>(this.head.getData());
        ListItem<T> li = list.head;
        list.count++;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            li.setNext(new ListItem<>(p.getData(), p.getNext()));
            li = li.getNext();
        }
        return list;
    }

    public void print() {
        System.out.println("Список:");
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            System.out.println(p.getData());
        }
    }
}