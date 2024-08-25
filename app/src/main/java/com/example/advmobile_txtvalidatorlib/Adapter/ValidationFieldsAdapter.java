package com.example.advmobile_txtvalidatorlib.Adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advmobile_txtvalidatorlib.R;
import com.example.textvalidationlib.Model.ValidationField;

import java.util.List;

public class ValidationFieldsAdapter extends RecyclerView.Adapter<ValidationFieldsAdapter.ValidationFieldViewHolder> {

    private List<ValidationField> validationFields;

    public ValidationFieldsAdapter(List<ValidationField> validationFields) {
        this.validationFields = validationFields;
    }

    @NonNull
    @Override
    public ValidationFieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_validation_field, parent, false);
        return new ValidationFieldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ValidationFieldViewHolder holder, int position) {
        ValidationField field = validationFields.get(position);
        holder.bind(field);
    }

    @Override
    public int getItemCount() {
        return validationFields.size();
    }

    public class ValidationFieldViewHolder extends RecyclerView.ViewHolder {
        private EditText editText;
        private ImageView iconPlaceholder;

        public ValidationFieldViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.validation_field_edit_text);
            iconPlaceholder = itemView.findViewById(R.id.icon_placeholder);
        }

        public void bind(ValidationField field) {
            editText.setText(field.getText());
            setHint(field);

            editText.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String newText = editText.getText().toString();
                        field.setText(newText);
                        updateValidationIcon(field.isValid(newText));

                    }
                }
            });

            // Add a TextWatcher to update the icon in real-time
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    updateValidationIcon(field.isValid(s.toString()));
                }
            });
        }

        private void setHint(ValidationField field) {
            editText.setHint("Enter " + field.getName() +" text to validate");
        }

        private void updateValidationIcon(boolean isValid) {
            if (isValid) {
                iconPlaceholder.setImageResource(R.drawable.check);
            } else {
                iconPlaceholder.setImageResource(R.drawable.error);
            }
        }
    }
}