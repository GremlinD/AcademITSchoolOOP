package ru.krivolutsky.work7.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HashTable<T> implements Collection<T> {
    private T element;
    private ArrayList[] array;

    public HashTable(int size) {
        this.array = new ArrayList[size];
    }

    @Override
    public int size() {
        return this.array.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size() > 0;
    }

    @Override
    public boolean contains(Object o) {
        int hash = Math.abs(o.hashCode() % this.size());
        for (Object object : this.array[hash]) {
            if (o.equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size() && array[index] != null;
            }

            @Override
            public T next() {
                return array[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
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
}
