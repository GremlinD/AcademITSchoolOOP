package ru.krivolutsky.work8.classes;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int length;

    public MyArrayList(Collection<? extends T> col) {

    }

    public MyArrayList(int capacity) {

    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T t : items) {
            if (o.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size() && items[index] != null;
            }

            @Override
            public T next() {
                return items[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size()];
        System.arraycopy(items, 0, objects, 0, size());
        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return a;
    }

    @Override
    public boolean add(T t) {
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (T t : items) {
            if (o.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o :
                c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        items = null;
    }

    @Override
    public T get(int index) {
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        T t = items[index];
        items[index] = element;
        return t;
    }

    @Override
    public void add(int index, T element) {
        if (length == items.length - 1) {
            increaseCapacity();
        }
        if (length - 1 - index >= 0) System.arraycopy(items, index, items, index + 1, length - 1 - index);
        items[index] = element;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public T remove(int index) {
        T t = items[index];
        if (length - 1 - index >= 0) System.arraycopy(items, index + 1, items, index, length - 1 - index);
        return t;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public void trimToSize(int size) {
        items = Arrays.copyOf(items, size);
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
