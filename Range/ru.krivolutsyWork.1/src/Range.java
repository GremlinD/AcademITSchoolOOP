import java.util.Scanner;

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
        double epsilon = 1.0e-10;
        return to - number >= epsilon && number - from >= epsilon;
    }

    /**
     * Получение интервала пересечения
     */
    public Range getIntersectionLength(Range secondRange) {
        to = (this.to < secondRange.to) ? this.to : secondRange.to;
        from = (this.from > secondRange.from) ? this.from : secondRange.from;
        if (from < to) {
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
            return new Range[]{Range.this, secondRange};
        } else {
            from = (this.from < secondRange.from) ? this.from : secondRange.from;
            to = (secondRange.to > this.to) ? secondRange.to : this.to;
            return new Range[]{new Range(from, to)};
        }
    }

    /**
     * Разность двух интервалов
     */
    public Range[] getDifferenceIntervals(Range secondRange) {
        if (this.to < secondRange.from || secondRange.to < this.from) {
            return new Range[]{Range.this};
        } else if (this.from < secondRange.from && secondRange.to < this.to) {
            return new Range[]{new Range(this.from, secondRange.from), new Range(secondRange.to, this.to)};
        } else if (this.from < secondRange.from && this.to < secondRange.to) {
            return new Range[]{new Range(this.from, secondRange.from)};
        } else if (secondRange.from < this.from && this.to > secondRange.to) {
            return new Range[]{new Range(secondRange.to, this.to)};
        }
        return null;
    }

    public static void main(String[] args) {
        Range range = new Range(-4, 8);

        System.out.printf("Значение from сейчас: %f%n", range.getFrom());
        System.out.println("Введите число, которое нужно записать в from:");
        Scanner scanner = new Scanner(System.in);
        range.setFrom(scanner.nextDouble());

        System.out.printf("Значение to сейчас: %f%n", range.getTo());
        System.out.println("Введите число, которое нужно записать в to:");
        range.setTo(scanner.nextDouble());

        System.out.printf("Длина между новыми числами равна: %f%n", range.getLength());

        System.out.println("Введите число, которое хотите проверить на вхождение в диапазон:");
        System.out.println(range.isInside(scanner.nextDouble()));
    }
}

