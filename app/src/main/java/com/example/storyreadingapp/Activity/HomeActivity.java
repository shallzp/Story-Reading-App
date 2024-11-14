package com.example.storyreadingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.storyreadingapp.R;

public class HomeActivity extends AppCompatActivity {

    // Button declarations for each category
    private Button animalShowButton, fantasyShowButton, folktaleShowButton, fictionShowButton, funnyShowButton, videoShowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Ensure this matches your XML layout file name

        // Initialize buttons
        animalShowButton = findViewById(R.id.animal_show);
        fantasyShowButton = findViewById(R.id.fantasy_show);
        folktaleShowButton = findViewById(R.id.folktale_show);
        fictionShowButton = findViewById(R.id.fiction_show);
        funnyShowButton = findViewById(R.id.funny_show);
        videoShowButton = findViewById(R.id.video_show);

        // Set up click listeners for story categories
        animalShowButton.setOnClickListener(view -> openViewStory("Animal Stories"));
        fantasyShowButton.setOnClickListener(view -> openViewStory("Fantasy Stories"));
        folktaleShowButton.setOnClickListener(view -> openViewStory("Folktale Stories"));
        fictionShowButton.setOnClickListener(view -> openViewStory("Fiction Stories"));
        funnyShowButton.setOnClickListener(view -> openViewStory("Funny Stories"));

        // Set up click listener for videos category
        videoShowButton.setOnClickListener(view -> openViewVideo()); // Launch the video activity
    }

    // Method to open ViewStories activity and pass the selected category
    private void openViewStory(String category) {
        Intent intent = new Intent(HomeActivity.this, ViewStories.class);
        intent.putExtra("CATEGORY", category); // Pass the category name to ViewStories
        startActivity(intent);
    }

    // Method to open ViewVideo activity
    private void openViewVideo() {
        Intent intent = new Intent(HomeActivity.this, ViewVideos.class);
        startActivity(intent);
    }
}
