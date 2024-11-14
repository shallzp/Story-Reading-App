package com.example.storyreadingapp.Domain;

public class Story {
    private String title;
    private String category;
    private String img_path;
    private String pdf_path;

    public Story(String title, String category, String img_path, String pdf_path) {
        this.title = title;
        this.category = category;
        this.img_path = img_path;
        this.pdf_path = pdf_path;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getImg() { return img_path; }
    public String getPdf() { return pdf_path; }
}
