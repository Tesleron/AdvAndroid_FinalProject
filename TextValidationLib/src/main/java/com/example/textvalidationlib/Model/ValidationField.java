package com.example.textvalidationlib.Model;

public interface ValidationField {
    boolean isValid(String input);

    // Getters and setters
    String getText();

    String getName();

    void setText(String text);
}
