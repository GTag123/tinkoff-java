package edu.hw10.Task1.Generator.FieldsGenerator;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class FloatFieldsGenerator implements FieldsGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        float min = Float.MIN_VALUE;
        float max = Float.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }
        return ThreadLocalRandom.current().nextFloat(min, max);
    }
}
