package com.flashmob_team.usr.flashmob_project.Chat;

/*
채팅 말풍선 설정 클래스
 */
public class ChatMessage {
    public boolean left;
    public String message;

    public ChatMessage(boolean left, String message) {
        super();
        this.left = left;
        this.message = message;
    }
}