package com.example.textvalidationlib.Model;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.textvalidationlib.R;

public class CustomValidationActivity extends AppCompatActivity {
    private SeekBar lengthSeekBar;
    private TextView lengthValueTextView;
    private CheckBox lowerCaseCheckBox;
    private CheckBox upperCaseCheckBox;
    private CheckBox numericalCheckBox;
    private CheckBox emailCheckBox;
    private EditText customNameEditText;
    private CheckBox regexCheckBox;
    private EditText customRegexEditText;
    private Button addCustomValidationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_validation);
        findViews();
        setupListeners();

    }

    private void setupListeners() {
        addCustomValidationButton.setOnClickListener(v -> createCustomValidation());

        emailCheckBox.setOnClickListener(view -> {
            boolean isChecked = emailCheckBox.isChecked();
            if (isChecked) {
                // Disable and uncheck other checkboxes
                setCheckboxesEnabled(false, false);
                lowerCaseCheckBox.setChecked(false);
                upperCaseCheckBox.setChecked(false);
                numericalCheckBox.setChecked(false);
                regexCheckBox.setChecked(false);
            } else {
                // Enable other checkboxes
                setCheckboxesEnabled(true, false);
            }
        });

        regexCheckBox.setOnClickListener(view -> {
            boolean isChecked = regexCheckBox.isChecked();
            if (isChecked) {
                customRegexEditText.setVisibility(View.VISIBLE);
                // Disable and uncheck other checkboxes
                setCheckboxesEnabled(false, true);
                lowerCaseCheckBox.setChecked(false);
                upperCaseCheckBox.setChecked(false);
                numericalCheckBox.setChecked(false);
                emailCheckBox.setChecked(false);
            } else {
                customRegexEditText.setVisibility(View.GONE);
                // Enable other checkboxes
                setCheckboxesEnabled(true, true);
            }
        });

        lengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lengthValueTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: Code to execute when user starts dragging the thumb
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: Code to execute when user stops dragging the thumb
            }
        });
    }

    private void setCheckboxesEnabled(boolean enabled, boolean isRegex) {
        lowerCaseCheckBox.setEnabled(enabled);
        upperCaseCheckBox.setEnabled(enabled);
        numericalCheckBox.setEnabled(enabled);
        lengthSeekBar.setEnabled(enabled);

        if (isRegex)
            emailCheckBox.setEnabled(enabled);
        else
            regexCheckBox.setEnabled(enabled);

        if (enabled == false) {
            lengthSeekBar.setProgress(0);
        }
    }

    private void findViews() {
        lengthSeekBar = findViewById(R.id.length_seekbar);
        lengthValueTextView = findViewById(R.id.length_value_textview);

        lowerCaseCheckBox = findViewById(R.id.lowercase_checkbox);
        upperCaseCheckBox = findViewById(R.id.uppercase_checkbox);
        numericalCheckBox = findViewById(R.id.numerical_checkbox);
        emailCheckBox = findViewById(R.id.email_checkbox);
        customNameEditText = findViewById(R.id.custom_name_edittext);
        addCustomValidationButton = findViewById(R.id.add_custom_validation_button);

        regexCheckBox = findViewById(R.id.regex_checkbox);
        customRegexEditText = findViewById(R.id.custom_template_edittext);;
    }

    private void createCustomValidation() {
        int length = lengthSeekBar.getProgress();
        boolean hasLowerCase = lowerCaseCheckBox.isChecked();
        boolean hasUpperCase = upperCaseCheckBox.isChecked();
        boolean hasNumerical = numericalCheckBox.isChecked();
        boolean hasEmail = emailCheckBox.isChecked();
        boolean isRegex = regexCheckBox.isChecked();
        String customRegex = customRegexEditText.getText().toString();
        String customName = customNameEditText.getText().toString();

        // Create a CustomValidation object
        CustomValidation customValidation = new CustomValidation(customName, length, hasLowerCase, hasUpperCase, hasNumerical, hasEmail, isRegex, customRegex);

        // Send the custom validation back to MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("customValidation", customValidation);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
