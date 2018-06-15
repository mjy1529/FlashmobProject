package com.flashmob_team.usr.flashmob_project.Main;

public class SearchItem {
    String title;
    String date;
    String category;

    public SearchItem(String title, String date, String category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
