package edu.hw2;

public record CallingInfo(String className, String methodName) {
    public static CallingInfo callingInfo() {
        StackTraceElement previousStackTraceElement = new Throwable().getStackTrace()[1];
        return new CallingInfo(
            previousStackTraceElement.getClassName(),
            previousStackTraceElement.getMethodName()
        );
    }
}
