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
        long countPerson = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .count();
        System.out.println(countPerson);
        List<String> streamPerson1 = persons.stream()
                .filter(Person -> (Person.getAge() >= 18) && (Person.getAge() <= 27))
                .map(Person -> Person.getFamily())
                .collect(Collectors.toList());
        System.out.println(streamPerson1);
        List<String> streamPerson2 = persons.stream()
                .filter(Person -> (Person.getAge() >= 18) && (((Person.getSex() == Sex.MAN) && (Person.getAge() <= 60)) || ((Person.getSex() == Sex.WOMAN) && (Person.getAge() <= 65))))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person -> Person.getFamily())
                .collect(Collectors.toList());
        System.out.println(streamPerson2);
    }
}