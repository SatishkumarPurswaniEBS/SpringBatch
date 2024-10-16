package com.gog.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("firstBatchJob")
    private Job job;

    @Autowired
    @Qualifier("secondBatchJob")
    private Job secondBatchJob;

    @SneakyThrows
    @GetMapping("chinTapakDumDum")
    public String startBatch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> parameters = new HashMap<>();
        parameters.put("time", new JobParameter(System.currentTimeMillis(), Long.class));
        JobParameters jobParameters = new JobParameters(parameters);
        JobExecution je = jobLauncher.run(job, jobParameters);
        System.out.print("Batch Started..");
        while(je.isRunning()){
            System.out.print("...");
        }
        System.out.println("Batch Completed");
        return "Batch Completed";
    }


    @GetMapping("AbraKaDabra")
    public String abraKaDabra() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> parameters = new HashMap<>();
        parameters.put("time", new JobParameter(System.currentTimeMillis(), Long.class));
        JobParameters jobParameters = new JobParameters(parameters);
        JobExecution je = jobLauncher.run(secondBatchJob, jobParameters);
        System.out.print("secondBatchJob Batch Started..");
        while(je.isRunning()){
            System.out.print("...");
        }
        System.out.println("secondBatchJob Batch Completed");
        return "secondBatchJob Batch Completed";
    }
}
