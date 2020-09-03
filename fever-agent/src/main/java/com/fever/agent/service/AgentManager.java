package com.fever.agent.service;

import com.fever.agent.model.AgentResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Component
@Scope("prototype")
public class AgentManager {

    private Integer totalVirtualUser;
    private Integer activeUser;
    private Integer executedTestCount;
    private Integer successCount;
    private Integer errorCount;
    private Boolean isRun;
    private List<Integer> tpsList = new ArrayList<>();

    @Async
    public void start() throws InterruptedException {
        this.activeUser = 0;
        this.executedTestCount = 0;
        this.successCount = 0;
        this.errorCount = 0;
        this.isRun = true;

        while (isRun) {
            this.activeUser = 0;
            Thread.sleep(1000);
            if (!isRun) {
                break;
            }
            tpsList.add(activeUser);
            System.out.println("Active User : " + activeUser);
            System.out.println("Execute Test Count : " + this.executedTestCount);
        }

        Double average = tpsList.stream().mapToInt(val -> val).average().orElse(0.0);

        AgentResult agentResult = new AgentResult();
        agentResult.setAvgTPS(average);
        agentResult.setPeekTPS(Collections.max(tpsList));
        agentResult.setExecuteTestCount(this.executedTestCount);
        agentResult.setSuccessTestCount(this.successCount);
        agentResult.setErrorTestCount(this.errorCount);
        agentResult.setTotalVirtualUser(this.totalVirtualUser);
        System.out.println(agentResult.toString());
    }

    public void increaseUserCount() {
        this.activeUser++;
    }

    public void increaseSuccessCount() {
        this.successCount++;
    }

    public void increaseErrorCount() {
        this.errorCount++;
    }

    public void increaseTestCount() {
        this.executedTestCount++;
    }
}
