package com.example.storyreadingapp.Activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.storyreadingapp.Adapter.StoryAdapter;
import com.example.storyreadingapp.Domain.Story;
import com.example.storyreadingapp.R;
import java.util.ArrayList;
import java.util.List;

public class ViewStories extends AppCompatActivity {

    private RecyclerView recyclerViewStory;
    private StoryAdapter storyAdapter;
    private List<Story> storyList;
    private TextView categoryHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_stories);

        initRecyclerView();
    }

    private List<Story> loadStoriesByCategory(String category) {
        List<Story> allStories = new ArrayList<>();

        // Adjusted to use only image names without paths
        allStories.add(new Story("Lions are Always Brave", "Animal Stories", "lions_are_always_brave", "Lions are Always Brave.pdf"));
        allStories.add(new Story("The Tortoise and The Rabbit", "Animal Stories", "the_tortoise_and_the_rabbit", "The Tortoise and The Rabbit.pdf"));
        allStories.add(new Story("Thuli's Special Secret", "Animal Stories", "thulis_special_secret", "Thuli's Special Secret.pdf"));
        allStories.add(new Story("Old Women in a Bottle", "Fantasy Stories", "old_woman_in_a_bottle", "Old Women in a Bottle.pdf"));
        allStories.add(new Story("The Choco Fantasy", "Fantasy Stories", "the_choco_fantasy", "The Choco Fantasy.pdf"));
        allStories.add(new Story("The Fantasy", "Fantasy Stories", "the_fantasy", "The Fantasy.pdf"));
        allStories.add(new Story("Kottavi Raja and His Sleepy Kingdom", "Fiction Stories", "kottavi_raja_and_his_sleepy_kingdom", "Kottavi Raja and His Sleepy Kingdom.pdf"));
        allStories.add(new Story("The City Flooding", "Fiction Stories", "the_city_flooding", "The City Flooding.pdf"));
        allStories.add(new Story("The Space Scientist", "Fiction Stories", "the_space_scientist", "The Space Scientist.pdf"));
        allStories.add(new Story("Happy Diwali", "Folktale Stories", "happy_diwali", "Happy Diwali.pdf"));
        allStories.add(new Story("Rama Goes to Town", "Folktale Stories", "rama_goes_to_town", "Rama Goes to Town.pdf"));
        allStories.add(new Story("The Magic Powder", "Folktale Stories", "the_magic_powder", "The Magic Powder.pdf"));
        allStories.add(new Story("Phani's funny Chappal", "Funny Stories", "phanis_funny_chappals", "Phani's funny Chappal.pdf"));
        allStories.add(new Story("Playtime", "Funny Stories", "playtime", "Playtime.pdf"));
        allStories.add(new Story("The Peacock's Dance", "Funny Stories", "the_peacocks_dance", "The Peacock's Dance.pdf"));

        List<Story> filteredStories = new ArrayList<>();
        for (Story story : allStories) {
            if (story.getCategory().equals(category)) {
                filteredStories.add(story);
            }
        }
        return filteredStories;
    }

    private void initRecyclerView() {
        String category = getIntent().getStringExtra("CATEGORY");

        categoryHeading = findViewById(R.id.categoryTitle);
        categoryHeading.setText(category);

        recyclerViewStory = findViewById(R.id.recyclerView);
        recyclerViewStory.setLayoutManager(new LinearLayoutManager(this));

        storyList = loadStoriesByCategory(category);
        storyAdapter = new StoryAdapter(storyList, this); // Updated to use List<Story>

        recyclerViewStory.setAdapter(storyAdapter);
    }
}
