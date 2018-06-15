package com.flashmob_team.usr.flashmob_project.Chat;


/*
firebase연동
 */
public class ChatDTO {

    private String message;
    private String name;

    public ChatDTO() {}
    public ChatDTO(String name, String message) {
        this.name = name;
        this.message = message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getMessage() {
        return message;
    }
}