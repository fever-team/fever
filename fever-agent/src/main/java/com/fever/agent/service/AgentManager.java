package com.fever.agent.service;

import com.fever.agent.BeanUtils;
import com.fever.agent.model.AgentResult;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
public class AgentManager {

    private Integer totalVirtualUser;
    private Integer activeUser;
    private Integer executedTestCount;
    private Integer successCount;
    private Integer errorCount;
    private Boolean isRun;
    private List<Integer> tpsList;

    public AgentManager() {
        this.totalVirtualUser = 0;
        this.activeUser = 0;
        this.executedTestCount = 0;
        this.successCount = 0;
        this.errorCount = 0;
        this.isRun = true;
        tpsList = new ArrayList<>();
    }

    public void start() {
        RabbitTemplate rabbitTemplate = (RabbitTemplate)BeanUtils.getBean("rabbitTemplate");

        new Thread(()->{

            while (isRun) {
                this.activeUser = 0;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!isRun) {
                    break;
                }
                tpsList.add(activeUser);
                rabbitTemplate.convertAndSend("fever-agent-tps-queue", activeUser);
            }

            Double average = tpsList.stream().mapToInt(val -> val).average().orElse(0.0);

            AgentResult agentResult = new AgentResult();
            agentResult.setAvgTPS(average);
            agentResult.setPeekTPS(Collections.max(tpsList));
            agentResult.setExecuteTestCount(this.executedTestCount);
            agentResult.setSuccessTestCount(this.successCount);
            agentResult.setErrorTestCount(this.errorCount);
            agentResult.setTotalVirtualUser(this.totalVirtualUser);
            rabbitTemplate.convertAndSend("fever-agent-result-queue", agentResult);
        }).start();
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
