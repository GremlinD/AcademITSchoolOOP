package ru.krivolutsky.work7.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<T>[] lists = new ArrayList[30];
    private int count;

    private int calculateHash(Object o) {
        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count > 0;
    }

    @Override
    public boolean contains(Object o) {
        int hash = calculateHash(o);
        if (lists[hash] != null) {
            return lists[hash].contains(o);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private T lists(int index) {
        return (T) lists[index];
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentList = 0;
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < count;
        }

        @Override
        public T next() {
            ++currentIndex;
            return lists(currentIndex);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[lists.length];
        System.arraycopy(lists, 0, objects, 0, lists.length);
        return objects;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < lists.length)
            // Make a new array of a's runtime type, but my contents:
            return (T1[]) Arrays.copyOf(lists, lists.length, a.getClass());
        System.arraycopy(lists, 0, a, 0, lists.length);
        if (a.length > lists.length)
            a[lists.length] = null;
        return a;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(T t) {
        int hash = calculateHash(t);
        if (lists[hash] == null) {
            lists[hash] = new ArrayList<>();
        }
        lists[hash].add(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hash = calculateHash(o);
        if (lists[hash] != null) {
            for (Object object : lists[hash]) {
                if (object.equals(o)) {
                    lists[hash].remove(o);
                    if (lists[hash].size() == 0) {
                        lists[hash] = null;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        for (Object o : c) {
            int hash = calculateHash(o);
            if (lists[hash] != null) {
                if (!lists[hash].contains(o)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private T tableObject(Object o) {
        return (T) o;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }
        for (T t : c) {
            this.add(t);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        boolean isChanged = false;
        for (Object o : c) {
            int hash = calculateHash(o);
            if (lists[hash] != null) {
                while (lists[hash].contains(o)) {
                    lists[hash].remove(o);
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        boolean isChanged = false;
        for (ArrayList a : lists) {
            if (a != null) {
                for (int i = 0; i < a.size(); i++) {
                    if (!c.contains(a.get(i))) {
                        a.remove(i);
                        isChanged = true;
                    }
                }
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.lists.length; i++) {
            lists[i] = null;
        }
    }
}
