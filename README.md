# Android Text Validation Library

This project is an Android library that provides textual validators for checking numeric text, alphabetical, phone number
and password strength with fixed formats, email formats and even your own custom made rules for text validation using regex!
all validations are being determined on real-time

## Installation

To use this library in your Android project, add the following configuration to your `build.gradle` files:

### Step 1: Add Maven Repository

Add the following code to the `dependencyResolutionManagement` section in your project's `settings.gradle` file:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Step 2: Add Dependency
Add the library dependency to your app module's build.gradle file:
```
 dependencies {
    implementation 'com.github.Tesleron:AdvMobile_TxtValidatorLib:Tag'
}
```
Replace Tag with the specific version or commit tag you want to use from the repository.

# Usage
For any general usage on this library, import this interface:
```
    import com.example.textvalidationlib.Model.ValidationField;
    import com.example.advmobile_txtvalidatorlib.Adapter.ValidationFieldsAdapter;
``` 

## Validation list definition & adapter
The adapter makes sure validations are being determined in real-time using cute icons
![Alt text](https://ibb.co/bB8xwWr)
```
        ...
        
        validationFields = new ArrayList<ValidationField>();
        adapter = new ValidationFieldsAdapter(validationFields);
        
        ...
        
        validationFields.add(new NumericValidation());
        validationFields.add(new PasswordStrengthValidation());
        validationFields.add(new PhoneNumberValidation());
        validationFields.add(new VerbalValidation());

        adapter.notifyItemInserted(validationFields.size() - 1);
```

## Custom Validation
To implement custom validations, use these functions on the main activity
```
import com.example.textvalidationlib.Model.CustomValidation;

...

private static final int CUSTOM_VALIDATION_REQUEST_CODE = 1;

...

MaterialButton addFieldButton = findViewById(R.id.add_field_BTN_main);
        addFieldButton.setOnClickListener(v -> addNewValidationField());
        
        ...
        
        
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
```

## Primitive usage of the library
## Numeric Validation
```
import com.example.textvalidationlib.Model.NumericValidation;

boolean isValid = NumericValidation.getInstance().isValid("1234");
```
## Password Strength Validation
```
import com.example.textvalidationlib.Model.PasswordStrengthValidation;

boolean isValid = PasswordStrengthValidation.getInstance().isValid("P@ssW0rd1234");
```

## Phone Number Validation
```
import com.example.textvalidationlib.Model.PhoneNumberValidation;

boolean isValid = PhoneNumberValidation.getInstance().isValid("051-111-1111");
```
## Alphabetical Text Validation
```
import com.example.textvalidationlib.Model.VerbalValidation;

boolean isValid = VerbalValidation.getInstance().isValid("This is a text");
```
