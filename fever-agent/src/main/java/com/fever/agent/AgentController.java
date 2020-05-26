package com.fever.agent;

public class AgentController {

    private Thread thread;

    public void run() {
        while(true) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread Start ! ");
                }
            });
            thread.start();
        }
    }
}
