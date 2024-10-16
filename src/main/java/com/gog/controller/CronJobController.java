package com.gog.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJobController {

//    @Scheduled(cron = "0 0/1 * * * ?") // Every minute
//    @Scheduled(cron = "* * * ? * *") // Every second
    @Scheduled(cron = "${spring.task.scheduling.cron}") // Every minute
    public void runJob() {
        try {
            System.out.println("I will execute evevery minute");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
