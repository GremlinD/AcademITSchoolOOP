package ru.krivolutsky.work4.main;

import ru.krivolutsky.work3.classes.Vector;
import ru.krivolutsky.work4.classes.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(5, 7);
        System.out.print("Матрица нулей: ");
        System.out.println(matrix);

        double[][] array = new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        matrix = new Matrix(array);
        System.out.print("Матрица из массива:");
        System.out.println(matrix);

        Vector[] vectors = new Vector[]{new Vector(array[0]), new Vector(array[1]), new Vector(array[2])};
        matrix = new Matrix(vectors);
        System.out.print("Матрица из массива векторов:");
        System.out.println(matrix);

        Matrix matrix1 = new Matrix(matrix);
        System.out.print("Копия матрицы:");
        System.out.println(matrix1);

        System.out.print("Количество векторов в матрице:");
        System.out.println(matrix1.getRowsCount());
        System.out.print("Количество элементов в векторах:");
        System.out.println(matrix1.getColumnsCount());

        Vector vector = new Vector(matrix1.getRowByIndex(1));
        System.out.print("Вектор из строки матрицы:");
        System.out.println(vector);

        vector = new Vector(matrix.getColumnByIndex(2));
        System.out.print("Вектор из столбца матрицы:");
        System.out.println(vector);

        matrix1.setRowByIndex(1, vector);
        System.out.print("Измененная матрица, с помощью вставки вектора: ");
        System.out.println(matrix1);

        matrix1 = new Matrix(new double[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}});
        matrix1.transpose();
        System.out.print("Транспонированная матрица: ");
        System.out.println(matrix1);

        matrix1.multiplyByScalar(3);
        System.out.print("Матрица умноженная на скаляр: ");
        System.out.println(matrix1);

        matrix1 = new Matrix(new double[][]{{2, 4, 0}, {-2, 1, 3}, {-1, 0, 1}});
        vector = new Vector(new double[]{1, 2, -1});
        Vector vector1 = matrix1.multiplyByVector(vector);
        System.out.print("Матрица умноженная на вектор: ");
        System.out.println(vector1);

        System.out.print("Матирицы для сложения: ");
        System.out.println(matrix);
        System.out.println(matrix1);
        Matrix matrix2 = Matrix.sum(matrix, matrix1);
        System.out.print("Матрица суммы: ");
        System.out.println(matrix2);

        matrix2 = Matrix.difference(matrix, matrix1);
        System.out.print("Матрица разности: ");
        System.out.println(matrix2);

        Matrix saveMatrix = new Matrix(matrix);
        matrix.add(matrix1);
        System.out.print("Первая матрица после сложения: ");
        System.out.println(matrix);

        saveMatrix.subtract(matrix1);
        System.out.print("Первая матрица после вычитания: ");
        System.out.println(saveMatrix);

        matrix = new Matrix(new double[][]{{0, -3, -5, 0}, {-3, 0, 4, -6}, {0, -5, 0, 5}, {-4, 3, 5, -6}});
        System.out.print("Определитель матрицы: ");
        System.out.println(matrix);
        System.out.print("Равен: ");
        System.out.println(matrix.calculateDeterminant());

        matrix = new Matrix(new double[][]{{1, 2, 3}, {5, 6, 7}, {8, -4, 10}, {11, 4, 13}, {4, -4, 0}});
        matrix1 = new Matrix(new double[][]{{9, 8, 7, 6, 4}, {5, 4, 3, 5, -5}, {-11, 11, 12, 13, 0}});
        System.out.print("Результатом умножения матриц: ");
        System.out.println(matrix);
        System.out.println(matrix1);
        System.out.print("будет: ");
        System.out.println(Matrix.multiply(matrix, matrix1));
    }
}
