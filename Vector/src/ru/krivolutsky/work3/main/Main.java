package ru.krivolutsky.work3.main;

import ru.krivolutsky.work3.classes.Vector;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите размер массива заполненого нулями:");
        Scanner scanner = new Scanner(System.in);
        Vector vectorNullElements = new Vector(scanner.nextInt());
        System.out.print("Нулевой вектор: ");
        System.out.println(vectorNullElements);

        Vector vectorCopy = new Vector(vectorNullElements);
        System.out.print("Копия вектора: ");
        System.out.println(vectorCopy);

        double[] array = new double[]{4, 6, 2, 8, 4};
        Vector vectorArray = new Vector(array);
        System.out.print("Вектор созданный из массива: ");
        System.out.println(vectorArray);

        System.out.println("Введите длину вектора создаваемого из массива:");
        Vector vectorSizeArray = new Vector(scanner.nextInt(), array);
        System.out.print("Вектор созданный из массива, но длинее его: ");
        System.out.println(vectorSizeArray);

        System.out.print("Размерность последнего вектора: ");
        System.out.println(vectorSizeArray.getSize());

        Vector vector1 = new Vector(new double[]{4, 7, -3, 7, 3});
        Vector vector2 = new Vector(new double[]{-5, 8, 3, 9, 1, 7, -4, 9, 5});

        System.out.print("Сумма векторов, записанная в первый вектор: ");
        vector1.add(vector2);
        System.out.println(vector1);

        System.out.print("Сумма векторов, записанная в новый вектор: ");
        vector1 = new Vector(new double[]{4, 7, -3, 7, 3});
        Vector vector3 = Vector.sum(vector1, vector2);
        System.out.println(vector3);

        System.out.print("Разность векторов, записанная в первый вектор: ");
        vector1.subtract(vector2);
        System.out.println(vector1);

        System.out.print("Разность векторов, записанная в новый вектор: ");
        vector1 = new Vector(new double[]{4, 7, -3, 7, 3});
        vector3 = Vector.difference(vector1, vector2);
        System.out.println(vector3);

        System.out.print("Длина последнего вектора: ");
        System.out.println(vector3.getLength());

        System.out.print("Развернутый последний вектор: ");
        vector3.spin();
        System.out.println(vector3);

        System.out.println("Введите скаляр для умножения");
        vector3.multiplyByScalar(scanner.nextDouble());
        System.out.print("Последний массив умноженный на скаляр: ");
        System.out.println(vector3);

        System.out.println("Введите индекс по которому нужно вставить элемент и сам элемент:");
        vector3.setComponentByIndex(scanner.nextInt(), scanner.nextDouble());
        System.out.print("Послений вектор с изменненым элементом: ");
        System.out.println(vector3);

        System.out.println("Введите индекс элемента, который нужно показать:");
        double element = vector3.getComponentByIndex(scanner.nextInt());
        System.out.printf("Элемент по выбранному индексу: %f%n", element);

        vector3 = Vector.getScalarProduct(vector1, vector2);
        System.out.print("Скалярное произведение векторов");
        System.out.println(vector3);
    }
}
