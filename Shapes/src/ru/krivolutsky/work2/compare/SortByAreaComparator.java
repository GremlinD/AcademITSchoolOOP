package ru.krivolutsky.work2.compare;

import ru.krivolutsky.work2.classes.Shape;

import java.util.Comparator;

public class SortByAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}
