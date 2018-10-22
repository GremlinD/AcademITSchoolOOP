package ru.krivolutsky.work8.classes;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private Object[] items = new Object[10];
    private int length;

    public MyArrayList() {

    }

    public MyArrayList(int capacity) {
        this.items = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    private T items(int index) {
        return (T) items[index];
    }

    private  void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void print() {
        for (int i = 0; i < length; i++) {
            System.out.println(items[i]);
        }
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return items.length > 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    private class MyArrayListIterator implements Iterator<T>{
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex+1 < length;
        }

        @Override
        public T next() {
            ++currentIndex;
            return items(currentIndex);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[length];
        System.arraycopy(items, 0, array, 0, length);
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length)
            // Make a new array of a's runtime type, but my contents:
            return (T1[]) Arrays.copyOf(items, length, a.getClass());
        System.arraycopy(items, 0, a, 0, length);  //сделал как как в оригинальном ArrayList,
        // но остается ошибка.
        if (a.length > length)
            a[length] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        try {
            this.add(length, t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }
        int i = index;
        for (T t : c) {
            this.add(i, t);
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        for (int i = 0; i < length; i++) {
            if (c.contains(items[i])) {
                this.remove(i);
                i--;
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        for (int i = 0; i < length; i++) {
            if (!c.contains(items[i])) {
                this.remove(i);
                i--;
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            items[i] = null;
        }
        length = 0;
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
        if (length == items.length) {
            increaseCapacity();
        }
        length++;
        if (index < length - 1) {
            System.arraycopy(items, index, items, index + 1, length - index);
        }
        items[index] = element;
    }

    @Override
    public T remove(int index) {
        T t = items(index);
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }
        length--;
        return t;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (o.equals(items[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (o.equals(items[i])) {
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
