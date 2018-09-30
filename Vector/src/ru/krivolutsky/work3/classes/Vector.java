package ru.krivolutsky.work3.classes;

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
                System.arraycopy(this.arrayOfVectorComponents, size1, newVector, size1, size2 - size1);
            }
            this.arrayOfVectorComponents = newVector;
        }
    }

    public static Vector sum(Vector vector2) {
        int size1 = .arrayOfVectorComponents.length;
        int size2 = vector2.arrayOfVectorComponents.length;
        if (size1 > size2) {
            Vector newVector = new Vector(size1);
            for (int i = 0; i < size2; i++) {
                newVector.arrayOfVectorComponents[i] = this.arrayOfVectorComponents[i] + vector2.arrayOfVectorComponents[i];
            }
            if (size1 - size2 >= 0) {
                System.arraycopy(this.arrayOfVectorComponents, size2, newVector.arrayOfVectorComponents, size2, size1 - size2);
            }
            return newVector;
        } else {
            Vector newVector = new Vector(size2);
            for (int i = 0; i < size1; i++) {
                newVector.arrayOfVectorComponents[i] = this.arrayOfVectorComponents[i] + vector2.arrayOfVectorComponents[i];
            }
            if (size2 - size1 >= 0) {
                System.arraycopy(this.arrayOfVectorComponents, size1, newVector.arrayOfVectorComponents, size1, size2 - size1);
            }
            return newVector;
        }
    }

    public void substract(Vector vector2) {
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
                newVector[i] = - vector2.arrayOfVectorComponents[i];
            }
            this.arrayOfVectorComponents = newVector;
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

    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < this.arrayOfVectorComponents.length; i++) {
            this.arrayOfVectorComponents[i] *= scalar;
        }
    }


}
