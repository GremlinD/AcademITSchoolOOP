package ru.krivolutsky.work3.classes;

import java.util.Arrays;

public class Vector {
    private double[] arrayOfVectorComponents;

    public Vector(int size) {
        try {
            this.arrayOfVectorComponents = new double[size];
            for (int i = 0; i < size; i++) {
                this.arrayOfVectorComponents[i] = 0;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный размер вектора.");
        }
    }

    public Vector(Vector vector) {
        this.arrayOfVectorComponents = vector.arrayOfVectorComponents;
    }

    public Vector(double[] array) {
        this.arrayOfVectorComponents = array;
    }

    public Vector(int size, double[] array) {
        try {
            this.arrayOfVectorComponents = new double[size];
            for (int i = 0; i < size; i++) {
                if (size < array.length) {
                    this.arrayOfVectorComponents[i] = array[i];
                } else {
                    this.arrayOfVectorComponents[i] = 0;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный размер вектора.");
        }
    }

    public int getSize() {
        return this.arrayOfVectorComponents.length;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("{ ");
        for (int i = 0; i < this.arrayOfVectorComponents.length; i++) {
            line.append(i);
            if (i == this.arrayOfVectorComponents.length - 1) {
                line.append(",");
            }
            line.append(" }");
        }
        return String.valueOf(line);
    }

    public void add(Vector vector2) {
        int sizeVector1 = this.arrayOfVectorComponents.length;
        int sizeVector2 = vector2.arrayOfVectorComponents.length;
        if (sizeVector1 < sizeVector2) {
            for (int i = 0; i < sizeVector1; i++) {
                vector2.arrayOfVectorComponents[i] += this.arrayOfVectorComponents[i];
            }
            Arrays.sort(vector2.arrayOfVectorComponents);
        } else {
            for (int i = 0; i < sizeVector2; i++) {
                this.arrayOfVectorComponents[i] += vector2.arrayOfVectorComponents[i];
            }
            Arrays.sort(this.arrayOfVectorComponents);
        }
    }

    public double getLength() {
        int length = 0;
        for (int i = 0; i < this.arrayOfVectorComponents.length; i++) {
            length += Math.pow(this.arrayOfVectorComponents[i], 2);
        }
        return Math.sqrt(length);
    }

    public void expand() {
        for (int i = 0; i < this.arrayOfVectorComponents.length; i++) {
            this.arrayOfVectorComponents[i] *= -1;
        }
    }

    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < this.arrayOfVectorComponents.length; i++) {
            this.arrayOfVectorComponents[i] *= scalar;
        }
    }


}
