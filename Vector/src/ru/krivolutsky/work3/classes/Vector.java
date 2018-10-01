package ru.krivolutsky.work3.classes;

import java.util.Arrays;

public class Vector {
    private double[] arrayOfVectorComponents;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.arrayOfVectorComponents = new double[size];
        for (int i = 0; i < size; i++) {
            this.arrayOfVectorComponents[i] = 0;
        }
    }

    public Vector(Vector vector) {
        this.arrayOfVectorComponents = vector.arrayOfVectorComponents;
    }

    public Vector(double[] array) {
        this.arrayOfVectorComponents = array;
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.arrayOfVectorComponents = new double[size];
        for (int i = 0; i < size; i++) {
            if (i < array.length) {
                this.arrayOfVectorComponents[i] = array[i];
            } else {
                this.arrayOfVectorComponents[i] = 0;
            }
        }
    }

    public int getSize() {
        return this.arrayOfVectorComponents.length;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("{ ");
        for (int i = 0; i < this.arrayOfVectorComponents.length; i++) {
            line.append(this.arrayOfVectorComponents[i]);
            if (i != this.arrayOfVectorComponents.length - 1) {
                line.append(",");
            }
        }
        line.append(" }");
        return String.valueOf(line);
    }

    public void add(Vector vector2) {
        int size1 = this.arrayOfVectorComponents.length;
        int size2 = vector2.arrayOfVectorComponents.length;
        if (size1 > size2) {
            double[] newVector = new double[size1];
            for (int i = 0; i < size2; i++) {
                newVector[i] = this.arrayOfVectorComponents[i] + vector2.arrayOfVectorComponents[i];
            }
            if (size1 - size2 >= 0) {
                System.arraycopy(this.arrayOfVectorComponents, size2, newVector, size2, size1 - size2);
            }
            this.arrayOfVectorComponents = newVector;
        } else {
            double[] newVector = new double[size2];
            for (int i = 0; i < size1; i++) {
                newVector[i] = this.arrayOfVectorComponents[i] + vector2.arrayOfVectorComponents[i];
            }
            if (size2 - size1 >= 0) {
                System.arraycopy(vector2.arrayOfVectorComponents, size1, newVector, size1, size2 - size1);
            }
            this.arrayOfVectorComponents = newVector;
        }
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        int size1 = vector1.arrayOfVectorComponents.length;
        int size2 = vector2.arrayOfVectorComponents.length;
        if (size1 > size2) {
            double[] newArrayVector = new double[size1];
            for (int i = 0; i < size2; i++) {
                newArrayVector[i] = vector1.arrayOfVectorComponents[i] + vector2.arrayOfVectorComponents[i];
            }
            if (size1 - size2 >= 0) {
                System.arraycopy(vector1.arrayOfVectorComponents, size2, newArrayVector, size2, size1 - size2);
            }
            return new Vector(newArrayVector);
        } else {
            double[] newArrayVector = new double[size2];
            for (int i = 0; i < size1; i++) {
                newArrayVector[i] = vector1.arrayOfVectorComponents[i] + vector2.arrayOfVectorComponents[i];
            }
            if (size2 - size1 >= 0) {
                System.arraycopy(vector2.arrayOfVectorComponents, size1, newArrayVector, size1, size2 - size1);
            }
            return new Vector(newArrayVector);
        }
    }

    public void subtract(Vector vector2) {
        int size1 = this.arrayOfVectorComponents.length;
        int size2 = vector2.arrayOfVectorComponents.length;
        if (size1 > size2) {
            double[] newVector = new double[size1];
            for (int i = 0; i < size2; i++) {
                newVector[i] = this.arrayOfVectorComponents[i] - vector2.arrayOfVectorComponents[i];
            }
            for (int i = size2; i < size1; i++) {
                newVector[i] = -this.arrayOfVectorComponents[i];
            }
            this.arrayOfVectorComponents = newVector;
        } else {
            double[] newVector = new double[size2];
            for (int i = 0; i < size1; i++) {
                newVector[i] = this.arrayOfVectorComponents[i] - vector2.arrayOfVectorComponents[i];
            }
            for (int i = size1; i < size2; i++) {
                newVector[i] = -vector2.arrayOfVectorComponents[i];
            }
            this.arrayOfVectorComponents = newVector;
        }
    }

    public static Vector difference(Vector vector1, Vector vector2) {
        int size1 = vector1.arrayOfVectorComponents.length;
        int size2 = vector2.arrayOfVectorComponents.length;
        if (size1 > size2) {
            double[] newArrayVector = new double[size1];
            for (int i = 0; i < size2; i++) {
                newArrayVector[i] = vector1.arrayOfVectorComponents[i] - vector2.arrayOfVectorComponents[i];
            }
            for (int i = size2; i < size1; i++) {
                newArrayVector[i] = -vector1.arrayOfVectorComponents[i];
            }
            return new Vector(newArrayVector);
        } else {
            double[] newArrayVector = new double[size2];
            for (int i = 0; i < size1; i++) {
                newArrayVector[i] = vector1.arrayOfVectorComponents[i] - vector2.arrayOfVectorComponents[i];
            }
            for (int i = size1; i < size2; i++) {
                newArrayVector[i] = -vector2.arrayOfVectorComponents[i];
            }
            return new Vector(newArrayVector);
        }
    }

    public double getLength() {
        int length = 0;
        for (double arrayOfVectorComponent : this.arrayOfVectorComponents) {
            length += Math.pow(arrayOfVectorComponent, 2);
        }
        return Math.sqrt(length);
    }

    public void expand() {
        for (int i = 0; i < this.arrayOfVectorComponents.length; i++) {
            this.arrayOfVectorComponents[i] *= -1;
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < this.arrayOfVectorComponents.length; i++) {
            this.arrayOfVectorComponents[i] *= scalar;
        }
    }

    public void setComponentByIndex(int index, double component) {
        this.arrayOfVectorComponents[index] = component;
    }

    public double getComponentByIndex(int index) {
        return this.arrayOfVectorComponents[index];
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

        if (arrayOfVectorComponents.length == vector.arrayOfVectorComponents.length) {
            for (int i = 0; i < arrayOfVectorComponents.length; i++) {
                if (arrayOfVectorComponents[i] != vector.arrayOfVectorComponents[i]) {
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
        hash = prime * hash + Double.hashCode(arrayOfVectorComponents.length);
        hash = prime * hash + Arrays.hashCode(arrayOfVectorComponents);
        return hash;
    }

    public static Vector getScalarProduct(Vector vector1, Vector vector2) {
        int size1 = vector1.arrayOfVectorComponents.length;
        int size2 = vector2.arrayOfVectorComponents.length;
        if (size1 > size2) {
            double[] newArrayVector = new double[size1];
            for (int i = 0; i < size2; i++) {
                newArrayVector[i] = vector1.arrayOfVectorComponents[i] * vector2.arrayOfVectorComponents[i];
            }
            for (int i = size2; i < size1; i++) {
                newArrayVector[i] = 0;
            }
            return new Vector(newArrayVector);
        } else {
            double[] newArrayVector = new double[size2];
            for (int i = 0; i < size1; i++) {
                newArrayVector[i] = vector1.arrayOfVectorComponents[i] * vector2.arrayOfVectorComponents[i];
            }
            for (int i = size1; i < size2; i++) {
                newArrayVector[i] = 0;
            }
            return new Vector(newArrayVector);
        }
    }
}