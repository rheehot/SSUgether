package org.collapsed.ssuparty_android.model.contest;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ContestData {
    private String content;
    private String date;
    private String homepage_url;
    private int id;
    private String image_url;
    private String title;

    public ContestData() {
    }

    public ContestData(String content, String date, String homepage_url, int id, String image_url, String title) {
        this.content = content;
        this.date = date;
        this.homepage_url = homepage_url;
        this.id = id;
        this.image_url = image_url;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomepage_url() {
        return homepage_url;
    }

    public void setHomepage_url(String homepage_url) {
        this.homepage_url = homepage_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
