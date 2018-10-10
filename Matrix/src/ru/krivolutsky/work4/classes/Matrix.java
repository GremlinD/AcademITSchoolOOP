package ru.krivolutsky.work4.classes;

import ru.krivolutsky.work3.classes.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int columnsCount, int rowsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу с размером меньше единицы.");
        }
        this.rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            this.rows[i] = new Vector(columnsCount);
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

    public int getColumnsCount() {
        return this.rows[0].getSize();
    }

    public Vector getRowByIndex(int index) {
        if (index > this.getRowsCount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс не соответствует количеству векторов в матрице.");
        }
        return new Vector(this.rows[index]);
    }

    public void setRowByIndex(int index, Vector vector) {
        if (index > this.getRowsCount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс не соответствует количеству векторов в матрице.");
        }
        if (vector.getSize() != this.rows[index].getSize()) {
            throw new IllegalArgumentException("Размер вектора не соответствует размеру векторов матрицы.");
        }
        this.rows[index] = new Vector(vector);
    }

    public Vector getColumnByIndex(int index) {
        if (index > this.getColumnsCount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс не соответствует количеству векторов в матрице.");
        }
        Vector vector = new Vector(this.getRowsCount());
        for (int i = 0; i < this.getRowsCount(); i++) {
            vector.setComponentByIndex(i, this.rows[i].getComponentByIndex(index));
        }
        return vector;
    }

    public void transpose() {
        Vector[] vectors = new Vector[this.getColumnsCount()];
        for (int i = 0; i < this.getColumnsCount(); i++) {
            vectors[i] = this.getColumnByIndex(i);
        }
        this.rows = vectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector v : this.rows) {
            v.multiplyByScalar(scalar);
        }
    }

    /**
     * Поиск определителя через приведение к треугольной матрице
     */
    public double calculateDeterminant() {
        if (this.getColumnsCount() != this.getRowsCount()) {
            throw new IllegalArgumentException("Матрица не является квадратной, вычисление определителя невозможно.");
        }
        Matrix matrix = new Matrix(this);
        for (int i = matrix.getRowsCount() - 1; i > 0; i--) {
            int permutationsNumber = 0;
            for (int j = 0; j < i; j++) {
                if (matrix.rows[j].getComponentByIndex(0) < matrix.rows[j + 1].getComponentByIndex(0)) {
                    permutationsNumber++;
                    Vector tmp = matrix.rows[j];
                    matrix.rows[j] = matrix.rows[j + 1];
                    matrix.rows[j + 1] = tmp;
                }
            }
            if (permutationsNumber == 0) {
                break;
            }
        }
        double epsilon = 1.0e-10;
        for (int i = 0; i < matrix.getColumnsCount() - 1; i++) {
            if (i != 0) {
                if (Math.abs(matrix.rows[i].getComponentByIndex(i)) <= epsilon) {
                    for (int j = i + 1; j < matrix.getRowsCount(); j++) {
                        if (Math.abs(matrix.rows[j].getComponentByIndex(i)) > epsilon) {
                            for (int k = 0; k < matrix.getColumnsCount(); k++) {
                                matrix.rows[i].setComponentByIndex(k, matrix.rows[j].getComponentByIndex(k) + matrix.rows[i].getComponentByIndex(k));
                            }
                            break;
                        }
                    }
                }
            }
            for (int j = this.getRowsCount() - 1; j > i; j--) {
                if (Math.abs(matrix.rows[j - 1].getComponentByIndex(i)) > epsilon) {
                    double factor = -(matrix.rows[j].getComponentByIndex(i) / matrix.rows[j - 1].getComponentByIndex(i));
                    for (int k = 0; k < matrix.getColumnsCount(); k++) {
                        matrix.rows[j].setComponentByIndex(k, matrix.rows[j - 1].getComponentByIndex(k) * factor + matrix.rows[j].getComponentByIndex(k));
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

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора отличается от длин векторов матрицы, операция невозможна.");
        }
        Vector newVector = new Vector(this.getRowsCount());
        for (int i = 0; i < this.getRowsCount(); i++) {
            newVector.setComponentByIndex(i, Vector.getScalarProduct(this.rows[i], vector));
        }
        return newVector;
    }

    public void add(Matrix matrix) {
        if (this.getRowsCount() != matrix.getRowsCount() || this.getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера.");
        }
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.rows[i].add(matrix.rows[i]);
        }
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера.");
        }
        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);
        return newMatrix;
    }

    public void subtract(Matrix matrix) {
        if (this.getRowsCount() != matrix.getRowsCount() || this.getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера.");
        }
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix difference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера.");
        }
        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);
        return newMatrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Для умножения количество столбцов первой матрицы, должно быть равно количеству строк второй.");
        }
        int rowsCount = matrix1.getRowsCount();
        int columnsCount = matrix2.getColumnsCount();
        Matrix newMatrix = new Matrix(columnsCount, rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                newMatrix.rows[i].setComponentByIndex(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumnByIndex(j)));
            }
        }
        return newMatrix;
    }
}
