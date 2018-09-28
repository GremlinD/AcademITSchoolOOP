package ru.krivolutsky.work2.classes;

public class Square implements Shape {
    private double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public double getWidth() {
        return length;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return Math.pow(length, 2);
    }

    @Override
    public double getPerimeter() {
        return 4 * length;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Square square = (Square) o;

        return length == square.length;
    }

    @Override
    public int hashCode() {
        final int prime = 19;
        int hash = 1;
        hash = prime * hash + Double.hashCode(length);
        return hash;
    }

    @Override
    public String toString() {
        return "length = " + length;
    }
}
