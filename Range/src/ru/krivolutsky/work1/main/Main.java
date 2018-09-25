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
    }
}
