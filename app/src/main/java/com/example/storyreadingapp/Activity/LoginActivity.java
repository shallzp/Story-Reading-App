package com.example.storyreadingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.storyreadingapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneEditText, passwordEditText;
    private Button loginButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Ensure this matches your layout file name

        // Initialize UI components
        phoneEditText = findViewById(R.id.phoneNo);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.signUpButton); // Reusing the same button ID as in sign up

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set onClickListener for Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String phoneNo = phoneEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (phoneNo.isEmpty() || password.isEmpty()) {
            showToast("Please fill in all fields");
            return;
        }

        // Check user credentials
        boolean isValid = databaseHelper.validateUser(phoneNo, password);
        if (isValid) {
            Log.d("LoginActivity", "User logged in successfully");
            showToast("Login Successful");
            // Navigate to CategorySelect Activity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.d("LoginActivity", "Login failed");
            showToast("Invalid phone number or password");
        }
    }

    private void showToast(String message) {
        // Using Handler to delay the Toast
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
