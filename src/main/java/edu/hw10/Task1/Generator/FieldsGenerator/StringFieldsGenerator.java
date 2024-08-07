package edu.hw10.Task1.Generator.FieldsGenerator;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import edu.hw10.Task1.Annotation.NotNull;
import java.lang.annotation.Annotation;
import org.apache.commons.lang3.RandomStringUtils;

public class StringFieldsGenerator implements FieldsGenerator {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 8;

    @Override
    public Object generate(Annotation[] annotations) {
        int minLength = MIN_LENGTH;
        int maxLength = MAX_LENGTH;
        boolean isNotNull = false;
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                isNotNull = true;
            } else if (annotation instanceof Min minAnnotation) {
                minLength = (int) minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                maxLength = (int) maxAnnotation.value();
            }
        }
        if (!isNotNull) {
            return null;
        }
        return minLength < maxLength ? RandomStringUtils.randomAlphabetic(minLength, maxLength)
            : RandomStringUtils.randomAlphabetic(maxLength, minLength);
    }
}
