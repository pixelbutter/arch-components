package com.archsample.data.model;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("quoteText")
    private String content;

    @SerializedName("quoteAuthor")
    private String author;

    @SerializedName("quoteLink")
    private String link;

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getLink() {
        return link;
    }
}
