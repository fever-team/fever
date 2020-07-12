package com.fever.agent;

public class Agent {
    AgentState state;
    private Agent() {
        state = AgentState.Init;
    }

    private static class LazyHolder {
        public static final Agent INSTANCE = new Agent();
    }

    public static Agent getInstance() {
        return LazyHolder.INSTANCE;
    }

}
