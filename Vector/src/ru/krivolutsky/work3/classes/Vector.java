package ru.krivolutsky.work3.classes;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Вектор не может иметь размерность меньше единицы.");
        }
        this.components = new double[size];
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException("Вектор не может иметь размерность меньше единицы.");
        }
        this.components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Вектор не может иметь размерность меньше единицы.");
        }
        this.components = Arrays.copyOf(array, size);
    }

    public Vector(int size, Vector vector) {
        if (size <= 0) {
            throw new IllegalArgumentException("Вектор не может иметь размерность меньше единицы.");
        }
        this.components = Arrays.copyOf(vector.components, size);
    }

    public int getSize() {
        return this.components.length;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("{ ");
        for (int i = 0; i < this.components.length; i++) {
            line.append(this.components[i]);
            if (i != this.components.length - 1) {
                line.append(",");
            }
        }
        line.append(" }");
        return line.toString();
    }

    public void add(Vector vector2) {
        if (this.components.length < vector2.components.length) {
            this.components = Arrays.copyOf(this.components, vector2.components.length);
        }
        for (int i = 0; i < vector2.components.length; i++) {
            this.components[i] += vector2.components[i];
        }
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(vector1);
        newVector.add(vector2);
        return newVector;
    }

    public void subtract(Vector vector2) {
        if (this.components.length < vector2.components.length) {
            this.components = Arrays.copyOf(this.components, vector2.components.length);
        }
        for (int i = 0; i < vector2.components.length; i++) {
            this.components[i] -= vector2.components[i];
        }
    }

    public static Vector difference(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(vector1);
        newVector.subtract(vector2);
        return newVector;
    }

    public double getLength() {
        int length = 0;
        for (double arrayOfVectorComponent : this.components) {
            length += Math.pow(arrayOfVectorComponent, 2);
        }
        return Math.sqrt(length);
    }

    public void spin() {
        multiplyByScalar(-1);
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < this.components.length; i++) {
            this.components[i] *= scalar;
        }
    }

    public void setComponentByIndex(int index, double component) {
        this.components[index] = component;
    }

    public double getComponentByIndex(int index) {
        return this.components[index];
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (components.length == vector.components.length) {
            for (int i = 0; i < components.length; i++) {
                if (components[i] != vector.components[i]) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 19;
        int hash = 1;
        hash = prime * hash + Double.hashCode(components.length);
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        for (int i = 0; i < Math.min(vector1.getSize(), vector1.getSize()); i++) {
            scalarProduct += vector1.getComponentByIndex(i) * vector2.getComponentByIndex(i);
        }
        return scalarProduct;
    }
}