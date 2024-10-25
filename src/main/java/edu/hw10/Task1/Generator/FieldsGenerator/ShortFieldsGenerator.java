package edu.hw10.Task1.Generator.FieldsGenerator;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class ShortFieldsGenerator implements FieldsGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = (short) minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = (short) maxAnnotation.value();
            }
        }
        return (short) ThreadLocalRandom.current().nextInt(min, max);
    }
}
