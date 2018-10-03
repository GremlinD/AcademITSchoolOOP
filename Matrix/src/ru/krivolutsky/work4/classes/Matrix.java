package ru.krivolutsky.work4.classes;

import ru.krivolutsky.work3.classes.Vector;

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

    /*TODO
    Переделать
     */
    public double calculateDeterminant() {
        for (int i = this.getVectorsCount() - 1; i > 0; i--) {
            int permutationsNumber = 0;
            for (int j = 0; j < i; j++) {
                if (this.components[j].getComponentByIndex(0) > this.components[j + 1].getComponentByIndex(0)) {
                    permutationsNumber++;
                    Vector tmp = new Vector(this.components[j]);
                    this.components[j] = new Vector(this.components[j + 1]);
                    this.components[j + 1] = new Vector(tmp);
                }
            }
            if (permutationsNumber == 0) {
                break;
            }
        }
        for (int i = 0; i < this.getVectorsSize(); i++) {
            double factor = -(this.components[i + 1].getComponentByIndex(i) / this.components[i].getComponentByIndex(i));
            for (int j = i; j < this.getVectorsCount(); j++) {
                this.components[j].setComponentByIndex(j, this.components[j].getComponentByIndex(j) + this.components[i].getComponentByIndex(j) * factor);
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
        for (int i = 0; i < this.getVectorsCount(); i++) {
            for (int j = 0; j < this.getVectorsSize(); j++) {
                this.components[i].setComponentByIndex(j, this.components[i].getComponentByIndex(j) * vector.getComponentByIndex(j));
            }
        }
    }

    public void add(Matrix matrix) {
        if (this.getVectorsCount() < matrix.getVectorsCount()) {
        }
    }
}
