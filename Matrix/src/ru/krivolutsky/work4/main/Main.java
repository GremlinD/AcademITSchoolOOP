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
        System.out.println(matrix1.getVectorsCount());
        System.out.print("Количество элементов в векторах:");
        System.out.println(matrix1.getVectorsSize());

        Vector vector = new Vector(matrix1.getVectorLineByIndex(1));
        System.out.print("Вектор из строки матрицы:");
        System.out.println(vector);

        vector = new Vector(matrix.getVectorColumnByIndex(2));
        System.out.print("Вектор из столбца матрицы:");
        System.out.println(vector);

        matrix1.setVectorLineByIndex(1, vector);
        System.out.print("Измененная матрица, с помощью вставки вектора: ");
        System.out.println(matrix1);

        matrix1.transpose();
        System.out.print("Транспонированная матрица: ");
        System.out.println(matrix1);

        matrix1.multiplyByScalar(3);
        System.out.print("Матрица умноженная на скаляр: ");
        System.out.println(matrix1);

        matrix1.multiplyByVector(vector);
        System.out.print("Матрица умноженная на вектор: ");
        System.out.println(matrix1);

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

        matrix = new Matrix(new double[][]{{3, -3, -5, 8}, {-3, 2, 4, -6}, {2, -5, -7, 5}, {-4, 3, 5, -6}});
        System.out.print("Определитель матрицы: ");
        System.out.println(matrix);
        System.out.print("Равен: ");
        System.out.println(matrix.calculateDeterminant());

        matrix = new Matrix(new double[][]{{1,2,3},{5,6,7},{8,9,10},{11,12,13}});
        matrix1 = new Matrix(new double[][]{{9,8,7,6},{5,4,3,2},{1,11,12,13}});
        System.out.print("Результатом умножения матриц: ");
        System.out.println(matrix);
        System.out.println(matrix1);
        System.out.print("будет: ");
        System.out.println(Matrix.multiply(matrix, matrix1));


    }
}
