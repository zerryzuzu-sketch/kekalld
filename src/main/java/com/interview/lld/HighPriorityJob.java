package com.interview.lld;

import com.interview.lld.dto.Job;
import com.interview.lld.scheduler.JobProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class HighPriorityJob implements IJobStrategies {
    private final JobProcessor jobProcessor;

    @Override
    public void submit(Job job) {
        CompletableFuture.runAsync(() ->{
            job.setJobStatus(JobStatus.RUNNING);
            try {
                jobProcessor.execute(job);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
