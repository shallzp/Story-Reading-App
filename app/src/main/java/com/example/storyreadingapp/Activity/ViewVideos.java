package com.example.storyreadingapp.Activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyreadingapp.Adapter.VideoAdapter;
import com.example.storyreadingapp.R;

import java.util.Arrays;
import java.util.List;

public class ViewVideos extends AppCompatActivity {

    private RecyclerView recyclerViewVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_videos);

        recyclerViewVideo = findViewById(R.id.recyclerView);
        recyclerViewVideo.setLayoutManager(new LinearLayoutManager(this));

        // List of video URLs to display in WebView
        List<String> videoUrls = Arrays.asList(
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/bYkN5Qk_C8U?si=Zc0gYw4Lkly4k8na\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/x-uoEZ3_qA4?si=dkzpM1ZAA0NZ16_P\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KqiZi_v9gzg?si=RJtexLnDG2xPSXNT\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/wwdfDDfwFrk?si=h4D-_5GPLX3g2QgN\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Mt_CIBlEGos?si=z1Poc3d49baYfG5M\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        );

        // Set up VideoAdapter with the list of URLs
        VideoAdapter videoAdapter = new VideoAdapter(videoUrls, this);
        recyclerViewVideo.setAdapter(videoAdapter);
    }
}
