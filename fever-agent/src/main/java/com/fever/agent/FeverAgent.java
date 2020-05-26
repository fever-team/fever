package com.fever.agent;

import net.grinder.communication.Connector;

import java.net.Socket;

public class FeverAgent {

    public static void main(String[] args) {
        FeverAgent agent = new FeverAgent();
        agent.startAgent();
    }

    public void startAgent() {
        AgentController agentController = new AgentController();
        agentController.run();
    }
}
