package ru.krivolutsky.work8.classes;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private Object[] items;
    private int length;
    private int itemsCount = 0;
    private int modCount = 0;

    public MyArrayList() {

    }

    public MyArrayList(int capacity) {
        items = new Object[capacity];
        length = capacity;
    }

    @Override
    public int size() {
        return itemsCount;
    }

    @Override
    public boolean isEmpty() {
        return length > 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < length; i++) {
            if (!items[i].equals(o)) {
                return false;
            }
        }
        return true;
    }

    /*TODO

     */
    @Override
    public Iterator<T> iterator() {
        /*
        return new Iterator<ArrayList>() {
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
        */
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[length];
        System.arraycopy(items, 0, objects, 0, length);
        return objects;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length)
            // Make a new array of a's runtime type, but my contents:
            return (T1[]) Arrays.copyOf(items, length, a.getClass());
        System.arraycopy(items, 0, a, 0, length);
        if (a.length > length)
            a[length] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        this.add(itemsCount, t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                this.remove(i);
                modCount++;
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
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() < 1) {
            return false;
        }
        int i = 0;
        for (T t : c) {
            this.add(length + i, t);
            i++;
        }
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        int i = 0;
        if (c.size() < 1) {
            return false;
        }
        for (T t : c) {
            this.add(index + i, t);
            i++;
        }
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean mod = false;
        for (int i = 0; i < length; i++) {
            if (c.contains(items[i])) {
                this.remove(i);
                length--;
                i--;
                mod = true;
            }
        }
        modCount++;
        return mod;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean mod = false;
        for (int i = 0; i < length; i++) {
            if (!c.contains(items[i])) {
                this.remove(i);
                length--;
                i--;
                mod = true;
            }
        }
        modCount++;
        return mod;
    }

    @Override
    public void clear() {
        if (length > 0) {
            this.subList(0, length).clear();
        }
    }

    @SuppressWarnings("unchecked")
    private T items(int index) {
        return (T) items[index];
    }

    @Override
    public T get(int index) {
        return items(index);
    }

    @Override
    public T set(int index, T element) {
        T t = items(index);
        items[index] = element;
        return t;
    }

    @Override
    public void add(int index, T element) {
        if (itemsCount >= items.length - 1) {
            increaseCapacity();
        }
        if (index == length - 1) {
            items[index] = element;
            ++itemsCount;
        } else {
            if (itemsCount - index >= 0) System.arraycopy(items, index, items, index + 1, itemsCount - index);
            items[index] = element;
            ++itemsCount;
        }
        modCount++;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public T remove(int index) {
        T t = items(index);
        if (length - 1 - index >= 0) System.arraycopy(items, index + 1, items, index, length - 1 - index);
        length--;
        modCount++;
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
