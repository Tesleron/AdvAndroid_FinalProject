package com.example.advmobile_txtvalidatorlib;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.textvalidationlib.Model.CustomValidation;
import com.example.textvalidationlib.Model.CustomValidationActivity;
import com.example.textvalidationlib.Model.NumericValidation;
import com.example.textvalidationlib.Model.PasswordStrengthValidation;
import com.example.textvalidationlib.Model.PhoneNumberValidation;
import com.example.textvalidationlib.Model.VerbalValidation;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advmobile_txtvalidatorlib.Adapter.ValidationFieldsAdapter;
import com.example.textvalidationlib.Model.ValidationField;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ValidationFieldsAdapter adapter;
    private List<ValidationField> validationFields;

    private static final int CUSTOM_VALIDATION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.validation_fields_recycler_view);
        validationFields = new ArrayList<>();
        adapter = new ValidationFieldsAdapter(validationFields);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        MaterialButton addFieldButton = findViewById(R.id.add_field_BTN_main);
        addFieldButton.setOnClickListener(v -> addNewValidationField());

        validationFields.add(new NumericValidation());
        validationFields.add(new PasswordStrengthValidation());
        validationFields.add(new PhoneNumberValidation());
        validationFields.add(new VerbalValidation());

        adapter.notifyItemInserted(validationFields.size() - 1);
    }

    private void addNewValidationField() {
        Intent intent = new Intent(this, CustomValidationActivity.class);
        startActivityForResult(intent, CUSTOM_VALIDATION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("pttt","reached here after add validation");
        if (requestCode == CUSTOM_VALIDATION_REQUEST_CODE && resultCode == RESULT_OK) {
            CustomValidation customValidation = (CustomValidation) data.getSerializableExtra("customValidation");
            validationFields.add(customValidation);
            adapter.notifyItemInserted(validationFields.size() - 1);
        }
    }

}
