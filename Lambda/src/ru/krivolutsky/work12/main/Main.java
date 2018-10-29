package ru.krivolutsky.work12.main;

import ru.krivolutsky.work12.classes.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Михаил", 12));
        people.add(new Person("Семен", 43));
        people.add(new Person("Арсений", 65));
        people.add(new Person("Виктор", 13));
        people.add(new Person("Ольга", 98));
        people.add(new Person("Михаил", 23));
        people.add(new Person("Семен", 54));
        people.add(new Person("Ольга", 34));
        people.add(new Person("Виктор", 83));
        people.add(new Person("Оксана", 56));
        //получить список уникальных имен
        Stream<String> streamString = people.stream().map(Person::getName);
        streamString = streamString.distinct();
        List<String> list = streamString.collect(Collectors.toList());
        //вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.
        System.out.println(list.stream().collect(Collectors.joining(", ", "Имена: ", ".")));
        //получить список людей младше 18
        Stream<Person> streamPerson = people.stream().filter(p -> p.getAge() < 18);
        List<Person> minor = streamPerson.collect(Collectors.toList());
        //посчитать для них средний возраст
        OptionalDouble average = minor.stream().mapToDouble(Person::getAge).average();
        if (average.isPresent()) {
            System.out.printf("Средний возраст: %s%n", average.getAsDouble());
        }
        //при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        Map<String, Double> averageByNames = people.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        averageByNames.forEach((name, ageAverage) -> System.out.printf("name %s : %s%n", name, ageAverage));
        //получить людей, возраст которых от 20 до 45
        streamPerson = people.stream().filter(p -> p.getAge() >= 20 && p.getAge() <= 45);
        //вывести в консоль их имена в порядке убывания возраста
        streamPerson = streamPerson.sorted((p1, p2) -> p2.getAge() - p1.getAge());
        List<Person> from20To45 = streamPerson.collect(Collectors.toList());
        System.out.println(from20To45.stream().map(Person::getName).collect(Collectors.joining(", ", "Возраст: ", ".")));
    }
}
