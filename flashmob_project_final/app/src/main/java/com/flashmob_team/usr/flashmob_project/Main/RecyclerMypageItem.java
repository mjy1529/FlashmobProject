package com.flashmob_team.usr.flashmob_project.Main;

public class RecyclerMypageItem {

    private String title;
    private String date;
    private String place;

    public RecyclerMypageItem(String title, String date, String place) {
        this.title = title;
        this.date = date;
        this.place = place;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
