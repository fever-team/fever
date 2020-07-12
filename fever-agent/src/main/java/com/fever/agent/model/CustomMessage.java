package com.fever.agent.model;

public class CustomMessage {

    private String text;

    private int userCount;

    private boolean secret;

    protected CustomMessage() {
    }

    public CustomMessage(String text, int userCount, boolean secret) {
        this.text = text;
        this.userCount = userCount;
        this.secret = secret;
    }

    public String getText() {
        return text;
    }

    public int getUserCount() {
        return userCount;
    }

    public boolean isSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "text='" + text + '\'' +
                ", userCount=" + userCount +
                ", secret=" + secret +
                '}';
    }
}
