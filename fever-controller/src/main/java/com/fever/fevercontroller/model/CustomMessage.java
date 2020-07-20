package com.fever.fevercontroller.model;

public class CustomMessage {

    private String requestURL;

    private int totalSecond;

    private int interval;

    protected CustomMessage() {
    }

    public CustomMessage(String requestURL, int totalSecond, int interval) {
        this.requestURL = requestURL;
        this.totalSecond = totalSecond;
        this.interval = interval;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public int getTotalSecond() {
        return totalSecond;
    }

    public int getInterval() {
        return interval;
    }
}
