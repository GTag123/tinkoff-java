package edu.hw10.Task1.ModelForTest;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import edu.hw10.Task1.Annotation.NotNull;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public final class Teacher {

    private final String name;
    private final int age;

    private Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Teacher create(@NotNull @Min(10) @Max(15) String name, @Min(30) @Max(60) int age) {
        return new Teacher(name, age);
    }
}
