package edu.project3.Printers;

public class CLIPrinter implements Printer {

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
