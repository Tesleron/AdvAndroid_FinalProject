package com.example.textvalidationlib.Model;

public class PhoneNumberValidation implements ValidationField {
    private String text;
    public PhoneNumberValidation(){
    }

    private static final PhoneNumberValidation instance = new PhoneNumberValidation();

    public static PhoneNumberValidation getInstance() {
        return instance;
    }

    @Override
    public boolean isValid(String input) {
        return input.matches("0\\d{2}-\\d{3}-\\d{4}");
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return "Phone Number";
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
