package com.example.textvalidationlib.Model;

public class NumericValidation implements ValidationField{

    private String text;
    public NumericValidation(){
    }

    private static final NumericValidation instance = new NumericValidation();

    public static NumericValidation getInstance() {
        return instance;
    }

    @Override
    public boolean isValid(String input) {
        return input.matches("[0-9\\s]+");
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return "Numeric";
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
