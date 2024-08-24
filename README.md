# Android Text Validation Library

This project is an Android library that provides textual validators for checking numeric text, alphabetical, phone number
and password strength with fixed formats.

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
