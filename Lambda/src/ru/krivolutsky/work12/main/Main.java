package ru.krivolutsky.work12.main;

import ru.krivolutsky.work12.classes.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("q", 12));
        people.add(new Person("w", 43));
        people.add(new Person("e", 65));
        people.add(new Person("q", 13));
        people.add(new Person("w", 98));
        people.add(new Person("e", 23));
        people.add(new Person("q", 54));
        people.add(new Person("w", 34));
        people.add(new Person("e", 83));
        people.add(new Person("q", 56));

        List<Person> uniquePeople = people.stream().distinct().collect(Collectors.toList()); //Спрашивал у вас, будет ли эта строка работать, вы ответили да. Не смог понять почему список е изменяется.
        System.out.println(uniquePeople.stream().map(Person::getName).collect(Collectors.joining(", ", "Имена: ", ".")));

        List<Person> minor = people.stream().filter(p -> p.getAge() < 18).collect(Collectors.toList());
        System.out.println(minor.stream().mapToDouble(Person::getAge).average().getAsDouble());

        Map<String, Double> averageByNames = people.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        List<Person> from20To45 = people.stream().filter(p -> p.getAge() > 18 && p.getAge() < 45).sorted((p1, p2) -> p2.getAge()-p1.getAge()).collect(Collectors.toList());
        System.out.println(from20To45.stream().map(Person::getName).collect(Collectors.joining(", ", "Возраст: ", ".")));
    }
}
