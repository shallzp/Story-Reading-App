package com.example.storyreadingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.storyreadingapp.R;

public class IntroActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DURATION = 3000; // Duration in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); // Set the layout to the XML layout provided

        // Redirect to the main activity after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the IntroActivity
            }
        }, SPLASH_SCREEN_DURATION);
    }
}
