package ru.krivolutsky.work4.classes;

import ru.krivolutsky.work3.classes.Vector;

public class Matrix {
    private Vector[] components;

    public Matrix(int vectorsSize, int vectorsCount) {
        this.components = new Vector[vectorsCount];
        for (int i = 0; i < vectorsCount; i++) {
            this.components[i] = new Vector(vectorsSize);
        }
    }

    public Matrix(Matrix matrix) {
        this.components = new Vector[matrix.vectorsCount()];
        for (int i = 0; i < matrix.vectorsCount(); i++) {
            this.components[i] = new Vector(matrix.components[i]);
        }
    }

    public Matrix(double[][] array) {
        this.components = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            this.components[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        this.components = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            this.components[i] = new Vector(vectors[i]);
        }
    }

    public int vectorsCount() {
        return this.components.length;
    }

    public int vectorsSize() {
        return this.components[0].getSize();
    }

    public Vector getVectorLineByIndex(int index) {
        return this.components[index];
    }

    public void setVectorLineByIndex(int index, Vector vector) {
        this.components[index] = new Vector(vector);
    }

    public Vector getVectorColumnByIndex(int index) {
        Vector vector = new Vector(this.vectorsCount());
        for (int i = 0; i < this.vectorsCount(); i++) {
            vector.setComponentByIndex(i, this.components[i].getComponentByIndex(index));
        }
        return vector;
    }


}
