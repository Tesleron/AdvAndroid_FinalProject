package com.example.textvalidationlib.Model;

import java.io.Serializable;

public class CustomValidation implements ValidationField, Serializable {
    private String name;
    private int length;
    private boolean hasLowerCase;
    private boolean hasUpperCase;
    private boolean hasNumerical;
    private boolean hasEmail;
    private boolean isRegex;
    private String text = "";
    private String template = "";

    public CustomValidation(String name, int length, boolean hasLowerCase, boolean hasUpperCase, boolean hasNumerical, boolean hasEmail
            , boolean isRegex, String customRegex) {
        this.name = name;
        this.length = length;
        this.hasLowerCase = hasLowerCase;
        this.hasUpperCase = hasUpperCase;
        this.hasNumerical = hasNumerical;
        this.hasEmail = hasEmail;
        this.isRegex = isRegex;
        if (this.isRegex && !customRegex.isEmpty())
            setTemplate(customRegex);
    }

    @Override
    public boolean isValid(String text) {

        if (isRegex) {
            boolean matchRegex = text.matches(template);
            return matchRegex;
        }
        else if (hasEmail) {
            boolean matchEmail = text.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            return matchEmail;
        }
        else {
            if (text.length() != length) return false;
            if (hasLowerCase && !text.matches(".*[a-z].*")) return false;
            if (hasUpperCase && !text.matches(".*[A-Z].*")) return false;
            if (hasNumerical && !text.matches(".*\\d.*")) return false;
            return true;
        }
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
