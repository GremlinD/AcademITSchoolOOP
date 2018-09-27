package ru.krivolutsky.work1.main;

import ru.krivolutsky.work1.classes.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Range range = new Range(-4, 8);

        System.out.printf("Значение from сейчас: %f%n", range.getFrom());
        System.out.println("Введите число, которое нужно записать в from:");
        Scanner scanner = new Scanner(System.in);

        range.setFrom(scanner.nextDouble());

        System.out.printf("Значение to сейчас: %f%n", range.getTo());
        System.out.println("Введите число, которое нужно записать в to:");
        range.setTo(scanner.nextDouble());

        System.out.printf("Длина между новыми числами равна: %f%n", range.getLength());

        System.out.println("Введите число, которое хотите проверить на вхождение в диапазон:");
        System.out.println(range.isInside(scanner.nextDouble()));

        System.out.println("Введите позиции начала и конца двух диапазонов для проверки:");
        Range range1 = new Range(scanner.nextDouble(), scanner.nextDouble());
        Range range2 = new Range(scanner.nextDouble(), scanner.nextDouble());

        Range rangeIntersection = range1.getIntersection(range2);

        if (rangeIntersection != null) {
            System.out.printf("Пересечение: %f, %f", rangeIntersection.getFrom(), rangeIntersection.getTo());
        } else {
            System.out.println("Нет пересечений");
        }
        System.out.println();

        Range[] rangesCombining = range1.getCombiningIntervals(range2);
        for (Range r : rangesCombining) {
            System.out.printf("Объединение: %f, %f%n", r.getFrom(), r.getTo());
        }
        System.out.println();

        Range[] rangesDifference = range1.getDifferenceIntervals(range2);
        if (rangesDifference.length != 0) {
            for (Range r : rangesDifference) {
                System.out.printf("Разность: %f, %f%n", r.getFrom(), r.getTo());
            }
        } else {
            System.out.println("Разности нет");
        }
    }
}
