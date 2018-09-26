package ru.krivolutsky.work2.classes;

import java.util.Comparator;

public class SortByArea implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        if (o1.getArea() > o2.getArea()) {
            return 1;
        } else if (o1.getArea() == o2.getArea()) {
            return 0;
        } else {
            return -1;
        }
    }
}
