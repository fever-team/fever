package com.fever.agent;

public class AgentController {

    public void run() {
        Agent agent = new Agent();
        while(true) {
            if (agent.state == AgentState.Init) {

            } else if (agent.state == AgentState.Idle) {

            } else if (agent.state == AgentState.Start) {

            } else if (agent.state == AgentState.Stop) {

            } else if (agent.state == AgentState.Progress) {

            }
        }
    }
}
