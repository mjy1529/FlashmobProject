package com.flashmob_team.usr.flashmob_project.Main;

public class RecyclerLikeItem {

    private String title;
    private String date;
    private int current_peoplenum; //현재 참여 인원
    private int total_peoplenum; //총 모집인원
    private String place;

    public RecyclerLikeItem(String title, String date, int current_peoplenum, int total_peoplenum, String place) {
        this.title = title;
        this.date = date;
        this.current_peoplenum = current_peoplenum;
        this.total_peoplenum = total_peoplenum;
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
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

    public int getCurrent_peoplenum() {
        return current_peoplenum;
    }

    public void setCurrent_peoplenum(int current_peoplenum) {
        this.current_peoplenum = current_peoplenum;
    }

    public int getTotal_peoplenum() {
        return total_peoplenum;
    }

    public void setTotal_peoplenum(int total_peoplenum) {
        this.total_peoplenum = total_peoplenum;
    }
}
