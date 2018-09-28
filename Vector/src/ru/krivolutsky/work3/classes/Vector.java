package ru.krivolutsky.work3.classes;

public class Vector {
    private double[] arrayOfVectorComponents;

    public Vector (int size) {
        try {
            this.arrayOfVectorComponents = new double[size];
            for (int i = 0; i < size; i++) {
                this.arrayOfVectorComponents[i] = 0;
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Неверный размер вектора.");
        }
    }

    public Vector (Vector vector) {
         this.arrayOfVectorComponents = vector.arrayOfVectorComponents;
    }

    public Vector (double[] array) {
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

    public int getSize () {
        return this.arrayOfVectorComponents.length;
    }

    @Override
    public String toString () {
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
}
