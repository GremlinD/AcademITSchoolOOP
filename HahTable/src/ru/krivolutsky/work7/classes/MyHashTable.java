package ru.krivolutsky.work7.classes;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    @SuppressWarnings("unchecked")
    private ArrayList<T>[] lists = new ArrayList[30];
    private int count;
    private int modCount;

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
        if (o == null) {
            return false;
        }
        int hash = calculateHash(o);
        if (lists[hash] != null) {
            return lists[hash].contains(o);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private T lists(Object o) {
        return (T) o;
    }

    /*ToDo
    Дописать ошибку при изменении списка
     */
    private class MyArrayListIterator implements Iterator<T> {
        private int nextCount = 0;
        private int currentIndex = -1;
        private int currentList = 0;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return nextCount + 1 < count;
        }

        @Override
        public T next() {
            while (true) {
                if (modCount != currentModCount) {
                    throw new ConcurrentModificationException("Коллекция была изменена.");
                }
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
        return new MyArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[count];
        int i = 0;
        for (ArrayList a : lists) {
            if (a != null) {
                for (Object o : a) {
                    objects[i] = o;
                    i++;
                }
            }
        }
        return objects;
    }

    /*todo
    разобраться с функцией
     */
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

    @Override
    public boolean add(T t) {
        if (t == null) {
            return false;
        }
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
        if (o == null) {
            return false;
        }
        int hash = calculateHash(o);
        if (lists[hash] != null) {
            for (Object object : lists[hash]) {
                if (object.equals(o)) {
                    lists[hash].remove(o);
                    if (lists[hash].size() == 0) {
                        lists[hash] = null;
                        count--;
                        modCount++;
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
                if (!lists[hash].contains(lists(o))) {
                    return false;
                }
            } else {
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
            int hash = calculateHash(o);
            if (lists[hash] != null) {
                while (lists[hash].contains(lists(o))) {
                    lists[hash].remove(lists(o));
                    count--;
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
        int count =  -1;
        for (ArrayList<T> a : lists) {
            count++;
            if (a != null) {
                int removeCount = 0;
                for (int i = 0; i < a.size(); i++) {
                    if (!c.contains(a.get(i))) {
                        a.remove(a.get(i));
                        removeCount++;
                        count--;
                        isChanged = true;
                    }
                }
                if (removeCount == size()) {
                    lists[count] = null;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int currentCount = 0;
        for (Object o : lists) {
            if (o == null) {
                continue;
            }
            builder.append(o);
            if (currentCount < count - 1) {
                builder.append(",");
            }
            currentCount++;
        }
        builder.append("]");
        return builder.toString();
    }
}
