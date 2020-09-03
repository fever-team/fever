package com.fever.agent.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class AgentManager {

    private Integer activeUser = 0;
    private Integer executedTestCount = 0;
    private Boolean isRun = true;

    @Async
    public void start() throws InterruptedException {
        while (isRun) {
            Thread.sleep(1000);
            System.out.println("Active User : " + activeUser);
            System.out.println("Execute Test Count : " + this.executedTestCount);
            this.activeUser = 0;

        }
    }

    public void increaseUserCount() {
        this.activeUser++;
    }

    public void increaseTestCount() {
        this.executedTestCount++;
    }
}
