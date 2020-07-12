package com.fever.fevercontroller.model;

public class ChatMessage {
    private String chatRoomId;
    private String writer;
    private String message;

    public ChatMessage() {
    }

    public ChatMessage(String chatRoomId, String writer, String message) {
        this.chatRoomId = chatRoomId;
        this.writer = writer;
        this.message = message;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
