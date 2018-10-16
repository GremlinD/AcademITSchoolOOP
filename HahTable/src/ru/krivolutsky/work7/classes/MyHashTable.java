package ru.krivolutsky.work7.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyHashTable<T> implements Collection<ArrayList> {
    private ArrayList[] hashTable = new ArrayList[10];

    @Override
    public int size() {
        return hashTable.length;
    }

    @Override
    public boolean isEmpty() {
        return hashTable.length > 0;
    }

    private int calculateHashCode(Object o) {
        return Math.abs(o.hashCode() % hashTable.length);
    }

    @Override
    public boolean contains(Object o) {
        int hash = calculateHashCode(o);
        if (hash < hashTable.length && hashTable[hash] != null) {
            for (Object object : hashTable[hash]) {
                if (o.equals(object)) {
                    return true;
                }
            }
        }
        return false;
    }

    private class MyHashTableIterator {
        private int currentIndex = -1;

        public boolean hasNext() {
            return currentIndex + 1 < size();
        }

        public ArrayList next() {
            ++currentIndex;
            return hashTable[currentIndex];
        }
    }

    @Override
    public Iterator<ArrayList> iterator() {
        return new Iterator<>() {
            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex + 1 < size();
            }

            @Override
            public ArrayList next() {
                ++currentIndex;
                return hashTable[currentIndex];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] object = new Object[hashTable.length];
        System.arraycopy(hashTable, 0, object, 0, hashTable.length);
        return object;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(ArrayList t) {
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
    public boolean addAll(Collection<? extends ArrayList> c) {
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
