package com.example.textvalidationlib.Model;

public class PasswordStrengthValidation  implements ValidationField {
    private String text;
    public PasswordStrengthValidation(){
    }

    private static final PasswordStrengthValidation instance = new PasswordStrengthValidation();

    public static PasswordStrengthValidation getInstance() {
        return instance;
    }

    @Override
    public boolean isValid(String input) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{11,}$";
        return input.matches(pattern);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return "Password Strength";
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
