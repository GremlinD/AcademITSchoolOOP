package ru.krivolutsky.work7.classes;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    @SuppressWarnings("unchecked")
    private ArrayList<T>[] lists = new ArrayList[30];
    private int count;
    private int modCount;

    private int calculateHash(Object o) {
        return o != null ? Math.abs(o.hashCode() % lists.length) : 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private class MyHashTableIterator implements Iterator<T> {
        private int nextCount = 0;
        private int currentIndex = -1;
        private int currentList = 0;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return nextCount + 1 <= count;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Таблица закончилась.");
            }
            if (modCount != currentModCount) {
                throw new ConcurrentModificationException("Коллекция была изменена.");
            }
            while (true) {
                if (currentList >= lists.length) {
                    throw new NoSuchElementException("Таблица закончилась.");
                }
                if (lists[currentList] == null) {
                    currentList++;
                    continue;
                }
                if (currentIndex + 1 < lists[currentList].size()) {
                    currentIndex++;
                    nextCount++;
                    return lists[currentList].get(currentIndex);
                } else {
                    currentIndex = -1;
                    currentList++;
                }
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[count];
        int i = 0;
        for (T t : this) {
            objects[i] = t;
            i++;
        }
        return objects;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        Object[] objects = new Object[count];
        int i = -1;
        for (Object o : a) {
            i++;
            objects[i] = o;
        }
        if (a.length < count) {
            return (T1[]) Arrays.copyOf(objects, count, a.getClass());
        }
        return (T1[]) objects;
    }

    @Override
    public boolean add(T t) {
        int hash = calculateHash(t);
        if (lists[hash] == null) {
            lists[hash] = new ArrayList<>();
        }
        lists[hash].add(t);
        count++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hash = calculateHash(o);
        boolean isChange = false;
        if (lists[hash] != null) {
            isChange = lists[hash].remove(o);
            if (isChange) {
                count--;
            }
            if (lists[hash].size() == 0) {
                lists[hash] = null;
            }
        }
        return isChange;
    }

    @Override
    public boolean contains(Object o) {
        int hash = calculateHash(o);
        if (lists[hash] != null) {
            boolean is = lists[hash].contains(o);
            return is;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
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
            while (true) {
                if (!this.remove(o)) {
                    break;
                } else {
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
        for (ArrayList<T> list : this.lists) {
            if (list == null) {
                continue;
            }
            for (int j = 0; j < list.size(); j++) { //требует foreach, но foreach вызывает ошибку.
                if (!c.contains(list.get(j))) {
                    this.remove(list.get(j));
                    isChanged = true;
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
        count = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int currentCount = 0;
        for (ArrayList<T> o : lists) {
            if (o == null) {
                continue;
            }
            builder.append(o);
            if (currentCount < count - 1) {
                builder.append(",");
            }
            currentCount += o.size();
        }
        builder.append("]");
        return builder.toString();
    }
}
