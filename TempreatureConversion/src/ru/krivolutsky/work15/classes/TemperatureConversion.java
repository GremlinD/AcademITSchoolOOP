package ru.krivolutsky.work15.classes;

public class TemperatureConversion {
    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double celsiusToFahrenheit(double celsius) {
        return celsius * ((double) 9 / 5) + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * ((double) 5 / 9);
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * ((double) 9 / 5) + 32;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * ((double) 5 / 9) + 273.15;
    }
}
