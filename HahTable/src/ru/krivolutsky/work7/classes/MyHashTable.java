package ru.krivolutsky.work7.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyHashTable<T> implements Collection<T> {
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
        private int currentIndex = -1;
        private int currentList = 0;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < count;
        }

        @Override
        public T next() {

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
