package ru.krivolutsky.work7.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList[] lists = new ArrayList[10];

    private int calculateHash(Object o) {
        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public int size() {
        return lists.length;
    }

    @Override
    public boolean isEmpty() {
        return lists.length > 0;
    }

    @Override
    public boolean contains(Object o) {
        int hash = calculateHash(o);
        if (lists[hash] != null) {
            for (Object object : lists) {
                if (object.equals(o)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
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
        for (int i = 0; i < this.lists.length; i++) {
            lists[i] = null;
        }
    }
}
