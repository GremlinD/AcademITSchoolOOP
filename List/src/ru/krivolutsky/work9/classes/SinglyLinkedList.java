package ru.krivolutsky.work9.classes;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getHeadItem() {
        return head.getData();
    }

    public T getByIndex(int index) {
        try {
            if (index < 0 || index > count - 1) {
                throw new IndexOutOfBoundsException();
            }
            ListItem<T> p = head;
            for (int i = 0; i < index; i++) {
                p = p.getNext();
            }
            return p.getData();
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Индекс выходит за границы списка");
        }
        return null;
    }

    public T changeByIndex(int index, T item) {
        try {
            if (index < 0 || index > count - 1) {
                throw new IndexOutOfBoundsException();
            }
            ListItem<T> p = head;
            for (int i = 0; i < index; i++) {
                p = p.getNext();
            }
            T t = p.getData();
            p.setData(item);
            return t;
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Индекс выходит за границы списка");
        }
        return null;
    }

    public T deleteByIndex(int index) {
        try {
            if (index < 0 || index > count - 1) {
                throw new IndexOutOfBoundsException();
            }
            ListItem<T> p = head;
            ListItem<T> prev = head;
            for (int i = 0; i < index; i++) {
                prev = p;
                p = p.getNext();
            }
            T t = p.getData();
            prev.setNext(p.getNext());
            count--;
            return t;
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Индекс выходит за границы списка");
        }
        return null;
    }

    public void setToStart(T item) {
        head = new ListItem<>(item, head);
        count++;
    }

    public void setByIndex(int index, T item) {
        try {
            if (index < 0 || index > count) {
                throw new IndexOutOfBoundsException();
            }
            ListItem<T> p = head;
            ListItem<T> prev = head;
            for (int i = 0; i < index; i++) {
                prev = p;
                p = p.getNext();
            }
            ListItem<T> li = new ListItem<>(item, p);
            prev.setNext(li);
            count++;
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Индекс выходит за границы списка");
        }
    }

    public boolean deleteByValue(T item) {
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(item)) {
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

    public T deleteStartItem() {
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
        list.head = new ListItem<>(this.head.getData());
        ListItem<T> li = list.head;
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
