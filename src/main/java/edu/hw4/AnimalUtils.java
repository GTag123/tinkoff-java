package edu.hw4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public final class AnimalUtils {
    private AnimalUtils() {}

    private static final int MAX_HEIGHT = 100;

    public static List<Animal> heightSortAnimal(List<Animal> list) {
        return list.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList()); // Stream.toList() - immutable type
    }

    public static List<Animal> weightSortAnimal(List<Animal> list, int count) {
        return list.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(count)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Long> countAnimalType(List<Animal> list) {
        // Grouping by Animal::type, Collectors.counting() - counts the number of elements in each group
        return list.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal getLongestNameAnimal(List<Animal> list) {
        // find max element in the stream according to the specified comparator
        return list.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex countGenders(List<Animal> list) {
        return list.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Comparator.comparingLong(Entry::getValue))
            .map(Entry::getKey)
            .orElse(null);
    }

    public static Map<Animal.Type, Animal> getHeaviestAnimal(List<Animal> list) {
        // Function.identity() == animal -> animal
        // Use BinaryOperator.maxBy to return Animal type (not Integer like in Comparator)
        return list.stream()
            .collect(Collectors.toMap(
                Animal::type, animal -> animal,
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }

    public static Animal getEldestAnimal(List<Animal> list, int k) {
        return list.stream().sorted(Comparator.comparingInt(Animal::age))
            .skip(k - 1)
            .findFirst().orElse(null);
    }

    public static Optional<Animal> findHeaviestAnimalBelowKHeight(List<Animal> list, int k) {
        return list.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer sumOfPaws(List<Animal> list) {
        //  map() returns type - Stream<Integer>, mapToInt() - IntStream
        return list.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> findAnimalsWithMismatchedAgeAndLegs(List<Animal> list) {
        return list.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    public static List<Animal> findAnimalsThatCanBiteAndTall(List<Animal> list) {
        return list.stream()
            .filter(animal -> (animal.height() > MAX_HEIGHT && animal.bites()))
            .collect(Collectors.toList());
    }

    public static Long countAnimalsWhoseWeightExceedsHeight(List<Animal> list) {
        return list.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> animalsWhoseNamesConsistOfMoreTwoWords(List<Animal> list) {
        return list.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    public static Boolean isDogWithHeightGreaterBelowK(List<Animal> list, int k) {
        // anyMatch() - returns true if >=1 element of the stream satisfies the condition
        return list.stream()
            .anyMatch(animal -> (animal.type() == Animal.Type.DOG && animal.height() > k));
    }

    public static Map<Animal.Type, Integer> getSumOfWeightByTypeAndAgeRange(List<Animal> list, int k, int l) {
        return list.stream()
            .filter(animal -> (animal.age() >= k && animal.age() <= l))
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortAnimalsByTypeGenderAndName(List<Animal> list) {
        return list.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    public static Boolean isSpidersBiteMoreThanDogs(List<Animal> list) {
        return list.stream()
            .filter(animal -> ((animal.type() == Animal.Type.SPIDER || animal.type() == Animal.Type.DOG)
                && animal.bites()))
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Animal::type, Collectors.counting()),
                res -> res.get(Animal.Type.DOG) < res.get(Animal.Type.SPIDER)));
    }

    @SafeVarargs
    public static Animal getHeaviestFishIn(List<Animal>... list) {
        // Arrays.stream(list) преобразует массив списков животных list в поток (Stream)
        return Arrays.stream(list).flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> getAnimalsErrorMap(List<Animal> list) {
        return list.stream().collect(Collectors.collectingAndThen(
            Collectors.toMap(Animal::name, AnimalUtils::validateAnimal),
            map -> {
                map.values().removeIf(Set::isEmpty);
                return map;
            }
        ));
    }

    private static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.age() <= 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.AGE));
        }
        if (animal.height() <= 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.HEIGHT));
        }
        if (animal.weight() <= 0) {
            errors.add(new ValidationError(ValidationError.ErrorType.WEIGHT));
        }
        return errors;
    }

    public static Map<String, String> getReadableAnimalsErrorMap(List<Animal> list) {
        return list.stream().collect(Collectors.collectingAndThen(
            Collectors.toMap(Animal::name,
                animal -> validateAnimal(animal).stream().map(
                    err -> err.errorType().toString()).collect(Collectors.joining(", "))),
            map -> {
                map.values().removeIf(String::isEmpty);
                return map;
            }
        ));
    }
}
