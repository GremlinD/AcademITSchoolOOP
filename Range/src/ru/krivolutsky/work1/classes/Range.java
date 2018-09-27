package ru.krivolutsky.work1.classes;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number <= to && number >= from;
    }

    /**
     * Получение интервала пересечения
     */
    public Range getIntersection(Range secondRange) {
        double to = Math.min(this.to, secondRange.to);
        double from = Math.max(this.from, secondRange.from);
        if (from <= to) {
            return new Range(from, to);
        } else {
            return null;
        }
    }

    /**
     * Объединение двух интервалов
     */
    public Range[] getCombiningIntervals(Range secondRange) {
        if (this.to < secondRange.from || secondRange.to < this.from) {
            double from = this.from;
            double to = this.to;
            double secondTo = secondRange.to;
            double secondFrom = secondRange.from;
            return new Range[]{new Range(from, to), new Range(secondFrom, secondTo)};
        } else {
            double from = Math.min(this.from, secondRange.from);
            double to = Math.max(secondRange.to, this.to);
            return new Range[]{new Range(from, to)};
        }
    }

    /**
     * Разность двух интервалов
     */
    public Range[] getDifferenceIntervals(Range secondRange) {
        double from = this.from;
        double to = this.to;
        double secondTo = secondRange.to;
        double secondFrom = secondRange.from;
        double epsilon = 1.0e-10;
        if (this.to < secondRange.from || secondRange.to < this.from) {
            return new Range[]{new Range(from, to)};
        } else if (Math.abs(this.from - secondRange.to) < epsilon || Math.abs(secondRange.from - this.to) < epsilon) {
            double maximumFrom = (this.from > secondRange.from) ? this.from : secondRange.from;
            double minimumFrom = (this.from < secondRange.from) ? this.from : secondRange.from;
            return new Range[]{new Range(minimumFrom, maximumFrom - 1)};
        } else if (this.from < secondRange.from && secondRange.to < this.to) {
            return new Range[]{new Range(from, secondFrom - 1), new Range(secondTo + 1, to)};
        } else if (this.from < secondRange.from && this.to < secondRange.to) {
            return new Range[]{new Range(from, secondFrom - 1)};
        } else if (secondRange.from < this.from && this.to > secondRange.to) {
            return new Range[]{new Range(secondTo, to - 1)};
        }
        return new Range[]{};
    }
}