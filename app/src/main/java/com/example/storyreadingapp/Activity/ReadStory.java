package com.example.storyreadingapp.Activity;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.storyreadingapp.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadStory extends AppCompatActivity {

    MediaPlayer player;

    TextView pageNo;
    ImageView book;

    PdfRenderer renderer;
    int total_pages = 0;
    int display_page = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_story);

        Button prevBtn = findViewById(R.id.prevBtn);
        Button nextBtn = findViewById(R.id.nextBtn);
        pageNo = findViewById(R.id.pgNo);
        book = findViewById(R.id.bookView);

        // Get PDF filename from Intent
        String pdfFileName = getIntent().getStringExtra("PDF_PATH");

        try {
            File pdfFile = getFileFromAssets(pdfFileName);
            ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY);
            renderer = new PdfRenderer(parcelFileDescriptor);
            total_pages = renderer.getPageCount();
            display_page = 0;
            _display(display_page);

            // Playing sound in background
            player = MediaPlayer.create(ReadStory.this, R.raw.story);
            player.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        prevBtn.setOnClickListener(v -> {
            if (display_page > 0) {
                display_page--;
                _display(display_page);
            }
        });

        nextBtn.setOnClickListener(v -> {
            if (display_page < (total_pages - 1)) {
                display_page++;
                _display(display_page);
            }
        });
    }

    private File getFileFromAssets(String fileName) throws IOException {
        File file = new File(getCacheDir(), fileName);
        if (!file.exists()) {
            try (InputStream asset = getAssets().open(fileName);
                 FileOutputStream output = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = asset.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            }
        }
        return file;
    }

    private void _display(int _n) {
        if (renderer != null) {
            PdfRenderer.Page page = renderer.openPage(_n);
            Bitmap mBitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
            page.render(mBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            book.setImageBitmap(mBitmap);
            page.close();
            pageNo.setText((_n + 1) + "/" + total_pages);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (renderer != null) {
            renderer.close();
        }
        // Release MediaPlayer to avoid memory leaks
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
