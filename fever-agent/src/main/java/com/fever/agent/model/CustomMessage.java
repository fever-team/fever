package com.fever.agent.model;

public class CustomMessage {

    private String requestURL;

    private int totalSecond;

    private int interval;

    protected CustomMessage(){
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

    @Override
    public String toString() {
        return "CustomMessage{" +
                "requestURL='" + requestURL + '\'' +
                ", totalSecond=" + totalSecond +
                ", interval=" + interval +
                '}';
    }
}
