package edu.hw10.Task1.Generator.FieldsGenerator;

import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class BooleanFieldsGenerator implements FieldsGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
