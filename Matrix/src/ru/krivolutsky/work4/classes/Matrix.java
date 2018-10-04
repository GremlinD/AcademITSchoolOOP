package ru.krivolutsky.work4.classes;

import ru.krivolutsky.work3.classes.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] components;

    public Matrix(int vectorsSize, int vectorsCount) {
        if (vectorsCount <= 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу с размером меньше единицы.");
        }
        this.components = new Vector[vectorsCount];
        for (int i = 0; i < vectorsCount; i++) {
            this.components[i] = new Vector(vectorsSize);
        }
    }

    public Matrix(Matrix matrix) {
        this.components = new Vector[matrix.getVectorsCount()];
        for (int i = 0; i < matrix.getVectorsCount(); i++) {
            this.components[i] = new Vector(matrix.components[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Нельзя создать матрицу с размером ноль.");
        }
        int maximumSize = 0;
        for (double[] a : array) {
            maximumSize = Math.max(maximumSize, a.length);
        }
        this.components = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            this.components[i] = new Vector(maximumSize, array[i]);
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
        this.components = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            this.components[i] = new Vector(maximumSize, vectors[i]);
        }
    }

    public int getVectorsCount() {
        return this.components.length;
    }

    public int getVectorsSize() {
        return this.components[0].getSize();
    }

    public Vector getVectorLineByIndex(int index) {
        if (index >= this.getVectorsCount()) {
            throw new IllegalArgumentException("Индекс превышает количество векторов в матрице.");
        }
        return this.components[index];
    }

    public void setVectorLineByIndex(int index, Vector vector) {
        if (index >= this.getVectorsCount()) {
            throw new IllegalArgumentException("Индекс превышает количество векторов в матрице.");
        }
        this.components[index] = new Vector(vector);
    }

    public Vector getVectorColumnByIndex(int index) {
        if (index >= this.getVectorsSize()) {
            throw new IllegalArgumentException("Индекс превышает количество векторов в матрице.");
        }
        Vector vector = new Vector(this.getVectorsCount());
        for (int i = 0; i < this.getVectorsCount(); i++) {
            vector.setComponentByIndex(i, this.components[i].getComponentByIndex(index));
        }
        return vector;
    }

    public void transpose() {
        for (int i = 0; i < this.getVectorsCount(); i++) {
            for (int j = i + 1; j < this.getVectorsSize(); j++) {
                double tmp = this.components[i].getComponentByIndex(j);
                this.components[i].setComponentByIndex(j, this.components[j].getComponentByIndex(i));
                this.components[j].setComponentByIndex(i, tmp);
            }
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < this.getVectorsCount(); i++) {
            for (int j = 0; j < this.getVectorsSize(); j++) {
                this.components[i].setComponentByIndex(j, this.components[i].getComponentByIndex(j) * scalar);
            }
        }
    }

    /**
     * Поиск определителя через приведение к реугольной матрице
     */
    public double calculateDeterminant() {
        if (this.getVectorsSize() != this.getVectorsCount()) {
            throw new IllegalArgumentException("Матрица не является квадратной, вычисление определителя невозможно.");
        }
        for (int i = 0; i < this.getVectorsSize() - 1; i++) {
            for (int j = i + 1; j < this.getVectorsCount(); j++) {
                double factor = -(this.components[j].getComponentByIndex(i) / this.components[i].getComponentByIndex(i));
                for (int k = 0; k < this.getVectorsSize(); k++) {
                    this.components[j].setComponentByIndex(k, this.components[i].getComponentByIndex(k) * factor + this.components[j].getComponentByIndex(k));
                }
            }
        }
        double determinant = 1;
        for (int i = 0; i < this.getVectorsCount(); i++) {
            if (this.components[i].getComponentByIndex(i) != 0) {
                determinant *= this.components[i].getComponentByIndex(i);
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
        for (int i = 0; i < this.getVectorsCount(); i++) {
            stringBuilder.append("{");
            for (int j = 0; j < this.getVectorsSize() - 1; j++) {
                stringBuilder.append(this.components[i].getComponentByIndex(j));
                stringBuilder.append(",");
            }
            stringBuilder.append(this.components[i].getComponentByIndex(this.getVectorsSize() - 1));
            stringBuilder.append("}");
            if (i < this.getVectorsCount() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void multiplyByVector(Vector vector) {
        if (vector.getSize() != this.getVectorsSize()) {
            throw new IllegalArgumentException("Длина вектора отличается от длин векторов матрицы, операция невозможна.");
        }
        for (int i = 0; i < this.getVectorsCount(); i++) {
            for (int j = 0; j < this.getVectorsSize(); j++) {
                this.components[i].setComponentByIndex(j, this.components[i].getComponentByIndex(j) * vector.getComponentByIndex(j));
            }
        }
    }

    public void add(Matrix matrix) {
        if (this.getVectorsCount() < matrix.getVectorsCount()) {
            this.components = Arrays.copyOf(this.components, matrix.getVectorsCount());
        }
        if (this.getVectorsSize() < matrix.getVectorsSize()) {
            for (int i = 0; i < this.getVectorsCount(); i++) {
                this.components[i] = new Vector(matrix.getVectorsSize(), this.components[i]);
            }
        }
        for (int i = 0; i < matrix.getVectorsCount(); i++) {
            for (int j = 0; j < matrix.getVectorsSize(); j++) {
                this.components[i].setComponentByIndex(j, this.components[i].getComponentByIndex(j) + matrix.components[i].getComponentByIndex(j));
            }
        }
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);
        return newMatrix;
    }

    public void subtract(Matrix matrix) {
        if (this.getVectorsCount() < matrix.getVectorsCount()) {
            this.components = Arrays.copyOf(this.components, matrix.getVectorsCount());
        }
        if (this.getVectorsSize() < matrix.getVectorsSize()) {
            for (int i = 0; i < this.getVectorsCount(); i++) {
                this.components[i] = new Vector(matrix.getVectorsSize(), this.components[i]);
            }
        }
        for (int i = 0; i < matrix.getVectorsCount(); i++) {
            for (int j = 0; j < matrix.getVectorsSize(); j++) {
                this.components[i].setComponentByIndex(j, this.components[i].getComponentByIndex(j) - matrix.components[i].getComponentByIndex(j));
            }
        }
    }

    public static Matrix difference(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);
        return newMatrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getVectorsCount() != matrix2.getVectorsSize()) {
            throw new IllegalArgumentException("Для умножения количество столбцов первой матрицы, должно быть равно количеству строк второй.");
        }
        int width = Math.max(matrix1.getVectorsCount(), matrix2.getVectorsCount());
        int height = Math.max(matrix1.getVectorsSize(), matrix2.getVectorsSize());
        Matrix newMatrix = new Matrix(height, width);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newMatrix.components[i].setComponentByIndex(j, Vector.getScalarProduct(matrix1.components[i], matrix2.getVectorColumnByIndex(j)));
            }
        }
        return newMatrix;
    }
}
