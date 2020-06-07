package com.fever.agent;

import java.net.Socket;

public class AgentController {

    private Thread thread;
    private String host = "http://localhost";
    private int port = 9000;

    public void run() {
        while(true) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try(Socket socket = new Socket(host, port)) {
                        // TODO : 구현
                    }catch (Exception e) {
                        // TODO : 구현
                    }
                }
            });
            thread.start();
        }
    }
}
