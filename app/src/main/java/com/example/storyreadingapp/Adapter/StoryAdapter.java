package com.example.storyreadingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.storyreadingapp.Domain.Story;
import com.example.storyreadingapp.Activity.ReadStory;
import com.example.storyreadingapp.R;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private List<Story> items;
    private Context context;

    public StoryAdapter(List<Story> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        Story story = items.get(position);
        holder.title.setText(story.getTitle());

        // Assuming images are stored in drawable folder
        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(story.getImg(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.story_img);

        switch (position) {
            case 0:
                holder.layout.setBackgroundResource(R.color.yellow_light);
                break;
            case 1:
                holder.layout.setBackgroundResource(R.color.light_blue);
                break;
            case 2:
                holder.layout.setBackgroundResource(R.color.light_green);
                break;
        }

        // Set up the click listener for the read button
        holder.readButton.setOnClickListener(v -> {
            String storypath = story.getPdf();

            Intent intent = new Intent(context, ReadStory.class);
            intent.putExtra("PDF_PATH", storypath); // Pass the PDF path
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView story_img;
        ConstraintLayout layout;
        Button readButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.storyTitle);
            story_img = itemView.findViewById(R.id.storyImg);
            layout = itemView.findViewById(R.id.video_layout);
            readButton = itemView.findViewById(R.id.readStory);
        }
    }
}
