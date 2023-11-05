package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class AnimalTest {
    private AnimalTest() {}

    private final List<Animal> animalList = new ArrayList<>();

    @BeforeEach
    public void clearList() {
        animalList.clear();
    }

    @Test
    @DisplayName("Test heightSortAnimal function")
    public void testHeightSortAnimal() {
        animalList.add(Animal.builder().height(155).build());
        animalList.add(Animal.builder().height(7).build());
        animalList.add(Animal.builder().height(34).build());
        animalList.add(Animal.builder().height(26).build());
        animalList.add(Animal.builder().height(59).build());

        List<Integer> res = AnimalUtils.heightSortAnimal(animalList)
            .stream().map(Animal::height).toList();

        assertThat(res).containsExactly(7, 26, 34, 59, 155);
    }

    @Test
    @DisplayName("Test weightSortAnimal function")
    public void testWeightSortAnimal() {
        animalList.add(Animal.builder().weight(10).build());
        animalList.add(Animal.builder().weight(1).build());
        animalList.add(Animal.builder().weight(25).build());
        animalList.add(Animal.builder().weight(17).build());

        List<Integer> res = AnimalUtils.weightSortAnimal(animalList, 3)
            .stream().map(Animal::weight).toList();

        assertThat(res).containsExactly(25, 17, 10);
    }

    @Test
    @DisplayName("Test countAnimalType function")
    public void testCountAnimalType() {
        animalList.add(Animal.builder().type(Animal.Type.DOG).build());
        animalList.add(Animal.builder().type(Animal.Type.FISH).build());
        animalList.add(Animal.builder().type(Animal.Type.DOG).build());
        animalList.add(Animal.builder().type(Animal.Type.CAT).build());
        animalList.add(Animal.builder().type(Animal.Type.FISH).build());
        animalList.add(Animal.builder().type(Animal.Type.FISH).build());


        Map<Animal.Type, Long> res = AnimalUtils.countAnimalType(animalList);

        //  создает объект entry, который представляет пару ключ-значение в мапе
        assertThat(res).contains(entry(Animal.Type.FISH, 3L), entry(Animal.Type.DOG, 2L), entry(Animal.Type.CAT, 1L));
    }

    @Test
    @DisplayName("Test getLongestNameAnimal function")
    public void testGetLongestNameAnimal() {
        animalList.add(Animal.builder().name("Bobik").build());
        animalList.add(Animal.builder().name("Yap").build());
        animalList.add(Animal.builder().name("Panamochka").build());
        animalList.add(Animal.builder().name("Murzik").build());

        Animal res = AnimalUtils.getLongestNameAnimal(animalList);

        assertThat(res.name()).isEqualTo("Panamochka");
    }

    @Test
    @DisplayName("Test countGenders function")
    public void testCountGenders() {
        animalList.add(Animal.builder().sex(Animal.Sex.M).build());
        animalList.add(Animal.builder().sex(Animal.Sex.M).build());
        animalList.add(Animal.builder().sex(Animal.Sex.F).build());
        animalList.add(Animal.builder().sex(Animal.Sex.M).build());

        Animal.Sex res = AnimalUtils.countGenders(animalList);

        assertThat(res).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Test getHeaviestAnimal function")
    public void testGetHeaviestAnimal() {
        animalList.add(Animal.builder().type(Animal.Type.FISH).weight(15).build());
        animalList.add(Animal.builder().type(Animal.Type.FISH).weight(5).build());
        animalList.add(Animal.builder().type(Animal.Type.FISH).weight(30).build());
        animalList.add(Animal.builder().type(Animal.Type.BIRD).weight(44).build());

        Map<Animal.Type, Animal> res = AnimalUtils.getHeaviestAnimal(animalList);

        assertThat(res).contains(
            entry(Animal.Type.FISH, Animal.builder().type(Animal.Type.FISH).weight(30).build()),
            entry(Animal.Type.BIRD, Animal.builder().type(Animal.Type.BIRD).weight(44).build())
        );
    }

    @Test
    @DisplayName("Test getEldestAnimal function")
    public void getEldestAnimal() {
        animalList.add(Animal.builder().age(10).build());
        animalList.add(Animal.builder().age(7).build());
        animalList.add(Animal.builder().age(2).build());
        animalList.add(Animal.builder().age(15).build());

        Animal actual = AnimalUtils.getEldestAnimal(animalList, 3);

        assertThat(actual.age()).isEqualTo(10);
    }

    @Test
    @DisplayName("Test findHeaviestAnimalBelowKHeight function")
    public void testFindHeaviestAnimalBelowKHeight() {
        animalList.add(Animal.builder().height(10).weight(40).build());
        animalList.add(Animal.builder().height(25).weight(15).build());
        animalList.add(Animal.builder().height(100).weight(150).build());
        animalList.add(Animal.builder().height(50).weight(40).build());

        Optional<Animal> res = AnimalUtils.findHeaviestAnimalBelowKHeight(animalList, 50);

        assertThat(res.get()).isEqualTo(Animal.builder().height(10).weight(40).build());
    }

    @Test
    @DisplayName("sumOfPaws")
    public void testSumOfPaws() {
        animalList.add(Animal.builder().type(Animal.Type.DOG).build());
        animalList.add(Animal.builder().type(Animal.Type.FISH).build());
        animalList.add(Animal.builder().type(Animal.Type.BIRD).build());
        animalList.add(Animal.builder().type(Animal.Type.CAT).build());
        animalList.add(Animal.builder().type(Animal.Type.SPIDER).build());
        animalList.add(Animal.builder().type(Animal.Type.SPIDER).build());

        Integer res = AnimalUtils.sumOfPaws(animalList);
        assertThat(res).isEqualTo(26);
    }

    @Test
    @DisplayName("Test findAnimalsWithMismatchedAgeAndLegs function")
    public void testFindAnimalsWithMismatchedAgeAndLegs() {
        animalList.add(Animal.builder().type(Animal.Type.CAT).age(4).build());
        animalList.add(Animal.builder().type(Animal.Type.DOG).age(5).build());
        animalList.add(Animal.builder().type(Animal.Type.BIRD).age(4).build());
        animalList.add(Animal.builder().type(Animal.Type.FISH).age(0).build());

        List<Animal> res = AnimalUtils.findAnimalsWithMismatchedAgeAndLegs(animalList);

        assertThat(res.stream().map(Animal::age).collect(Collectors.toList())).containsExactly(5, 4);
    }

    @Test
    @DisplayName("Test findAnimalsThatCanBiteAndTall function")
    public void testFindAnimalsThatCanBiteAndTall() {
        animalList.add(Animal.builder().bites(true).height(110).build());
        animalList.add(Animal.builder().bites(true).height(90).build());
        animalList.add(Animal.builder().bites(false).height(120).build());

        List<Animal> res = AnimalUtils.findAnimalsThatCanBiteAndTall(animalList);

        assertThat(res).hasSize(1);
    }

    @Test
    @DisplayName("Test countAnimalsWhoseWeightExceedsHeight function")
    public void testCountAnimalsWhoseWeightExceedsHeight() {
        animalList.add(Animal.builder().weight(120).height(100).build());
        animalList.add(Animal.builder().weight(120).height(120).build());
        animalList.add(Animal.builder().weight(90).height(100).build());
        animalList.add(Animal.builder().weight(130).height(100).build());

        Long res = AnimalUtils.countAnimalsWhoseWeightExceedsHeight(animalList);

        assertThat(res).isEqualTo(2);
    }

    @Test
    @DisplayName("Test animalsWhoseNamesConsistOfMoreTwoWords function")
    public void testAnimalsWhoseNamesConsistOfMoreTwoWords() {
        animalList.add(Animal.builder().name("One Two Three").build());
        animalList.add(Animal.builder().name("Tyler Durden").build());
        animalList.add(Animal.builder().name("Super Dora Dura").build());
        animalList.add(Animal.builder().name("Ryan").build());

        List<Animal> res = AnimalUtils.animalsWhoseNamesConsistOfMoreTwoWords(animalList);

        assertThat(res.stream().map(Animal::name).collect(Collectors.toList())).containsExactly(
            "One Two Three",
            "Super Dora Dura"
        );
    }

    @Test
    @DisplayName("Test isDogWithHeightGreaterBelowK function")
    public void testIsDogWithHeightGreaterBelowK() {
        animalList.add(Animal.builder().type(Animal.Type.DOG).height(120).build());
        animalList.add(Animal.builder().type(Animal.Type.DOG).height(80).build());
        animalList.add(Animal.builder().type(Animal.Type.CAT).height(100).build());

        boolean actual = AnimalUtils.isDogWithHeightGreaterBelowK(animalList, 90);

        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("Test getSumOfWeightByTypeAndAgeRange function")
    public void testGetSumOfWeightByTypeAndAgeRange() {
        animalList.add(Animal.builder().type(Animal.Type.DOG).weight(35).age(10).build());
        animalList.add(Animal.builder().type(Animal.Type.DOG).weight(50).age(8).build());
        animalList.add(Animal.builder().type(Animal.Type.SPIDER).weight(1).age(5).build());
        animalList.add(Animal.builder().type(Animal.Type.FISH).weight(300).age(50).build());

        Map<Animal.Type, Integer> res = AnimalUtils.getSumOfWeightByTypeAndAgeRange(animalList, 5, 10);

        assertThat(res.get(Animal.Type.DOG)).isEqualTo(85);
        assertThat(res.get(Animal.Type.SPIDER)).isEqualTo(1);
    }

    @Test
    @DisplayName("Test sortAnimalsByTypeGenderAndName function")
    public void testSortAnimalsByTypeGenderAndName() {
        animalList.add(Animal.builder().type(Animal.Type.BIRD).sex(Animal.Sex.F).name("Bird1").build());
        animalList.add(Animal.builder().type(Animal.Type.BIRD).sex(Animal.Sex.M).name("Bird3").build());
        animalList.add(Animal.builder().type(Animal.Type.BIRD).sex(Animal.Sex.F).name("Bird2").build());
        animalList.add(Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.F).name("Cat").build());

        List<Animal> res = AnimalUtils.sortAnimalsByTypeGenderAndName(animalList);

        assertThat(res).containsExactly(
            Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.F).name("Cat").build(),
            Animal.builder().type(Animal.Type.BIRD).sex(Animal.Sex.M).name("Bird3").build(),
            Animal.builder().type(Animal.Type.BIRD).sex(Animal.Sex.F).name("Bird1").build(),
            Animal.builder().type(Animal.Type.BIRD).sex(Animal.Sex.F).name("Bird2").build()
        );
    }

    @Test
    @DisplayName("Test isSpidersBiteMoreThanDogs function")
    public void testIsSpidersBiteMoreThanDogs() {
        animalList.add(Animal.builder().type(Animal.Type.SPIDER).bites(true).build());
        animalList.add(Animal.builder().type(Animal.Type.DOG).bites(true).build());
        animalList.add(Animal.builder().type(Animal.Type.SPIDER).bites(true).build());
        animalList.add(Animal.builder().type(Animal.Type.DOG).bites(true).build());
        animalList.add(Animal.builder().type(Animal.Type.DOG).bites(true).build());

        boolean actual = AnimalUtils.isSpidersBiteMoreThanDogs(animalList);

        assertThat(actual).isEqualTo(false);
    }

    @Test
    @DisplayName("Test getHeaviestFishIn function")
    public void testGetHeaviestFishIn() {
        animalList.add(Animal.builder().type(Animal.Type.FISH).weight(100).build());
        animalList.add(Animal.builder().type(Animal.Type.DOG).weight(100).build());

        List<Animal> animalList2 = new ArrayList<>();
        animalList2.add(Animal.builder().type(Animal.Type.CAT).weight(200).build());

        List<Animal> animalList3 = new ArrayList<>();
        animalList3.add(Animal.builder().type(Animal.Type.FISH).weight(300).build());

        Animal res = AnimalUtils.getHeaviestFishIn(animalList, animalList2, animalList3);

        assertThat(res).isEqualTo(animalList3.get(0));
    }

    @Test
    @DisplayName("Test getAnimalsErrorMap function")
    public void testGetAnimalsErrorMap() {
        animalList.add(Animal.builder().weight(-35).height(-50).age(0).name("Laura").build());
        animalList.add(Animal.builder().weight(-1).height(2).age(0).name("Snowy").build());
        animalList.add(Animal.builder().weight(12).height(13).age(10).name("Normic").build());

        Map<String, Set<ValidationError>> res = AnimalUtils.getAnimalsErrorMap(animalList);

        assertThat(res)
            .containsExactly(
                entry(
                    "Laura",
                    Set.of(
                        new ValidationError(ValidationError.ErrorType.AGE),
                        new ValidationError(ValidationError.ErrorType.HEIGHT),
                        new ValidationError(ValidationError.ErrorType.WEIGHT)
                    )
                ),
                entry(
                    "Snowy",
                    Set.of(
                        new ValidationError(ValidationError.ErrorType.WEIGHT),
                        new ValidationError(ValidationError.ErrorType.AGE)
                    )
                )
            );
    }

    @Test
    @DisplayName("Test getReadableAnimalsErrorMap function")
    public void getReadableAnimalsErrorMap() {
        animalList.add(Animal.builder().name("Biba").age(-1).weight(120).height(-20).build());
        animalList.add(Animal.builder().name("Boba").age(1).weight(120).height(20).build());

        Map<String, String> actual = AnimalUtils.getReadableAnimalsErrorMap(animalList);

        assertThat(actual)
            .hasEntrySatisfying("Biba", val -> assertThat(val).isIn("AGE, HEIGHT", "HEIGHT, AGE"));
    }
}
