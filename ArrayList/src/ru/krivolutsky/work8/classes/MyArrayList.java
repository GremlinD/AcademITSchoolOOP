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

    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
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

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
