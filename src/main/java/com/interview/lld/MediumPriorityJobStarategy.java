package com.interview.lld;

import com.interview.lld.dto.Job;
import com.interview.lld.scheduler.JobProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.ZoneId;



@Component
@RequiredArgsConstructor
public class MediumPriorityJobStarategy implements  IJobStrategies {

    private final TaskScheduler taskScheduler;
    private final JobProcessor jobProcessor;

    @Override
    public void submit(Job job) {
        taskScheduler.schedule(()->{
            job.setJobStatus(JobStatus.RUNNING);
            try {
                jobProcessor.execute(job);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },job.getJobScheduledTime().atZone(ZoneId.systemDefault()).toInstant());
    }
}
