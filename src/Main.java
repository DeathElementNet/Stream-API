import java.util.*;
import java.util.List;

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

        System.out.println("1. Младше 18 лет: " +
                persons.stream()
                        .filter(person -> person.getAge() < 18)
                        .count());


        System.out.println("2. Лица с призывным возрастом:");
        persons.stream()
                .filter(person -> person.getSex() == Sex.NAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(person -> person.getFamily())
                .forEach(System.out::println);


        System.out.println("3. Люди с образованием , работоспособные граждане:");
        persons.stream()
                .filter(person -> person.getSex() == Sex.WOMAN
                        ? person.getAge() >= 18 && person.getAge() < 60
                        : person.getAge() >= 18 && person.getAge() < 65)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted((person1, person2) -> person1.getFamily().compareTo(person2.getFamily()))
                .map(person -> person.getFamily() + " "
                        + person.getName() + ", "
                        + person.getAge() + ", "
                        + person.getEducation())
                .forEach(System.out::println);
    }
}