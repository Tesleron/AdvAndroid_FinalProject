package com.example.textvalidationlib.Model;

public class VerbalValidation implements ValidationField {
    private String text;
    public VerbalValidation(){
    }

    private static final VerbalValidation instance = new VerbalValidation();

    public static VerbalValidation getInstance() {
        return instance;
    }

    @Override
    public boolean isValid(String input) {
        return input.matches("[a-zA-Z\\s]+");
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public String getName() {
        return "Verbal";
    }

    @Override
    public void setText(String text) {

    }
}
