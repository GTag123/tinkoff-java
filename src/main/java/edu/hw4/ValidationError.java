package edu.hw4;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record ValidationError(ErrorType errorType) {
    private static final String ERROR_STRING = "should be greater than 0";

    @Getter // generates getter methods for all fields of the class
    @AllArgsConstructor // generates the constructor
    enum ErrorType {

        AGE("Age " + ERROR_STRING),

        HEIGHT("Height " + ERROR_STRING),

        WEIGHT("Weight " + ERROR_STRING);

        private final String errorMessage;
    }
}
