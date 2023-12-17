package edu.hw10.Task1.Generator.FieldsGenerator;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class DoubleFieldsGenerator implements FieldsGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
