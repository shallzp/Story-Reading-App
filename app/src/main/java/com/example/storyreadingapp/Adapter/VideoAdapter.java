package com.example.storyreadingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyreadingapp.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<String> videoUrls;
    private final Context context;

    public VideoAdapter(List<String> videoUrls, Context context) {
        this.videoUrls = videoUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        String videoUrl = videoUrls.get(position);

        holder.webView.setWebViewClient(new WebViewClient());  // Ensures loading within the WebView
        WebSettings webSettings = holder.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  // Enable JavaScript for video playback
        holder.webView.setWebChromeClient(new WebChromeClient());

        // Load HTML content into the WebView
        String html = "<html><body style=\"margin:0;padding:0;\">" + videoUrl + "</body></html>";
        holder.webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

    @Override
    public int getItemCount() {
        return videoUrls.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        WebView webView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.video);
        }
    }
}
