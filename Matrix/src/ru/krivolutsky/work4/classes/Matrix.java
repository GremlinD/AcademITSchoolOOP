package ru.krivolutsky.work4.classes;

import ru.krivolutsky.work3.classes.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsSize, int rowsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу с размером меньше единицы.");
        }
        this.rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            this.rows[i] = new Vector(rowsSize);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.getRowsCount()];
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу с размером ноль.");
        }
        int maximumSize = 0;
        for (double[] a : array) {
            maximumSize = Math.max(maximumSize, a.length);
        }
        this.rows = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            this.rows[i] = new Vector(maximumSize, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу с размером ноль.");
        }
        int maximumSize = 0;
        for (Vector vector : vectors) {
            maximumSize = Math.max(maximumSize, vector.getSize());
        }
        this.rows = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            this.rows[i] = new Vector(maximumSize, vectors[i]);
        }
    }

    public int getRowsCount() {
        return this.rows.length;
    }

    public int getRowsSize() {
        return this.rows[0].getSize();
    }

    public Vector getRowByIndex(int index) {
        if (index >= this.getRowsCount()) {
            throw new IndexOutOfBoundsException("Индекс превышает количество векторов в матрице.");
        }
        return new Vector(this.rows[index]);
    }

    public void setRowByIndex(int index, Vector vector) {
        if (index >= this.getRowsCount()) {
            throw new IndexOutOfBoundsException("Индекс превышает количество векторов в матрице.");
        }
        if (vector.getSize() > this.rows[index].getSize()) {
            throw new IndexOutOfBoundsException("Размер вектора больше размеров векторов матрицы.");
        }
        this.rows[index] = new Vector(vector);
    }

    public Vector getColumnByIndex(int index) {
        if (index >= this.getRowsSize()) {
            throw new IndexOutOfBoundsException("Индекс превышает количество векторов в матрице.");
        }
        Vector vector = new Vector(this.getRowsCount());
        for (int i = 0; i < this.getRowsCount(); i++) {
            vector.setComponentByIndex(i, this.rows[i].getComponentByIndex(index));
        }
        return vector;
    }

    public void transpose() {
        Matrix matrix = new Matrix(this.getRowsCount(), this.getRowsSize());
        for (int i = 0; i < this.getRowsSize(); i++) {
            matrix.setRowByIndex(i, new Vector(this.getColumnByIndex(i)));
        }
        this.rows = matrix.rows;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < this.getRowsCount(); i++) {
            this.rows[i].multiplyByScalar(scalar);
        }
    }

    /**
     * Поиск определителя через приведение к треугольной матрице
     */
    public double calculateDeterminant() {
        if (this.getRowsSize() != this.getRowsCount()) {
            throw new IllegalArgumentException("Матрица не является квадратной, вычисление определителя невозможно.");
        }
        Matrix matrix = new Matrix(this);
        //Сортировка по первому элементу вектора
        for (int i = matrix.rows.length - 1; i > 0; i--) {
            int permutationsNumber = 0;
            for (int j = 0; j < i; j++) {
                if (Math.abs(matrix.rows[j].getComponentByIndex(0)) < Math.abs(matrix.rows[j + 1].getComponentByIndex(0))) {
                    permutationsNumber++;
                    Vector tmp = matrix.rows[j];
                    matrix.rows[j] = new Vector(matrix.rows[j + 1]);
                    matrix.rows[j + 1] = new Vector(tmp);
                }
            }
            if (permutationsNumber == 0) {
                break;
            }
        }
        double epsilon = 1.0e-10;
        for (int i = 0; i < matrix.getRowsSize() - 1; i++) {
            for (int j = i + 1; j < matrix.getRowsCount(); j++) {
                if (Math.abs(matrix.rows[i].getComponentByIndex(i)) > epsilon) {
                    double factor = -(matrix.rows[j].getComponentByIndex(i) / matrix.rows[i].getComponentByIndex(i));
                    for (int k = 0; k < matrix.getRowsSize(); k++) {
                        matrix.rows[j].setComponentByIndex(k, matrix.rows[i].getComponentByIndex(k) * factor + matrix.rows[j].getComponentByIndex(k));
                    }
                }
            }
        }
        double determinant = 1;
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            if (Math.abs(matrix.rows[i].getComponentByIndex(i)) > epsilon) {
                determinant *= matrix.rows[i].getComponentByIndex(i);
            } else {
                return 0;
            }
        }
        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < this.getRowsCount(); i++) {
            stringBuilder.append(this.rows[i].toString());
            if (i != this.getRowsCount() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public Matrix multiplyByVector(Vector vector) {
        if (vector.getSize() != this.getRowsSize()) {
            throw new IndexOutOfBoundsException("Длина вектора отличается от длин векторов матрицы, операция невозможна.");
        }
        Matrix matrix = new Matrix(this.getRowsSize(), this.getRowsSize());
        for (int i = 0; i < this.getRowsCount(); i++) {
            for (int j = 0; j < this.getRowsSize(); j++) {
                matrix.rows[i].setComponentByIndex(j, this.rows[i].getComponentByIndex(j) * vector.getComponentByIndex(j));
            }
        }
        return matrix;
    }

    public void add(Matrix matrix) {
        if (this.getRowsCount() != matrix.getRowsCount() || this.getRowsSize() != matrix.getRowsSize()) {
            throw new IndexOutOfBoundsException("Матрицы должны быть одинакового размера.");
        }
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.rows[i].add(matrix.rows[i]);
        }
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);
        return newMatrix;
    }

    public void subtract(Matrix matrix) {
        if (this.getRowsCount() != matrix.getRowsCount() || this.getRowsSize() != matrix.getRowsSize()) {
            throw new IndexOutOfBoundsException("Матрицы должны быть одинакового размера.");
        }
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix difference(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);
        return newMatrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsSize()) {
            throw new IndexOutOfBoundsException("Для умножения количество столбцов первой матрицы, должно быть равно количеству строк второй.");
        }
        int rowsCount = Math.max(matrix1.getRowsCount(), matrix2.getRowsCount());
        int rowsSize = Math.max(matrix1.getRowsSize(), matrix2.getRowsSize());
        Matrix newMatrix = new Matrix(rowsSize, rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < rowsSize; j++) {
                newMatrix.rows[i].setComponentByIndex(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumnByIndex(j)));
            }
        }
        return newMatrix;
    }
}
