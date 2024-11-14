package com.example.storyreadingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.storyreadingapp.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText, ageEditText, phoneEditText, passwordEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton selectedGenderRadioButton;
    private Button signUpBtn;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Initialize UI components
        nameEditText = findViewById(R.id.name);
        ageEditText = findViewById(R.id.age);
        phoneEditText = findViewById(R.id.phoneNo);
        passwordEditText = findViewById(R.id.password);
        genderRadioGroup = findViewById(R.id.radioGroupGender);
        signUpBtn = findViewById(R.id.signUpButton);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set onClickListener for Sign-Up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignUp();
            }
        });
    }

    private void handleSignUp() {
        String name = nameEditText.getText().toString().trim();
        String ageString = ageEditText.getText().toString().trim();
        String phoneNo = phoneEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId != -1) {
            selectedGenderRadioButton = findViewById(selectedGenderId);
        }
        String gender = selectedGenderRadioButton != null ? selectedGenderRadioButton.getText().toString() : "";

        // Debugging: Log the values
        Log.d("SignUpActivity", "Name: " + name);
        Log.d("SignUpActivity", "Age: " + ageString);
        Log.d("SignUpActivity", "Phone: " + phoneNo);
        Log.d("SignUpActivity", "Gender: " + gender);
        Log.d("SignUpActivity", "Password: " + password);

        if (name.isEmpty() || ageString.isEmpty() || phoneNo.isEmpty() || password.isEmpty() || gender.isEmpty()) {
            showToast("Please fill in all fields");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            showToast("Invalid Age");
            return;
        }

        if (databaseHelper.checkUserExists(phoneNo)) {
            showToast("User with this phone number already exists");
            return;
        }

        boolean isInserted = databaseHelper.addUser(name, age, phoneNo, gender, password);
        if (isInserted) {
            Log.d("SignUpActivity", "User inserted successfully");
            showToast("Sign up Successfully");
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.d("SignUpActivity", "Sign up failed");
            showToast("Sign up failed. Please try again.");
        }
    }

    private void showToast(String message) {
        // Using Handler to delay the Toast
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
