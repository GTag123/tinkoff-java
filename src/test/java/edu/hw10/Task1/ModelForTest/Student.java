package edu.hw10.Task1.ModelForTest;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import edu.hw10.Task1.Annotation.NotNull;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Student {

    private final String name;
    private final int age;

    public Student(@NotNull String name, @Min(20) @Max(30) int age) {
        this.name = name;
        this.age = age;
    }
}
