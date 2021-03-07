package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("--- Функционал --- \n" +
                    "1. Кол-во несовершеннолетних (т.е людей младше 18 лет)\n" +
                    "2. Список фамилий призывников (т.е. мужчин от 18 и до 27 лет)\n" +
                    "3. Отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке \n" +
                    "(т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин)\n" +
                    "4. Выйти из программы");
            int operation = scanner.nextInt();
            if (operation == 4) {
                break;
            }

            switch (operation) {
                case 1:
                    long underagePersons = persons.stream()
                            .filter(person -> person.getAge() < 18)
                            .count();
                    System.out.println(underagePersons);
                    break;

                case 2:
                    List<String> inductee = persons.stream()
                            .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                            .filter(person -> person.getSex().equals(Sex.MAN))
                            .map(Person::getFamily)
                            .collect(Collectors.toList());
                    System.out.println(inductee);
                    break;

                case 3:
                    List<Person> workingPeople = persons.stream()
                            .filter(person -> person.getEducation().equals(Education.HIGHER))
                            .filter(person -> person.getAge() >= 18)
                            .filter(person -> person.getSex().equals(Sex.WOMAN) ? person.getAge() <= 60 : person.getAge() <= 65)
                            .sorted(Comparator.comparing(Person::getFamily, Comparator.naturalOrder()))
                            .collect(Collectors.toList());
                    System.out.println(workingPeople.toString());
                    break;

                default:
                    System.out.println("Такой функции в списке нет...");
            }
        }
    }
}
