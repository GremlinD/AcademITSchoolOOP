package ru.krivolutsky.work2.main;

import ru.krivolutsky.work2.classes.*;
import ru.krivolutsky.work2.compare.SortByAreaComparator;
import ru.krivolutsky.work2.compare.SortByPerimeterComparator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Shape findFigureWithLargestArea(List<Shape> shapes) {
        shapes.sort(new SortByAreaComparator());
        return shapes.get(shapes.size() - 1);
    }

    private static Shape findFigureWithLargestPerimeter(List<Shape> shapes) {
        shapes.sort(new SortByPerimeterComparator());
        return shapes.get(shapes.size() - 2);
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(4));
        shapes.add(new Rectangle(2, 4));
        shapes.add(new Square(5));
        shapes.add(new Triangle(1, 2, 4, 7, -1, -4));
        shapes.add(new Square(7));
        shapes.add(new Rectangle(6, 3));
        shapes.add(new Circle(8));

        Shape maximumAreaShape = findFigureWithLargestArea(shapes);
        System.out.printf("Информация о фигуре с наибольшей площадью: класс - %s, площадь - %f, высота - %f, периметр - %f, ширина - %f%n", maximumAreaShape.getClass().toString(), maximumAreaShape.getArea(), maximumAreaShape.getHeight(), maximumAreaShape.getPerimeter(), maximumAreaShape.getWidth());
        Shape secondMaximumPerimeterShape = findFigureWithLargestPerimeter(shapes);
        System.out.printf("Информация о фигуре со вторым по величине периметром: класс - %s, площадь - %f, высота - %f, периметр - %f, ширина - %f", secondMaximumPerimeterShape.getClass().toString(), secondMaximumPerimeterShape.getArea(), secondMaximumPerimeterShape.getHeight(), secondMaximumPerimeterShape.getPerimeter(), secondMaximumPerimeterShape.getWidth());
    }
}
