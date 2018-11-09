package ru.krivolutsky.work8.classes;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Object[10];
    private int length;
    private int modCount = 0;

    public MyArrayList() {
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
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
        return this.indexOf(o) != -1;
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public T next() {
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException("Коллекция была изменена.");
            }
            if (currentIndex >= items.length) {
                throw new NoSuchElementException("Таблица закончилась.");
            }
            ++currentIndex;
            return items[currentIndex];
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
        if (a.length < length) {
            return (T1[]) Arrays.copyOf(items, length, a.getClass());
        }
        System.arraycopy(items, 0, a, 0, length);
        if (a.length > length) {
            a[length] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        this.add(length, t);
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int i = this.indexOf(o);
        if (i != -1) {
            this.remove(i);
            modCount++;
            return true;
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
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        if (c.size() == 0) {
            return false;
        }
        if (length + c.size() >= items.length) {
            increaseCapacity();
        }
        System.arraycopy(items, index, items, index + 1, length + c.size() - index);
        int i = index;
        for (T t : c) {
            items[i] = t;
            i++;
        }
        modCount++;
        length += c.size();
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
                modCount++;
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
                modCount++;
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
        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (element == null) {
            return null;
        }
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        T t = items[index];
        items[index] = element;
        modCount++;
        return t;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        if (length == items.length) {
            increaseCapacity();
        }
        if (index < length - 1) {
            System.arraycopy(items, index, items, index + 1, length - index);
        }
        items[index] = element;
        length++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка");
        }
        T t = items[index];
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }
        length--;
        modCount++;
        return t;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i] == null) {
                if (o == null) {
                    return i;
                }
                else {
                    if (i == length - 1){
                        return -1;
                    } else {
                        continue;
                    }
                }
            }
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (items[i] == null) {
                if (o == null) {
                    return i;
                } else {
                    return -1;
                }
            }
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < items.length; i++) {
            builder.append(items[i]);
            if (i != items.length - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
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
