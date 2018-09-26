package ru.krivolutsky.work2.main;

import ru.krivolutsky.work2.classes.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(4));
        shapes.add(new Rectangle(2, 4));
        shapes.add(new Square(5));
        shapes.add(new Triangle(1, 2, 4, 7, -1, -4));
        shapes.add(new Square(7));
        shapes.add(new Rectangle(6, 3));
        shapes.add(new Circle(8));


    }
}
